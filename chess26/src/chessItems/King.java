/**
 * 
 */
package chessItems;

/**
 * @author Matthew Ya
 * @author Taehee Lee
 */
public class King implements ChessPiece {

	/**
	 * 
	 */
	private boolean white;
	private boolean castling;
	private int moves;
	public King(boolean white) {
		// TODO Auto-generated constructor stub
		this.white = white;
		this.castling = true;
		this.moves = 0;
	}

	/* (non-Javadoc)
	 * @see chessItems.ChessPiece#isLegal(int, int, int, int, ChessPiece)
	 */
	@Override
	public boolean isLegal(int fstart, int rstart, int fend, int rend, ChessPiece board[][]) throws ArrayIndexOutOfBoundsException{
		// TODO Auto-generated method stub
		
		//checks for legal king move
		if(Math.abs(fstart - fend) > 1 || Math.abs(rstart - rend) > 1){
			return false;
		}
		
		//checks to see if the end point has a piece and if the piece is a player's own piece.
		if(board[rend][fend] != null && (board[rend][fend].isWhite() == board[rstart][fstart].isWhite())){
			return false;
		}
		
		board[rend][fend] = board[rstart][fstart];
		board[rstart][fstart] = null;
		moves++;
		return true;
	}

	/* (non-Javadoc)
	 * @see chessItems.ChessPiece#isWhite()
	 */
	@Override
	public boolean isWhite() {
		// TODO Auto-generated method stub
		return this.white;
	}
	
	/* (non-Javadoc)
	 * @see chessItems.ChessPiece#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if(this.white){
			return "wK";
		}
		return "bK";
	}
	/* (non-Javadoc)
	 * @see chessItems.ChessPiece#getMoves()
	 */
	@Override
	public int getMoves(){
		return this.moves;
	}
}
