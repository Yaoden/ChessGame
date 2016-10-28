package chessItems;
import java.lang.StringBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * @author Matthew Ya
 * @author Taehee Lee
 */
public class ChessBoard {
	private ChessPiece board[][];
	
	//used to indicate if it's white's turn or black's turn
	private boolean white;
	private boolean checkmate;
	private boolean check;
	public ChessBoard() {
		// TODO Auto-generated constructor stub
		this.board = new ChessPiece[8][8];
		//White pieces
		
		//Pawns
		board[6][0] = new Pawn(true);
		board[6][1] = new Pawn(true);
		board[6][2] = new Pawn(true);
		board[6][3] = new Pawn(true);
		board[6][4] = new Pawn(true);
		board[6][5] = new Pawn(true);
		board[6][6] = new Pawn(true);
		board[6][7] = new Pawn(true);
		
		//Other Pieces
		board[7][0] = new Rook(true);
		board[7][1] = new Knight(true);
		board[7][2] = new Bishop(true);
		board[7][3] = new Queen(true);
		board[7][4] = new King(true);
		board[7][5] = new Bishop(true);
		board[7][6] = new Knight(true);
		board[7][7] = new Rook(true);
		
		//Black pieces
		
		//Pawns	
		board[1][0] = new Pawn(false);
		board[1][1] = new Pawn(false);
		board[1][2] = new Pawn(false);
		board[1][3] = new Pawn(false);
		board[1][4] = new Pawn(false);
		board[1][5] = new Pawn(false);
		board[1][6] = new Pawn(false);
		board[1][7] = new Pawn(false);
		
		//Other Pieces
		board[0][0] = new Rook(false);
		board[0][1] = new Knight(false);
		board[0][2] = new Bishop(false);
		board[0][3] = new Queen(false);
		board[0][4] = new King(false);
		board[0][5] = new Bishop(false);
		board[0][6] = new Knight(false);
		board[0][7] = new Rook(false);
		
		//white's turn 
		this.white = true;
		this.check = false;
		this.checkmate = false;
	}
	
	public void play(){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String array[], mes = "";
		while(!this.checkmate){
			System.out.println(toString());
			if(this.white){
				System.out.print("White's move: ");
			}else{
				System.out.print("Black's move: ");
			}
			
			try {
				
				
				mes = reader.readLine();
				if(mes.isEmpty()){
					System.out.println("Error: Empty input");
				}else if(mes.compareToIgnoreCase("resign") == 0){
					checkmate = true;
					if(this.white){
						System.out.println("White resigns.\nBlack wins.");
					}else{
						System.out.println("Black resigns.\nWhite wins.");
					}
				}else{
					array = mes.split(" ");	
					if(array.length > 3 || array.length < 2){
						System.out.println("Illegal input 1");
					}else{
						if(array[0].length() != 2 || array[1].length() != 2){
							System.out.println("Illegal input 2");
						}else if(array.length == 3 && !(array[2].compareToIgnoreCase("N") == 0 || array[2].compareToIgnoreCase("Q") == 0|| array[2].compareToIgnoreCase("R") == 0 || array[2].compareToIgnoreCase("B") == 0 || array[2].compareToIgnoreCase("draw") == 0)){
							System.out.println("Illegal input 3");
						}else{
							move(array);
						}
					}
				}
				
			} catch (IOException e) {
				System.out.println("IOException: Looks like you fucked up the BufferedReader somehow. Good job.");
			} catch (ArrayIndexOutOfBoundsException r){
				System.out.println("Illegal move, try again");
			}
		}
	}
	
	private void move(String array[]) throws ArrayIndexOutOfBoundsException{
		String start = array[0], end = array[1], other;
		
		
		int fstart = start.toLowerCase().charAt(0) - 'a';
		int rstart = 8 - Character.getNumericValue(start.charAt(1));
	
		int fend = end.toLowerCase().charAt(0) - 'a';
		int rend = 8 - Character.getNumericValue(end.charAt(1));
		
		if(array.length == 3){
			other = array[2];
		}
		
		if(this.white != board[rstart][fstart].isWhite()){
			System.out.println("Illegal move, try again");
		}else if(!board[rstart][fstart].isLegal(fstart, rstart, fend, rend, this.board)){
			System.out.println("Illegal move, try again");
		}
	}
	public String toString(){
		
		StringBuilder mes = new StringBuilder();
		mes.append("\n");
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
