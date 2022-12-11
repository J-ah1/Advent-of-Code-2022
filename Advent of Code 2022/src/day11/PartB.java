package day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class PartB {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new FileReader("Inputs/day11_input.txt"))) {
    	    String line = br.readLine();
    	    String[] words;
    	    List<Monkey> monkeys = new ArrayList<Monkey>();
    	    // Create the monkeys using the data gathered from the file
    	    while (line != null) {
    	    	line = br.readLine(); // skip monkey 0 line
    	    	
    	    	// Gather starting items values
    	    	words = line.trim().substring(16).split(", ");
    	    	Queue<Integer> items = new LinkedList<Integer>();
    	    	for(String item : words) {
    	    		items.offer(Integer.parseInt(item));
    	    	}
    	    	
    	    	// Gather operation values
    	    	line = br.readLine();
    	    	words = line.trim().split(" ");
    	    	char opp = words[4].charAt(0);
    	    	String secondVal = words[5];
    	    	
    	    	// Gather test values
    	    	line = br.readLine();
    	    	words = line.trim().split(" ");
    	    	int testVal = Integer.parseInt(words[3]);
    	    	line = br.readLine();
    	    	words = line.trim().split(" ");
    	    	int testTrueMonkeyInt = Integer.parseInt(words[5]);
    	    	line = br.readLine();
    	    	words = line.trim().split(" ");
    	    	int testFalseMonkeyInt = Integer.parseInt(words[5]);
    	    	
    	    	// Create a monkey using said values and add to the list
    	    	monkeys.add(new Monkey(items, opp, secondVal, testVal, testTrueMonkeyInt, testFalseMonkeyInt));
    	    	
    	    	line = br.readLine(); // skip black line
    	        line = br.readLine(); 
    	    }
    	    
    	    // Designate test result monkey references to other monkeys
    	    for(Monkey currMonkey : monkeys) {
	    		currMonkey.linkMonkeys();
    	    }
    	    
    	    // Do a 20 round loop (each monkey goes once)
    	    for(int numRounds = 0; numRounds < 10000; numRounds++) {
    	    	for(Monkey currMonkey : monkeys) {
    	    		// Keep inspecting items until out of items
    	    		while(currMonkey.inspectItem(false));
    	    	}
    	    }
    	    
    	    // Order the monkeys by number of inspections
    	    Queue<Monkey> pq = new PriorityQueue<Monkey>((a, b) -> b.getInspectTimes() - a.getInspectTimes());
    	    for(Monkey currMonkey : monkeys) {
    	    	pq.offer(currMonkey);
    	    }
    	    
    	    // Get monkey business utilizing the priority queue
    	    long monkeyBusiness = 1;
    	    for(int i = 0; i < 2; i++) {
    	    	monkeyBusiness *= (long) pq.poll().getInspectTimes();
    	    }
    	    System.out.println(monkeyBusiness);

    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
