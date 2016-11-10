/**
 * 
 */
package chessItems;

/**
 * @author Matthew Ya
 * @author Taehee Lee
 */
public class Knight implements ChessPiece {

	/**
	 * 
	 */
	private boolean white;
	private int moves;
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
		if(Math.abs(rstart-rend)== 2  && Math.abs(fstart-fend)== 1 || Math.abs(rstart-rend)== 1  && Math.abs(fstart-fend)== 2){//legal moves for a knight in L shape
			if(board[rend][fend] != null && board[rend][fend].isWhite()){//Makes sure the piece where it ends up is not white
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
			return "wN";
		}
		return "bN";
	}
	
	/* (non-Javadoc)
	 * @see chessItems.ChessPiece#getMoves()
	 */
	@Override
	public int getMoves(){
		return this.moves;
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
