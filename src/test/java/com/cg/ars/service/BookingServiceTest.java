package com.cg.ars.service;

import org.junit.Assert;
import org.junit.Test;

import com.cg.ars.dto.Booking;
import com.cg.ars.exception.BookingException;

public class BookingServiceTest
{
	private BookingService bser;

	public BookingServiceTest() {
		bser = new BookingServiceImpl();
	}

	
	//bookTicketValidate Test Cases
	@Test
	public void bookTicketTest1()
	{
		Booking booking = new Booking("KOL12345","test@capgemini.com",1,"First",5000.00,2,"4565767878980987","Kolkata","Pune");
		bser.bookTicket(booking);
	}
	
	@Test(expected=BookingException.class)
	public void bookTicketTest2()
	{
		bser.bookTicket(null);
	}
	
	//viewBookDetailsValidate Test Cases
	@Test
	public void viewBookDetailsTest1()
	{
		Booking booking = bser.viewBookDetails("KOL12345");
		Assert.assertNotNull(booking);
	}
			
	@Test(expected=BookingException.class)
	public void viewBookDetailsTest2()
	{
		Assert.assertEquals(null,bser.viewBookDetails("fsdgfs"));
	}
	
	//viewBookDetailsValidate Test Cases
	@Test
	public void updateBookingDetailsTest1()
	{
		Booking booking = new Booking("PUNE12345","test@capgemini.com",1,"First",5000.00,2,"4565767878980987","Pune","Mumbai");
		Booking bookingRet = bser.updateBookingDetails(booking);
		
		Assert.assertNotNull(bookingRet);
	}
					
	@Test(expected=BookingException.class)
	public void updateBookingDetailsTest2()
	{
		Booking booking = new Booking(null,"test@capgemini.com",1,"First",5000.00,2,"4565767878980987","Pune","Mumbai");
		bser.updateBookingDetails(booking);
	}

	//EmailValidate Test Cases
	@Test
	public void validateEmailTest1() throws BookingException
	{
		Assert.assertEquals(true, bser.validateEmail("ars@capgemini.com"));
	}
	
	@Test(expected=BookingException.class)
	public void validateEmailTest2() throws BookingException
	{
		Assert.assertEquals(false, bser.validateEmail("ars.@@gmail..com"));
	}
	
	
	//CreditCardInfoValidate Test Cases
	@Test
	public void validateCreditCardInfoTest1() throws BookingException
	{
		Assert.assertEquals(true, bser.validateCreditCardInfo("5678946756372345"));
	}
			
	@Test(expected=BookingException.class)
	public void validateCreditCardInfoTest2() throws BookingException
	{
		Assert.assertEquals(false, bser.validateCreditCardInfo("acgd4563667677"));
	}
	
	//SourceCityValidate Test Cases
	@Test
	public void validateSrcCityTest1() throws BookingException
	{
		Assert.assertEquals(true, bser.validateCity("Pune"));
	}
					
	@Test(expected=BookingException.class)
	public void validateSrcCityTest2() throws BookingException
	{
		Assert.assertEquals(false, bser.validateCity("kolkata"));
	}
	
	//DestinationCityValidate Test Cases
	@Test
	public void validateDestCityTest1() throws BookingException
	{
		Assert.assertEquals(true, bser.validateCity("Pune"));
	}
							
	@Test(expected=BookingException.class)
	public void validateDestCityTest2() throws BookingException
	{
		Assert.assertEquals(false, bser.validateCity("delhi"));
	}
}
