package com.cg.ars.dao;

import javax.persistence.EntityManager;

import com.cg.ars.dto.Booking;
import com.cg.ars.util.JPAUtil;

public class BookingDaoImpl implements BookingDao
{
	private EntityManager entityManager;

	public BookingDaoImpl()
	{
		entityManager = JPAUtil.getEntityManager();
	}
	
	@Override
	public Booking viewBookDetails(String bookingId)
	{
		Booking booking = entityManager.find(Booking.class, bookingId);
		
		return booking;
	}

	@Override
	public Booking updateBookingDetails(Booking booking)
	{
		entityManager.getTransaction().begin();
		
		Booking book = entityManager.merge(booking);
		
		entityManager.getTransaction().commit();
		
		return book;
	}

	@Override
	public void bookTicket(Booking booking)
	{
		entityManager.getTransaction().begin();
		
		entityManager.persist(booking);
		
		entityManager.getTransaction().commit();
	}

	@Override
	public void cancelBooking(Booking booking)
	{
		entityManager.getTransaction().begin();
		
		entityManager.remove(booking);
		
		entityManager.getTransaction().begin();
	}
}
