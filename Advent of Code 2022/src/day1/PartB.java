package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;

public class PartB {
	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new FileReader("Inputs/day1_input.txt"))) {
    	    String line = br.readLine();
    	    Queue<Integer> pq = new PriorityQueue<Integer>((a, b) -> b - a);
    	    int currTotal = 0;
    	    while (line != null) {
    	    	if(line.length() == 0) {
    	    		pq.add(currTotal);
    	    		currTotal = 0;
    	    	}else{
    	    		currTotal += Integer.parseInt(line);
    	    	}
    	        line = br.readLine();
    	    }
    	    int answer = 0;
    	    for(int i = 0; i < 3; i++) {
    	    	answer += pq.poll();
    	    }
    	    System.out.println(answer);
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
