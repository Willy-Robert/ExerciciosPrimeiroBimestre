package model;

public abstract class Piece {
    protected Position position;
    protected Board board;
    protected Color color;

    public Piece(Board board, Color color) {
        this.board = board;
        this.color = color;
    }

    public abstract boolean[][] possibleMoves();
}
