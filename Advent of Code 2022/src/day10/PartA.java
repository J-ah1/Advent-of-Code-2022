package day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PartA {
	
	private static int cycle = 1;
	private static int x = 1;

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new FileReader("Inputs/day10_input.txt"))) {
    	    String line = br.readLine();
    	    String[] words;
    	    int total = 0;
    	    while (line != null) {
    	    	words = line.split(" ");
    	    	if(words[0].equals("noop")) {
    	    		total += incrementCycle(1);
    	    		if(cycle >= 220) break;
    	    	}else if(words[0].equals("addx")) {
    	    		total += incrementCycle(2);
    	    		x += Integer.parseInt(words[1]);
    	    		if(cycle >= 220) break;
    	    	}
    	        line = br.readLine();
    	    }
    	    
    	    System.out.println(total);
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static int incrementCycle(int times){
		int returnVal = 0;
		for(int i = 0; i < times; i++) {
			if((cycle + 20) % 40 == 0) {
				System.out.printf("%d %d \n", x, cycle);
				returnVal += x * cycle;
			}
			cycle++;
		}
		return returnVal;
	}

}
