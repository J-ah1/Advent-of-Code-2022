package day15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartB {

	static final int MAX_ROWCOL_NUM = 4000000;

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new FileReader("Inputs/day15_input.txt"))) {
			
			List<int[]> sensorBeaconPairs = new ArrayList<int[]>();
			// (NTS: ...don't forget to parse for negatives...)
			Pattern intPattern = Pattern.compile("-?\\d+");
    	    Matcher matcher;
			
    	    String line = br.readLine();

    	    // Gathering all sensor/beacon data
    	    while (line != null) {
    	    	int[] sensorBeaconPair = new int[4];
    	    	matcher = intPattern.matcher(line);
    	    	matcher.find();
    	    	sensorBeaconPair[0] = Integer.parseInt(matcher.group());
    	    	matcher.find();
    	    	sensorBeaconPair[1] = Integer.parseInt(matcher.group());
    	    	matcher.find();
    	    	sensorBeaconPair[2] = Integer.parseInt(matcher.group());
    	    	matcher.find();
    	    	sensorBeaconPair[3] = Integer.parseInt(matcher.group());
    	    	sensorBeaconPairs.add(sensorBeaconPair);
    	        line = br.readLine();
    	    }
    	    
    	    // Accounting for x values in the check row that can't have a beacon
    	    Set<String> invVals = new HashSet<String>();
    	    int[][] goodIdea = new int[MAX_ROWCOL_NUM+1][MAX_ROWCOL_NUM+1];
    	    //
    	    // Iterating through each sensor/beacon pair
    	    // Calculating the man distance and distance from the row we'll check
    	    for(int[] sbInfo : sensorBeaconPairs) {
    	    	int sourceX = sbInfo[0];
    	    	int sourceY = sbInfo[1];
    	    	int manDist = Math.abs(sbInfo[2]-sourceX) + Math.abs(sbInfo[3]-sourceY);
    	    	for(int checkRowY = sourceY-manDist;
    	    			checkRowY <= MAX_ROWCOL_NUM && checkRowY <= sourceY+manDist;
    	    			checkRowY++) {
    	    		
    	    		int distFromCheckRow = Math.abs(sourceY-checkRowY);
        	    	
        	    	// Now, if the check row is within the man distance... 
    	    		// Start adding all "invalid" x values to the set
    	    		goodIdea[sourceX][checkRowY] = 1;
    	    		for(int i = 1; i <= manDist - distFromCheckRow
    	    				&& (sourceX+i <= MAX_ROWCOL_NUM ||
    	    						sourceX-i >= 0); i++) {
    	    			if(sourceX+i <= MAX_ROWCOL_NUM) goodIdea[sourceX+i][checkRowY] = 1;
    	    			if(sourceX-i >= 0) goodIdea[sourceX-i][checkRowY] = 1;
    	    		}
    	    	}
    	    	System.out.println("Next pair!");
    	    	System.out.println();
    	    }
    	    
    	    

    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String createHash(int x, int y) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.valueOf(x));
		sb.append(",");
		sb.append(String.valueOf(y));
		return sb.toString();
	}

}
