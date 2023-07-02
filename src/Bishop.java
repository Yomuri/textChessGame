public class Bishop extends Queen {
    public Bishop(Point position, boolean side){
        super(position, side);
    }
    @Override
    public boolean isValidMove(Piece[][] board, Point goal){
        Point position = super.getPosition();
        if (!isAscending(position, goal) && !isDescending(position, goal))
            return false;
        int determinant = isAscending(position, goal) ? 1 : -1;
        Point lower = goal.getRow() < position.getRow() ? goal : position,
              upper = goal.getRow() < position.getRow() ? position : goal;
        for (int i = 0; i < BOARD_SIZE && i < upper.getRow() - lower.getRow() - 1; i++){
            Piece temp = board[lower.getRow() + i * determinant][lower.getCol() + i];
            if (temp == null)
                continue;
            return false;
        }
        return targetsNullOrEnemy(board, goal, position);
    }
    @Override
    public String toString(){
        return super.getSide() ? "\u265D" : "\u2657";
    }
}
