import java.util.Scanner;

public class Board {
    private final int BOARD_SIZE = 8, FIRST_CAPITAL_LETTER = 65;
    private final boolean WHITE = true;
    private Piece[][] board;
    private boolean currentSide, isRunning;
    private Scanner scan;
    public Board(){
        board = new Piece[BOARD_SIZE][BOARD_SIZE];
        initializeBoard();
        scan = new Scanner(System.in);
        currentSide = WHITE;
        isRunning = true;
    }
    public void play(){
        while(isRunning) {
            display();
            playTurn();
            currentSide = !currentSide;
            isRunning = !hasWon();
        }
    }
    private void display(){
        for (int i = BOARD_SIZE - 1; i >= 0; i--){
            System.out.print((i + 1) + " ");
            for (int j = 0; j < BOARD_SIZE; j++){
                System.out.print((board[i][j] == null ? " " : board[i][j]) + " ");
            }
            System.out.println();
        }
        System.out.println("  A B C D E F G H");
    }
    private void playTurn(){
        Point start = getCoordinate("start");
        Piece piece = board[start.getRow()][start.getCol()];
        if (piece == null || piece.getSide() != currentSide){
            System.out.println("That's not your piece, lets try again.");
            display();
            playTurn();
            return;
        }
        Point end = getCoordinate("end");
        if (Piece.isWithinBoard(end) && piece.isValidMove(board, end)){
            board[end.getRow()][end.getCol()] = piece;
            board[start.getRow()][start.getCol()] = null;
        }
        else {
            System.out.println("Invalid move, please try again.");
            display();
            playTurn();
        }
    }
    private Point getCoordinate(String typeOfPoint) throws IllegalArgumentException {
        System.out.println("Enter the " + typeOfPoint + " point");
        String coordinate = scan.nextLine().toUpperCase();
        Point point = new Point(
            Integer.parseInt(coordinate.substring(1, 2)) - 1, 
            Integer.valueOf(coordinate.charAt(0)) - FIRST_CAPITAL_LETTER
        );
        if (coordinate.length() < 2 || !Piece.isWithinBoard(point))
            throw new IllegalArgumentException();
        return point;
    }
    private boolean hasWon(){
        int whiteCount = 0, 
            blackCount = 0;
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                Piece piece = board[i][j];
                if (piece == null)
                    continue;
                if (piece instanceof King){
                    if (piece.getSide())
                        whiteCount++;
                    else
                        blackCount++;
                }
            }
        }
        return whiteCount == 0 || blackCount == 0;
    }
    private void initializeBoard(){
        for (int i = 0; i < BOARD_SIZE; i++){
            if (i < BOARD_SIZE - 2 && i > 1)
                continue;
            boolean player = i < 2; // True is white : False is black
            for (int j = 0; j < BOARD_SIZE; j++){
                Point position = new Point(i, j);
                if (i == 1 || i == BOARD_SIZE - 2){
                    board[i][j] = new Pawn(position, player);
                    continue;
                }
                switch(j) {
                    case 0, BOARD_SIZE - 1 ->
                        board[i][j] = new Rook(position, player);
                    case 1, BOARD_SIZE - 2 ->
                        board[i][j] = new Knight(position, player);
                    case 2, BOARD_SIZE - 3 ->
                        board[i][j] = new Bishop(position, player);
                    case 3 ->
                        board[i][j] = player == WHITE ? new Queen(position, player) : new King(position, player);
                    case 4 ->
                        board[i][j] = player == WHITE ? new King(position, player) : new Queen(position, player);
                }
            }
        }
    }
}
