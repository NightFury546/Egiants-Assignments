package com.manoj.practice;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Java8Example {

	public static void main(String[] args) {

		        Stream<String> language = Stream.of("java", "python", "node", null, "ruby", null, "php");
		        
		        
		        // it will print the null values 
		        //List<String> result = language.collect(Collectors.toList());
		        
		        // check for the null values 
		        
		        List<String> result = language.filter(x -> x != null).collect(Collectors.toList());

		        result.forEach(System.out::println);
	}

}
