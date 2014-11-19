import java.util.Scanner;

/** A controller for the Connect 4 Game. 
 * Contains the main() method which will controls and runs the program
 * Creates a view, creates players, creates a model, and manages and displays moves while the game is not over 
 * There is no major logic in this file, it just calls the methods of the other files to make things run.
 * Used Scot Drysdale's Chips.java program in CS10, Fall 2014 as a reference for the structure of this program
 * 
 * @author Diksha Gautham
 * @author Vineeth B. 
 */

public class Connect4Controller {

	/** A helper method to make a player, either human or computer
	 * @param prompt, a message to prompt to the user 
	 * @param input, the name of the player 
	 * @return
	 */
  public static Player makePlayer(String prompt, Scanner input) {
    System.out.print(prompt);
    String name = input.nextLine();
    if (name.contains(ComputerAIPlayer.COMPUTER_NAME))
      return new ComputerAIPlayer(name);
    else
      return new HumanPlayer(name);
  }
  
  /**
   * Our controller method which actually sets up the game and makes it run 
   * This is called in our 'main' method later 
   * 
   */
  
	public static void playGame() {

		// create a view 
		Connect4View view = new Connect4ViewGraphical(); // uncomment this to use graphical view
//	Connect4View view = new Connect4ViewTextual();

		// create players
		Player player1;
		Player player2;

		// Set the players 
		Scanner input = new Scanner(System.in);
		player1 = makePlayer("What is the first player's name? Type 'Computer' if you want to play the computer", input);
		player2 = makePlayer("What is the second player's name? Type 'Computer' if you want to play the computer", input);
		
		
		// create a model 
		Connect4Model model = new Connect4Model(player1, player2);
		
		// while the game isn't over
		while (!model.gameIsOver()) {
			// get the move from the player 
			Player currentPlayer = model.getPlayerToMove();
			
			System.out.println();
			System.out.println("It is " + currentPlayer.getName() + "'s move.");
						
			int move = currentPlayer.getMove(model, view);

			// make the move through the model
			if(!model.isValidMove(move))
				System.out.println("Sorry that was an invalid move");
			model.makeMove(move);
			
			// display the move via the view
			view.display(model);
	}
		System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * *");
    System.out.println(model.getPlayers()[1-model.getPlayerToMoveNumber()].getName() + " wins!");
	}
	
	
	/** A main method to run the program
	 * 
	 */
	
	public static void main(String[] args) {
	  String response;  // Holds the answer to the question asked below
    Scanner input = new Scanner(System.in);  // To read input
        
    // Prompt user to start the game 
		do {
      playGame();

      System.out.print("\n\nWant to play another game (\"yes\" if you do)? ");
      response = input.nextLine();
    }
		// Keep playing if they said yes 
    while ((response.toLowerCase()).equals("yes"));
  }
	
}
