/**
 * Connect4Move.java
 * 
 * @author Diksha Gautham
 * @author Vineeth Bhuvanagiri
 * A class to store a move in the game connect4, and access it's value, calculated by our static evaluation function
 * Used the "KalahMove" class by Scot Drysdale as reference 
 * 
 */

public class Connect4Move {
	public int value;       // The value of this move, calculated from our static eval function
	public int move;        // The number of the column 

	
	/**A constructor for the move
	 * @param value, calculated by static eval function
	 * @param move, integer value of the column
	 */
	
	public Connect4Move(int value, int move) {
		this.value = value; 
		this.move = move;
	}
}