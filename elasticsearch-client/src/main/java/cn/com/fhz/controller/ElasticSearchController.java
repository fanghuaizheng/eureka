package cn.com.fhz.controller;

import cn.com.fhz.component.ElasticsearchComponent;
import cn.com.fhz.config.Config;
import cn.com.fhz.constant.Constant;
import cn.com.fhz.entity.SearchAdEntity;
import cn.com.fhz.searchEntity.MySearchResult;
import cn.com.fhz.searchEntity.SearchResponseVO;
import cn.com.fhz.searchEntity.SearchResquestVO;
import cn.com.fhz.utils.CommonUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 客户端 elasticsearch 的控制器
 * Created by hzfang on 2018/1/26.
 */
@RestController
@RequestMapping("elasticsearch")
public class ElasticSearchController {

    private static Logger logger = LoggerFactory.getLogger(ElasticSearchController.class);


    @Autowired
    Config config;

    @Autowired
    CommonUtils commonUtils;

    @Autowired
    ElasticsearchComponent elasticsearchComponent;

    /**
     * 单个索引增加
     *
     * @param id    操作的索引id
     * @param data  操作的索引数据，以json格式传
     * @param clazz 操作的索引类型
     * @return
     */
    @RequestMapping("addIndex")
    public Object addIndex(@RequestParam("id") String id, @RequestParam("data") String data,
                           @RequestParam("clazz") Class clazz) {

        //存储操作的结果
        JSONObject result = new JSONObject();
        RestHighLevelClient client = null;
        try {
            //转化后的表
            String type = clazz.getSimpleName();


            client = new RestHighLevelClient(RestClient.builder(
                    new HttpHost(config.url, config.port, config.schme)));


            IndexRequest indexRequest = null;
            if (StringUtils.isNotBlank(id)) {
                indexRequest = new IndexRequest(config.index, type, id);
            } else {
                indexRequest = new IndexRequest(config.index, type);

            }

            indexRequest.source(data, XContentType.JSON);

            IndexResponse indexResponse = client.index(indexRequest);
            if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {

                logger.info("创建成功");

                commonUtils.putValue2Result(result, Constant.CREATE);


            } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
                logger.info("更新成功");

                commonUtils.putValue2Result(result, Constant.UPDATE);

            }

        } catch (Exception e) {
            e.printStackTrace();
            commonUtils.putValue2Result(result, Constant.ERROR);

        } finally {

            ElasticsearchComponent.closeResurse(client);

            return result;

        }
    }

    @RequestMapping("addIndexs")
    public Object addIndex(@RequestParam("dataList") String dataparam,
                           @RequestParam("clazz") Class clazz) {

        JSONObject result = new JSONObject();
        RestHighLevelClient client = null;

        try {

            client = elasticsearchComponent.getClient();

            String type = clazz.getSimpleName();

            BulkRequest bulkRequest = new BulkRequest();
            List<String> dataList = JSONObject.parseArray(dataparam, String.class);

            for (String data : dataList
                    ) {
                bulkRequest.add(new IndexRequest(config.index, type).source(data,
                        XContentType.JSON));
            }
            BulkResponse bulkResponse = client.bulk(bulkRequest);

            int create = 0;
            int update = 0;

            for (BulkItemResponse bulkItemResponse : bulkResponse
                    ) {
                if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.INDEX ||
                        bulkItemResponse.getOpType() == DocWriteRequest.OpType.CREATE) {
                    logger.info("创建成功");
                    create++;
                } else if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.UPDATE) {
                    logger.info("更新成功");
                    update++;
                }
            }
            commonUtils.putValue2Result(result, Constant.BATCH_OP);
            result.put("create_num", create);
            result.put("update_num", update);
        } catch (Exception e) {
            e.printStackTrace();
            commonUtils.putValue2Result(result, Constant.ERROR);
        } finally {
            ElasticsearchComponent.closeResurse(client);
            return result;
        }


    }


    /**
     * 搜索的总入口
     * @return
     */
    @RequestMapping("search")
    public Object search(@RequestParam("searchRequestVO") String vo) {


        SearchResponseVO searchResponseVO = new SearchResponseVO();
        SearchResquestVO searchResquestVO = JSONObject.parseObject(vo, SearchResquestVO.class);

        ArrayList<Object> data = new ArrayList<>();
        try {

            RestHighLevelClient client = elasticsearchComponent.getClient();

            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

            MatchQueryBuilder matchQuery = QueryBuilders.
                    matchQuery(searchResquestVO.getField(), searchResquestVO.getValue());
            matchQuery.fuzziness(Fuzziness.AUTO);
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
            boolQuery.must(matchQuery);

            //设置高亮
            HighlightBuilder highlightBuilder = new HighlightBuilder();

            HighlightBuilder.Field highField = new HighlightBuilder.Field(searchResquestVO.getField());
            highlightBuilder.field(highField);

            sourceBuilder.query(boolQuery);

            sourceBuilder.highlighter(highlightBuilder);

            //设置分页
            if (searchResquestVO.getFrom() != null && searchResquestVO.getFrom().intValue() >= 0) {
                sourceBuilder.from(searchResquestVO.getFrom());
            }
            if (searchResquestVO.getSize() != null && searchResquestVO.getSize().intValue() > 0) {
                sourceBuilder.size(searchResquestVO.getSize());
            }

            //设置排序
            if (searchResquestVO.getSortField() != null) {
                if (searchResquestVO.getSort() != null && searchResquestVO.getSort()) {//按照升序排序
                    sourceBuilder.sort(new FieldSortBuilder(searchResquestVO.getSortField()).order(SortOrder.ASC));

                } else {//默认排序字段降序
                    sourceBuilder.sort(new FieldSortBuilder(searchResquestVO.getSortField()).order(SortOrder.DESC));
                }
            }


            SearchRequest searchRequest = new SearchRequest(config.index);
            searchRequest.types(searchResquestVO.getType());


            searchRequest.source(sourceBuilder);

            SearchResponse searchResponse = client.search(searchRequest);

            SearchHits searchHits = searchResponse.getHits();


            MySearchResult success = MySearchResult.SUCCESS;

            searchResponseVO.setCode(success.getCode());
            searchResponseVO.setMsg(success.getMsg());
            for (SearchHit hit : searchHits
                    ) {
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                Class responseClazz = searchResquestVO.getResponseClazz();
                Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                HighlightField highlightField = highlightFields.get(searchResquestVO.getField());
                Text[] texts = highlightField.fragments();
                String string = texts[0].string();
                String[] highLight = new String[]{string};

                sourceAsMap.put("highLightValue", highLight);

                String jsonString = JSONObject.toJSONString(sourceAsMap);

                Object object = JSONObject.parseObject(jsonString, responseClazz);

                data.add(object);
            }
            searchResponseVO.setData(data);

        } catch (Exception e) {

            e.printStackTrace();

            MySearchResult error = MySearchResult.ERROR;

            searchResponseVO.setCode(error.getCode());
            searchResponseVO.setMsg(error.getMsg());
            searchResponseVO.setData(data);

        }finally {
            return searchResponseVO;
        }


    }

}
