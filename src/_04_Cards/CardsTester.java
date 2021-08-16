package _04_Cards;

import java.util.ArrayList;

public class CardsTester {
	public static void main(String[] args) {
		Deck d = new Deck();
		for (Card c: d) {
			System.out.println(c);
		}
		
		System.out.println("===========================");
		d.shuffle();
		for (Card c: d) {
			System.out.println(c);
		}
		
		System.out.println("Deck size: " + d.size());
		Hand hand = d.deal(5);
		System.out.println("===========================");
		//for (Card c: hand) {
		//	System.out.println(c);
		//}
		System.out.println(hand);
		
		System.out.println("Deck size: " + d.size());
	
		System.out.println("===========================");
		PokerHand p = d.dealPokerHand();
		System.out.println(p);
		p.sort();
		System.out.println(p);
	}
}
