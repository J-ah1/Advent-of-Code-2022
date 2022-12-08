package day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PartB {

	// NTS: This also goes through 6 N length passes
	// However, this makes comparisons on a growing list
	// So, to my understanding, it'd be O(n^2)
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
    	    
    	    
    	    
    	    // Setting up scenicScores, the outermost is all 0 whereas the inner is 1
    	    int numRows = heights.size();
    	    int numCols = heights.get(0).size();
    	    int[][] scenicScore = new int[numRows][numCols];
    	    for(int y = 1; y < numRows-1; y++) {
    	    	for(int x = 1; x < numCols-1; x++) {
    	    		scenicScore[y][x] = 1;
        	    }
    	    }
    	    
    	    
    	    
    	    // Calculating the scenic score for each tree
    	    // This is done by going from left-right/top-bottom for each row/col
    	    // and constantly comparing to a prevHeights list, incrementing score accordingly
    	    // and adding to said list
    	    // This is also done in reverse order
    	    int currScore;
    	    List<Integer> prevHeights = new ArrayList<Integer>();
    	    // Checking each row
    	    for(int y = 1; y < numRows-1; y++) {
    	    	prevHeights.clear();
    	    	prevHeights.add(heights.get(y).get(0));
    	    	// Going through each col left-right
    	    	for(int x = 1; x < numCols-1; x++) {
    	    		currScore = 1;
    	    		int currHeight = heights.get(y).get(x);
    	    		for(int i = prevHeights.size()-1;
    	    				i > 0 && prevHeights.get(i) < currHeight;
    	    				i--) {
    	    			currScore++;
    	    		}
    	    		scenicScore[y][x] *= currScore;
    	    		prevHeights.add(heights.get(y).get(x));
        	    }
    	    	prevHeights.clear();
    	    	prevHeights.add(heights.get(y).get(numCols-1));
    	    	// Going through each col right-left
    	    	for(int x = numCols-2; x > 0; x--) {
    	    		currScore = 1;
    	    		int currHeight = heights.get(y).get(x);
    	    		for(int i = prevHeights.size()-1;
    	    				i > 0 && prevHeights.get(i) < currHeight;
    	    				i--) {
    	    			currScore++;
    	    		}
    	    		scenicScore[y][x] *= currScore;
    	    		prevHeights.add(heights.get(y).get(x));
        	    }
    	    }
    	    // Checking each col
    	    for(int x = 1; x < numCols-1; x++) {
    	    	prevHeights.clear();
    	    	prevHeights.add(heights.get(0).get(x));
    	    	// Going through each row left-right
    	    	for(int y = 1; y < numRows-1; y++) {
    	    		currScore = 1;
    	    		int currHeight = heights.get(y).get(x);
    	    		for(int i = prevHeights.size()-1;
    	    				i > 0 && prevHeights.get(i) < currHeight;
    	    				i--) {
    	    			currScore++;
    	    		}
    	    		scenicScore[y][x] *= currScore;
    	    		prevHeights.add(heights.get(y).get(x));
        	    }
    	    	prevHeights.clear();
    	    	prevHeights.add(heights.get(numRows-1).get(x));
    	    	// Going through each row right-left
    	    	for(int y = numRows-2; y > 0; y--) {
    	    		currScore = 1;
    	    		int currHeight = heights.get(y).get(x);
    	    		for(int i = prevHeights.size()-1;
    	    				i > 0 && prevHeights.get(i) < currHeight;
    	    				i--) {
    	    			currScore++;
    	    		}
    	    		scenicScore[y][x] *= currScore;
    	    		prevHeights.add(heights.get(y).get(x));
        	    }
    	    }
    	    
    	    // Looking through all scores and getting maxScore
    	    int maxScore = 0;
    	    for(int y = 0; y < numRows; y++) {
    	    	for(int x = 0; x < numCols; x++) {
    	    		if(maxScore < scenicScore[y][x]) maxScore = scenicScore[y][x];
        	    }
    	    }
    	    System.out.println(maxScore);

    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
