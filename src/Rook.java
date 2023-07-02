public class Rook extends Queen {
    public Rook(Point position, boolean side){
        super(position, side);
    }
    @Override
    public boolean isValidMove(Piece[][] board, Point goal){
        Point position = super.getPosition();
        if (!isSameRow(position, goal) && !isSameCol(position, goal))
            return false;
        int determinantRow = isSameRow(position, goal) ? 0 : 1,
            determinantCol = Math.abs(1 - determinantRow);
        Point lower = goal.getRow() <= position.getRow() && goal.getCol() <= position.getCol() ? goal : position,
              upper = goal.getRow() <= position.getRow() && goal.getCol() <= position.getCol() ? position : goal;
        for (int i = 0; i < BOARD_SIZE && i < upper.getRow() - lower.getRow() - 1; i++){
            Piece temp = board[lower.getRow() + i * determinantRow][lower.getCol() + i * determinantCol];
            if (temp == null)
                continue;
            return false;
        }
        return targetsNullOrEnemy(board, goal, position);
    }
    @Override
    public String toString(){
        return super.getSide() ? "\u265C" : "\u2656";
    }
}
