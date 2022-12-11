package day11;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Monkey {
	static List<Monkey> monkeys = new ArrayList<Monkey>();
	static int prodOfDivs = 1;
	private Queue<Integer> items;
	
	// Defines the operation to be done when inspecting items
	private boolean secondIsOld = false;
	private int secondVal;
	private char opp;
	
	// Defines the values used to test which monkey to throw to
	private int testVal;
	private int testTrueMonkeyInt = -1;
	private int testFalseMonkeyInt = -1;
	private Monkey testTrueMonkey;
	private Monkey testFalseMonkey;
	
	private int inspectTimes;
	
	// Constructor that defines (most) class variables
	public Monkey(Queue<Integer> items, char opp, String secondVal, int testVal, int testTrueMonkeyInt, int testFalseMonkeyInt) {
		monkeys.add(this);
		this.items = items;
		this.opp = opp;
		if(secondVal.equals("old")) {
			secondIsOld = true;
		}else {
			this.secondVal = Integer.parseInt(secondVal);
		}
		this.testVal = testVal;
		prodOfDivs *= testVal;
		this.testTrueMonkeyInt = testTrueMonkeyInt;
		this.testFalseMonkeyInt = testFalseMonkeyInt;
		inspectTimes = 0;
	}
	
	// Defines references for monkeys (should be done after all monkeys are created)
	public void linkMonkeys() {
		if(testTrueMonkeyInt != -1) testTrueMonkey = monkeys.get(testTrueMonkeyInt);
		if(testFalseMonkeyInt != -1) testFalseMonkey = monkeys.get(testFalseMonkeyInt);
	}
	
	// Inspects item; also, returns false if no item to inspect
	public boolean inspectItem(boolean careless) {
		if(items.peek() == null) return false;
		
		// Get the most recent item worry and run operation on it
		long worry = items.poll();
		switch(opp) {
			case '*':
				worry *= (secondIsOld ? worry : secondVal);
				break;
			case '+':
				worry += (secondIsOld ? worry : secondVal);
				break;
			default:
				break;
		}
		
		// After back and forth refactoring code...
		// Modulo trick...wow
		if(careless) {
			worry /= 3;
		}else {
			worry = worry % prodOfDivs;
		} 
		
		// Test the item worry and pass to the corresponding monkey
		if(worry % testVal == 0) {
			testTrueMonkey.giveItem((int)worry);
		}else{
			testFalseMonkey.giveItem((int)worry);
		}
		inspectTimes++;
		return true;
	}
	
	public void giveItem(int item) {
		items.offer(item);
	}
	
	public int getInspectTimes() {
		return inspectTimes;
	}
}
