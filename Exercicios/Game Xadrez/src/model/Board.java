package model;

public class Board {
    private final int rows;
    private final int columns;
    private Piece[][] pieces;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public void placePiece(Piece piece, Position position) {
        pieces[position.getRow()-1][position.getColumn()-'a'] = piece;
        piece.position = position;
    }

    public Piece removePiece(Position position) {
        Piece piece = pieces[position.getRow()-1][position.getColumn()-'a'];
        if (piece != null) {
            pieces[position.getRow()-1][position.getColumn()-'a'] = null;
        }
        return piece;
    }
}
