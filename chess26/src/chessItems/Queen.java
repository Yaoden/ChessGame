/**
 * 
 */
package chessItems;

/**
 * @author Matthew Ya
 * @author Taehee Lee
 */
public class Queen implements ChessPiece {

	/**
	 * 
	 */
	private boolean white;
	private int moves;
	
	public Queen(boolean white) {
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
		int fchange = 0, rchange = 0;
		fchange = fstart - fend;
		rchange = rstart - rend;
		if(Math.abs(fchange) == Math.abs(rchange)){
			//positive/positive array change
			if(rchange < 0 && fchange < 0){
				for(int i = 1; i < Math.abs(rchange); i++){
					if(board[rstart + i][fstart + i] != null){
						return false;
					}
				}
				
			//negative/negative array change
			}else if(rchange > 0 && fchange > 0){
				for(int i = 1; i < rchange; i++){
					if(board[rstart - i][fstart - i] != null){
						return false;
					}
				}
				
			//positive/negative array change
			}else if(rchange < 0 && fchange > 0){
				for(int i = 1; i < Math.abs(rchange); i++){
					if(board[rstart + i][fstart - i] != null){
						return false;
					}
				}
				
			//negative/positive array change
			}else if(rchange > 0 && fchange < 0){
				for(int i = 1; i < Math.abs(rchange); i++){
					if(board[rstart - i][fstart + i] != null){
						return false;
					}
				}
			}
		}else if(fchange == 0){ //checking up and down movements
			if(rchange < 0){
				for(int i = 1; i < Math.abs(rchange); i++){
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
				for(int i = 1; i < Math.abs(fchange); i++){
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
	
		//System.out.println("Queen");
		
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
			return "wQ";
		}
		return "bQ";
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
		
		int fchange = 0, rchange = 0;
		fchange = fstart - fend;
		rchange = rstart - rend;
		if(Math.abs(fchange) == Math.abs(rchange)){
			//positive/positive array change
			if(rchange < 0 && fchange < 0){
				for(int i = 1; i < Math.abs(rchange); i++){
					if(board[rstart + i][fstart + i] != null){
						return false;
					}
				}
				
			//negative/negative array change
			}else if(rchange > 0 && fchange > 0){
				for(int i = 1; i < rchange; i++){
					if(board[rstart - i][fstart - i] != null){
						return false;
					}
				}
				
			//positive/negative array change
			}else if(rchange < 0 && fchange > 0){
				for(int i = 1; i < Math.abs(rchange); i++){
					if(board[rstart + i][fstart - i] != null){
						return false;
					}
				}
				
			//negative/positive array change
			}else if(rchange > 0 && fchange < 0){
				for(int i = 1; i < Math.abs(rchange); i++){
					if(board[rstart - i][fstart + i] != null){
						return false;
					}
				}
			}
		}else if(fchange == 0){ //checking up and down movements
			if(rchange < 0){
				for(int i = 1; i < Math.abs(rchange); i++){
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
				for(int i = 1; i < Math.abs(fchange); i++){
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
		return true;
	}
}
