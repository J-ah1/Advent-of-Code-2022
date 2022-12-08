package day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PartA {

	// NTS: This goes through 6 N length passes, so O(N)
	// Could probably re-factor this still
	
	public static void main(String[] args) {
		
		try(BufferedReader br = new BufferedReader(new FileReader("Inputs/day8_input.txt"))) {
			// Gathering all the heights and making a 2d array
    	    String line = br.readLine();
    	    List<List<Integer>> heights = new ArrayList<List<Integer>>();
    	    while (line != null) {
    	    	List<Integer> addList = new ArrayList<Integer>();
    	    	for(char c : line.toCharArray()) {
    	    		addList.add(Integer.parseInt(Character.toString(c)));
    	    	}
    	    	heights.add(addList);
    	        line = br.readLine();
    	    }
    	    
    	    
    	    
    	    // Setting up the visibility array, labeling outermost trees visible
    	    int numRows = heights.size();
    	    int numCols = heights.get(0).size();
    	    int[][] isVisible = new int[numRows][numCols];
    	    for(int x = 0; x < numCols; x++) {
    	    	isVisible[0][x] = 1;
    	    	isVisible[numRows-1][x] = 1;
    	    }
    	    for(int y = 0; y < numRows; y++) {
    	    	isVisible[y][0] = 1;
    	    	isVisible[y][numCols-1] = 1;
    	    }
    	    
    	    // Setting if a tree is visible
    	    // This is done by going from left-right/top-bottom for each row/col
    	    // and checking any time a "new max height" is found
    	    // This is also done in reverse order
    	    int max;
    	    // Checking each row
    	    for(int y = 1; y < numRows-1; y++) {
    	    	max = heights.get(y).get(0);
    	    	// Going through each col left-right
    	    	for(int x = 1; x < numCols-1; x++) {
    	    		if(max < heights.get(y).get(x)) {
    	    			max = heights.get(y).get(x);
    	    			isVisible[y][x] = 1;
    	    		}
        	    }
    	    	max = heights.get(y).get(numCols-1);
    	    	// Going through each col right-left
    	    	for(int x = numCols-2; x > 0; x--) {
    	    		if(max < heights.get(y).get(x)) {
    	    			max = heights.get(y).get(x);
    	    			isVisible[y][x] = 1;
    	    		}
        	    }
    	    }
    	    // Checking each col
    	    for(int x = 1; x < numCols-1; x++) {
    	    	max = heights.get(0).get(x);
    	    	// Going through each row left-right
    	    	for(int y = 1; y < numRows-1; y++) {
    	    		if(max < heights.get(y).get(x)) {
    	    			max = heights.get(y).get(x);
    	    			isVisible[y][x] = 1;
    	    		}
        	    }
    	    	max = heights.get(numRows-1).get(x);
    	    	// Going through each row right-left
    	    	for(int y = numRows-2; y > 0; y--) {
    	    		if(max < heights.get(y).get(x)) {
    	    			max = heights.get(y).get(x);
    	    			isVisible[y][x] = 1;
    	    		}
        	    }
    	    }
    	    
    	    // Counting the visible trees in the isVisible arr
    	    int total = 0;
    	    for(int y = 0; y < numRows; y++) {
    	    	for(int x = 0; x < numCols; x++) {
    	    		if(isVisible[y][x] == 1) total++;
        	    }
    	    }
    	    System.out.println(total);

    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
