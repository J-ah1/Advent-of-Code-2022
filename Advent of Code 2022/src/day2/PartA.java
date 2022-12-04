package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PartA {
	/*
	 * Used to obtain score based on OPP(y-axis/cols) vs US(x-axis/rows)
	 *    R P S
	 *  R 4 1 7
	 *  P 8 5 2
	 *  S 3 9 6
	 */
	static int[][] scoring = {
			{4,1,7},
			{8,5,2},
			{3,9,6}
	};
	
	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new FileReader("Inputs/day2_input.txt"))) {
    	    String line = br.readLine();
    	    int total = 0;
    	    while (line != null) {
    	    	total += getScore(line);
    	        line = br.readLine();
    	    }
    	    System.out.println(total);
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static int getScore(String line) {
		// Getting the corresponding scoring indices
		int opp = line.charAt(0) - 'A';
		int us = line.charAt(2) - 'X';
		return scoring[us][opp];
	}
}
