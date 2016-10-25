/**
 * 
 */
package chessItems;

/**
 * @author matth
 *
 */
public class King implements ChessPiece {

	/**
	 * 
	 */
	private boolean white;
	private boolean castling;
	
	public King() {
		// TODO Auto-generated constructor stub
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
		return "";
	}
}
