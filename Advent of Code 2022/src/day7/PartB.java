package day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PartB {
	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new FileReader("Inputs/day1_input.txt"))) {
    	    String line = br.readLine();

    	    while (line != null) {
    	    	
    	        line = br.readLine();
    	    }

    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
