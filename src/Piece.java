public class Piece {
    protected static final int BOARD_SIZE = 8;
    private Point position;
    private boolean side;
    public Piece(Point position, boolean side){
        this.position = position;
        this.side = side;
    }
    public Point getPosition(){
        return this.position;
    }
    public boolean getSide(){
        return this.side;
    }
    protected boolean targetsNullOrEnemy(Piece[][] board, Point goal, Point position){
        return board[goal.getRow()][goal.getCol()] == null || board[goal.getRow()][goal.getCol()] != null && board[goal.getRow()][goal.getCol()].getSide() != this.getSide();
    }
    public static boolean isWithinBoard(Point position){
        return position.getRow() >= 0 && position.getRow() < BOARD_SIZE &&
               position.getCol() >= 0 && position.getCol() < BOARD_SIZE;
    }
    public boolean isValidMove(Piece[][] board, Point goal){
        return false;
    }
    @Override
    public String toString(){
        return " ";
    }
}
