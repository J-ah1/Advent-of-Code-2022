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

public class PartA {
	
	static final int CHECK_ROW_NUM = 2000000;

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
    	    Set<Integer> checkRowInvXVals = new HashSet<Integer>();
    	    // Also, accounting for any beacons that are on the check row
    	    Set<Integer> beaconsOnCheckRow = new HashSet<Integer>();
    	    // Iterating through each sensor/beacon pair
    	    // Calculating the man distance and distance from the row we'll check
    	    for(int[] sbInfo : sensorBeaconPairs) {
    	    	int sourceX = sbInfo[0];
    	    	int sourceY = sbInfo[1];
    	    	int manDist = Math.abs(sbInfo[2]-sourceX) + Math.abs(sbInfo[3]-sourceY);
    	    	int distFromCheckRow = Math.abs(sourceY-CHECK_ROW_NUM);
    	    	if(sbInfo[3] == CHECK_ROW_NUM) beaconsOnCheckRow.add(sbInfo[2]);
    	    	// Now, if the check row is within the man distance... 
    	    	if(distFromCheckRow <= manDist) {
    	    		// Start adding all "invalid" x values to the set
    	    		checkRowInvXVals.add(sourceX);
    	    		for(int i = 1; i <= manDist - distFromCheckRow; i++) {
    	    			checkRowInvXVals.add(sourceX+i);
    	    			checkRowInvXVals.add(sourceX-i);
    	    		}
    	    	}
    	    }
    	    
    	    // Remove from the set any x values that have beacons
    	    for(Integer beaconX : beaconsOnCheckRow) {
    	    	checkRowInvXVals.remove(beaconX);
    	    }
    	    System.out.println(checkRowInvXVals.size());

    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
