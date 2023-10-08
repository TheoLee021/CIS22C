/**
 * Card.java
 * @author Theo Lee
 * CIS 22C, Applied Lab 1
 */
public class Card implements Comparable<Card>{
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
	@Override public String toString() {
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
	@Override public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		else if (!(obj instanceof Card)) {
			return false;
		}
		else {
			Card newCard = (Card) obj;
			if (this.rank != newCard.rank) {
				return false;
			}
			else if (this.suit != newCard.suit) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
