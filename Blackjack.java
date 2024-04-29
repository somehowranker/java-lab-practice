import java.util.Random;
import java.util.Scanner;

public class Blackjack {
	public static void main(String[] args){
		int seed = Integer.parseInt(args[0]);
		int num_players = Integer.parseInt(args[1]);
		
		String victory = "[Win] ";
		String defeat = "[Lose] ";
		String draw = "[Draw] ";
		
		Deck deck = new Deck();
		deck.setCard();
		deck.shuffle(seed);
		
		Player player = new Player();
		
		House house = new House();
		
		Computer[] AI_players = new Computer[num_players-1]; 
	
		player.setDeck(deck.dealCard());
		
		for(int i=0; i < num_players-1; i++) {
			AI_players[i] = new Computer();
			AI_players[i].setDeck(deck.dealCard());
		}
		
		house.setDeck(deck.dealCard());
		
		player.setDeck(deck.dealCard());
		
		for(int i=0; i < num_players-1; i++) {
			AI_players[i].setDeck(deck.dealCard());
		}
		
		house.setDeck(deck.dealCard());
		
		System.out.println("House: HIDDEN, " + house.myDeck[1].getSuit());
		System.out.println("Player1: " + player.myDeck[0].getSuit() + ", " + player.myDeck[1].getSuit() +  " (" + player.getSum() + ")");
		for(int i=0; i< num_players-1;i++) {
			System.out.println("Player"+ (i+2) + ": " + AI_players[i].myDeck[0].getSuit() + ", " + AI_players[i].myDeck[1].getSuit() +  " (" + AI_players[i].getSum() + ")");
		}
		
		if(house.getSum() == 21) {
			System.out.println();
			System.out.println("--- Game Results ---");
			System.out.println("House: " + house.myDeck[0].getSuit() + ", "+ house.myDeck[1].getSuit() + " (" + house.getSum() + ")");
			if(player.isBusted()) {
				System.out.print(defeat);
			}
			else {
				if(house.isBusted()) {
					System.out.print(victory);
				}
				else if(house.getSum() < player.getSum()) {
				System.out.print(victory);}
				else if(house.getSum() > player.getSum()) {
					System.out.print(defeat);}
				else {
					System.out.print(draw);
				}
			}
			System.out.println("Player1: " + player.myDeck[0].getSuit() + ", " + player.myDeck[1].getSuit() +  " (" + player.getSum() + ")");
			for(int i=0; i< num_players-1;i++) {
				if(AI_players[i].isBusted()) {
					System.out.print(defeat);
				}
				else {
					if(house.isBusted()) {
						System.out.print(victory);
					}
					else if(house.getSum() < AI_players[i].getSum()) {
					System.out.print(victory);}
					else if(house.getSum() > AI_players[i].getSum()) {
						System.out.print(defeat);}
					else {
						System.out.print(draw);
					}
				}
				System.out.println("Player"+ (i+2) + ": " + AI_players[i].myDeck[0].getSuit() + ", " + AI_players[i].myDeck[1].getSuit() +  " (" + AI_players[i].getSum() + ")");
			}	
		}
		else {
			//player1 turn
			System.out.println();
			System.out.println("--- Player1 turn ---");
			System.out.print("Player1: ");
			for(int j=0; j<player.handNum;j++) {
				System.out.print(player.myDeck[j].getSuit());
				if(j != player.handNum-1)
					System.out.print(", ");
				}
			System.out.println(" (" + player.getSum() + ")");
		
			while(true) {
			Scanner scn = new Scanner(System.in);
			String player_cmd = scn.nextLine();
			if(player_cmd.equals("Hit")) {
				player.hit(deck.dealCard());
				System.out.print("Player1: ");
				for(int i=0; i< player.handNum;i++) {
					System.out.print(player.myDeck[i].getSuit());
					if(i != player.handNum-1)
						System.out.print(", ");
					}
				if(player.isBusted()) {
					System.out.println(" (" + player.getSum() + ") " + "- Bust!");
					break;
				}
				else System.out.println(" (" + player.getSum() + ")");
				}
			else if(player_cmd.equals("Stand")) {
				System.out.print("Player1: ");
				for(int i=0; i< player.handNum;i++) {
					System.out.print(player.myDeck[i].getSuit());
					if(i != player.handNum-1)
						System.out.print(", ");
					}	
				System.out.println(" (" + player.getSum() + ")");
				break;
				}
			}
			//player2 turn
			for(int i = 0; i<num_players-1;i++) {
				System.out.println();
				System.out.println("--- Player" + (i+2) + " turn ---");
				System.out.print("Player" + (i+2) + ": ");
				for(int j=0; j< AI_players[i].handNum;j++) {
					System.out.print(AI_players[i].myDeck[j].getSuit());
					if(j != AI_players[i].handNum-1)
						System.out.print(", ");
					}
				if(AI_players[i].isBusted()) {
					System.out.println(" (" + AI_players[i].getSum() + ") " + "- Bust!");
					break;
				}
				else System.out.println(" (" + AI_players[i].getSum() + ")");
			
				while(true) {
					if(AI_players[i].getSum() < 14) {
						System.out.println("Hit");
							AI_players[i].hit(deck.dealCard());
							System.out.print("Player"+(i+2)+": ");
							for(int j=0; j< AI_players[i].handNum;j++) {
								System.out.print(AI_players[i].myDeck[j].getSuit());
								if(j != AI_players[i].handNum-1)
									System.out.print(", ");
								}
							if(AI_players[i].isBusted()) {
								System.out.println(" (" + AI_players[i].getSum() + ") " + "- Bust!");
								break;
							}
							else System.out.println(" (" + AI_players[i].getSum() + ")");
						}
					else if(AI_players[i].getSum() > 17) {
						//stand
						System.out.println("Stand");
						System.out.print("Player"+(i+2)+": ");
						for(int j=0; j< AI_players[i].handNum;j++) {
							System.out.print(AI_players[i].myDeck[j].getSuit());
							if(j != AI_players[i].handNum-1)
								System.out.print(", ");
							}	
						System.out.println(" (" + AI_players[i].getSum() + ")");
						break;
					}
					else {
						Random random = new Random();
						int is_hit = (int)(random.nextInt(2));
						if(is_hit == 1) {
							//hit
							System.out.println("Hit");
							AI_players[i].hit(deck.dealCard());
							System.out.print("Player"+(i+2)+": ");
							for(int j=0; j< AI_players[i].handNum;j++) {
								System.out.print(AI_players[i].myDeck[j].getSuit());
								if(j != AI_players[i].handNum-1)
									System.out.print(", ");
								}
							if(AI_players[i].isBusted()) {
								System.out.println(" (" + AI_players[i].getSum() + ") " + "- Bust!");
								break;
							}
							else System.out.println(" (" + AI_players[i].getSum() + ")");
						}
						else {
							//stand
							System.out.println("Stand");
							System.out.print("Player"+(i+2)+": ");
							for(int j=0; j< AI_players[i].handNum;j++) {
								System.out.print(AI_players[i].myDeck[j].getSuit());
								if(j != AI_players[i].handNum-1)
									System.out.print(", ");
								}	
							System.out.println(" (" + AI_players[i].getSum() + ")");
							break;
						}
					}
				}
			}
			//House Turn
			System.out.println();
			System.out.println("--- House turn ---");
			System.out.print("House: ");
			for(int j=0; j<house.handNum;j++) {
				System.out.print(house.myDeck[j].getSuit());
				if(j != house.handNum-1)
					System.out.print(", ");
				}
			System.out.println(" (" + house.getSum() + ")");
		
			while(true) {
			if(house.getSum() <= 16) {
				System.out.println("Hit");
				house.hit(deck.dealCard());
				System.out.print("House: ");
				for(int i=0; i< house.handNum;i++) {
					System.out.print(house.myDeck[i].getSuit());
					if(i != house.handNum-1)
						System.out.print(", ");
					}
				if(house.isBusted()) {
					System.out.println(" (" + house.getSum() + ") " + "- Bust!");
					break;
				}
				else System.out.println(" (" + house.getSum() + ")");
				}
			else{
				System.out.println("Stand");
				System.out.print("House: ");
				for(int i=0; i< house.handNum;i++) {
					System.out.print(house.myDeck[i].getSuit());
					if(i != house.handNum-1)
						System.out.print(", ");
					}	
				System.out.println(" (" + house.getSum() + ")");
				break;
				}
			}
			System.out.println();
			System.out.println("--- Game Results ---");
		
			
			
			
			System.out.print("House: ");
			for(int i=0; i< house.handNum;i++) {
				System.out.print(house.myDeck[i].getSuit());
				if(i != house.handNum-1)
					System.out.print(", ");
				}
			if(house.isBusted()) {
				System.out.println(" (" + house.getSum() + ") " + "- Bust!");

			}
			else System.out.println(" (" + house.getSum() + ")");
			
			

			
			if(player.isBusted()) {
				System.out.print(defeat);
			}
			else {
				if(house.isBusted()) {
					System.out.print(victory);
				}
				else if(house.getSum() < player.getSum()) {
				System.out.print(victory);}
				else if(house.getSum() > player.getSum()) {
					System.out.print(defeat);}
				else {
					System.out.print(draw);
				}
			}
			System.out.print("Player1: ");
			for(int i=0; i< player.handNum;i++) {
				System.out.print(player.myDeck[i].getSuit());
				if(i != player.handNum-1)
					System.out.print(", ");
				}
			if(player.isBusted()) {
				System.out.println(" (" + player.getSum() + ") " + "- Bust!");
				
			}
			else System.out.println(" (" + player.getSum() + ")");
			
			for(int i=0; i< num_players-1;i++) {
				if(AI_players[i].isBusted()) {
					System.out.print(defeat);
				}
				else {
					if(house.isBusted()) {
						System.out.print(victory);
					}
					else if(house.getSum() < AI_players[i].getSum()) {
					System.out.print(victory);}
					else if(house.getSum() > AI_players[i].getSum()) {
						System.out.print(defeat);}
					else {
						System.out.print(draw);
					}
				}
				System.out.print("Player" + (i+2) + ": ");
				for(int j=0; j< AI_players[i].handNum;j++) {
					System.out.print(AI_players[i].myDeck[j].getSuit());
					if(j != AI_players[i].handNum-1)
						System.out.print(", ");
					}
				if(AI_players[i].isBusted()) {
					System.out.println(" (" + AI_players[i].getSum() + ") " + "- Bust!");

				}
				else System.out.println(" (" + AI_players[i].getSum() + ")");
			
			}	
		}
		
		
		
		//player hit stand 결정
		//나머지 컴퓨터 hit stand 결정 
		//House 가려진 패 공개 , hit stand 결정
		//게임 결과 프린트
		
	}
}

class Card { //ace는 원래 11 -> 21 초과 시 1,jqk는 10
	private int value;
	private String suit;
	
	public Card() {}
	public Card(int theValue, String theSuit) {
		this.value = theValue;
		this.suit = theSuit;
	}
	
	public void setValue() {
		this.value = 1;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getSuit() {
		return suit;
	}
	
}

class Deck {
	private Card[] deck = new Card[52];
	private int cardsUsed;
	//덱 생성 -> card 52개 만들기 
	void setCard(){
		for(int i=0;i<13;i++) {
			if(i == 0) {
				deck[i] = new Card(11, "Ac");
				deck[i+1] = new Card(11, "Ah");
				deck[i+2] = new Card(11, "Ad");
				deck[i+3] = new Card(11, "As");	
			}
			else if(i == 10) {
				deck[i*4] = new Card(10, "Jc");
				deck[i*4+1] = new Card(10, "Jh");
				deck[i*4+2] = new Card(10, "Jd");
				deck[i*4+3] = new Card(10, "Js");	
			}
			else if(i == 11) {
				deck[i*4] = new Card(10, "Qc");
				deck[i*4+1] = new Card(10, "Qh");
				deck[i*4+2] = new Card(10, "Qd");
				deck[i*4+3] = new Card(10, "Qs");	
			}
			else if(i == 12) {
				deck[i*4] = new Card(10, "Kc");
				deck[i*4+1] = new Card(10, "Kh");
				deck[i*4+2] = new Card(10, "Kd");
				deck[i*4+3] = new Card(10, "Ks");	
			}
			else {
				String temp = Integer.toString(i+1);
				deck[i*4] = new Card(i+1, temp+"c");
				deck[i*4+1] = new Card(i+1, temp+"h");
				deck[i*4+2] = new Card(i+1, temp+"d");
				deck[i*4+3] = new Card(i+1, temp+"s");	
			}
		}
	}
	
	
	public void shuffle(int seed) {
		Random random = new Random(seed);
		for (int i = deck.length-1; i>0; i--) {
			int rand = (int)(random.nextInt(i+1));
			Card temp = deck[i]; //덱에서 i번째 카드가 temp를 저장 
			deck[i] = deck[rand]; // 덱에서 무작위 카드 한장을 뽑아 덱에서 i번째 카드로 저장 
			deck[rand] = temp; //덱에서 무작위번째 카드에 temp를 save -> 기본적인swap함수 
			
		}
		cardsUsed = 0;
	}
	public Card dealCard() {
		if (cardsUsed == deck.length)
			throw new IllegalStateException("No cards are left in the deck.");
		cardsUsed++;
		return deck[cardsUsed -1]; 
	}
}

class Hand {
	Card[] myDeck = new Card[52];
	int handNum=0;
	int handSum=0;
	
	public void setDeck(Card card){
		if(handSum == 22) {
			card.setValue();
		}
		this.myDeck[handNum]=card;
		handNum++;
	}
	
	public int getSum(){
		handSum=0;
		
		for(int i=0;i<handNum;i++) {
			handSum+=myDeck[i].getValue();
		}
		return handSum;
	}
	
	public void Sum(){
		for(int i=0;i<handNum;i++) {
			handSum+=myDeck[i].getValue();
		}
	}
	
	
	public Card[] getDeck() {
		return myDeck;
	}
	
	public void hit(Card card) {
		setDeck(card);
	} //한 장씩 더 받고, dealCar ->컴퓨터는 정해진 규칙대로,플레이어는 입력값 대
	
	public boolean isBusted() {
		if (getSum() > 21) {
			for(int i = 0; i < handNum; i++) {
				if(myDeck[i].getValue() == 11) {
					myDeck[i].setValue();
					Sum();
				}
			}
			if(getSum()>21)
				return true;	
			else
				return false;
		}
		else {
			return false;
		}
	}
}

class Computer extends Hand {
	//두 장 받고, 14 이하면 무조건 hit
	
	
}
class Player extends Hand {
	//input 받아서 hit이면 한장 더, stand면 그만 
	
}

class House extends Hand {

	
}
