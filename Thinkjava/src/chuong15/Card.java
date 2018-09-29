package chuong15;

public class Card {
	int suit, rank;
    static String[] suits = { "Cơ", "Rô", "Tép", "Bích" };
    static String[] ranks = { "narf", "Ace", "2", "3", "4", "5", "6",
			      "7", "8", "9", "10", "Jack", "Queen", "King" };
    public Card() { 
	this.suit = 0;  this.rank = 0;
    }
    public Card(int suit, int rank) { 
	this.suit = suit;  this.rank = rank;
    }
    public void print() {
	System.out.println(ranks[rank] + "  " + suits[suit]);
    }

    public String toString() {
	return ranks[rank] + "  " + suits[suit];
    }

    public boolean equals(Card that) {
	return (this.suit == that.suit && this.rank == that.rank);
    }

    public int compareTo(Card that) {

	if (this.suit > that.suit) return 1;
	if (this.suit < that.suit) return -1;
	int rank1 = (this.rank + 11) % 13;
	int rank2 = (that.rank + 11) % 13;
	if (rank1 > rank2) return 1;
	if (rank1 < rank2) return -1;
	return 0;
    }
}

