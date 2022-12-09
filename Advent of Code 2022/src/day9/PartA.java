package day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PartA {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new FileReader("Inputs/day9_input.txt"))) {
    	    String line = br.readLine();
    	    // Setting initial positions of knots
    	    int[] headPos = {0,0};
    	    int[] tailPos = {0,0};
    	    int[] distApart = {0,0};
    	    // Using a set to keep track of visited tail positions
    	    Set<String> visited = new HashSet<String>();
    	    addToVisited(visited, tailPos);
    	    while (line != null) {
    	    	String[] input = line.split(" ");
    	    	// Getting the moving direction and looping the num times specified in input
    	    	int[] headDir = getHeadDir(input[0]);
    	    	for(int i = 0; i < Integer.parseInt(input[1]); i++) {
    	    		headPos[0] += headDir[0];
    	    		headPos[1] += headDir[1];
    	    		distApart[0] = headPos[0] - tailPos[0];
    	    		distApart[1] = headPos[1] - tailPos[1];
    	    		
    	    		// Using the distance apart to determine how to move the following knot
    	    		if(Math.abs(distApart[0]) > 1 || Math.abs(distApart[1]) > 1) {
    	    			if(Math.abs(distApart[0]) > 1) {
    	    				// distApart X is 2 or -2
    	    				tailPos[0] += headDir[0];
    	    				if(distApart[1] != 0) {
    	    					// distApart Y is -1 or 1
    	    					tailPos[1] += distApart[1];
    	    				}
    	    			}else{
    	    				// distApart Y is 2 or -2
    	    				tailPos[1] += headDir[1];
    	    				if(distApart[0] != 0) {
    	    					// distApart X is -1 or 1
    	    					tailPos[0] += distApart[0];
    	    				}
    	    			}
    	    			addToVisited(visited, tailPos);
    	    		}
    	    		
    	    	}
    	    	
    	        line = br.readLine();
    	    }

    	    System.out.println(visited.size());
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Making a hash using int[] and adding to the visited set
	private static void addToVisited(Set<String> visited, int[] tailPos) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.valueOf(tailPos[0]));
		sb.append(",");
		sb.append(String.valueOf(tailPos[1]));
		visited.add(sb.toString());
	}

	// Converting the letter of dir to a x,y "add value"
	private static int[] getHeadDir(String dir) {
		switch(dir) {
			case "L":
				return new int[]{-1, 0};
			case "R":
				return new int[]{1, 0};
			case "U":
				return new int[]{0, 1};
			case "D":
				return new int[]{0, -1};
			default:
				return new int[2];
		}
	}

}
