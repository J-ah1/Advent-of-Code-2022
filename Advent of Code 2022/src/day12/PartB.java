package day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.PriorityQueue;
import java.util.Queue;

public class PartB {
	
public static void main(String[] args) {
		
		// Counting num of lines for use in defining the array size
		Path path = Paths.get("Inputs/day12_input.txt");
		long totalLines = 0;
		try {
			totalLines = Files.lines(path).count();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try(BufferedReader br = new BufferedReader(new FileReader("Inputs/day12_input.txt"))) {
    	    String line = br.readLine();
    	    int lineLength = line.length();
    	    

    	    // Gather data and create a 2d array of elevations + noting S and E locations
    	    int[] endPos = new int[2];
    	    int[][] elevations = new int[lineLength][(int) totalLines];
    	    int lineNum = 0;
    	    while (line != null) {
    	    	for(int x = 0; x < lineLength; x++) {
    	    		if(line.charAt(x) == 'S') {
    	    			elevations[x][lineNum] = (int)'a' - 'a';
    	    			continue;
    	    		}
    	    		if(line.charAt(x) == 'E') {
    	    			endPos[0] = x;
    	    			endPos[1] = lineNum;
    	    			elevations[x][lineNum] = (int)'z' - 'a';
    	    			continue;
    	    		}
    	    		elevations[x][lineNum] = (int)line.charAt(x) - 'a';
    	    	}
    	    	lineNum++;
    	        line = br.readLine();
    	    }
    	    
    	    // Visited marks positions we've been to as 1
    	    int[][] visited = new int[lineLength][(int) totalLines];
    	    // DistanceFromStart denotes how many steps from the start the position is
    	    int[][] distanceFromStart = new int[lineLength][(int) totalLines];
    	    
    	    // Creating a priority queue of positions that's ordered by min distanceFromStart val
    	    Queue<int[]> pq = new PriorityQueue<int[]>((a, b) -> distanceFromStart[a[0]][a[1]] - distanceFromStart[b[0]][b[1]]);
    	    
    	    // Setting all distanceFromStart values to be max EXCEPT ...
    	    // ALL the "a" elevation positions (as they can ALL be "starting positions")
    	    // Also putting them into pq (just like the starting position)
    	    for(int y = 0; y < (int) totalLines; y++) {
    	    	for(int x = 0; x < lineLength; x++) {
    	    		if(elevations[x][y] == 0) {
    	    			pq.offer(new int[] {x, y});
    	    			continue;
    	    		};
    	    		distanceFromStart[x][y] = Integer.MAX_VALUE;
    	    	}
    	    }
    	    
    	    // Defining movement values
    	    int[][] directions = {
    	    		{1, 0},
    	    		{-1, 0},
    	    		{0, 1},
    	    		{0, -1},
    	    };
    	    
    	    // Will constantly go through / populate pq to get all distanceFromStart values
    	    while(!pq.isEmpty()) {
    	    	// Getting the current position values
    	    	int[] currPos = pq.poll();
    	    	int currX = currPos[0];
    	    	int currY = currPos[1];
    	    	// Skipping this position if already visited + setting it as visited
    	    	if(visited[currX][currY] == 1) continue;
    	    	visited[currX][currY] = 1;
    	    	int currElevation = elevations[currX][currY];
    	    	int currDistance = distanceFromStart[currX][currY];
    	    	
    	    	// Checking each direction if possible / is a shorter path
    	    	for(int[] direction : directions) {
    	    		int nextX = currX + direction[0];
    	    		int nextY = currY + direction[1];
    	    		// Checking if move is out of bounds
    	    		if(nextX < 0 || nextX >= lineLength || nextY < 0 || nextY >= (int)totalLines) continue;
    	    		// Checking if move is towards a visited position
    	    		if(visited[nextX][nextY] == 1) continue;
    	    		// Checking if move is towards too high an elevation
    	    		if(elevations[nextX][nextY] > currElevation+1) continue;
    	    		// Updating distance of the adjacent position if new minimum
    	    		distanceFromStart[nextX][nextY] = Math.min(currDistance + 1, distanceFromStart[nextX][nextY]);
    	    		// Adding to pq to visit later
    	    		pq.offer(new int[] {nextX, nextY});
    	    	}
    	    }
    	    
    	    System.out.println(distanceFromStart[endPos[0]][endPos[1]]);
    	    
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
