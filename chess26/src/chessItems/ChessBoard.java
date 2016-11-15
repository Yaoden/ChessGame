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
	
	/**
	 * This determines whose turn it is. True for white and false for black.
	 */
	private boolean white;
	
	/**
	 * This determines if white's king is check.
	 */
	private boolean wcheck;
	
	/**
	 * This determines if black's king is check.
	 */
	private boolean bcheck;
	
	/**
	 * True if a player calls "draw?" 
	 */
	private boolean draw;
	
	/**
	 * Contains the position of the white king
	 * array[0] - rank of white king
	 * array[1] - file of white king
	 */
	private int[] wking;
	
	/**
	 * Contains the position of the white king
	 * array[0] - rank of black king
	 * array[1] - file of black king
	 */
	private int[] bking;
	
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
		this.wcheck = false;
		this.bcheck = false;
		this.draw = false;
		
		//keeps track of king
		
		//white king starts at 7,4
		this.wking = new int[2];
		this.wking[0] = 7;
		this.wking[1] = 4;
		
		//black king starts at 0,4
		this.bking = new int[2];
		this.bking[0] = 0;
		this.bking[1] = 4;
	}
	/**
	 * User calls this method to start and play the game.
	 * <p>
	 * The user interaction is mainly in this method (User Layer). The user is asked for an input and
	 * the method goes through a series of checks.
	 * <p>
	 * <p>
	 * Basic checks for empty input and "resign"
	 * 1. If the input is empty, do not attempt to make a move.
	 * 2. If the input is "resign", end the game.
	 * <p>
	 * <p>
	 * Procedure for moves
	 * 1. The input from user is split into array.
	 * 2. Checks for draw: If the input is draw and the class variable draw is true then the game ends.
	 * 3. Checks the length of the array: If the array is not between 2 and 3 then the input is illegal.
	 * 4. Checks the length of the item in array[0] and array[1]: If the length of both items are not 2 then the input is illegal.
	 * 5. If the move is legal and the game is not a draw, then the input is passed in the move() function.
	 * <p>
	 */
	public void play(){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String array[] = null, mes = "";
		while(!isCheckMate()){
			System.out.println(toString());
			
			if(this.white){
				if(this.wcheck){
					System.out.println("White King is in Check");
				}
				System.out.print("White's move: ");
			}else{
				if(this.bcheck){
					System.out.println("Black King is in Check");
				}
				System.out.print("Black's move: ");
			}
			
			try {
				
				
				mes = reader.readLine();
				if(mes.isEmpty()){
					System.out.println("Error: Empty input");
				}else if(mes.compareToIgnoreCase("resign") == 0){
					break;
				}else{
					/*1*/
					array = mes.split(" ");	
					
					/*2*/
					if(this.draw){
						if(array[0].compareToIgnoreCase("draw") == 0){
							break;
						}
					}
					
					/*3*/
					if(array.length > 3 || array.length < 2){
						System.out.println("Illegal input");
					}else{
						
						/*4*/
						if(array[0].length() != 2 || array[1].length() != 2){
							System.out.println("Illegal input");
						}else{
							/*5*/
							move(array);
						}
					}
				}
				
			} catch (IOException e) {
				System.out.println("IOException: Looks like you fucked up the BufferedReader somehow. Good job.");
			} catch (ArrayIndexOutOfBoundsException r){
				System.out.println("Illegal move, try again EXCEPTION");
			} catch (NullPointerException n){
				System.out.println("Illegal move: no chess piece at " + array[0]);
			}
		}
	}
	/**
	 * 
	 * 
	 * 
	 * Procedure:
	 * 1. checks if selected piece belongs to that player. If it doesn't then the move is illegal.
	 * 2. checks if the king is attempting to castle in, through, or out of check.
	 * 3. If player is in check, the outOfCheck() method checks if the input removes player from check. If not then the move is illegal.
	 * 4. Handles draw and promotion if array is size 3. If the input is invalid then the move is illegal.
	 * 5. Checks to see if the move is legal by calling the isLegal() method on the selected chess piece. If not then the move is illegal. 
	 * 
	 * @param array								contains the input of the chess piece move. The array also contains "draw?" or the letter indicating promotion
	 * @throws ArrayIndexOutOfBoundsException	is thrown if the input is not a legal space on the ChessBoard
	 * @throws NullPointerException				is thrown if the selected space has no ChessPiece
	 */
	private void move(String array[]) throws ArrayIndexOutOfBoundsException, NullPointerException{
		String start = array[0]; 
		String end = array[1];
		String other;
		
		//converts values into the corresponding values in the 2-d array.
		int fstart = start.toLowerCase().charAt(0) - 'a';
		int rstart = 8 - Character.getNumericValue(start.charAt(1));
	
		int fend = end.toLowerCase().charAt(0) - 'a';
		int rend = 8 - Character.getNumericValue(end.charAt(1));
		
		ChessPiece promotion = null;
		
		/* 1. 
		 * checks to see if the piece selected belongs to player 
		 */
		if(this.white != board[rstart][fstart].isWhite()){
			System.out.println("Illegal move: cannot select piece that does not belong to you.");
			return;
		}
		
		/* 2.
		 * checks to see if king is attempting to castle in,through, or out of check 
		 */
		if((this.board[rstart][fstart].toString().charAt(1) == 'K') && (this.board[rstart][fstart].getMoves() == 0) && (Math.abs(fstart-fend) == 2)){
	
			//this checks if king is trying to castle when in check
			if((this.wcheck && this.white) || (this.bcheck && !this.white)){
				System.out.println("Illegal move, try again");
				return;
			}else{
				//this checks if king is trying to castle into check
				if(!outOfCheck(fstart, rstart, fend, rend)){
					System.out.println("Illegal move, try again");
					return;
				}
				
				//checks if king is trying to castle through check
				//short castling
				if(fstart < fend){
					for(int i = fstart + 1; i < 7; i++){
						if(board[rstart][i] == null){
							if(isCheck(i, rstart, !this.white, this.board)){
								System.out.println("Illegal move, try again");
								return;
							}
						}else{
							break;
						}
					}
				//long castling
				}else{
					for(int i = fstart - 1; i > 0; i--){
						if(board[rstart][i] == null){
							if(isCheck(i, rstart, !this.white, this.board)){
								System.out.println("Illegal move, try again");
								return;
							}
						}else{
							break;
						}
					}
				}
			}
		}
		/* 3.
		 * If the current player check, then check to see if the moves will uncheck current player.
		 */
		if((this.wcheck && this.white) || (this.bcheck && !this.white)){
			if(!outOfCheck(fstart, rstart, fend, rend)){
				System.out.println("Illegal move: king is in check");
				return;
			}else{
				if(this.white){
					this.wcheck = false;
				}else{
					this.bcheck = false;
				}
			}
		}
		
		//At this point, we know the move is legal and the player does not want to call draw.
		if(this.draw){
			this.draw = false;
		}
		
		/* 4.
		 * handles the third argument: draw or promotion
		 */
		if(array.length == 3){
			other = array[2];
			if(other.compareToIgnoreCase("draw?") == 0){
				this.draw = true;
			}else if(this.board[rstart][fstart].toString().charAt(1) == 'p' && ((this.white && rend == 0) ||(!this.white && rend == 7))){
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
				System.out.println("Illegal move: third input is invalid for " + this.board[rstart][fstart] + ".");
				return;
			}
		}
		
		
		/* 5.
		 * handles legal move
		 */
		if(this.board[rstart][fstart].isLegal(fstart, rstart, fend, rend, this.board)){
			
			//handles promotion
			if(this.board[rend][fend] != null && this.board[rend][fend].toString().charAt(1) == 'p'){
				
				if((this.white && rend == 0) || (!this.white && rend == 7)){
					if(promotion == null){
						board[rend][fend] = new Queen(this.white);
					}else{
						board[rend][fend] = promotion;
					}
				}
			//keeps track of the kings position. 
			}else if(this.board[rend][fend] != null && this.board[rend][fend].toString().charAt(1) == 'K'){
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
	/**
	 * Checks to see if the move puts the player's king outside of check. This method assumes that the player is in check.
	 * 
	 * <p>
	 * Without making a move on the actual board, I replicated the setup of the board onto a new board called test.
	 * On test, I would check to see if the move is legal and make the actual move. Then, I would check to see if 
	 * the player's king on the board test is in check. If the player's king is still in check then return false. 
	 * Otherwise, return true.
	 * <p>
	 * 
	 * 
	 * @param fstart file from which a piece is being moved
	 * @param rstart rank from which a piece is being moved
	 * @param fend   file to which a piece is being moved
	 * @param rend   rank to which a piece is being moved
	 * @return		false if the move does not put the player's king out of check. True if it does.
	 * @throws ArrayIndexOutOfBoundsException	if the player selects a piece outside the board or moves a piece outside the board.
	 * @throws NullPointerException				if the plater selects a place on the board that does not contain a piece. 
	 */
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
		if(isCheck(file, rank, !this.white, test)){
			return false;
		}
		return true;
	}
	
	/**
	 * Prints out the representation of the board with all the pieces that exist on the board.
	 */
	public String toString(){
		
		StringBuilder mes = new StringBuilder();
		mes.append("\n");
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(this.board[i][j] != null){
					mes.append(this.board[i][j].toString() + " ");
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
	
	/**
	 * Checks to see if the king on the given board is in check.
	 * 
	 * <p>
	 * This method loops through the entire board and checks to see if the given file/rank of the king
	 * is in check.
	 * <p>
	 * 
	 * @param fend	file of where the king is located.
	 * @param rend  rank of where the king is located.
	 * @param w		boolean value that determines which color pieces to check. True if white and false if black.
	 * @param test  the board where the king resides on.
	 * @return		true if the king is in check.
	 */
	private boolean isCheck(int fend, int rend, boolean w, ChessPiece test[][]){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(test[i][j] !=null){
					if(test[i][j].isWhite() == w){
						if(test[i][j].isCheck(j, i, fend, rend, test)){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	/**
	 * Checks if the player's king is in checkmate.
	 * 
	 * <p>
	 * This method loops through every possible position the king can go to.
	 * An example of this would be:
	 * 				-1,-1	-1,0	-1,1
	 * 				 0,-1	 KING	 0,1
	 * 				 1,-1	 1,0	 1,1
	 * If that position is within the board and null, then the method calls the isCheck() method with the position as the argument.
	 * If one of those positions return false, then we know that the king is not in checkmate because I can move to a open square.
	 * <p>
	 * 
	 * @return true 	if the king is checkmate
	 */
	private boolean isCheckMate(){

		int file = 0, rank = 0;
		
		if((this.white && this.wcheck) || (!this.white && this.bcheck)){
			
			if(this.white){
				file = wking[1];
				rank = wking[0];
			}else{
				file = bking[1];
				rank = bking[0];				
			}
			
			for(int i = -1; i <= 1 ; i++){
				for(int j = -1; j <= 1; j++){
					if((file + j >= 0 && file + j < 8) && (rank + i >= 0 && rank + i < 8)){
						if(this.board[rank+i][file+j] == null || (this.board[rank+i][file+j] != null && (this.board[rank+i][file+j].isWhite() != this.board[rank][file].isWhite()))){
							if(this.white){
								if(!isCheck(file+j, rank+i, !this.white, this.board)){
									return false;
								}
							}else{
								if(!isCheck(file+j, rank+i, !this.white, this.board)){
									return false;
								}
							}
						}
					}
				}
			}
		}else{
			return false;
		}
		if(this.white){
			System.out.println("Checkmate: Black wins");
		}else{
			System.out.println("Checkmate: White wins");
		}
		return true;
	}
}
