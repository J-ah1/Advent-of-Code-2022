package day7;

import java.util.ArrayList;
import java.util.List;

public class Dir{
	String name;
	int size; // size includes children
	Dir parent;
	List<Dir> children;
	
	public Dir(String name, Dir parent) {
		this.name = name;
		this.parent = parent;
		size = 0;
		children = new ArrayList<Dir>();
	}
	
	// adding also increases all previous parent sizes
	public void addSize(int add) {
		size += add;
		if(parent != null) parent.addSize(add);
	}
	
	public void addChild(Dir child) {
		children.add(child);
	}
	
	public Dir getParent() {
		return parent;
	}
	
	public int getSize() {
		return size;
	}
	
	public List<Dir> getChildren(){
		return children;
	}
}
