package com.cg.ars.service;

import org.junit.Assert;
import org.junit.Test;

import com.cg.ars.exception.UserException;

public class UserServiceTest
{
	private UserService user;
	
	public UserServiceTest()
	{
		user = new UserServiceImpl();
	}
	
	@Test
	public void testValidateMobileNo1() throws UserException
	{
		Assert.assertEquals(true, user.validateMobileNo("8582964407"));
	}
	
	@Test
	public void testValidateMobileNo2() throws UserException
	{
		Assert.assertEquals(true, user.validateMobileNo("85829 64407"));
	}
	
	@Test
	public void testValidateMobileNo3() throws UserException
	{
		Assert.assertEquals(true, user.validateMobileNo("858 296 4407"));
	}
	
	@Test
	public void testValidateMobileNo4() throws UserException
	{
		Assert.assertEquals(true, user.validateMobileNo("+91 8582964407"));
	}
	
	@Test
	public void testValidateMobileNo5() throws UserException
	{
		Assert.assertEquals(true, user.validateMobileNo("+91-521-8582964407"));
	}
	
	@Test
	public void testValidateMobileNo6() throws UserException
	{
		Assert.assertEquals(true, user.validateMobileNo("+91 254 85829 64407"));
	}
	
	@Test
	public void testValidateMobileNo7() throws UserException
	{
		Assert.assertEquals(true, user.validateMobileNo("+91 254 858 296 4407"));
	}
	
	@Test(expected=UserException.class)
	public void testValidateMobileNo8() throws UserException
	{
		user.validateMobileNo("+91-254-85-296-4407");
	}
	
	@Test(expected=UserException.class)
	public void testValidateMobileNo9() throws UserException
	{
		user.validateMobileNo("+91-254-296-4407 522");
	}
	
	@Test(expected=UserException.class)
	public void testValidateMobileNo10() throws UserException
	{
		user.validateMobileNo("");
	}
}
