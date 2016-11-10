package chessItems;
/**
 * @author Matthew Ya
 * @author Taehee Lee
 */
public interface ChessPiece {
	
	/**
	 * Checks to see if move is legal and then moves the piece.
	 * 
	 * <p>
	 * Each Piece that implements this method will have two fiels: boolean white and int moves.
	 * boolean white - determines the color of the piece (true = white, false = black).
	 * int moves - contains the number of moves this piece has made.
	 * <p>
	 * 
	 * @param fstart file from which a piece is being moved
	 * @param rstart rank from which a piece is being moved
	 * @param fend   file to which a piece is being moved
	 * @param rend   rank to which a piece is being moved
	 * @param board  the board where the piece resides on
	 * @return 		 true if the move is valid otherwise false
	 */
	public boolean isLegal(int fstart, int rstart, int fend, int rend, ChessPiece board[][]);
	
	/**
	 * Gets the color of the piece (white or black)
	 * 
	 * @return true if piece is white, false if piece is black.
	 */
	public boolean isWhite();
	
	/**
	 * Creates a string representation of the piece.
	 * <p>
	 * The string representation contains two letters: the first being the initial of the color and the second
	 * being the initial of the piece itself (R for Rook, K for King, etc)
	 * <p>
	 * @return a string containing the initials of the piece.
	 */
	public String toString();
	
	/**
	 * Obtains the number of moves this piece has made.
	 * 
	 * @return the number of moves this piece has made.
	 */
	public int getMoves();
	
	/**
	 * Checks to see if the selected piece can take opponent's king.
	 * <p>
	 * This method is mainly used in the ChessBoard's isCheck() function. This method internally is somewhat the same as isLegal()
	 * but has a completely different function since it is being used by the isCheck() method in the ChessBoard class. 
	 * <p>
	 * 
	 * @param fstart file from which a piece is being moved
	 * @param rstart rank from which a piece is being moved
	 * @param fend   file where the king is located
	 * @param rend   rank where the king is located
	 * @param board  the board where the pieces reside on
	 * @return 		 true if king is check, false if not.
	 */
	public boolean isCheck(int fstart, int rstart, int fend, int rend, ChessPiece board[][]);
}
