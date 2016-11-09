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
		
		//checks for castling
		if((Math.abs(fstart - fend) == 2) && (Math.abs(rstart - rend) == 0) && (this.moves == 0)){
			//white castling
			if(this.white && ((board[7][0] != null && board[7][0].toString().charAt(1) == 'R' && board[7][0].getMoves() == 0) || (board[7][7] != null && board[7][7].toString().charAt(1) == 'R' && board[7][7].getMoves() == 0))){
				
				//short castling
				if(fstart < fend){
					for(int i = fstart + 1; i < 7; i++){
						if(board[rstart][i] != null){
							return false;
						}
					}
					board[7][6] = board[7][4];
					board[7][5] = board[7][7];
					board[7][7] = null;
					board[7][4] = null;
				//long castling
				}else{
					for(int i = fstart - 1; i > 0; i--){
						if(board[rstart][i] != null){
							return false;
						}
					}
					board[7][2] = board[7][4];
					board[7][3] = board[7][0];
					board[7][4] = null;
					board[7][0] = null;
				}
			//black castling
			}else if(!this.white && ((board[0][0] != null && board[0][0].toString().charAt(1) == 'R' && board[0][0].getMoves() == 0) || (board[0][7] != null && board[0][7].toString().charAt(1) == 'R' && board[0][7].getMoves() == 0))){
				//short castling
				if(fstart < fend){
					for(int i = fstart + 1; i < 7; i++){
						if(board[rstart][i] != null){
							return false;
						}
					}
					board[0][6] = board[0][4];
					board[0][5] = board[0][7];
					board[0][7] = null;
					board[0][4] = null;
				//long castling
				}else{
					for(int i = fstart - 1; i > 0; i--){
						if(board[rstart][i] != null){
							return false;
						}
					}
					board[0][2] = board[0][4];
					board[0][3] = board[0][0];
					board[0][4] = null;
					board[0][0] = null;
				}
			}else{
				return false;
			}
			
			
			
			return true;
			
		//checks for legal king move
		}else if(Math.abs(fstart - fend) > 1 || Math.abs(rstart - rend) > 1){
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
	
	/* (non-Javadoc)
	 * @see chessItems.ChessPiece#isCheck(int, int)
	 */
	@Override
	public boolean isCheck(int fstart, int rstart, int fend, int rend, ChessPiece board[][]) {
		// TODO Auto-generated method stub
		if(Math.abs(fstart - fend) > 1 || Math.abs(rstart - rend) > 1){
			return false;
		}
		return true;
	}
}
