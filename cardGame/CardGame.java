/*
Author: Samir Ahmed
Date: June 18th 2025
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
	//These Array lists store the deck and the players' cards respectively 
	private static ArrayList<Card> deckOfCards = new ArrayList<Card>();
	private static ArrayList<Card> player1Cards = new ArrayList<Card>();
	private static ArrayList<Card> player2Cards = new ArrayList<Card>();
	private static int player1Score = 0;
	private static int player2Score = 0;
	
	private static final String[] CARD_FACTS = {
			"The four suits in a deck of cards represent the four seasons, with 52 cards representing the weeks in a year.",
			"Playing cards were first invented in China during the Tang dynasty around the 9th century AD.",
			"The first Joker was created in 1860 as a third trump card for the game of Euchre.",
			"The King of Hearts is the only king without a mustache, which is why he's sometimes called the 'Suicide King'.",
			"The tradition of placing Aces above Kings wasn't widespread until after the French Revolution.",
			"A shuffled deck is a unique sequence. The number of possible ways to arrange a standard 52-card deck is astronomical. If you could shuffle a deck perfectly every second since the beginning of the universe, you would have repeated a sequence 0 times."

		};

	public static void main(String[] args) {
		Scanner input = null;
		try {
			input = new Scanner(new File("cardGame/cards.txt")); //read the deck
		} catch (FileNotFoundException e) {
			System.out.println("error");
			e.printStackTrace();
		}
		
		//read each line from cards.txt and build Card objects
		while(input.hasNext()) {
			String[] fields = input.nextLine().split(",");
			Card newCard = new Card(fields[0], fields[1].trim(),
					Integer.parseInt(fields[2].trim()), fields[3]);
			deckOfCards.add(newCard);
		}

		shuffle();

		//deal 5 cards to each player
		for(int i = 0; i < 5; i++) {
			player1Cards.add(deckOfCards.remove(0));
			player2Cards.add(deckOfCards.remove(0));
		}
		
		//print player hands with ASCII art display
		CardDisplay.clearScreen();
		System.out.println("\nPlayer 1's hand:");
		CardDisplay.displayCards(player1Cards);
		
		System.out.println("\nPlayer 2's hand:");
		CardDisplay.displayCards(player2Cards);
		
		//Check for pairs and determine winner
		boolean player1HasPair = checkFor2Kind(player1Cards);
		boolean player2HasPair = checkFor2Kind(player2Cards);
		
		System.out.println("\nResults:");
		System.out.println("Player 1" + (player1HasPair ? " has a pair!" : " has no pair."));
		System.out.println("Player 2" + (player2HasPair ? " has a pair!" : " has no pair."));
				//Determine and display winner
		determineAndDisplayWinner(player1HasPair, player2HasPair);
		
		//Display a random card fact
		displayRandomCardFact();
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
		//check for 2 of a kind in a player's hand
	public static boolean checkFor2Kind(ArrayList<Card> hand) {
		for(int i = 0; i < hand.size() - 1; i++) {
			Card current = hand.get(i);
			
			for(int j = i+1; j < hand.size(); j++) {
				Card next = hand.get(j);
				if(current.getName().equals(next.getName())) {
					return true;
				}
			}
		}
		return false;
	}
	//Get the highest card value from a hand
	private static int getHighestCardValue(ArrayList<Card> hand) {
		int highest = 0;
		for(Card card : hand) {
			int cardValue = card.getComparisonValue();
			if(cardValue > highest) {
				highest = cardValue;
			}
		}
		return highest;
	}

	//Determine and display the winner
	private static void determineAndDisplayWinner(boolean player1HasPair, boolean player2HasPair) {
		System.out.println("\nFinal Result:");
		
		if(player1HasPair && !player2HasPair) {
			System.out.println("Player 1 wins with a pair!");
			player1Score++;
		} else if(!player1HasPair && player2HasPair) {
			System.out.println("Player 2 wins with a pair!");
			player2Score++;
		} else if(player1HasPair && player2HasPair) {
			// Both have pairs, compare highest cards
			int p1H = getHighestCardValue(player1Cards);
			int p2H = getHighestCardValue(player2Cards);
			
			if(p1H > p2H) {
				System.out.println("Both players have pairs. Player 1 wins with higher cards!");
				player1Score++;
			} else if(p2H > p1H) {
				System.out.println("Both players have pairs. Player 2 wins with higher cards!");
				player2Score++;
			} else {
				System.out.println("It's a tie! Both players have equivalent hands.");
			}
		} else {
			// Neither has pairs, compare highest cards
			int p1H = getHighestCardValue(player1Cards);
			int p2H = getHighestCardValue(player2Cards);
			
			if(p1H > p2H) {
				System.out.println("No pairs. Player 1 wins with high card: " + p1H);
				player1Score++;
			} else if(p2H > p1H) {
				System.out.println("No pairs. Player 2 wins with high card: " + p2H);
				player2Score++;
			} else {
				System.out.println("It's a tie! Both players have the same high card.");
			}
		}
		
		System.out.println("\nScores - Player 1: " + player1Score + ", Player 2: " + player2Score);
		System.out.println("Thanks for playing!");
		displayRandomCardFact();
		
	}
	
	private static void displayRandomCardFact() {
		int factIndex = (int) (Math.random() * CARD_FACTS.length);
		System.out.println("\nFun Fact About Playing Cards: " + CARD_FACTS[factIndex]);
		System.out.println("----------------------------------------");
	}
}//end class
