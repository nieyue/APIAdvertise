package com.nieyue.service.impl.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.nieyue.bean.Advertise;
import com.nieyue.service.AdvertiseService;
import com.nieyue.service.AdvertiseService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/spring-dao.xml","classpath:config/spring-service.xml"})
@TransactionConfiguration(transactionManager="txManager",defaultRollback=false)
public class AdvertiseServiceImplTest {
	@Resource
	 AdvertiseService  advertiseService;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddAdvertise() {
	}
	

	@Test
	public void testDelAdvertise() {
		advertiseService.delAdvertise(1000);
	}

	@Test
	public void testUpdateAdvertise() {
		Advertise p = advertiseService.loadAdvertise(1000);
		advertiseService.updateAdvertise(p);
	}

	@Test
	public void testLoadAdvertise() {
		Advertise n = advertiseService.loadAdvertise(1003);
		for (int i = 0; i <20; i++) {
			
			advertiseService.addAdvertise(n);
		}
		System.out.println(n);
	}

	@Test
	public void testCountAll() {
		int n = advertiseService.countAll();
		System.out.println(n);
	}


	@Test
	public void testBrowsePagingAdvertise() {
		List<Advertise> l = advertiseService.browsePagingAdvertise(1, 10, "update_date", "desc");
	for (int i = 0; i < l.size(); i++) {
		System.out.println(l.size());
		System.out.println(l.get(i).getAdName());
	}
	}

}
