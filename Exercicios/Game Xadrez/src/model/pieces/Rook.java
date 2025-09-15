package model.pieces;

import model.Board;
import model.Color;
import model.Piece;

public class Rook extends Piece {
    
    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        return new boolean[8][8];
    }
}
    }
}
