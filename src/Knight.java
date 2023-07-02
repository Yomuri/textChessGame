public class Knight extends Piece {
    public Knight(Point position, boolean side){
        super(position, side);
    }
    @Override
    public boolean isValidMove(Piece[][] board, Point goal){
        Point position = super.getPosition();
        return isInLShape(goal, position) &&
               targetsNullOrEnemy(board, goal, position);
    }
    private boolean isInLShape(Point goal, Point position){
         return Math.abs(goal.getRow() - position.getRow()) == 2 && Math.abs(goal.getCol() - position.getCol()) == 1 ||
                Math.abs(goal.getRow() - position.getRow()) == 1 && Math.abs(goal.getCol() - position.getCol()) == 2;
    }
    @Override
    public String toString(){
        return super.getSide() ? "\u265E" : "\u2658";
    }
}
