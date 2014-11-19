/** A Human Player to play Connect 4
 * @extends Player
 * @author Diksha Gautham, Vineeth B. 
 *
 */

public class HumanPlayer extends Player {
	
	public HumanPlayer(String name) {
		super(name);
	}

	/** 
	 * @Returns the move the player inputs, column number
	 * 
	 */
	
	public int getMove(Connect4State state, Connect4View view) {
		int move = view.getUserMove(state);
		System.out.println("\n");
		System.out.println(state.getPlayerToMove().getName() + " has placed a piece in column "+ (move+1));
		return move;
	}
	
}