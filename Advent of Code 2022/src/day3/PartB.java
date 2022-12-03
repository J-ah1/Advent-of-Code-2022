package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PartB {
	
	public static void main(String[] args) {
    	try(BufferedReader br = new BufferedReader(new FileReader("Inputs/day3_input.txt"))) {
    	    String line = br.readLine();
    	    Set<Character> foundChars = new HashSet<Character>();
    	    Set<Character> foundChars2 = new HashSet<Character>();
    	    int total = 0;
    	    while (line != null) {
    	    	// Create set of chars in first elf
    	    	for(int i = 0; i < line.length(); i++) {
    	    		foundChars.add(line.charAt(i));
    	    	}
    	    	
    	    	// Create set of chars in second elf AND in first elf
    	    	line = br.readLine();
    	    	if(line == null) break;
    	    	for(int i = 0; i < line.length(); i++) {
    	    		if(foundChars.contains(line.charAt(i))) {
    	    			foundChars2.add(line.charAt(i));
    	    		}
    	    	}
    	    	
    	    	// Create set of chars in third elf AND in second elf
    	    	line = br.readLine();
    	    	if(line == null) break;
    	    	for(int i = 0; i < line.length(); i++) {
    	    		if(foundChars2.contains(line.charAt(i))) {
    	    			total += getScore(line.charAt(i));
    	    			break;
    	    		}
    	    	}
    	    	
    	    	// Clearing sets for the next group of elves
    	    	foundChars.clear();
    	    	foundChars2.clear();
    	        line = br.readLine();
    	    }
    	    System.out.println(total);
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private static int getScore(Character c) {
    	if(Character.isUpperCase(c)) {
    		return c - 'A' + 27;
    	}
    	return c - 'a' + 1;
    }
}
