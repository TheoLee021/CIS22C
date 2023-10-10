/**
 * Card.java
 * @author Theo Lee
 * CIS 22C, Applied Lab 1
 */
public class Card implements Comparable<Card> {
	private String rank;
	private String suit;

    /**
     * Constructor for the Card class
     * @param rank the rank of card from 2 to A
     * @param suit the suit of card C, D, H, or S
     */
	public Card(String rank, String suit) {
		this.rank = rank.toUpperCase();
		this.suit = suit.toUpperCase();
	}

	/**
	 * Returns the card's rank
	 * @return rank a rank from 2 (low) to A (high)
	 */
	public String getRank() {
		return this.rank;
	}

	/**
	 * Returns the card's suit
	 * @return C, D, H, or S
	 */
	public String getSuit() {
		return this.suit;
	}

	/**
	 * Updates the card's rank
	 * @param rank a new rank
	 */
	public void setRank(String rank) {
		this.rank = rank.toUpperCase();
	}

	/**
	 * Updates the card's suit
	 * @param suit the new suit
	 */
	public void setSuit(String suit) {
		this.suit = suit.toUpperCase();
	}

	/**
	 * Concatenates rank and suit
	 * @return card rank and suit
	 */
	@Override
	public String toString() {
		return this.rank + this.suit;
	}

    /**
     * Overrides the equals method for Card
     * Compares rank and suit and
     * follows the equals formula given in
     * Lesson 4 and also in Joshua Block's text
     * @param obj another Object to compare for
     * equality
     * @return whether obj is a Card and, if so,
     * of equal rank and suit
     */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Card)) {
			return false;
		}
		Card newCard = (Card) obj;
		if (!this.rank.equals(newCard.rank)) {
			return false;
		}
		if (!this.suit.equals(newCard.suit)) {
			return false;
		}
		return true;
	}

    /**
     * Orders two cards first by suit (alphabetically)
     * Next by rank. "A" is considered the high card
     * Order goes 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A
     * @param card another Card to compare to this Card
     * @return a negative number if this comes before c
     * and a positive number if c comes before this
     * and 0 if this and c are equal according to the above
     * equals method
     */
	@Override
	public int compareTo(Card card) {
		int suitComparison = this.suit.compareTo(card.suit);
		if (suitComparison != 0) {
			return suitComparison;
		}

		String[] order = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
		int thisRankIndex = -1;
		int cardRankIndex = -1;

		for (int i = 0; i < order.length; i++) {
			if (order[i].equals(this.rank)) {
				thisRankIndex = i;
			}
			if (order[i].equals(card.rank)) {
				cardRankIndex = i;
			}
		}

		return Integer.compare(thisRankIndex, cardRankIndex);
	}
}
