import java.util.Scanner;

/** A textual view for the Connect 4 game. 
 * Prints out a Connect 4 board displaying the state of the game 
 * @Implements Connect4View @see this for detailed method descriptions 
 * @author Diksha Gautham 
 * @author Vineeth Bhuvanagiri
 *
 */


public class Connect4ViewTextual implements Connect4View {

	/** 
	 * Constructor to create a view
	 */
	public Connect4ViewTextual() {
	}
	
	/** 
	 * @see Connect4View
	 */
	public void display(Connect4State state) {
		for (int row=state.ROWS-1; row >=0 ; row--) {
			for (int col=0; col< state.COLS; col++) {
				System.out.print(state.getBoard()[row][col]);
			}
			System.out.print("\n");
		}	
	}
	
	/** 
	 * @see Connect4View
	 */
	public int getUserMove(Connect4State state) {
		System.out.println("Enter a column to make your move");
		int move;                  // The player's move
		Scanner input = new Scanner(System.in);
		move = input.nextInt();
		move=move-1;
		
	  return move; 
	}

	/** 
	 * @see Connect4View
	 */
	public void reportMove(int chosenMove, String name) {
		System.out.println(name + "has placed a piece in column " + chosenMove);
	}

	/** 
	 * @see Connect4View
	 */
	public int getIntAnswer(String question) {
		// TODO Auto-generated method stub
		return 0;
	}

	/** 
	 * @see Connect4View
	 */
	public void reportToUser(String message) {
		System.out.println(message);
	}
	
	/** 
	 * @see Connect4View
	 */
	public String getAnswer(String question) {
		System.out.println(question);
	  Scanner input = new Scanner(System.in);
	  String answer = input.nextLine();
	  input.close();
		return answer;
	}
	
}