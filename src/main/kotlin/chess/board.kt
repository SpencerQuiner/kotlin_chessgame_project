package src.main.kotlin.chess

import chess.pieces.Piece
import chess.pieces.Rook
import chess.pieces.Bishop
import chess.pieces.Knight
import chess.pieces.King
import chess.pieces.Queen
import chess.pieces.Pawn

class Board {
    private val grid: Array<Array<Piece?>> = Array(8) {arrayOfNulls<Piece>(8)}

    init{
        setupPieces()
    }

    private fun setupPieces() {
        val whitePieces = listOf(
            Rook("white",Pair(0,0),"KingRookWhite"),
            Knight("white",Pair(0,1),"kingKnightWhite"),
            Bishop("white",Pair(0,2),"KingBishopWhite"),
            King("white",Pair(0,3),"KingWhite"),
            Queen("white",Pair(0,4),"QueenWhite"),
            Bishop("white",Pair(0,5),"QueenBishopWhite"),
            Knight("white",Pair(0,6),"QueenKnightWhite"),
            Rook("white",Pair(0,7),"QueenRookWhite"),
            Pawn("white",Pair(1,0),"KingPawn4White"),
            Pawn("white",Pair(1,1),"KingPawn3White"),
            Pawn("white",Pair(1,2),"KingPawn2White"),
            Pawn("white",Pair(1,3),"KingPawn1White"),
            Pawn("white",Pair(1,4),"QueenPawn1White"),
            Pawn("white",Pair(1,5),"QueenPawn2White"),
            Pawn("white",Pair(1,6),"QueenPawn3White"),
            Pawn("white",Pair(1,7),"QueenPawn4White")
        )

        val blackPieces = listOf(
            Rook("black",Pair(7,0),"QueenRookBlack"),
            Knight("black",Pair(7,1),"QueenKnightBlack"),
            Bishop("black",Pair(7,2),"QueenBishopBlack"),
            Queen("black",Pair(7,3),"QueenBlack"),
            King("black",Pair(7,4),"KingBlack"),
            Bishop("black",Pair(7,5),"KingBishopBlack"),
            Knight("black",Pair(7,6),"KingKnightBlack"),
            Rook("black",Pair(7,7),"KingRookBlack"),
            Pawn("black",Pair(6,0),"QueenPawn4Black"),
            Pawn("black",Pair(6,1),"QueenPawn3Black"),
            Pawn("black",Pair(6,2),"QueenPawn2Black"),
            Pawn("black",Pair(6,3),"QueenPawn1Black"),
            Pawn("black",Pair(6,4),"KingPawn1Black"),
            Pawn("black",Pair(6,5),"KingPawn2Black"),
            Pawn("black",Pair(6,6),"KingPawn3Black"),
            Pawn("black",Pair(6,7),"KingPawn4Black")
        )

        for (piece in whitePieces){
            grid[piece.position.first][piece.position.second] = piece
        }

        for (piece in blackPieces){
            grid[piece.position.first][piece.position.second] = piece
        }
    }

    fun movePiece(startPosition: Pair<Int, Int>, targetPosition: Pair<Int, Int>): Boolean {
        val piece = grid[startPosition.first][startPosition.second] ?: return false

        // Check if the move is valid according to the piece's rules
        if (piece.isValidMove(targetPosition, grid)) {
            grid[targetPosition.first][targetPosition.second] = piece
            grid[startPosition.first][startPosition.second] = null
            piece.position = targetPosition
            return true
        }
        return false
    }

}