package day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartB {
	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new FileReader("Inputs/day4_input.txt"))) {
    	    String line = br.readLine();
    	    int total = 0;
    	    Pattern intPattern = Pattern.compile("\\d+");
    	    Matcher matcher;
    	    int i = 0;
    	    int[] intHolder = new int[4];
    	    while (line != null) {
    	    	matcher = intPattern.matcher(line);
    	    	while(i < 4 && matcher.find()) {
    	    		intHolder[i] = Integer.parseInt(matcher.group());
    	    		i++;
    	    	}
    	    	if(hasAnyContains(intHolder)) total++;
    	    	i = 0;
    	        line = br.readLine();
    	    }
    	    System.out.println(total);
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static boolean hasAnyContains(int[] bounds) {
		if(bounds[0] <= bounds[3] && bounds[2] <= bounds[1]) return true;
		return false;
	}
}
