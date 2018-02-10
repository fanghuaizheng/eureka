package cn.com.fhz;

import cn.com.fhz.elasticsearch.ElasticsearchConnentFactroy;
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
	ElasticsearchConnentFactroy connentFactroy;


	@Test
	public void contextLoads() {
	}

	@Test
	public void testElasticsearchPool(){

		ThreadA threadA = new ThreadA();

		threadA.setConnentFactroy(connentFactroy);

		ThreadA threadA1 = new ThreadA();

		threadA1.setConnentFactroy(connentFactroy);

		Thread a = new Thread(threadA);

		Thread b = new Thread(threadA1);

		a.start();

		b.start();




	}

}

class ThreadA implements Runnable{

	private ElasticsearchConnentFactroy connentFactroy = null;

	public ElasticsearchConnentFactroy getConnentFactroy() {
		return connentFactroy;
	}

	public void setConnentFactroy(ElasticsearchConnentFactroy connentFactroy) {
		this.connentFactroy = connentFactroy;
	}

	@Override
	public void run() {

		RestHighLevelClient client = connentFactroy.getClient();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		System.out.println(client);

	}
}