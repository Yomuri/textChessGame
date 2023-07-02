public class Queen extends Piece {
    public Queen(Point position, boolean side){
        super(position, side);
    }
    @Override
    public boolean isValidMove(Piece[][] board, Point goal){
        Point position = super.getPosition();
        if (isAscending(position, goal) || isDescending(position, goal) ||
            isSameRow(position, goal) || isSameCol(position, goal)){
            //Ascend   1 1 descend -1 1  there are no overlaps b/c if bishop movement, sameRow and sameCol will be false
            //same row 0 1 same col 1 0  if rook movement, isAscending and isDescending will be false
            int determinantRow = isAscending(position, goal) ? 1 : -1;
            if (isSameRow(position, goal))
                determinantRow = 0;
            int determinantCol = isAscending(position, goal) ? 1 : Math.abs(1 - determinantRow);
            Point lower = goal.getRow() <= position.getRow() ? goal : position,
                  upper = goal.getRow() <= position.getRow() ? position : goal;
            for (int i = 0; i < BOARD_SIZE && i < upper.getRow() - lower.getRow() - 1; i++){
                Piece temp = board[lower.getRow() + i * determinantRow][lower.getCol() + i * determinantCol];
                if (temp == null)
                    continue;
                return false;
            }
            return targetsNullOrEnemy(board, goal, position);
        }
        return false;
    }
    protected boolean isDescending(Point start, Point end){
        return end.getCol() - start.getCol() == start.getRow() - end.getRow();
    }
    protected boolean isAscending(Point start, Point end){
        return end.getCol() - start.getCol() == end.getRow() - start.getRow();
    }
    protected boolean isSameRow(Point start, Point end){
        return start.getRow() == end.getRow();
    }
    protected boolean isSameCol(Point start, Point end){
        return start.getCol() == end.getCol();
    }
    @Override
    public String toString(){
        return super.getSide() ? "\u265B" : "\u2655";
    }
}
