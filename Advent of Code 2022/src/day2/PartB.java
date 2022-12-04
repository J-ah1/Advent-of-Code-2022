package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PartB {
	/*
	 * Used to obtain score based on OPPONENT(y-axis/cols) vs OUTCOME(x-axis/rows)
	 *    R P S
	 *  L 3 1 2
	 *  D 4 5 6
	 *  W 8 9 7
	 */
	static int[][] scoring = {
			{3,1,2},
			{4,5,6},
			{8,9,7}
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
		int out = line.charAt(2) - 'X';
		return scoring[out][opp];
	}
}
