/*
Author: Samir Ahmed
Date: June 19th 2025
Program: Card Game Implementation featuring:
- Card deck loading from file
- ASCII art card display in terminal
- Shuffle functionality
- Pair checking
- Visual card representation

This program reads a deck of cards from cards.txt, shuffles them,
deals a hand to the player, and checks for pairs while displaying
the cards with ASCII art in the terminal.
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CardGame {
	//These Array list store the deck and the players card respectively 
	private static ArrayList<Card> deckOfCards = new ArrayList<Card>();
	private static ArrayList<Card> playerCards = new ArrayList<Card>();


	public static void main(String[] args) {

		Scanner input = null;
		try {
			input = new Scanner(new File("cardGame/cards.txt")); //read the deck
		} catch (FileNotFoundException e) {
			// error handeling 
			System.out.println("error");
			e.printStackTrace();
		}
		//read each line from cards.txt and build Card objeccts
		while(input.hasNext()) {
			String[] fields  = input.nextLine().split(",");
			//	public Card(String cardSuit, String cardName, int cardValue, String cardPicture) {
			Card newCard = new Card(fields[0], fields[1].trim(),
					Integer.parseInt(fields[2].trim()), fields[3]);
			deckOfCards.add(newCard);	//insert card to deck
		}

		shuffle();

		//for(Card c: deckOfCards)
			//System.out.println(c);

		//deal the player 5 cards
		for(int i = 0; i < 5; i++) {
			playerCards.add(deckOfCards.remove(0));
		}
		//print player hands with ASCII art display
		System.out.println("\nYour hand:");
		CardDisplay.clearScreen();
		CardDisplay.displayCards(playerCards);
		
		System.out.println("\nDo you have a pair? " + (checkFor2Kind() ? "Yes!" : "No"));

	}//end main

	public static void shuffle() {

		//shuffling the cards by deleting and reinserting
		for (int i = 0; i < deckOfCards.size(); i++) {
			int index = (int) (Math.random()*deckOfCards.size());
			Card c = deckOfCards.remove(index);
			//System.out.println("c is " + c + ", index is " + index);
			deckOfCards.add(c);
		}
	}
	
	//check for 2 of a kind in the players hand
	public static boolean checkFor2Kind() {

		int count = 0;
		for(int i = 0; i < playerCards.size() - 1; i++) {
			Card current = playerCards.get(i);
			Card next = playerCards.get(i+1);
			
			for(int j = i+1; j < playerCards.size(); j++) {
				next = playerCards.get(j);
				//System.out.println(" comparing " + current);
				//System.out.println(" to " + next);
				if(current.equals(next))
					count++;
			}//end of inner for
			if(count == 1)
				return true;

		}//end outer for
		return false;
	}
}//end class
