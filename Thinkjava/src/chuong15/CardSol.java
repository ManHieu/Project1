package chuong15;

public class CardSol {
	public static void main(String[] args) {
		Deck deck = new Deck();
		PokerHand hand = deck.deal();
		hand.print();
		System.out.println(hand.sameSuit());
	}
}
