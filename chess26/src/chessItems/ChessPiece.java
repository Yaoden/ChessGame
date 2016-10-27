/**
 * 
 */
package chessItems;

/**
 * @author Matthew Ya
 * @author Taehee Lee
 */
public interface ChessPiece {
	public boolean isLegal(int start, int end);
	public boolean isWhite();
	public String toString();
}
