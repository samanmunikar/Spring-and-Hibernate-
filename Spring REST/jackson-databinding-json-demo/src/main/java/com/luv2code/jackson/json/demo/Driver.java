package com.luv2code.jackson.json.demo;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Driver {

	public static void main(String[] args) {
		try {
            //create object Mapper
            ObjectMapper mapper = new ObjectMapper();

            //read JSON file and map/convert to JAVA POJO:
            //data/sample-lite.json
            Student theStudent =
                    mapper.readValue(new File("data/sample-full.json"), Student.class);

            //print firstName and lastName
            System.out.println("First Name : " + theStudent.getFirstName());
            System.out.println("Last Name: " + theStudent.getLastName());
            
            //print out address: street and city
            Address tempAddress = theStudent.getAddress();
            
            System.out.println("Street = " + tempAddress.getStreet());
            System.out.println("City = " + tempAddress.getCity());
            
            //print out the languages
            for (String tempLanguage : theStudent.getLanguages()) {
            	System.out.println(tempLanguage);
            }
            
            //now write JSON to output File
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File("data/output.json"), theStudent);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
	}

}
