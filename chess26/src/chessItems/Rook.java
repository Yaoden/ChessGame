/**
 * 
 */
package chessItems;

/**
 * @author Matthew Ya
 * @author Taehee Lee
 */
public class Rook implements ChessPiece {

	/**
	 * 
	 */
	private boolean white;
	private boolean castling;
	
	public Rook(boolean white) {
		// TODO Auto-generated constructor stub
		this.white = white;
		this.castling = true;
	}

	/* (non-Javadoc)
	 * @see chessItems.ChessPiece#isLegal(int, int, int, int, ChessPiece)
	 */
	@Override
	public boolean isLegal(int fstart, int rstart, int fend, int rend, ChessPiece board[][]){
		// TODO Auto-generated method stub
	if(board[rend][fend] != null && (board[rend][fend].isWhite() == board[rstart][fstart].isWhite())){
		return false;
	}else{
		if(fstart == fend){
			board[rend][fend] = board[rstart][fstart];
			board[rstart][fstart] = null;
			return true;
		}else if(rstart == rend){
			board[rend][fend] = board[rstart][fstart];
			board[rstart][fstart] = null;
			return true;
		}
	}
		return false;
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
			return "wR";
		}
		return "bR";
	}
}
