package day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PartB {

	private static int cycle = 1;
	private static int x = 1;
	private final static int width = 40;
	private final static int height = 6;
	private static boolean[] currRow = new boolean[width];

	public static void main(String[] args) {
		
		try(BufferedReader br = new BufferedReader(new FileReader("Inputs/day10_input.txt"))) {
    	    String line = br.readLine();
    	    String[] words;
    	    while (line != null) {
    	    	words = line.split(" ");
    	    	if(words[0].equals("noop")) {
    	    		incrementCycle(1);
    	    	}else if(words[0].equals("addx")) {
    	    		incrementCycle(2);
    	    		x += Integer.parseInt(words[1]);
    	    	}
    	        line = br.readLine();
    	    }
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void drawRow() {
		for(boolean pixel : currRow) {
			if(pixel) {
				System.out.print("#");
			}else {
				System.out.print(".");
			}
		}
		System.out.println();
	}
	
	private static void incrementCycle(int times){
		for(int i = 0; i < times; i++) {
			int pixelPos = (cycle-1)%width;
			// what happens when x is negative or greater than 40?
			currRow[pixelPos] = pixelPos >= x-1 && pixelPos <= x+1;
			if(cycle%width == 0) drawRow();
			cycle++;
		}
	}

}
