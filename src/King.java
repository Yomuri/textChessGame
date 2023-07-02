public class King extends Piece {
    public King(Point position, boolean side){
        super(position, side);
    }
    @Override
    public boolean isValidMove(Piece[][] board, Point goal){
        Point position = super.getPosition();
        return isWithinOne(goal, position) &&
               targetsNullOrEnemy(board, goal, position);
    }
    private boolean isWithinOne(Point goal, Point position){
        return Math.abs(goal.getRow() - position.getRow()) < 2 && Math.abs(goal.getCol() - position.getCol()) < 2;
    }
    @Override
    public String toString(){
        return super.getSide() ? "\u265A" : "\u2654";
    }
}
