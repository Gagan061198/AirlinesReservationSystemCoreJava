package com.cg.ars.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.metamodel.source.binder.SimpleIdentifierSource;

import com.cg.ars.dto.Airport;
import com.cg.ars.dto.Booking;
import com.cg.ars.dto.Flight;
import com.cg.ars.dto.User;
import com.cg.ars.exception.BookingException;
import com.cg.ars.exception.FlightException;
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
			System.out.println("======================== Welcome to Airline Reservation System =========================");
			System.out.println("======================== Login =====================");
			System.out.println();
			
			System.out.println("Username:");
			String username = BR.readLine();
			
			System.out.println("Password:");
			String password = BR.readLine();
			
			if(U_SER.verifyUser(username, password)) 
			{
				User user = U_SER.getUser(username);
				
				String role = user.getRole();
				
				if(role.equals("Admin")) 
				{
					System.out.println("1.Add Flight\n2.Modify Flight\n3.Delete Flight\n4.View Flights\n5.View Flights by Date\n6.Add Airport\n7.View Airports\n8.View Booking Details");
					int ch=BR.read();
					switch(ch)
					{
						case 1:	addFlight();
							break;
							
						case 2: modifyFlight();
							break;
						
						case 3: deleteFlight();
							break;
							
						case 4: viewFlights();
							break;
							
						case 5:	viewFlightsByDate();
							break;
							
						case 6:	addAirport();
							break;
						
						case 7:	viewAirports();
							break;
							
						case 8: viewBooking();
							break;
							
						default: System.out.println("*****THANK YOU*****");
							return;
						}
				}
				else if(role.equals("Executive"))
				{
					System.out.println("=========================Welcome Executive===========================");
					System.out.println("1. View Flight Details for a particular period");
					System.out.println("2. View Flight Occupancy Details from one region to another region");
					System.out.println("3. Exit");
					System.out.print("Enter Your Choice: ");
					
					int choice=Integer.parseInt(BR.readLine());
					switch(choice)
					{
					case 1:
						viewDetailsBasedOnDate();
						break;
						
					case 2:
						viewDetailsBasedOnRegion();
						break;
						
					default:
						return;
					}
				}
				else {
					System.out.println("*******Welcome User!!Choose your option*******");
					System.out.println("1:Book a Ticket\n2:View Booking Details\n3:Update Booking Details\n4:Cancel Booking");
					System.out.println("Enter option:");
					int choice=Integer.parseInt(BR.readLine());
					
					switch(choice) {
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

	private static void viewFlights()
	{
		List<Flight> flightList = F_SER.getAllFlights();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
		
		for (Flight f : flightList)
		{
			System.out.printf("%s%s%s%s%s%s%s%s%d%lf%d%lf",
					f.getFlightNo(),
					f.getAirline(),
					f.getArrCity(),
					f.getDepCity(),
					timeFormat.format(f.getArrTime()),
					timeFormat.format(f.getDepTime()),
					dateFormat.format(f.getArrDate()),
					dateFormat.format(f.getDepDate()),
					f.getFirstSeats(),
					f.getFirstSeatsFare(),
					f.getBussSeats(),
					f.getBussSeatsFare());
		}
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
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
		
		System.out.println("Flight ID: ");
		Flight f = F_SER.getFlight(BR.readLine());
		Flight flight = new Flight();
		flight=f;
		System.out.printf("%s%s%s%s%s%s%s%s%d%lf%d%lf",
							f.getFlightNo(),
							f.getAirline(),
							f.getArrCity(),
							f.getDepCity(),
							timeFormat.format(f.getArrTime()),
							timeFormat.format(f.getDepTime()),
							dateFormat.format(f.getArrDate()),
							dateFormat.format(f.getDepDate()),
							f.getFirstSeats(),
							f.getFirstSeatsFare(),
							f.getBussSeats(),
							f.getBussSeatsFare());
		
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	
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

	private static void deleteFlight() 
	{
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
			System.out.print("Flight Number: ");
			Flight flight =  F_SER.getFlight(BR.readLine());
			System.out.printf("%s%s%s%s%s%s%s%s%d%lf%d%lf",
					flight.getFlightNo(),
					flight.getAirline(),
					flight.getArrCity(),
					flight.getDepCity(),
					timeFormat.format(flight.getArrTime()),
					timeFormat.format(flight.getDepTime()),
					dateFormat.format(flight.getArrDate()),
					dateFormat.format(flight.getDepDate()),
					flight.getFirstSeats(),
					flight.getFirstSeatsFare(),
					flight.getBussSeats(),
					flight.getBussSeatsFare());
			F_SER.deleteFlight(flight);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private static void viewFlightsByDate() 
	{
		try 
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
			System.out.print("Travel Date (dd-MM-yyyy): ");
			Date date = new Date(dateFormat.parse(BR.readLine()).getTime());
			
			System.out.println("Departure City: ");
			String depCity=BR.readLine();
			
			System.out.println("Arrival City: ");
			String arrCity=BR.readLine();
			
			List<Flight> flights = F_SER.getFlights(date, depCity, arrCity);
			for (Flight f : flights)
			{
				System.out.printf("%s%s%s%s%s%s%s%s%d%lf%d%lf",
						f.getFlightNo(),
						f.getAirline(),
						f.getArrCity(),
						f.getDepCity(),
						timeFormat.format(f.getArrTime()),
						timeFormat.format(f.getDepTime()),
						dateFormat.format(f.getArrDate()),
						dateFormat.format(f.getDepDate()),
						f.getFirstSeats(),
						f.getFirstSeatsFare(),
						f.getBussSeats(),
						f.getBussSeatsFare());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	private static void addAirport() 
	{
		try 
		{
			Airport airPort = new Airport();
			System.out.print("Airport Name: ");
			airPort.setAirportName(BR.readLine());
			System.out.print("Abbreviation: ");
			airPort.setAbbreviation(BR.readLine());
			System.out.print("Location: ");
			airPort.setLocation(BR.readLine());
			A_SER.addAirport(airPort);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private static void viewAirports() 
	{
		List<Airport> Airports =  A_SER.getAllAirports();
		for (Airport a : Airports)
		{
			System.out.printf("%s%s%s%s%s%s%s%s%d%lf%d%lf",
					a.getAirportName(),
					a.getAbbreviation(),
					a.getLocation());
		}
	}
  
	private static void viewDetailsBasedOnDate() 
	{
		List<Flight> flightsByDate;
		String flightNo = null;
		System.out.println("=====================================================================");
		System.out.println("Enter the flight number for which the details have to be entered");
		
		try {
			flightNo = BR.readLine();
		} catch (IOException e)
		{
			System.out.println("Invalid Flight Number");
		}
		System.out.println("=====================================================================");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
		
	    Date startDate = null;
	    Date endDate = null;
	    
		try {
			System.out.println("Enter the start date of the flight");
			startDate = new Date(dateFormat.parse(BR.readLine()).getTime());
			
			System.out.println("Enter the end date of the flight");
			endDate = new Date(timeFormat.parse(BR.readLine()).getTime());
		} 
		catch (ParseException | IOException e) {
			System.out.println("enter the correct date according to the format dd-mm-yyyy");
		}
		
		flightsByDate = F_SER.getOccupancy(flightNo, startDate, endDate);
		
		System.out.println("Flight Details are : ");
		for(Flight f: flightsByDate)
		{
			System.out.printf("%s%s%s%s%s%s%s%s%d%lf%d%lf",
								f.getFlightNo(),
								f.getAirline(),
								f.getArrCity(),
								f.getDepCity(),
								timeFormat.format(f.getArrTime()),
								timeFormat.format(f.getDepTime()),
								dateFormat.format(f.getArrDate()),
								dateFormat.format(f.getDepDate()),
								f.getFirstSeats(),
								f.getFirstSeatsFare(),
								f.getBussSeats(),
								f.getBussSeatsFare());
		}
		
	}

	private static void viewDetailsBasedOnRegion()
	{
		List<Flight> flightsByRegion;

		String depCity = null;
		String arrCity = null;
	      
		try {
			System.out.println("Enter the Departure city");
			depCity = BR.readLine();
			
			System.out.println("Enter the Arrival city");
			arrCity = BR.readLine();
		} 
		catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		flightsByRegion = F_SER.getOccupancy(depCity, arrCity);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
		
		System.out.println("Flight Details are : ");
		for(Flight f: flightsByRegion)
		{
			System.out.printf("%s%s%s%s%s%s%s%s%d%lf%d%lf",
								f.getFlightNo(),
								f.getAirline(),
								f.getArrCity(),
								f.getDepCity(),
								timeFormat.format(f.getArrTime()),
								timeFormat.format(f.getDepTime()),
								dateFormat.format(f.getArrDate()),
								dateFormat.format(f.getDepDate()),
								f.getFirstSeats(),
								f.getFirstSeatsFare(),
								f.getBussSeats(),
								f.getBussSeatsFare());
		}
	}

	private static void bookTicket() 
	{
		String depCity = null;
		String arrCity = null;
		
		try 
		{
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			
			System.out.print("Travel Date (dd-MM-yyyy): ");
			Date date = new Date(format.parse(BR.readLine()).getTime());
        
			if (F_SER.validateDate(date) == true) 
			{
				System.out.println("City From: ");
				depCity = BR.readLine();
        
				System.out.println("City To: ");
				arrCity = BR.readLine();
			}
      
			List<Flight> flightList=F_SER.getFlights(date, depCity, arrCity);
      
			for(Flight flight : flightList) 
			{
				System.out.println(flight);
			}
		}
		catch(IOException | ParseException | FlightException e) 
		{
			e.printStackTrace();
		}
	}

	private static void viewBooking() 
	{
		try {
		System.out.print("Booking ID:");
		Booking booking = B_SER.viewBookDetails(BR.readLine());
		System.out.printf("%s%s%s%s%s%d%d%lf",
				booking.getBookingId(),
				booking.getSrcCity(),
				booking.getDestCity(),
				booking.getClass(),
				booking.getClassType(),
				booking.getNoOfPassengers(),
				booking.getSeatNumber(),
				booking.getTotalFare());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
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
}
