package model.pieces;

import model.Board;
import model.Color;
import model.Piece;

public class Bishop extends Piece {
    
    public Bishop(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        return new boolean[8][8];
    }
}
