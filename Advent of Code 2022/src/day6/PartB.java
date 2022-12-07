package day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class PartB {
	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new FileReader("Inputs/day6_input.txt"))) {
			int i = br.read();
			int start = 1;
			Queue<Integer> recentNums = new LinkedList<Integer>();
			Set<Integer> seenNums = new HashSet<Integer>();
    	    while (i != -1) {
    	    	// 10 is the end line char
    	    	if(i != 10){
    	    		recentNums.offer(i);
	    			while(!seenNums.add(i)) {
	    				seenNums.remove(recentNums.poll());
	    			}
	    			if(recentNums.size() == 14) break;
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
