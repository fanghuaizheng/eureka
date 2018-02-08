package cn.com.fhz;

import cn.com.fhz.elasticsearch.ElasticsearchPoolFactrory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchClientApplicationTests {

	@Autowired
	ElasticsearchPoolFactrory elasticsearchPoolFactrory;


	@Test
	public void contextLoads() {
	}

	@Test
	public void testElasticsearchPool(){
		ElasticsearchPoolFactrory poolFactrory = new ElasticsearchPoolFactrory();

		GenericObjectPool<RestHighLevelClient> pool = new GenericObjectPool<>(poolFactrory);

		RestHighLevelClient client = null;

//        new StackObjectPool(poolFactrory);

		try {
			for(int i = 0; i < 5; i++) {
				System.out.println("\n==========="+i+"===========");
				System.out.println("池中处于闲置状态的实例pool.getNumIdle()："+pool.getNumIdle());
				//从池里取一个对象，新创建makeObject或将以前闲置的对象取出来
				client = (RestHighLevelClient)pool.borrowObject();
				System.out.println("bo:"+client);
				System.out.println("池中所有在用实例数量pool.getNumActive()："+pool.getNumActive());
				if((i%2) == 0) {
					//用完之后归还对象
					pool.returnObject(client);
					System.out.println("归还对象！！！！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(client != null) {
					pool.returnObject(client);
				}
				//关闭池
				pool.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
