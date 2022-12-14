package day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartA {

	static int[][] rockStruct;
	// These values define the constraints of the rockStruct arr
	static int minX = Integer.MAX_VALUE;
	static int maxX = 0;
	static int maxY = 0;
	
	static int totalRestSand = 0;

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new FileReader("Inputs/day14_input.txt"))) {
			
			// Gathering the "rockLines": each text line of rock points that we'll
			// use to create the rock structure later
			List<List<int[]>> rockLines = new ArrayList<List<int[]>>();
			Pattern intPattern = Pattern.compile("\\d+");
    	    Matcher matcher;
    	    String line = br.readLine();
    	    while (line != null) {
    	    	List<int[]> rockLine = new ArrayList<int[]>();
    	    	matcher = intPattern.matcher(line);
    	    	
    	    	while(matcher.find()) {
    	    		int[] rockXY = new int[2];
        	    	rockXY[0] = Integer.parseInt(matcher.group());
        	    	minX = Math.min(minX, rockXY[0]);
        	    	maxX = Math.max(maxX, rockXY[0]);
        	    	
        	    	matcher.find();
        	    	rockXY[1] = Integer.parseInt(matcher.group());
        	    	maxY = Math.max(maxY, rockXY[1]);
        	    	
        	    	rockLine.add(rockXY);
    	    	}
    	    	
    	    	rockLines.add(rockLine);
    	        line = br.readLine();
    	    }
    	    
    	    // Creating the rock structure using the gathered "rock lines"
    	    // Rock struct is in x by y and defines 0 as air and 1 as rock
    	    createRockStruct(rockLines);
    	    
    	    // Constantly dropping a sand from the starting point until the func returns false
    	    while(dropSand(500-minX, 0));
    	    
    	    System.out.println(totalRestSand);

    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static boolean dropSand(int x, int y) {
		
		// Check if falling directly down will go into void
		if(y+1 > maxY) {
			return false;
		// if not, check if it can even drop down
		}else if(rockStruct[x][y+1] == 0) {
			return dropSand(x, y+1);
		// if not, check if falling down-left will go into void
		}else if(x-1 < 0) {
			return false;
		// if not, check if it can even drop down-left
		}else if(rockStruct[x-1][y+1] == 0) {
			return dropSand(x-1, y+1);
		// if not, check if falling down-right will go into void
		}else if(x > maxX-minX) {
			return false;
		// if not, check if it can even drop down-right
		}else if(rockStruct[x+1][y+1] == 0) {
			return dropSand(x+1, y+1);
		
		}
		// if all else fails, let sand rest and increment totalRestSand
		rockStruct[x][y] = 2;
		totalRestSand++;
		return true;
	}

	private static void createRockStruct(List<List<int[]>> rockLines) {
		rockStruct = new int[maxX-minX+1][maxY+1];
		
		// Go through each "rock line" and follow each point to create rock structures
		for(List<int[]> rockLine : rockLines) {
			// prevX/Y defines the where our "pointer" is; start at first rock point
			int prevX = rockLine.get(0)[0]-minX;
			int prevY = rockLine.get(0)[1];
			// Iterate through all the points, constantly moving prevX/Y
			for(int i = 1; i < rockLine.size(); i++) {
				// nextX/Y is the destination point
				int nextX = rockLine.get(i)[0]-minX;
				int nextY = rockLine.get(i)[1];
				// moveX/Y defines the direction to move in
				int moveX = (int) Math.signum(nextX - prevX);
				int moveY = (int) Math.signum(nextY - prevY);
				// Define the "pointer" loc as rock and keep moving until at destination 
				while(prevX != nextX || prevY != nextY) {
					rockStruct[prevX][prevY] = 1;
					prevX += moveX;
					prevY += moveY;
				}
				rockStruct[nextX][nextY] = 1;
			}
		}

	}

}
