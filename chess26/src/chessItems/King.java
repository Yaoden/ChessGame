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
	
	public King(boolean white) {
		// TODO Auto-generated constructor stub
		this.white = white;
		this.castling = true;
	}

	/* (non-Javadoc)
	 * @see chessItems.ChessPiece#isLegal(int, int)
	 */
	@Override
	public boolean isLegal(int start, int end) {
		// TODO Auto-generated method stub
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
}
