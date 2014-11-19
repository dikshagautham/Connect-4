/** 
 * A model for the Connect 4 game class 
 * Implements the connect 4 state interface to implement how the board pieces are stored
 * Used Scot Drysdale's KalahGame.java class for reference on the structure of this program
 * @see Connect4State for full method descriptions 
 * @author Diksha Gautham
 * @author Vineeth B. 
 *
 */

public class Connect4Model implements Connect4State {
	private char [][] board = new char[ROWS][COLS]; // the  board the game 
	private Player player1;
	private Player player2;
	private static final int[] Weights={0,1,3,10^5,10^10};
	private int playerToMove;
	
	/** Constructor to initialize an empty board
	 * 
	 * @param Player1
	 * @param Player2
	 */
	public Connect4Model(Player Player1, Player Player2){
		player1= Player1;
		player2= Player2;
		playerToMove=0;
		setEmptyBoard();
	}
	
	/** 
	 * Constructor that takes in a copy of a board as a parameter
	 * We use this in our ComputerAI player 
	 * @param Player1
	 * @param Player2
	 * @param newBoard, the new board to evaluate 
	 * @param playerNumber
	 */
	
	public Connect4Model(Player Player1, Player Player2, char[][] newBoard,int playerNumber){
		// initialize our board to be the same as the new board 
		for (int row=0; row<ROWS; row++){
			for(int col=0; col<COLS; col++){
				board[row][col]=newBoard[row][col];
			}
		}		
		// set up the players 
		player1= Player1;
		player2= Player2;
		playerToMove=playerNumber; // get the player whose turn it is 
		
	}
	
	/**
	 * @see Connect4State 
	 */
	public int getPlayerToMoveNumber(){
		return playerToMove;
	}
	
	/**
	 * *Make the board empty by going through all the rows and columns and setting them empty
	 * 
	 */
	public void setEmptyBoard() {
		for (int row=0; row <ROWS; row++) 
			for (int col=0; col<COLS; col++) {
				board[row][col] = '.';
			}
		
	}

	/** @return the board
	 * 
	 */
	public char[][] getBoard() {
		return board;
		
	}
	
	/**
	 * @see Connect4State 
	 */
	public Player[] getPlayers() {
		Player[] player_array = new Player[2];
		player_array[0] = player1;
		player_array[1] = player2;
		return player_array;
	}


	/**
	 * @see Connect4State 
	 */
	public int getPlayerNum() {
		if (getPlayerToMove().equals(player1))
			return 1;
		
		else 
			return 2;
	}

	/**
	 * @see Connect4State 
	 */
	public Player getPlayerToMove() {
		if (playerToMove == 0) {
				return player1;
		}
		else {
				return player2;
		}
	}

	/**
	 * @see Connect4State 
	 */
	public boolean isValidMove(int col) {
		if (col >= 0 && col < COLS 		// make sure that col is in the range of the board
				&& !(isFull())		// make sure the board is not full
				&& (board[ROWS-1][col] == EMPTY)) // make sure there is space in that column 
				return true; 
		return false;
	}

	/**
	 * @see Connect4State 
	 */
	public void makeMove(int col) {
		if (isValidMove(col)) { //only make the move if it's valid! 
			playerToMove=1-playerToMove; //increment our number of moves count 
			int row = 0;
			
			// set the tokens in their spots
			while (row < ROWS ) {
				if (board[row][col] == '.') {
					if (getPlayerToMove().equals(player1))
						board[row][col] = 'X';
					if (getPlayerToMove().equals(player2))
						board[row][col] = 'O';
					return;
				}
				row++;
			}
			
			
		}
	}

	/**
	 * @see Connect4State 
	 */
	public boolean isFull() {
		// Go through all the columns and check if their full 
		for (int cols=0; cols < COLS; cols++) 
			if (board[ROWS-1][cols] == '.')
				return false;
		return true;
	}

	/**
	 * @see Connect4State 
	 */
	public boolean isWinner() {
	// figure out what the computer's token is
		char opponentChar=Connect4State.CHECKERS[0];
		char compChar=Connect4State.CHECKERS[1];
		
	//evaluate horizontal victory. 
		for (int col=0; col<=COLS-4; col++){
			for (int row=0; row<ROWS; row++){
				int compCount=0;
				int opponentCount=0;
				
				for(int nextCol=col; nextCol<col+4; nextCol++){
					if (board[row][nextCol]==compChar)
						compCount++;
					if(board[row][nextCol]==opponentChar)
						opponentCount++;
				}
				if(compCount>=4|opponentCount>=4){
					return true;
				}
			}
		}
		// Check vertical victory  
		for (int col=0; col<COLS; col++){
			for (int row=0; row<=ROWS-4; row++){
				int compCount=0;
				int opponentCount=0;
				
				for(int nextRow=row; nextRow<row+4; nextRow++){
					if (board[nextRow][col]==compChar)
						compCount++;
					if(board[nextRow][col]==opponentChar)
						opponentCount++;
				}
				if(compCount>=4|opponentCount>=4)
					return true;
			}
			
		}
		
		
	//evaluate Positive Diagonal victory
		for (int col=0; col<=COLS-4; col++){
			for (int row=0; row<=ROWS-4; row++){
				int compCount=0;
				int opponentCount=0;
				
				for(int nextDiag=0; nextDiag<4; nextDiag++){
					if (board[row+nextDiag][col+nextDiag]==compChar)
						compCount++;
					if(board[row+nextDiag][col+nextDiag]==opponentChar)
						opponentCount++;
				}
				
				if(compCount>=4|opponentCount>=4)
					return true;
			}
			
				
		}
		
		
	//evaluate negative Diagonal Victory
		for (int col=0; col<=COLS-4; col++){
			for (int row=ROWS-1; row>=3; row--){
				int compCount=0;
				int opponentCount=0;
				
				for(int nextDiag=0; nextDiag<4; nextDiag++){
					if (board[row-nextDiag][col+nextDiag]==compChar)
						compCount++;
					if(board[row-nextDiag][col+nextDiag]==opponentChar)
						opponentCount++;
				}
				if(compCount>=4|opponentCount>=4)
					return true;
		}
	}
		
		// if there's no winner, we've reached the end and we return false 
		return false;
	}
	public static int generateScore(int compCount, int opponentCount, int score ){
	if (compCount==0){
		score-= Weights[opponentCount];
	}
	if (opponentCount==0){
		score+= Weights[compCount];
	}
	return score; 
}

	
/** A static evaluation function
 * 
 * @param state, the state of the game 
 * @return an integer "score" for the move. Higher numbers are better scores. 
 */

public int staticEvalFunction() {
	int score=0;
	char [][]board = getBoard();

	// Is the computer the first or second player? Set the appropriate tokens  
	char opponentChar=Connect4State.CHECKERS[1];
	char compChar=Connect4State.CHECKERS[0];
	
	// If player 1 is computer, set it's token to whatever is in CHECKERS[0]
	if(this.getPlayers()[1].getName().contains("Computer")){
	    opponentChar=Connect4State.CHECKERS[1];
	    compChar=Connect4State.CHECKERS[0];
	}
	// If player 2 is computer, set it's token to whatever is in CHECKERS[1]
	if(this.getPlayers()[0].getName().contains("Computer")){
	    opponentChar=Connect4State.CHECKERS[0];
	    compChar=Connect4State.CHECKERS[1];
	}


//evaluate horizontal score. 
	// go through all the columns and rows that can possibly have a 4 in a row 
	for (int col=0; col<=COLS-4; col++){
		for (int row=0; row<ROWS; row++){
			// keep track of how many the computer has, and how many the opponent has
			int compCount=0;
			int opponentCount=0;
			
			// Look at the next 4 spaces in the row and update the counts if we have tokens in those spots 
			for(int nextCol=col; nextCol<col+4; nextCol++){
				if (board[row][nextCol]==compChar)
					compCount++;
				if(board[row][nextCol]==opponentChar)
					opponentCount++;
			}
		// use our counts to update the score for that spot 
		score=generateScore(compCount,opponentCount,score);
			
	}
}
		
	//evaluate vertical score. 
	// go through all the columns and rows that can possibly have a 4 in a row
	for (int col=0; col<COLS; col++){
		for (int row=0; row<=ROWS-4; row++){
			int compCount=0;
			int opponentCount=0;
			
			// Look at the next 4 spaces in the column and update the counts if we have tokens in those spots 
			for(int nextRow=row; nextRow<row+4; nextRow++){
				if (board[nextRow][col]==compChar)
					compCount++;
				if(board[nextRow][col]==opponentChar)
					opponentCount++;
			}
		//System.out.println(compCount+","+opponentCount+","+score);	//TESTING 
		// use our counts to update the score for that spot 
		score=generateScore(compCount,opponentCount,score);	
	}
	}
	
	// evaluate Positive Diagonal score
	// go through all the columns and rows that can possibly have a 4 in a row on a positive diagonal
	for (int col=0; col<=COLS-4; col++){
		for (int row=0; row<=ROWS-4; row++){
			int compCount=0;
			int opponentCount=0;
			
			// Look at the next 4 spaces in the diagonal slots and update the counts if we have tokens in those spots 
			for(int nextDiag=0; nextDiag<4; nextDiag++){
				if (board[row+nextDiag][col+nextDiag]==compChar)
					compCount++;
				if(board[row+nextDiag][col+nextDiag]==opponentChar)
					opponentCount++;
			}
		//System.out.println(compCount+","+opponentCount+","+score);	
		// use our counts to update the score
		score=generateScore(compCount,opponentCount,score);
		
			
	}
	}
	
	// Evaluate negative Diagonal score
	// go through all the columns and rows that can possibly have a 4 in a row on a negative diagonal
		for (int col=0; col<=COLS-4; col++){
			for (int row=ROWS-1; row>=3; row--){
				int compCount=0;
				int opponentCount=0;
				
				// Look at the next 4 spaces in the diagonal slots and update the counts if we have tokens in those spots 
				for(int nextDiag=0; nextDiag<4; nextDiag++){
					if (board[row-nextDiag][col+nextDiag]==compChar)
						compCount++;
					if(board[row-nextDiag][col+nextDiag]==opponentChar)
						opponentCount++;
				}
			// use our counts to update the score
			score=generateScore(compCount,opponentCount,score);	
		}
	}
		
	//Return our final score
	return score;
	}
	


	/** Is the game over? 
	 * Return true if it is, false if not
	 */
	public boolean gameIsOver() {
		if (isWinner())
			return true;
		return false;
	}
	
}
