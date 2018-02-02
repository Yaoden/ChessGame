/**
 * 
 */
package chessItems;

/**
 * @author Matthew Ya
 * @author Taehee Lee
 */
public class Knight extends ChessPiece {

	public Knight(boolean white) {
		// TODO Auto-generated constructor stub
		this.white = white;
		this.moves = 0;
		
	}

	/* (non-Javadoc)
	 * @see chessItems.ChessPiece#isLegal(int, int, int, int, ChessPiece)
	 */
	@Override
	public boolean isLegal(int fstart, int rstart, int fend, int rend, ChessPiece board[][]) throws ArrayIndexOutOfBoundsException{
		// TODO Auto-generated method stub
		
		//legal moves for a knight in L shape
		if(Math.abs(rstart-rend)== 2  && Math.abs(fstart-fend)== 1 || Math.abs(rstart-rend)== 1  && Math.abs(fstart-fend)== 2){
			//Makes sure the knight does not attempt to take a piece of the same color.
			if(board[rend][fend] != null && (board[rend][fend].isWhite() == board[rstart][fstart].isWhite())){
				return false;
			}
		}else{
			return false;
		}
		
		board[rend][fend] = board[rstart][fstart];
		board[rstart][fstart] = null;
		moves++;
		return true;
	}

	
	/* (non-Javadoc)
	 * @see chessItems.ChessPiece#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if(this.white){
			return "wN";
		}
		return "bN";
	}
	
	
	/* (non-Javadoc)
	 * @see chessItems.ChessPiece#isCheck(int, int, int, int, ChessPiece)
	 */
	@Override
	public boolean isCheck(int fstart, int rstart, int fend, int rend, ChessPiece board[][]) {
		// TODO Auto-generated method stub
		if(Math.abs(rstart-rend)== 2  && Math.abs(fstart-fend)== 1 || Math.abs(rstart-rend)== 1  && Math.abs(fstart-fend)== 2){
			return true;
		}
		return false;
	}
}
