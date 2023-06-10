package com.shayartzi.jdeserialize2.legacy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shayartzi.jdeserialize2.legacy.GetOpt.OptionParseException;

class GetOptTests {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void parse_givenMultipleOptions_whenGivenArgs_parseCorrectly() throws OptionParseException {
		
		// Prepare GetOpt
		GetOpt go = new GetOpt();
        go.addOption("-optzero", 0, "zero-arg constructor");
        go.addOption("-optone", 1, "one-arg constructor");
        go.addOption("-opttwo", 2, "two-arg constructor");
        
        // Call with args
        go.parse(new String[]{"-optzero", "-optone", "0", "-opttwo", "1", "2", "foo"});
        
        // Validate options
        String expectedOptions = 
        		"Options:\r\n" +
        		"    -optone arg1: one-arg constructor\r\n" +
        		"    -opttwo arg1 arg2: two-arg constructor\r\n" +
        		"    -optzero: zero-arg constructor\r\n";
        
        assertEquals(expectedOptions, go.getDescriptionString());
        
        // Validate values
        Map<String, List<String>> expectedOptionValues = new HashMap<>();
        expectedOptionValues.put("-optzero", List.of());
        expectedOptionValues.put("-optone", List.of("0"));
        expectedOptionValues.put("-opttwo", List.of("1", "2"));
        
        assertEquals(expectedOptionValues, go.getOptionValues());

        // Validate other args (last cmd line args)
        assertEquals(List.of("foo"), go.getOtherArguments()); 
        
	}

}
