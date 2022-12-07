package day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PartA {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new FileReader("Inputs/dayX_input.txt"))) {
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
