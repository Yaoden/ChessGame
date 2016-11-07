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
	private int moves;
	
	public Rook(boolean white) {
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
		int fchange = 0, rchange = 0;
		fchange = fstart - fend;
		rchange = rstart - rend;
		if(fchange == 0){ //checking up and down movements
			if(rchange < 0){
				for(int i = 1; i < rchange; i++){
					if(board[rstart + i][fstart] != null){
						return false;
					}
				}
			}else{
				for(int i = 1; i < rchange; i++){
					if(board[rstart - i][fstart] != null){
						return false;
					}
				}
			}
		}else if(rchange == 0){ //checking side to side movements
			if(fchange < 0){
				for(int i = 1; i < fchange; i++){
					if(board[rstart][fstart + i] != null){
						return false;
					}
				}
			}else{
				for(int i = 1; i < fchange; i++){
					if(board[rstart][fstart - i] != null){
						return false;
					}
				}
			}
		}else{
			return false;
		}
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
			return "wR";
		}
		return "bR";
	}
	/* (non-Javadoc)
	 * @see chessItems.ChessPiece#getMoves()
	 */
	@Override
	public int getMoves(){
		return this.moves;
	}
}
