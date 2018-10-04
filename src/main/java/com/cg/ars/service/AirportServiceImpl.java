package com.cg.ars.service;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.ars.dao.AirportDao;
import com.cg.ars.dao.AirportDaoImpl;
import com.cg.ars.dto.Airport;
import com.cg.ars.exception.AirportException;

public class AirportServiceImpl implements AirportService 
{
	private AirportDao adao;
	
	private Logger logger;
	
	public AirportServiceImpl() 
	{
		adao = new AirportDaoImpl();
		
		logger = Logger.getLogger(this.getClass());
	}

	@Override
	public void addAirport(Airport airport) throws AirportException 
	{
		this.validateAbbreviation(airport.getAbbreviation());
		this.validateName(airport.getAirportName());
		this.validateLocation(airport.getLocation());
		logger.info("Airport Information Added");
		adao.addAirport(airport);
	}
	
	@Override
	public Airport getAirport(String airportId) throws AirportException 
	{
		try {
			Airport airport = adao.getAirport(airportId);
			
			if (airport == null) {
				throw new NullPointerException("Airport Record with Airport ID=" + airportId + " not found");
			}
			else {
				logger.info("Airport Information Retrieved");
				return airport;
			}
		}
		catch (Exception exc) {
			logger.error(exc.getMessage());
			throw new AirportException(exc.getMessage());
		}
	}

	@Override
	public List<Airport> getAllAirports() 
	{	
		logger.info("Information for all Airports Retrieved.");
		return adao.getAllAirports();
	}
	
	public void deleteAirport(String airportId) throws AirportException 
	{
		try {
			logger.info("Airport Information Deleted");
			adao.deleteAirport(airportId);
		}
		catch (Exception exc) {
			logger.error(exc.getMessage());
			throw new AirportException(exc.getMessage());
		}
	}

	@Override
	public boolean validateName(String name) throws AirportException 
	{
		String pattern = "([A-Z][a-z]+ )*[A-Z][a-z]+";
		
		if (Pattern.matches(pattern, name)) {
			return true;
		}
		else {
			logger.error("Invalid Airport Name");
			throw new AirportException("Invalid Airport Name");
		}
	}

	@Override
	public boolean validateAbbreviation(String abbreviation) throws AirportException 
	{
		String pattern = "[A-Z]{3,4}";
		
		if (Pattern.matches(pattern, abbreviation)) {
			return true;
		}
		else {
			logger.error("Invalid Airport Abbreviation");
			throw new AirportException("Invalid Airport Abbreviation");
		}
	}

	@Override
	public boolean validateLocation(String location) throws AirportException 
	{
		String pattern = "([A-Z][a-z]+ )*[A-Z][a-z]+";
		
		if (Pattern.matches(pattern, location)) {
			return true;
		}
		else {
			logger.error("Invalid Airport Location");
			throw new AirportException("Invalid Airport Location");
		}
	}
}
