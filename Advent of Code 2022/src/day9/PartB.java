package day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PartB {
	/*
	 * keep track of how far H is from [T] using X,Y (can be 0,0)
	 * whenever abs of X or Y is greater than 1, use BOTH the X and Y to det [T] movement
	 * if either 0, just move X or Y of other; if abs is 2,1 or 1,2, move in a SIMPLE diagnol
	 * have a hashset of X,Y positions of [T] (remember to add 0,0)
	 * try to add to said set after every [T] move
	 * return hashset size
	 * make helper functions accordingly
	 * 
	 * FOR NEXT PART
	 * make an int[] knotPos head-tail order
	 * when moving, move head then repeat the following...
	 * 	calc distApart move knot accordingly
	 * do the above until at tail
	 * try adding tail to hashset
	 * 
	 */
	
	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new FileReader("Inputs/day9_input.txt"))) {
    	    String line = br.readLine();
    	    // Setting initial positions of knots
    	    int[][] knotPos = {
    	    		{0,0},
    	    		{0,0},
    	    		{0,0},
    	    		{0,0},
    	    		{0,0},
    	    		{0,0},
    	    		{0,0},
    	    		{0,0},
    	    		{0,0},
    	    		{0,0},
    	    		};
    	    // Using a set to keep track of visited tail positions
    	    Set<String> visited = new HashSet<String>();
    	    addToVisited(visited, new int[]{0,0});
    	    while (line != null) {
    	    	String[] input = line.split(" ");
    	    	// Getting the moving direction and looping the num times specified in input
    	    	int[] headDir = getHeadDir(input[0]);
    	    	for(int moveNum = 0; moveNum < Integer.parseInt(input[1]); moveNum++) {
    	    		// Move the head
    	    		knotPos[0][0] += headDir[0];
    	    		knotPos[0][1] += headDir[1];
    	    		// Move the following knots in pairs of "moving knot" and "reacting knot"
    	    		// Note: unlike partA, moving knots can move DIAGNOL
    	    		for(int i = 1; i < knotPos.length; i++) {
    	    			int distApart[] = new int[2];
    	    			distApart[0] = knotPos[i-1][0] - knotPos[i][0];
        	    		distApart[1] = knotPos[i-1][1] - knotPos[i][1];
        	    		
        	    		// Using the distance apart to determine how to move the following knot
        	    		if(Math.abs(distApart[0]) > 1 || Math.abs(distApart[1]) > 1) {
        	    			if(Math.abs(distApart[0]) > 1) {
        	    				// distApart X is 2 or -2
        	    				knotPos[i][0] += distApart[0]/2;
        	    				if(distApart[1] != 0) {
        	    					// NOW, distApart Y is either negative or positive
        	    					// So we simply move 1 based on magnitude
        	    					knotPos[i][1] += (distApart[1] > 0 ? 1 : -1);
        	    				}
        	    			}else{
        	    				// distApart Y is 2 or -2
        	    				knotPos[i][1] += distApart[1]/2;
        	    				if(distApart[0] != 0) {
        	    					// NOW, distApart X is either negative or positive
        	    					// So we simply move 1 based on magnitude
        	    					knotPos[i][0] += (distApart[0] > 0 ? 1 : -1);
        	    				}
        	    			}
        	    			
        	    		}
    	    		}
    	    		addToVisited(visited, knotPos[knotPos.length-1]);
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
