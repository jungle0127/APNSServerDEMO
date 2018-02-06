package com.ps.apns.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class APNSServiceTest {
	@Autowired
	private APNSService apnsService;
	@Test
	public void testSend() {
		this.apnsService.sendNotification();
		Assert.assertTrue(true);
	}
	
}
