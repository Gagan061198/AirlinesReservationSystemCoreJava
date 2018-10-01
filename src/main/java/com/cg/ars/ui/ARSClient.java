package com.cg.ars.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.metamodel.source.binder.SimpleIdentifierSource;

import com.cg.ars.dto.Booking;
import com.cg.ars.dto.Flight;
import com.cg.ars.dto.User;
import com.cg.ars.exception.BookingException;
import com.cg.ars.exception.UserException;
import com.cg.ars.service.AirportService;
import com.cg.ars.service.AirportServiceImpl;
import com.cg.ars.service.BookingService;
import com.cg.ars.service.BookingServiceImpl;
import com.cg.ars.service.FlightService;
import com.cg.ars.service.FlightServiceImpl;
import com.cg.ars.service.UserService;
import com.cg.ars.service.UserServiceImpl;

public class ARSClient
{
	static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	
	public static final UserService U_SER = new UserServiceImpl();
	
	public static final FlightService F_SER = new FlightServiceImpl();
	
	public static final BookingService B_SER = new BookingServiceImpl();
	
	public static final AirportService A_SER = new AirportServiceImpl();
	
	public static void main(String[] args)
	{
		FlightAnimation fa = new FlightAnimation();
		fa.startAnimation();
		
		LogoAnimation la = new LogoAnimation();
		la.startAnimation();
		
		try 
		{
			System.out.println("========================Welcome to Airline Reservation System=========================");
			System.out.println("========================Login User=====================");
			System.out.println();
			System.out.println("Username:");
			String username=BR.readLine();
			System.out.println("Password:");
			String password=BR.readLine();
			boolean flag = U_SER.verifyUser(username, password);
			if(flag==true) 
			{
				User user = U_SER.getUser(username);
				String role=user.getRole();
				if(role.equals("Admin")) 
				{
					System.out.println("1.Add Flight\n2.Modify Flight\n3.Delete Flight\n4.View Flights\n5.View Flight by Date\n6.Add Airport\n7.View Airports\n8.View Booking Details");
					int ch=BR.read();
					switch(ch)
					{
						case 1:	addFlight();
							break;
							
						case 2: modifyFlight();
							break;
						
						case 3: deleteFlight();
							break;
							
						case 4: viewFlight();
							break;
							
						case 5:	viewFlightByDate();
							break;
							
						case 6:	addAirport();
							break;
						
						case 7:	viewAirports();
							break;
							
						case 8: viewBookingDetails();
							break;
							
						default: System.out.println("*****THANK YOU*****");
							return;
						}
				}
				else if(role.equals("Executive"))
				{
					System.out.println("=========================Welcome Executive===========================");
					System.out.println("1. Login");
					System.out.println("2. View Flight Occupancy Details");
					System.out.println("3. Exit");
					System.out.print("Enter Your Choice: ");
					
					int choice=Integer.parseInt(BR.readLine());
					switch(choice)
					{
					case 1:
						loginExecutive();
						break;
						
					case 2:
						viewFlightOccupancyDetails();
						break;
						
					default:
						return;
					}
				}
				else
				{
					System.out.println("*******Welcome User!!Choose your option*******");
					System.out.println("1:Book a Ticket\n2:View Booking Details\n3:Update Booking Details\n4:Cancel Booking");
					System.out.println("Enter option:");
					int choice=Integer.parseInt(BR.readLine());
					switch(choice)
					{
						case 1: bookTicket(); 
								break;
								
						case 2: viewBooking();
								break;
								
						case 3: updateBooking();
								break;
								
						case 4: cancelBooking();
								break;
								
						default: System.out.println("*****THANK YOU*****");
								
						return;
					}
					
				}
			}
		
		}
		
		catch (IOException | UserException e) {
			
			e.printStackTrace();
		}
		
	}

	private static void viewFlight() {
		F_SER.getAllFlights();
		
	}

	private static void addFlight() {
	
	try {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		
		Flight flight = new Flight();
		
		System.out.print("Flight Number: ");
		flight.setFlightNo(BR.readLine());
		
		System.out.print("Airline Name: ");
		flight.setAirline(BR.readLine());
		
		System.out.print("Departure City: ");
		flight.setDepCity(BR.readLine());
		
		System.out.print("Arrival City: ");
		flight.setArrCity(BR.readLine());
		
		System.out.print("Departure Date: ");
		Date dateDep = new Date(format.parse(BR.readLine()).getTime());
		flight.setDepDate(dateDep);
		
		System.out.print("Arrival Date: ");
		Date dateArr = new Date(format.parse(BR.readLine()).getTime());
		flight.setArrDate(dateArr);
		
		System.out.print("First Seats: ");
		flight.setFirstSeats(Integer.parseInt(BR.readLine()));
		
		System.out.print("First Seats Fare: ");
		flight.setFirstSeatsFare(Double.parseDouble(BR.readLine()));
		
		System.out.print("Business Seats: ");
		flight.setBussSeats(Integer.parseInt(BR.readLine()));
		
		System.out.print("Business Seats Fare: ");
		flight.setBussSeatsFare(Double.parseDouble(BR.readLine()));
		
		F_SER.addFlight(flight);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
		
	}

	private static void modifyFlight() {
	try {
		
		System.out.println("Flight ID: ");
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		
		Flight flight = new Flight();
		
		System.out.print("Flight Number: ");
		flight.setFlightNo(BR.readLine());
		
		System.out.print("Airline Name: ");
		flight.setAirline(BR.readLine());
		
		System.out.print("Departure City: ");
		flight.setDepCity(BR.readLine());
		
		System.out.print("Arrival City: ");
		flight.setArrCity(BR.readLine());
		
		System.out.print("Departure Date: ");
		Date dateDep = new Date(format.parse(BR.readLine()).getTime());
		flight.setDepDate(dateDep);
		
		System.out.print("Arrival Date: ");
		Date dateArr = new Date(format.parse(BR.readLine()).getTime());
		flight.setArrDate(dateArr);
		
		System.out.print("First Seats: ");
		flight.setFirstSeats(Integer.parseInt(BR.readLine()));
		
		System.out.print("First Seats Fare: ");
		flight.setFirstSeatsFare(Double.parseDouble(BR.readLine()));
		
		System.out.print("Business Seats: ");
		flight.setBussSeats(Integer.parseInt(BR.readLine()));
		
		System.out.print("Business Seats Fare: ");
		flight.setBussSeatsFare(Double.parseDouble(BR.readLine()));
		
		F_SER.modifyFlight(flight);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
		
	}

	private static void deleteFlight() {
		F_SER.deleteFlight();
		
	}

	private static void viewFlightByDate() {
		
		System.out.println("Date");
		F_SER.getFlights(date, depCity, arrCity);
		
	}

	private static void addAirport() {
		A_SER.addAirport(airport);
		
	}

	private static void viewAirports() {
		A_SER.getAllAirports();
		
	}

	private static void viewBookingDetails() {

		B_SER.viewBookDetails(bookingId);
	}

	private static void bookTicket() 
	{
		try 
		{
			Booking booking=new Booking();
			
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			
			System.out.print("Travel Date (dd-MM-yyyy): ");
			Date date = new Date(format.parse(BR.readLine()).getTime());
			
			System.out.println("City From: ");
			String depCity=BR.readLine();
			System.out.println("City To: ");
			String arrCity=BR.readLine();
					
			List<Flight> flightList=F_SER.getFlights(date, depCity, arrCity);
				for(Flight flight : flightList) 
				{
					System.out.println(flight);
				}
		}
		catch(IOException | ParseException e) 
		{
			e.printStackTrace();
		}
		
	}

	private static void viewBooking() 
	{
		
	}

	private static void updateBooking() 
	{
		
	}

	private static void cancelBooking() 
	{
		System.out.println("Booking Id:");
		try
		{
			String bookingId = BR.readLine();
			if(B_SER.validateBookingId(bookingId) == true) 
			{
				B_SER.cancelBooking(bookingId);
				System.out.println("Booking with Id "+bookingId+" cancelled successfully");
			}
		} 
		catch (IOException | BookingException e) 
		{
			e.printStackTrace();
		}
		
	}

	private static void loginExecutive() 
	{
		
		
	}

	private static void viewFlightOccupancyDetails() 
	{
		
		
	}
}
