public class Pawn extends Piece {
    private boolean hasntMoved;
    public Pawn(Point position, boolean side){
        super(position, side);
        hasntMoved = true;
    }
    @Override
    public boolean isValidMove(Piece[][] board, Point goal){
        int direction = super.getSide() ? 1 : -1;
        Point position = super.getPosition();
        if (canMoveTwoSpaces(board, goal, position, direction)){
            hasntMoved = false;
            return true;
        }
        if (!isMovingForward(goal, position, direction))
            return false;
        switch(Math.abs(goal.getCol() - position.getCol())){
            case 0:
                return board[goal.getRow()][goal.getCol()] == null;
            case 1:
                return targetsNullOrEnemy(board, goal, position) || isValidEnpessant(board, goal, position);
            default:
                return false;
        }
    }
    private boolean isValidEnpessant(Piece[][] board, Point goal, Point position){
        return board[position.getRow()][goal.getCol()].getSide() != this.getSide() && board[position.getRow()][goal.getCol()] instanceof Pawn;
    }
    private boolean isMovingForward(Point goal, Point position, int direction){
        return goal.getRow() == position.getRow() + direction;
    }
    private boolean canMoveTwoSpaces(Piece[][] board, Point goal, Point position, int direction){
        return board[goal.getRow()][goal.getCol()] == null && hasntMoved && goal.getCol() == position.getCol() &&
               board[goal.getRow() + direction][goal.getCol()] == null && goal.getRow() == position.getRow() + 2 * direction;
    }
    @Override
    public String toString(){
        return super.getSide() ? "\u265F": "\u2659";
    }
}
