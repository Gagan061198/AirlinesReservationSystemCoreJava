package com.cg.ars.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.cg.ars.dto.Airport;
import com.cg.ars.exception.AirportException;

public class AirportServiceTest
{
	private AirportService aser;
	
	public AirportServiceTest() {
		aser = new AirportServiceImpl();
	}
	
	@Test
	public void addAirportTest1() throws AirportException
	{
		Airport airport = new Airport();
		
		airport.setAbbreviation("ZZZ");
		airport.setAirportName("Zinda Hai Toh");
		airport.setLocation("Duniya");
		
		aser.addAirport(airport);
		
		aser.deleteAirport("ZZZ");
	}
	
	@Test(expected=AirportException.class)
	public void addAirportTest2() throws AirportException
	{
		Airport airport = new Airport();
		
		airport.setAbbreviation("zZZ");
		airport.setAirportName("Zinda Hai Toh");
		airport.setLocation("Duniya");
		
		aser.addAirport(airport);
		
		aser.deleteAirport("zZZ");
	}
	
	@Test(expected=AirportException.class)
	public void addAirportTest3() throws AirportException
	{
		Airport airport = new Airport();
		
		airport.setAbbreviation("ZZZ");
		airport.setAirportName("Zinda hai Toh");
		airport.setLocation("Duniya");
		
		aser.addAirport(airport);
		
		aser.deleteAirport("ZZZ");
	}
	
	@Test(expected=AirportException.class)
	public void addAirportTest4() throws AirportException
	{
		Airport airport = new Airport();
		
		airport.setAbbreviation("ZZZ");
		airport.setAirportName("Zinda Hai Toh");
		airport.setLocation("duniya");
		
		aser.addAirport(airport);
		
		aser.deleteAirport("ZZZ");
	}
	
	@Test
	public void getAllAirportsTest()
	{
		Object obj = aser.getAllAirports();
		
		if (obj == null) {
			Assert.assertEquals(null, obj);
		}
		else {
			Assert.assertTrue(obj instanceof List);
		}
	}
	
	@Test
	public void validateNameTest1() throws AirportException
	{
		Assert.assertEquals(true, aser.validateName("Indira Gandhi International Airport"));
	}
	
	@Test(expected=AirportException.class)
	public void validateNameTest2() throws AirportException
	{
		Assert.assertEquals(false, aser.validateName("pune"));
	}
	
	@Test
	public void validateAbbreviationTest1() throws AirportException 
	{
		Assert.assertEquals(true, aser.validateAbbreviation("KOL"));
	}
	
	@Test(expected=AirportException.class)
	public void validateAbbreviationTest2() throws AirportException 
	{
		Assert.assertEquals(false, aser.validateAbbreviation("kolkata"));
	}
	
	@Test
	public void validateLocationTest1() throws AirportException
	{
		Assert.assertEquals(true, aser.validateLocation("Pune"));
	}
	
	@Test(expected=AirportException.class)
	public void validateLocationTest2() throws AirportException
	{
		Assert.assertEquals(false, aser.validateLocation("pune"));
	}
}
