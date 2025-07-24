package com.learning.deisgnpatterns.behavioral;

/**
 * TEMPLATE METHOD DESIGN PATTERN - JAVA IMPLEMENTATION
 *
 * Intent:
 * Define the skeleton of an algorithm in a base class method (template method),
 * allowing subclasses to redefine specific steps of the algorithm without changing its structure.
 *
 * Real-World Analogy:
 * Consider a data parser that always follows the same high-level steps:
 * read data → parse → validate → save. The format-specific steps (parse, validate)
 * vary between CSV and JSON but the overall process remains consistent.
 */

//Abstract class defining the template for parsing and saving data.
//Implements the Template Method pattern where the structure of the algorithm is fixed,
//but some steps are delegated to subclasses.
abstract class DataParser {
	
	// Template method: defines the fixed sequence of steps for parsing and saving data.
	public final void parseAndSaveData() {
		readData();       // step 1: read the data (common)
		parseData();      // step 2: parse the data (customizable)
		validateData();   // step 3: validate the parsed data (customizable)
		saveData();       // step 4: save the data (common)
	}
	
	// Step to be implemented by subclass: how to parse the data
	protected abstract void parseData();
	
	// Step to be implemented by subclass: how to validate the data
	protected abstract void validateData();
	
	// Common step: reading data
	protected void readData() {
		System.out.println("reading data");
	}
	
	// Common step: saving data into the database
	protected void saveData() {
		System.out.println("saving the data into database");
	}
}

//CSV parser subclass implementing its version of parse and validate steps
class CsvParser extends DataParser {
	
	public void parseData() {
		System.out.println("CSV parser");
	}
	
	public void validateData() {
		System.out.println("CSV data validator");
	}
}

//JSON parser subclass implementing its version of parse and validate steps
class JsonParser extends DataParser {
	
	public void parseData() {
		System.out.println("JSON parser");
	}
	
	public void validateData() {
		System.out.println("JSON data validator");
	}
}

//Main class to demonstrate the Template Method pattern using CSV and JSON parsers
public class TemplatePattern {
	
	public static void main(String args[]) {
		
		// Creating parser for CSV
		DataParser csvParser = new CsvParser();
		
		// Creating parser for JSON
		DataParser jsonParser = new JsonParser();
		
		// Parsing and saving CSV data
		csvParser.parseAndSaveData();
		
		// Parsing and saving JSON data
		jsonParser.parseAndSaveData();
	}
}
