/**
 * CardApp.java
 * @author Theo Lee
 * CIS 22C, Applied Lab 1
 */
public class CardApp {
	private LinkedList<Card> list;
	
	/**
	 * User interface prompts user, reads and writes files.
	 */

	public static void main(String[] args) {
		CardApp deck = new CardApp();
		System.out.println(deck.toString());
	}
	
	/**
	 * Default constructor to initialize the deck
	 */
	public CardApp() {
		list = new LinkedList<Card>();
		Card spadeA = new Card("A", "S");
		list.addLast(spadeA);
		Card spade2 = new Card("2", "S");
		list.addLast(spade2);
	}
	
	/**
	 * Returns the deck of cards with each card separated
	 * by a blank space and a new line character at the end.
	 * @return The deck of cards as a string
	 */
	@Override public String toString() {
		return list.toString();
	}

}
