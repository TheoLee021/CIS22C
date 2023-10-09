import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * CardApp.java
 * 
 * @author Theo Lee CIS 22C, Applied Lab 1
 */
public class CardApp {
	private LinkedList<Card> list;

	/**
	 * User interface prompts user, reads and writes files.
	 */

	public static void main(String[] args) {
		CardApp deck = new CardApp();
		Scanner input = new Scanner(System.in);

		System.out.println("Enter the name of a file containing a deck of cards: cards1.txt\n"
				+ "Please open shuffled.txt and sorted.txt.\n" + "\n" + "Goodbye!\n");

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("card1.txt"));
			deck.list.positionIterator();
			for (int i = 0; i < deck.list.getLength(); i++) {
				Card currentCard = deck.list.getIterator();
				out.write(currentCard.toString());
				out.newLine();
				deck.list.advanceIterator();
			}
			out.close();
		} catch (IOException e) {
		}

		// Test
		System.out.println(deck.toString());
		deck.list.positionIterator();
		Card firstCard = deck.list.getIterator();
		System.out.println(firstCard);

		for (int i = 0; i < 12; i++) {
			deck.list.advanceIterator();
		}
		Card secondCard = deck.list.getIterator();
		System.out.println(secondCard);

		System.out.println(firstCard.compareTo(secondCard));
	}

	/**
	 * Default constructor to initialize the deck
	 */
	public CardApp() {
		list = new LinkedList<Card>();

		String[] suits = { "S", "H", "D", "C" };
		String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };

		for (int i = 0; i < suits.length; i++) {
			for (int j = 0; j < ranks.length; j++) {
				Card card = new Card(ranks[j], suits[i]);
				list.addLast(card);
			}
		}
	}

	/**
	 * Inserts a new Card into the deck
	 * 
	 * @param card a playing Card
	 */
	public void addCard(Card card) {
		list.addLast(card);
	}

	/**
	 * Shuffles cards following this algorithm: First swaps first and last card
	 * Next, swaps every even card with the card 3 nodes away from that card. Stops
	 * when it reaches the 3rd to last node Then, swaps ALL cards with the card that
	 * is 2 nodes away from it, starting at the 2nd card and stopping stopping at
	 * the 3rd to last node
	 */
	public void shuffle() {
		// Step 1: Swap first and last card
		Card firstCard = list.getFirst();
		Card lastCard = list.getLast();

		list.removeFirst();
		list.removeLast();

		list.addLast(firstCard);
		list.addFirst(lastCard);

		// Step 2: Swap every even card
		for (int i = 2; i < list.getLength() - 3; i += 2) {
			list.positionIterator();
			for (int j = 0; j < i; j++) {
				list.advanceIterator();
			}
			Card currentCard = list.getIterator();
			list.removeIterator();

			list.positionIterator();
			for (int k = 0; k < i + 2; k++) {
				list.advanceIterator();
			}
			Card swapCard = list.getIterator();
			list.removeIterator();

			list.positionIterator();
			for (int l = 0; l < i - 1; l++) {
				list.advanceIterator();
			}
			list.addIterator(swapCard);

			list.positionIterator();
			for (int z = 0; z < i + 2; z++) {
				list.advanceIterator();
			}
			list.addIterator(currentCard);
		}

		// Step 3: Swap ALL cards
		list.positionIterator();
		Card currentCard = list.getIterator();
		list.removeIterator();

		list.positionIterator();
		list.advanceIterator();
		Card swapCard = list.getIterator();

		list.addIterator(currentCard);
		list.removeIterator();
		list.addFirst(swapCard);

		for (int i = 1; i < list.getLength() - 3; i++) {
			list.positionIterator();
			for (int j = 0; j < i; j++) {
				list.advanceIterator();
			}
			currentCard = list.getIterator();
			list.removeIterator();

			list.positionIterator();
			for (int k = 0; k < i + 1; k++) {
				list.advanceIterator();
			}
			swapCard = list.getIterator();
			list.removeIterator();

			list.positionIterator();
			for (int l = 0; l < i - 1; l++) {
				list.advanceIterator();
			}
			list.addIterator(swapCard);

			list.positionIterator();
			for (int z = 0; z < i + 1; z++) {
				list.advanceIterator();
			}
			list.addIterator(currentCard);
		}
	}

	/**
	 * Implements the bubble sort algorithm to sort cardList into sorted order,
	 * first by suit (alphabetical order) then by rank from 2 to A
	 */
	public void sort() {
		int size = list.getLength();

		if (size > 1) {
			boolean wasChanged;

			do {
				list.positionIterator();
				Card current = list.getIterator();
				Card previous = null;
				list.advanceIterator();
				Card next = list.getIterator();
				wasChanged = false;

				int index = 0;
				while (next != null) {
					if (current.compareTo(next) > 0) {
						wasChanged = true;

						list.positionIterator();
						for (int i = 0; i < index; i++) {
							list.advanceIterator();
						}

						list.addIterator(next);
						list.removeIterator();

						list.positionIterator();
						for (int i = 0; i < index; i++) {
							list.advanceIterator();
						}
						list.addIterator(current);
						list.removeIterator();

						previous = next;
						current = list.getIterator();
						list.advanceIterator();
						next = list.getIterator();
					} else {
						previous = current;
						current = next;
						list.advanceIterator();
						next = list.getIterator();
					}
					index++;
				}
			} while (wasChanged);
		}
	}

	/**
	 * Returns the deck of cards with each card separated by a blank space and a new
	 * line character at the end.
	 * 
	 * @return The deck of cards as a string
	 */
	@Override
	public String toString() {
		return list.toString();
	}
}
