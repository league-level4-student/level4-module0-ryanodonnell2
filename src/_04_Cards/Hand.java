package _04_Cards;

import java.util.ArrayList;

public class Hand extends ArrayList<Card>{
	
	@Override
	public String toString() {
		String output = "";
		for (Card c: this) {
			output += c + " ";
		}
		return output; 
	}
}
