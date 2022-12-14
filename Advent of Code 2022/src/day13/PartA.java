package day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PartA {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new FileReader("Inputs/day13_input.txt"))) {
    	    String line = br.readLine();

    	    int pairNum = 1;
    	    int sum = 0;
    	    List<Integer> leftList;
    	    List<Integer> rightList;
    	    while (line != null) {	
    	    	Node leftNode = createNodeTree(line);
    	    	line = br.readLine();
    	    	Node rightNode = createNodeTree(line);
    	    	
    	    	leftList = new ArrayList<Integer>();
    	    	rightList = new ArrayList<Integer>();
    	    	populateIntList(leftNode, leftList);
    	    	populateIntList(rightNode, rightList);
    	    	
    	    	
    	    	if(compareLists(leftList, rightList)) {
    	    		sum += pairNum;
    	    	}
    	    	
    	        line = br.readLine(); // skip blank
    	        line = br.readLine();
    	        pairNum++;
    	    }
    	    System.out.println(sum);
    	    
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// UGHHH
	// Need to account for [] actually...
	// I believe [], [[]] proves true...
	private static boolean compareLists(List<Integer> leftList, List<Integer> rightList) {
		int i = 0;
		while(i < leftList.size() && i < rightList.size()) {
			if(leftList.get(i) < rightList.get(i)) return true;
			if(leftList.get(i) > rightList.get(i)) return false;
			i++;
		}
		return rightList.size() < leftList.size();
	}

	// Could probably have just done this during node creation?
	private static void populateIntList(Node currNode, List<Integer> currList) {
		if(currNode.hasVal()) {
			currList.add(currNode.getVal());
		} else {
			for(Node child : currNode.getChildren()) {
				populateIntList(child, currList);
			}
		}
	}
	
	// DELETE THIS
	// uh... how do we structure this?
	private static int compareTrees(Node leftNode, Node rightNode) {
		if(leftNode.hasVal() && rightNode.hasVal()){
			if(leftNode.getVal() < rightNode.getVal()) return 1;
			if(leftNode.getVal() > rightNode.getVal()) return -1;
		}

		// do the following if there's no rightVal...
		if(!rightNode.hasVal()) {
			for(Node child : rightNode.getChildren()) {
				int res = compareTrees(leftNode, child);
				if(res != 0) {
					return res;
				}
			}
		}
		// check if after above, there's still no rightVal...

		// do the following if there's no leftVal...
		if(!leftNode.hasVal()) {
			for(Node child : leftNode.getChildren()) {
				int res = compareTrees(child, rightNode);
				if(res != 0) {
					return res;
				}
			}
		}
		
		return (leftNode.hasVal() ? -1 : 0);
	}
	
	private static Node createNodeTree(String line) {
		Node root = new Node(null);
		Node currNode = root;
		StringBuilder sb = new StringBuilder();
		for(char c : line.toCharArray()) {
			if(!Character.isDigit(c) && !sb.isEmpty()) {
				currNode.addChild(new Node(currNode, Integer.parseInt(sb.toString())));
				sb.setLength(0);
			}
			switch(c) {
				case '[':
					Node addNode = new Node(currNode);
					currNode.addChild(addNode);
					currNode = addNode;
					break;
				case ']':
					currNode = currNode.getParent();
					break;
				case ',':
					// skip
					break;
				default:
					sb.append(c);
					break;
			}
		}
		return root;
	}

}
