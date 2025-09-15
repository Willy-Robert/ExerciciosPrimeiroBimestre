package model.pieces;

import model.Board;
import model.Color;
import model.Piece;

public class Knight extends Piece {
    
    public Knight(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        return new boolean[8][8];
    }
}
