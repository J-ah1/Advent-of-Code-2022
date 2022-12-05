package day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartB {
	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new FileReader("Inputs/day5_input.txt"))) {
    	    String line = br.readLine();
    	    
    	    // Populate a map of tempStacks by finding needed chars
    	    Map<Integer, Stack<Character>> stacks = new HashMap<Integer, Stack<Character>>();
    	    Map<Integer, Stack<Character>> tempStacks = new HashMap<Integer, Stack<Character>>();
    	    while (line.length() != 0) {
    	    	// Get starting stacks first
    	    	if(line.charAt(1) == '1') break;
    	    	for(int i = 1; i < line.length(); i+= 4) {
    	    		if(line.charAt(i) == ' ') continue;
    	    		if(!tempStacks.containsKey(i/4)) tempStacks.put(i/4, new Stack<Character>());
    	    		tempStacks.get(i/4).push(line.charAt(i));
    	    	}
    	    	line = br.readLine();
    	    }
    	    
    	    // Reverse the stacks by moving from the tempStacks map to stacks map
    	    for(int i : tempStacks.keySet()) {
    	    	stacks.put(i, new Stack<Character>());
    	    	Stack<Character> currStack = stacks.get(i);
    	    	Stack<Character> currTempStack = tempStacks.get(i);
    	    	while(currTempStack.size() > 0) {
    	    		currStack.push(currTempStack.pop());
    	    	}
    	    }

    	    // Skip to the next section of file
    	    line = br.readLine();
    	    line = br.readLine();
    	    while(line != null) {
    	    	// Finally, move all the stacks according to instructions
    	    	moveCrates(line, stacks);
    	    	line = br.readLine();
    	    }
    	    // Also, print the top of each stack out
    	    for(int i : stacks.keySet()) {
    	    	System.out.print(stacks.get(i).pop());
    	    }
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void moveCrates(String instructions, Map<Integer, Stack<Character>> stacks) {
		// Capture the 3 ints in the instructions: numCratesToMove, intOfFromStack, and intOfToStack
		// Note: the stacks indices in instructions start from 1, as opposed to 'stacks' which starts from 0
		Pattern intPattern = Pattern.compile("(\\d+)\\D+(\\d+)\\D+(\\d+)");
	    Matcher matcher = intPattern.matcher(instructions);
	    matcher.find();
	    int numCratesToMove = Integer.parseInt(matcher.group(1));
	    Stack<Character> fromStack = stacks.get(Integer.parseInt(matcher.group(2))-1);
	    Stack<Character> toStack = stacks.get(Integer.parseInt(matcher.group(3))-1);
	    
	    // Preserving order by placing onto a temp stack...
	    Stack<Character> tempStack = new Stack<Character>();
	    while(fromStack.size() > 0 && numCratesToMove > 0) {
	    	tempStack.push(fromStack.pop());
	    	numCratesToMove--;
	    }
	    
	    // ...THEN placing onto the to stack 
	    while(tempStack.size() > 0) {
	    	toStack.push(tempStack.pop());
	    }
	}
}
