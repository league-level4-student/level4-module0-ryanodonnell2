package _04_Cards;

import java.util.ArrayList;
import java.util.Random;

// Is-a relationship: 
public class Deck extends ArrayList<Card> {
	// Has-a relationship: private ArrayList<Card> _cards;
	public Deck() {
		for (int i = Card.MIN_VALUE; i <= Card.MAX_VALUE; i++) {
			Card c = new Card(i, Card.Suits.CLUBS);
			Card d = new Card(i, Card.Suits.DIAMONDS);
			Card h = new Card(i, Card.Suits.HEARTS);
			Card s = new Card(i, Card.Suits.SPADES);
			this.add(c);
			add(d);
			add(h);
			add(s);
		}
	}
	
	public void shuffle() {
		Random rnd = new Random();
		for (int i = 0; i < 100000; i++) {
			int i1 = rnd.nextInt(size());
			int i2 = rnd.nextInt(size());
			if (i1 != i2) {
				Card temp = get(i1);
				set(i1, get(i2));
				set(i2, temp);
			}
		}
	}
	
	public Hand deal(int count) {
		Hand dealt = new Hand();
		if (count <= size()) {
			for(int i = 0; i < count; i++) {
				dealt.add(this.remove(0));
			}
		}
		return dealt;
	}
	
	public PokerHand dealPokerHand() {
		PokerHand dealt = new PokerHand();
		if (5 <= size()) {
			for(int i = 0; i < 5; i++) {
				dealt.add(this.remove(0));
			}
		}
		return dealt;
	}
}
