package cn.com.fhz.controller;

import cn.com.fhz.component.ElasticsearchComponent;
import cn.com.fhz.config.Config;
import cn.com.fhz.constant.Constant;
import cn.com.fhz.searchEntity.MySearchResult;
import cn.com.fhz.searchEntity.SearchResponseVO;
import cn.com.fhz.searchEntity.SearchResquestVO;
import cn.com.fhz.utils.CommonUtils;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
@Api(value = "elasticsearch的控制器value",description = "elasticsearch客户端总入口")
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
    @ApiOperation(value = "增加单个索引",notes = "根据传入的参数，生成指定类型的索引")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "索引创建的Id",required = false,dataType = "String"),
            @ApiImplicitParam(name = "data",value = "索引的数据",required = true,dataType = "String"),
            @ApiImplicitParam(name = "clazz",value = "操作的索引类型",required = true,dataType = "Class")
    })
    @PostMapping("addIndex")
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

    @ApiOperation(value = "批量增加索引,",notes = "批量增加索引")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dataList",value = "List集合转的String格式",required = true,dataType = "String"),
            @ApiImplicitParam(name = "clazz",value = "增加索引的类对象",required = true,dataType = "Class")

    })
    @PostMapping("addIndexs")
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
    @ApiOperation(value = "搜索总入口",notes = "指定的接受对象，来搜索结果")
    @ApiImplicitParam(name = "searchRequestVO",value = "搜索的类，转成String传输",required = true,paramType = "query",
            dataType = "String")
    @PostMapping("search")
    public Object search(@RequestParam("searchRequestVO") String vo) {


        SearchResponseVO searchResponseVO = new SearchResponseVO();
        SearchResquestVO searchResquestVO = JSONObject.parseObject(vo, SearchResquestVO.class);



        ArrayList<Object> data = new ArrayList<>();

        //先判断参数是否有为空的
        if (searchResquestVO.getField()==null||searchResquestVO.getValue()==null){

            MySearchResult isNull = MySearchResult.PARAM_IS_NULL;

            commonUtils.putValue2Result(searchResponseVO,isNull,data);

            return searchResponseVO;
        }

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
                //如果没有传入返回的类类型，那么用父类，OBject
                if (responseClazz==null){
                    responseClazz = Object.class;
                }
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
