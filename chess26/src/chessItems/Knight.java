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
	private boolean firstMove;
	public Knight(boolean white) {
		// TODO Auto-generated constructor stub
		this.white = white;
		this.firstMove = true;
	}

	/* (non-Javadoc)
	 * @see chessItems.ChessPiece#isLegal(int, int, int, int, ChessPiece)
	 */
	@Override
	public boolean isLegal(int fstart, int rstart, int fend, int rend, ChessPiece board[][]){
		// TODO Auto-generated method stub
		if(Math.abs(rstart-rend)== 2  && Math.abs(fstart-fend)== 1 || Math.abs(rstart-rend)== 1  && Math.abs(fstart-fend)== 2){
			if(board[rend][fend].isWhite()){
				return false;
			}
			board[rend][fend] = board[rstart][fstart];
			board[rstart][fstart] = null;
			
		
		}else{
		return false;
		}
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
}
