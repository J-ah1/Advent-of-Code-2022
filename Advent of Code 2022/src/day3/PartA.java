package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PartA {
	
	public static void main(String[] args) {
    	try(BufferedReader br = new BufferedReader(new FileReader("Inputs/day3_input.txt"))) {
    	    String line = br.readLine();
    	    Set<Character> foundChars = new HashSet<Character>();
    	    int total = 0;
    	    while (line != null) {
    	    	for(int i = 0; i < line.length()/2; i++) {
    	    		foundChars.add(line.charAt(i));
    	    	}

    	    	for(int i = line.length()/2; i < line.length(); i++) {
    	    		if(foundChars.contains(line.charAt(i))) {
    	    			total += getScore(line.charAt(i));
    	    			break;
    	    		}
    	    	}
    	    	
    	    	foundChars.clear();
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
