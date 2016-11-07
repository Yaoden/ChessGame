/**
 * 
 */
package chessItems;

/**
 * @author Matthew Ya
 * @author Taehee Lee
 */
public interface ChessPiece {
	public boolean isLegal(int fstart, int rstart, int fend, int rend, ChessPiece board[][]);
	public boolean isWhite();
	public String toString();
	public int getMoves();
}
