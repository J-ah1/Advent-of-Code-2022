package day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import java.util.Queue;


public class PartA {
	
	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new FileReader("Inputs/day7_input.txt"))) {
    	    String line = br.readLine();
    	    Dir rootDir = new Dir("C:", null);
    	    Dir currDir = rootDir;
    	    while (line != null) {
    	    	String[] words = line.split(" ");
    	    	// Find out the type of line and act accordingly
    	    	// Ignore "ls" and "dir" lines
    	    	if(words[0].equals("$")) {
    	    		// Line is a command
    	    		if(words[1].equals("cd")) {
    	    			String nextDir = words[2];
    	    			if(nextDir.equals("..")) {
    	    				currDir = currDir.getParent();
    	    			}else{
    	    				// This assumes a child dir is accessed EXACTLY ONCE
    	    				Dir nextNode = new Dir(nextDir, currDir);
    	    				currDir.addChild(nextNode);
    	    				currDir = nextNode;
    	    			}
    	    		}
    	    	}else if(!words[0].equals("dir")) {
    	    		// Line is a file
    	    		// This assumes a file is SEEN EXACTLY ONCE
    	    		currDir.addSize(Integer.parseInt(words[0]));
    	    	}
    	        line = br.readLine();
    	    }
    	    
    	    // BFS through nodes, adding sizes below 100000
    	    int total = 0;
    	    Queue<Dir> q = new LinkedList<Dir>();
    	    q.offer(rootDir);
    	    while(!q.isEmpty()) {
    	    	currDir = q.poll();
    	    	if(currDir.size <= 100000) {
    	    		total += currDir.size;
    	    	}
    	    	for(Dir child: currDir.getChildren()) {
    	    		q.add(child);
    	    	}
    	    }
    	    System.out.println(total);
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
