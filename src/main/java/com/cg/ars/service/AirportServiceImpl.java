package com.cg.ars.service;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

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
	
	/**
	 * Add Airport
	 */
	@Override
	public void addAirport(Airport airport) throws AirportException 
	{
		this.validateAbbreviation(airport.getAbbreviation());
		this.validateName(airport.getAirportName());
		this.validateLocation(airport.getLocation());
		
		logger.info("Airport Information Added [airportId=" + airport.getAbbreviation() + "]");
		adao.addAirport(airport);
	}
	
	/**
	 * Get Airport Details by ID
	 * @return Airport instance
	 */
	@Override
	public Airport getAirport(String airportId) throws AirportException 
	{
		try {
			Airport airport = adao.getAirport(airportId);
			
			if (airport == null) {
				throw new NullPointerException("Airport Record with [airportId=" + airportId + "] not found");
			}
			else {
				logger.info("Airport Information Retrieved [airportId=" + airportId + "]");
				return airport;
			}
		}
		catch (Exception exc) {
			logger.error(exc.getMessage());
			throw new AirportException(exc.getMessage());
		}
	}
	
	/**
	 * Get List of All Airports
	 * @return List of All Airports
	 */
	@Override
	public List<Airport> getAllAirports() 
	{	
		logger.info("Information for all Airports Retrieved.");
		return adao.getAllAirports();
	}
	
	/**
	 * Delete Airport Information by airportId
	 */
	public void deleteAirport(String airportId) throws AirportException 
	{
		try {
			logger.info("Airport Information Deleted [airportId=" + airportId + "]");
			adao.deleteAirport(airportId);
		}
		catch (Exception exc) {
			logger.error(exc.getMessage());
			throw new AirportException(exc.getMessage());
		}
	}

	/**
	 * Validate airport name by pattern each word must start with UPPERCASE followed by lowercase alphabets
	 * @return boolean; true if valid, otherwise false
	 */
	@Override
	public boolean validateName(String name) throws AirportException 
	{
		String pattern = "([A-Z][a-z]+ )*[A-Z][a-z]+";
		
		if (Pattern.matches(pattern, name)) {
			return true;
		}
		else {
			logger.error("Invalid Airport Name [name=" + name + "]");
			throw new AirportException("Invalid Airport Name [name=" + name + "]\nFormat: Each word must start with UPPERCASE followed by lowercase alphabets");
		}
	}

	/**
	 * Validate abbreviation by pattern 3 to 4 UPPERCASE alphabets
	 * @return boolean; true if valid, otherwise false
	 */
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

	/**
	 * Validate airport location by pattern each word must start with UPPERCASE followed by lowercase alphabets
	 * @return boolean; true if valid, otherwise false
	 */
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
