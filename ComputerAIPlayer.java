/** 
 * A Computer Player to play Connect 4. 
 * Implements Alpha-Beta pruning to efficiently conduct game-tree search to find the best move 
 * Uses a definied static evaluation function to heuristally evaluate the value of each move 
 * 
 * @author Diksha Gautham
 * @author Vineeth B. 
 * 
 * Used Scot Drysdale's "ComputerKalahPlayerAB.java" class as a reference for the structure of this program
 *
 */


public class ComputerAIPlayer extends Player {
	private final int DEPTH = 5; // the number of moves we want the computer to look ahead 
	public static String COMPUTER_NAME = "Computer"; //The string of the computer's name 
	
	public ComputerAIPlayer(String name) {
		super(name);
	}
	
	/** A method that looks ahead a certain number of moves and return the optimal 
	 * move for the computer to make
	 * @param state, the state object of the game
	 * @param view, the view object of the game 
	 */

	public int getMove(Connect4State state, Connect4View view) {
		int	move = pickMove(state, DEPTH, -Integer.MAX_VALUE, Integer.MAX_VALUE).move; // returns the column of our best move
				
		view.reportMove(move, state.getPlayerToMove().getName()); // report the move to the view 
		
		System.out.println("\n");
		System.out.println(COMPUTER_NAME+ " has placed a piece in column "+ (move+1));
		return move; 

	}	
	
	
	private Connect4Move pickMove (Connect4State state, int depth, int low, int high) {
		Connect4Move bestMove;         // Hold best move found and its value
		Connect4Move currentMove;
		int playerToMove = ((Connect4Model) state).getPlayerToMoveNumber();
		char [][] board= state.getBoard();

		
		// A dummy move that will be replaced when a real move is evaluated, column number irrelevant
		bestMove = new Connect4Move(Integer.MIN_VALUE,-1);   
		
		// Run through possible moves 
		for (int col=0; col<state.COLS; col++) {
			
			// If it's a legal move 
			if (state.isValidMove(col)) { 
				
				// Make a copy of state to run tests on 
				Connect4Model copy = new Connect4Model(state.getPlayers()[0], state.getPlayers()[1], board,playerToMove);
				copy.makeMove(col);
			
	
				// Is the game over when we make this move? 
				if (copy.gameIsOver()) {
					// if so, set the move's value equal to something really high so it wins
					currentMove=new Connect4Move(Integer.MAX_VALUE,col);   
				}
				
				// BASE CASE for recursion: if the depth is 0, get the score of that move with out static eval function
				else if (depth==0) {
					currentMove = new Connect4Move(copy.staticEvalFunction(),col);
				} 
				
				// If the depth isn't 0, we still keep looking ahead moves   
				else {
					// Recurse to look at the the next move and get its value
					currentMove = pickMove(copy, depth - 1, -high, -low); // Switch the high and low to 'flip' whether the next move is either a maximizer or a minimizer
					currentMove.value = -currentMove.value;   // A good score for the opponent is a bad score for this player 
					currentMove.move = col; // set the current move to the current column 
					
				}
				
				// If we find a better move, update our best move 
					if (currentMove.value > bestMove.value)  {  
						bestMove=currentMove;
						low = Math.max(low, bestMove.value);   // Update the lowest value, also
				}
			}
			
	}
		// Finally, return our best move! 
		return bestMove;
	}
}
	
	
	