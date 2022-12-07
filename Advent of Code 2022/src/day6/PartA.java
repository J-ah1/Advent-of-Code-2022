package day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class PartA {
	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new FileReader("Inputs/day6_input.txt"))) {
			int i = br.read();
			int start = 1;
			// Keep track of nums in sequence (recentNums) and O(1) access seen nums (seenNums)
			Queue<Integer> recentNums = new LinkedList<Integer>();
			Set<Integer> seenNums = new HashSet<Integer>();
			// Go through each char int value (no need to convert to char)
    	    while (i != -1) {
    	    	// 10 is the end line char
    	    	if(i != 10){
    	    		recentNums.offer(i);
    	    		// If we try to add a duplicate, keep removing from the beginning until this isn't the case
	    			while(!seenNums.add(i)) {
	    				seenNums.remove(recentNums.poll());
	    			}
	    			// Break if we can form a length 4 seq of ints
	    			if(recentNums.size() == 4) break;
    	    		start++;
    	    	}
    	    	i = br.read();
    	    }
    	    System.out.println(start);

    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
