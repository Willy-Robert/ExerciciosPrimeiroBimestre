package controller;

import model.*;
import model.pieces.*;

public class Game {
    private Board board;
    private int turn;
    private Color currentPlayer;
    private boolean check;
    private boolean checkMate;
    private Piece enPassantVulnerable;
    private Piece promoted;

    public Game() {
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }

    public int getTurn() { return turn; }
    public Color getCurrentPlayer() { return currentPlayer; }
    public boolean isCheck() { return check; }
    public boolean isCheckMate() { return checkMate; }
    public Piece getEnPassantVulnerable() { return enPassantVulnerable; }
    public Piece getPromoted() { return promoted; }

    private void placeNewPiece(char column, int row, Piece piece) {
        board.placePiece(piece, new Position(column, row));
    }

    private void initialSetup() {
        // White pieces
        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('b', 1, new Knight(board, Color.WHITE));
        placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('d', 1, new Queen(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));
        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('g', 1, new Knight(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));

        for (char c = 'a'; c <= 'h'; c++) {
            board.placePiece(new Pawn(board, Color.WHITE), new Position(c, 2));
        }

        // Black pieces
        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('b', 8, new Knight(board, Color.BLACK));
        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('d', 8, new Queen(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK));
        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('g', 8, new Knight(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));

        for (char c = 'a'; c <= 'h'; c++) {
            board.placePiece(new Pawn(board, Color.BLACK), new Position(c, 7));
        }
    }

    // ...remaining code...
}