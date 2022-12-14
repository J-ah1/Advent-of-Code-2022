package day13;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private Node parent;
	private List<Node> children;
	private int val; // Nodes can either be "parent lists" OR int/vals
	private boolean hasVal = false;
	
	public Node(Node parent) {
		this.parent = parent;
		children = new ArrayList<Node>();
	}
	
	public Node(Node parent, int val) {
		this.parent = parent;
		children = new ArrayList<Node>();
		hasVal = true;
		this.val = val;
	}

	public Node getParent() {
		return parent;
	}
	
	public List<Node> getChildren(){
		return children;
	}
	
	public void addChild(Node child) {
		children.add(child);
	}
	
	public int getVal() {
		return val;
	}

	public boolean hasVal() {
		return hasVal;
	}
	
}
