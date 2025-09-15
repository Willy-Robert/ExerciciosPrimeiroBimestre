public package controller;

import model.board.Board;
import model.board.Position;
import model.pieces.*;

/**
 * Controls game flow, turn switching, legal move validation (incl. king safety),
 * and hooks for game status (check/checkmate/stalemate) and special rules.
 */
public class Game {

    private final Board board;
    private boolean isWhiteTurn;
    private boolean isGameOver;
    private Piece selectedPiece;

    public Game() {
        this.board = new Board();
        this.isWhiteTurn = true;
        this.isGameOver = false;
        setupPieces();
    }

    // --------------------------- Setup ---------------------------

    private void setupPieces() {
        // White back rank
        board.placePiece(new Rook(board, true),   new Position(7, 0));
        board.placePiece(new Knight(board, true), new Position(7, 1));
        board.placePiece(new Bishop(board, true), new Position(7, 2));
        board.placePiece(new Queen(board, true),  new Position(7, 3));
        board.placePiece(new King(board, true),   new Position(7, 4));
        board.placePiece(new Bishop(board, true), new Position(7, 5));
        board.placePiece(new Knight(board, true), new Position(7, 6));
        board.placePiece(new Rook(board, true),   new Position(7, 7));
        // White pawns
        for (int c = 0; c < 8; c++) {
            board.placePiece(new Pawn(board, true), new Position(6, c));
        }

        // Black back rank
        board.placePiece(new Rook(board, false),   new Position(0, 0));
        board.placePiece(new Knight(board, false), new Position(0, 1));
        board.placePiece(new Bishop(board, false), new Position(0, 2));
        board.placePiece(new Queen(board, false),  new Position(0, 3));
        board.placePiece(new King(board, false),   new Position(0, 4));
        board.placePiece(new Bishop(board, false), new Position(0, 5));
        board.placePiece(new Knight(board, false), new Position(0, 6));
        board.placePiece(new Rook(board, false),   new Position(0, 7));
        // Black pawns
        for (int c = 0; c < 8; c++) {
            board.placePiece(new Pawn(board, false), new Position(1, c));
        }
    }

    // --------------------------- Public API ---------------------------

    public Board getBoard() { return board; }

    public boolean isWhiteTurn() { return isWhiteTurn; }

    public boolean isGameOver() { return isGameOver; }

    public Piece getSelectedPiece() { return selectedPiece; }

    public void selectPiece(Position position) {
        if (isGameOver) return;
        Piece piece = board.getPieceAt(position);
        if (piece != null && piece.isWhite() == isWhiteTurn) {
            selectedPiece = piece;
        } else {
            selectedPiece = null;
        }
    }

    /**
     * Attempts to move the currently selected piece to destination.
     * Returns true if the move was executed.
     */
    public boolean movePiece(Position destination) {
        if (selectedPiece == null || isGameOver) return false;

        // Movement rule check (no king-safety here)
        if (!selectedPiece.canMoveTo(destination)) return false;

        // King-safety: reject moves that leave own king in check
        if (moveCausesCheck(selectedPiece, destination)) return false;

        // Perform the move (with capture if any)
        Position from = selectedPiece.getPosition();
        Piece capturedPiece = board.getPieceAt(destination);

        board.removePiece(from);
        if (capturedPiece != null) board.removePiece(destination);
        board.placePiece(selectedPiece, destination);

        // Special rules (promotion, castling rook hop, en passant removal, etc.)
        checkSpecialConditions(selectedPiece, from, destination, capturedPiece);

        // Update game state: check/checkmate/stalemate
        checkGameStatus();

        // Switch turn and clear selection
        isWhiteTurn = !isWhiteTurn;
        selectedPiece = null;
        return true;
    }

    // --------------------------- King Safety ---------------------------

    /**
     * Simulates moving 'piece' to 'destination' and returns true if, after that
     * simulation, the mover's own king is attacked by the opponent.
     *
     * Assumes Piece.canMoveTo checks only geometric/move rules, not king-safety.
     */
    private boolean moveCausesCheck(Piece piece, Position destination) {
        Position from = piece.getPosition();
        Piece captured = board.getPieceAt(destination);

        // --- Simulate ---
        board.removePiece(from);
        if (captured != null) board.removePiece(destination);
        board.placePiece(piece, destination);

        boolean inCheck;
        try {
            Position myKing = findKingPosition(piece.isWhite());
            if (myKing == null) {
                // Shouldn't happen in legal chess; if king not found treat as unsafe
                return true;
            }
            inCheck = isSquareAttacked(myKing, !piece.isWhite());
        } finally {
            // --- Undo ---
            board.removePiece(destination);
            board.placePiece(piece, from);
            if (captured != null) board.placePiece(captured, destination);
        }

        return inCheck;
    }

    /**
     * Finds the position of the king of the given color by scanning the board.
     */
    private Position findKingPosition(boolean whiteKing) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Position p = new Position(r, c);
                Piece piece = board.getPieceAt(p);
                if (piece instanceof King && piece.isWhite() == whiteKing) {
                    return p;
                }
            }
        }
        return null;
    }

    /**
     * True if any piece of 'byWhite' side currently can move to 'square'.
     * Important: relies on Piece.canMoveTo to express attack pattern + path blocking.
     * It must NOT do its own king-safety filtering to avoid recursion.
     */
    private boolean isSquareAttacked(Position square, boolean byWhite) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Position from = new Position(r, c);
                Piece p = board.getPieceAt(from);
                if (p == null || p.isWhite() != byWhite) continue;
                if (from.equals(square)) continue; // ignore same-square edge case
                if (p.canMoveTo(square)) return true;
            }
        }
        return false;
    }

    // --------------------------- Special Rules & Game Status ---------------------------

    /**
     * Hook for promotions, castling, and en passant.
     * Implement according to your piece state and board representation.
     *
     * @param mover          the piece that moved
     * @param from           original position (before move)
     * @param to             destination position (after move)
     * @param previouslyAtTo captured piece if any
     */
    private void checkSpecialConditions(Piece mover, Position from, Position to, Piece previouslyAtTo) {
        // Promotion example (naive): if a white pawn reaches rank 0 or a black pawn rank 7
        // replace it by a Queen. Adjust to your UI/promotion choice mechanism.
        if (mover instanceof Pawn) {
            if (mover.isWhite() && to.getRow() == 0) {
                board.removePiece(to);
                board.placePiece(new Queen(board, true), to);
            } else if (!mover.isWhite() && to.getRow() == 7) {
                board.removePiece(to);
                board.placePiece(new Queen(board, false), to);
            }
        }

        // Castling example (sketch):
        // If mover is King and |to.col - from.col| == 2, move the rook accordingly.
        if (mover instanceof King) {
            int dr = to.getRow() - from.getRow();
            int dc = to.getCol() - from.getCol();
            if (dr == 0 && Math.abs(dc) == 2) {
                // king-side or queen-side
                if (dc == 2) {
                    // King-side: rook from (row, 7) to (row, 5)
                    Position rookFrom = new Position(from.getRow(), 7);
                    Position rookTo   = new Position(from.getRow(), 5);
                    Piece rook = board.getPieceAt(rookFrom);
                    if (rook instanceof Rook && rook.isWhite() == mover.isWhite()) {
                        board.removePiece(rookFrom);
                        board.placePiece(rook, rookTo);
                    }
                } else if (dc == -2) {
                    // Queen-side: rook from (row, 0) to (row, 3)
                    Position rookFrom = new Position(from.getRow(), 0);
                    Position rookTo   = new Position(from.getRow(), 3);
                    Piece rook = board.getPieceAt(rookFrom);
                    if (rook instanceof Rook && rook.isWhite() == mover.isWhite()) {
                        board.removePiece(rookFrom);
                        board.placePiece(rook, rookTo);
                    }
                }
            }
        }

        // En passant:
        // If a pawn moved diagonally into an empty square and EP right existed,
        // remove the captured pawn behind the destination. This needs EP state tracking
        // (e.g., last double-step file/row). Add your EP state and logic here.
    }

    /**
     * Sets isGameOver and could store winner/state if you track that.
     * For now, a minimal placeholder. You can expand to:
     *  - detect check on side-to-move,
     *  - generate any legal move to see checkmate/stalemate,
     *  - threefold/50-move if you track history,
     *  - insufficient material, etc.
     */
    private void checkGameStatus() {
        // Minimal example: detect simple checkmate/stalemate requires move generation.
        // Placeholder keeps the game going.
        this.isGameOver = false;
    }
}


 Main {
    
}
