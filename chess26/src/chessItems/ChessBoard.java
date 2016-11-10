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
	private boolean wcheck;
	private boolean bcheck;
	private boolean draw;
	private int[] wking;
	private int[] bking;
	public ChessBoard() {
		// TODO Auto-generated constructor stub
		this.board = new ChessPiece[8][8];
		//White pieces
		
		//Pawns
		/*
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
		*/
		
		
		//testing purposes/////////////////////////
		board[0][3] = new Queen(false);
		board[7][3] = new Queen(true);
		board[0][4] = new King(false);
		board[7][4] = new King(true);
		///////////////////////////////////////////
		
		//white's turn 
		this.white = true; //change to true
		this.wcheck = false;
		this.bcheck = false;
		this.checkmate = false;
		this.draw = false;
		
		//keeps track of king
		this.wking = new int[2];
		this.wking[0] = 7;
		this.wking[1] = 4;
		this.bking = new int[2];
		this.bking[0] = 0;
		this.bking[1] = 4;
	}
	
	public void play(){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String array[] = null, mes = "";
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
					//checks for draw
					if(this.draw){
						if(array[0].compareToIgnoreCase("draw") == 0){
							//end game
							break;
						}else{
							this.draw = false;
						}
					}
					
					if(array.length > 3 || array.length < 2){
						System.out.println("Illegal input");
					}else{
						if(array[0].length() != 2 || array[1].length() != 2){
							System.out.println("Illegal input");
						}else{
							move(array);
						}
					}
				}
				
			} catch (IOException e) {
				System.out.println("IOException: Looks like you fucked up the BufferedReader somehow. Good job.");
			} catch (ArrayIndexOutOfBoundsException r){
				System.out.println("Illegal move, try again EXCEPTION");
				r.printStackTrace();
			} catch (NullPointerException n){
				System.out.println("Illegal move: no chess piece at " + array[0]);
				n.printStackTrace();
			}
		}
	}
	
	private void move(String array[]) throws ArrayIndexOutOfBoundsException, NullPointerException{
		String start = array[0], end = array[1], other;
		
		
		int fstart = start.toLowerCase().charAt(0) - 'a';
		int rstart = 8 - Character.getNumericValue(start.charAt(1));
	
		int fend = end.toLowerCase().charAt(0) - 'a';
		int rend = 8 - Character.getNumericValue(end.charAt(1));
		
		ChessPiece promotion = null;
		
		//checks to see if the piece selected belongs to player
		if(this.white != board[rstart][fstart].isWhite()){
			System.out.println("Illegal move: cannot select piece that does not belong to you.");
			return;
		}
		
		if(this.wcheck || this.bcheck){
			if(!outOfCheck(fstart, rstart, fend, rend)){
				System.out.println("Illegal move: king is in check");
				return;
			}
		}
		
		//handles the third argument: draw or promotion
		if(array.length == 3){
			other = array[2];
			if(other.compareToIgnoreCase("draw") == 0){
				this.draw = true;
			}else if(board[rstart][fstart].toString().charAt(1) == 'p' && ((this.white && rend == 0) ||(!this.white && rend == 7))){
				if(other.compareToIgnoreCase("N") == 0){
					promotion = new Knight(this.white);
				}else if(other.compareToIgnoreCase("Q") == 0){
					promotion = new Queen(this.white);
				}else if(other.compareToIgnoreCase("R") == 0){
					promotion =  new Rook(this.white);
				}else if(other.compareToIgnoreCase("B") == 0){
					promotion = new Bishop(this.white);
				}else{
					System.out.println("Illegal move: third input is invalid.");
					return;
				}
			}else{
				System.out.println("Illegal move: third input is invalid for " + board[rstart][fstart] + ".");
				return;
			}
		}
		
		
		//handles legal move
		if(board[rstart][fstart].isLegal(fstart, rstart, fend, rend, this.board)){
			//handles promotion
			if(board[rend][fend] != null && board[rend][fend].toString().charAt(1) == 'p'){
				
				if((this.white && rend == 0) || (!this.white && rend == 7)){
					if(promotion == null){
						board[rend][fend] = new Queen(this.white);
					}else{
						board[rend][fend] = promotion;
					}
				}
			//keeps track of the kings position. 
			}else if(board[rend][fend] != null && board[rend][fend].toString().charAt(1) == 'K'){
				if(this.white){
					wking[0] = rend;
					wking[1] = fend;
				}else{
					bking[0] = rend;
					bking[1] = fend;
				}
			}
			
			//checks if opponent is in check
			if(this.white){
				this.bcheck = isCheck(bking[1], bking[0], true, this.board);
			}else{
				this.wcheck = isCheck(wking[1], wking[0], false, this.board);
			}
			
			this.white = !this.white;
		}else{
			System.out.println("Illegal move, try again");
		}
	}
	
	private boolean outOfCheck(int fstart, int rstart, int fend, int rend) throws ArrayIndexOutOfBoundsException, NullPointerException{
		
		ChessPiece test[][] = new ChessPiece[8][8];
		int file = 0, rank = 0;
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				test[i][j] = this.board[i][j];
			}
		}
		if(test[rstart][fstart].toString().charAt(1) == 'K'){
			file = fend;
			rank = rend;
		}else{
			if(this.white){
				file = wking[1];
				rank = wking[0];
			}else{
				file = bking[1];
				rank = bking[0];				
			}
		}
		
		if(!test[rstart][fstart].isLegal(fstart, rstart, fend, rend, test)){
			return false;
		}
		
		if(this.white){
			if(isCheck(file, rank, false, test)){
				return false;
			}
		}else{
			if(isCheck(file, rank, true, test)){
				return false;
			}
		}
		return true;
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
	private boolean isCheck(int fend, int rend, boolean w, ChessPiece test[][]){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(test[i][j] !=null){
					if(test[i][j].isWhite() == w){
						if(test[i][j].isCheck(j, i, fend, rend, test)){
							return true;
						}
					//checks for black pieces on black's turn
					}/*else if(!board[i][j].isWhite() && !this.white){
						if(board[i][j].isCheck(j, i, fend, rend, this.board)){
							return true;
						}
					}*/
				}
			}
		}
		return false;
	}
}
