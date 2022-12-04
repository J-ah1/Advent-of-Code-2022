package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PartA {
	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new FileReader("Inputs/day1_input.txt"))) {
    	    String line = br.readLine();
    	    int total = 0;
    	    int currTotal = 0;
    	    while (line != null) {
    	    	if(line.length() == 0) {
    	    		total = Math.max(total, currTotal);
    	    		currTotal = 0;
    	    	}else{
    	    		currTotal += Integer.parseInt(line);
    	    	}
    	        line = br.readLine();
    	    }
    	    total = Math.max(total, currTotal);
    	    System.out.println(total);
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
