/**
 * 
 */
package chessItems;

/**
 * @author Matthew Ya
 * 
 */
public class King extends ChessPiece {

	public King(boolean white) {
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
			
		/**checks for legal king move in this vicinity.
		 * 				-1,-1	-1,0	-1,1
		 * 				 0,-1	 KING	 0,1
		 * 				 1,-1	 1,0	 1,1
		 */
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
	 * @see chessItems.ChessPiece#isCheck(int, int, int, int, ChessPiece)
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
