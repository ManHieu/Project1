package chuong15;

public class PokerHand extends Deck {
	public PokerHand(int numCard) {
		this.cards = new Card[5];
	}
	public boolean sameSuit() {
		int suit = this.cards[0].suit;
		for(int i = 1; i < this.cards.length ; i ++) {
			if(this.cards[i].suit != suit) return false;
		}
		return true;
	}
}
