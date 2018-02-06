package com.ps.apns.dao.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.ps.apns.dao.model.Token;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class})
@DbUnitConfiguration(databaseConnection = "primaryDatasource")
public class TokenServiceTest {
	@Autowired
	private TokenService tokenService;
	
	@Test
	@DatabaseSetup("classpath:dbunit/token.xml")
	public void testInsert() {
		this.tokenService.addToken("b877f978e26d114a444d9cb9b647578bb9e4b087fcfc130bcc0a45df0caaea70");
		Assert.assertTrue(true);
	}
	@Test
	@DatabaseSetup("classpath:dbunit/token.xml")
	public void testGetToken() {
		Token tokenPojo = this.tokenService.getToken(1L);
		Assert.assertNotNull(tokenPojo);
		String token = tokenPojo.getToken();
		Assert.assertNotNull(token);
	}
}
