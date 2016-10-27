package chessItems;
import java.lang.StringBuilder;
/**
 * @author Matthew Ya
 * @author Taehee Lee
 */
public class ChessBoard {
	private ChessPiece board[][];
	public ChessBoard() {
		// TODO Auto-generated constructor stub
		this.board = new ChessPiece[8][8];
		//White pieces
		
		//Black pieces
	}
	public String toString(){
		
		StringBuilder mes = new StringBuilder();
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(board[i][j] != null){
					mes.append(board[i][j].toString() + " ");
				}else{
					if(i%2 == 0){
						if(j%2 == 0){
							mes.append("   ");
						}else{
							mes.append("## ");
						}
					}else{
						if(j%2 == 0){
							mes.append("## ");
						}else{
							mes.append("   ");
						}
					}
				}
			}
			mes.append(8-i);
			mes.append("\n");
		}
		mes.append(" a  b  c  d  e  f  g  h\n");
		return mes.toString();
	}
}
