/**
 * CardApp.java
 * @author Theo Lee
 * CIS 22C, Applied Lab 1
 */

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CardApp {
	private LinkedList<Card> list;
	private static final int OFFSET = 3;

	/**
	 * User interface prompts user, reads and writes files.
	 */
	public static void main(String[] args) {
		CardApp deck = new CardApp();
		Scanner input = new Scanner(System.in);

		System.out.println("Enter the name of a file containing a deck of cards:\n");
		System.out.println("Please open shuffled.txt and sorted.txt.\n" + "\n" + "Goodbye!\n");

		String fileName = input.nextLine();

		deck.linkedListFileReader(fileName);

		deck.shuffle();
		deck.linkedListFileWriter("shuffled.txt");
		deck.sort();
		deck.linkedListFileWriter("sorted.txt");
	}
	/**
	 * FileWriter for Linked List
	 * @param fileName
	 */
	public void linkedListFileWriter(String fileName) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
			this.list.positionIterator();
			for (int i = 0; i < this.list.getLength(); i++) {
				Card currentCard = this.list.getIterator();
				out.write(currentCard.toString());
				out.newLine();
				this.list.advanceIterator();
			}
			out.close();
		} catch (IOException error) {
		}
	}
	/**
	 * The File Reader for Linked List
	 * @param filename
	 */
	public void linkedListFileReader(String filename) {
		try {
			Scanner scanner = new Scanner(new FileReader(filename));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();
				String rank = line.substring(0, line.length() - 1);
				String suit = line.substring(line.length() - 1);
				Card card = new Card(rank, suit);
				list.addLast(card);
			}
			scanner.close();
		} catch (FileNotFoundException error) {
			error.printStackTrace();
		}
	}

    /**
     * Default constructor to initialize the deck
     */
	public CardApp() {
		list = new LinkedList<Card>();
	}

    /**
     * Inserts a new Card into the deck
     * @param card a playing Card
     */
	public void addCard(Card card) {
		list.addLast(card);
	}

    /**
     * Shuffles cards following this algorithm:
     * First swaps first and last card
     * Next, swaps every even card with the card 3
     * nodes away from that card. Stops when it
     * reaches the 3rd to last node
     * Then, swaps ALL cards with the card that is
     * 2 nodes away from it, starting at the 2nd card
     * and stopping stopping at the 3rd to last node
     */
	public void shuffle() {
		// Step 1: Swap first and last card
		Card firstCard = list.getFirst();
		Card lastCard = list.getLast();

		list.removeFirst();
		list.addFirst(lastCard);
		list.removeLast();
		list.addLast(firstCard);

		// System.out.println("shuffle step1");
		// System.out.println(this.toString());

		// Step 2: Swap every even card
		for (int i = 1; i < list.getLength() - OFFSET; i += 2) {
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

		// System.out.println("shuffle step2");
		// System.out.println(this.toString());

		// Step 3: Swap ALL cards
		for (int i = 1; i < list.getLength() - 2; i++) {
			list.positionIterator();
			for (int j = 0; j < i; j++) {
				list.advanceIterator();
			}
			Card currentCard = list.getIterator();
			list.removeIterator();

			list.positionIterator();
			for (int k = 0; k < i + 1; k++) {
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
			for (int z = 0; z < i + 1; z++) {
				list.advanceIterator();
			}
			list.addIterator(currentCard);
		}

		// System.out.println("shuffle step3");
		// System.out.println(this.toString());
	}

    /**
     * Implements the bubble sort algorithm
     * to sort cardList into sorted order, first by suit
     * (alphabetical order)
     * then by rank from 2 to A
     */
	public void sort() {
		int size = list.getLength();
		Card currentCard = null;
		Card nextCard = null;

		for (int i = 0; i < size - 1; i++) {
			for (int j = 0; j < size - i - 1; j++) {
				list.positionIterator();
				for (int k = 0; k < j; k++) {
					list.advanceIterator();
				}
				currentCard = list.getIterator();
//				list.removeIterator();

				list.positionIterator();
				for (int k = 0; k < j + 1; k++) {
					list.advanceIterator();
				}
				nextCard = list.getIterator();
//				list.removeIterator();

//				list.positionIterator();
				if (currentCard.compareTo(nextCard) > 0) {
					list.positionIterator();
					for (int k = 0; k < j; k++) {
						list.advanceIterator();
					}
					list.removeIterator();

					list.positionIterator();
					for (int k = 0; k < j; k++) {
						list.advanceIterator();
					}
					list.addIterator(currentCard);
				}
			}
		}
	}

    /**
     * Returns the deck of cards with each card separated
     * by a blank space and a new line character at the end.
     * @return The deck of cards as a string.
     */
	@Override
	public String toString() {
		return list.toString();
	}
}