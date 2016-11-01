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
	public Queen(boolean white) {
		// TODO Auto-generated constructor stub
		this.white = white;
	}

	/* (non-Javadoc)
	 * @see chessItems.ChessPiece#isLegal(int, int, int, int, ChessPiece)
	 */
	@Override
	public boolean isLegal(int fstart, int rstart, int fend, int rend, ChessPiece board[][]){
		// TODO Auto-generated method stub
		int fchange = 0, rchange = 0;
		if(Math.abs(fstart - fend) == Math.abs(rstart - rend)){
			fchange = fstart - fend;
			rchange = rstart - rend;
			
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
		
			if(board[rend][fend] != null && (board[rend][fend].isWhite() == board[rstart][fstart].isWhite())){
				return false;
			}
		}else if(Math.abs(fstart-fend) == 0 || Math.abs(rstart-rend) == 0){//checking for straight movements
			if(board[rend][fend] != null && (board[rend][fend].isWhite() == board[rstart][fstart].isWhite())){
				return false;
			}
			board[rend][fend] = board[rstart][fstart];
			board[rstart][fstart] = null;
			return true;
				
		}else{
			return false;
		}
		board[rend][fend] = board[rstart][fstart];
		board[rstart][fstart] = null;
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
}
