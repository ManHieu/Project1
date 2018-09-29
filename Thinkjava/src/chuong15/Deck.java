package chuong15;

class Deck {

  Card[] cards;

  public Deck(int n) {
    this.cards = new Card[n];
  }
  public Deck() {
    this.cards = new Card[52];
    int index = 0;
    for (int suit = 0; suit <= 3; suit++) {
      for (int rank = 1; rank <= 13; rank++) {
        this.cards[index] = new Card(suit, rank);
        index++;
      }
    }
  }

  public void print() {
    for (int i=0; i<cards.length; i++) {
      cards[i].print();
    }
  }
  public int findCard (Card card) {
    for (int i = 0; i< cards.length; i++) {
      if (card.equals(cards[i])) {
        return i;
      }
    }
    return -1;
  }

  public int findBisect(Card card, int low, int high) {
    if (high < low) return -1;
    int mid = (high + low) / 2;
    int comp = card.compareTo(cards[mid]);
    if (comp == 0) {

      return mid;
    } else if (comp < 0) {
      return findBisect(card, low, mid-1);
    } else {
      return findBisect(card, mid+1, high);    }
  }
  public  PokerHand deal() {
	  PokerHand hand = new PokerHand(5);
	  for(int i = 0; i < 5; i ++) {
		  hand.cards[i]= this.cards[randInt(0, this.cards.length)];
	  }
	  return hand;
  }
  public int randInt(int low, int high) {

    int x = (int)(Math.random() * (high-low) + low);

    return x;

  }

  public void swapCards(int i, int j) {
    Card temp = cards[i];
    cards[i] = cards[j];
    cards[j] = temp;
  }

  public void shuffle() {

    for (int i=0; i<cards.length; i++) {
      int j = randInt(i, cards.length-1);
      swapCards(i, j);
    }
  }
  public void sort() {
    for (int i=0; i<cards.length; i++) {
      int j = indexLowestCard(i, cards.length-1);
      swapCards(i, j);
    }
  }

  public int indexLowestCard(int low, int high) {
    int winner = low;
    for (int i=low+1; i<=high; i++) {
      if (cards[i].compareTo(cards[winner]) < 0) {
        winner = i;
      }
    }
    return winner;
  }    

  public Deck subdeck(int low, int high) {
    Deck sub = new Deck(high-low+1);
    for (int i = 0; i<sub.cards.length; i++) {
      sub.cards[i] = cards[low+i];
    }
    return sub;
  }
  public static Deck merge(Deck d1, Deck d2) {

    Deck result = new Deck (d1.cards.length + d2.cards.length);

    int choice;    // records the winner (1 means d1, 2 means d2)
    int i = 0;     // traverses the first input deck
    int j = 0;     // traverses the second input deck
    for (int k = 0; k < result.cards.length; k++) {
      choice = 1;
      if (i == d1.cards.length)
        choice = 2;
      else if (j == d2.cards.length)
        choice = 1;
      else if (d1.cards[i].compareTo(d2.cards[j]) > 0)
        choice = 2;

      if (choice == 1) {
        result.cards[k] = d1.cards[i];  i++;
      } 
      else {
        result.cards[k] = d2.cards[j];  j++;
      }			
    }
    return result;
  }

  public Deck mergeSort() {
    if (cards.length < 2) {
      return this;
    }
    int mid = (cards.length-1) / 2;

    Deck d1 = subdeck(0, mid);
    Deck d2 = subdeck(mid+1, cards.length-1);

    d1 = d1.mergeSort();
    d2 = d2.mergeSort();

    return merge(d1, d2);
  }
}