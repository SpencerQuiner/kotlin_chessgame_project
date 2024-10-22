package src.main.kotlin.chess.pieces

import kotlin.math.abs

class King(color: String, position: Pair<Int, Int>, id: String) : Piece(color, position, id) {


    override fun isValidMove(targetPosition: Pair<Int, Int>, board: Array<Array<Piece?>>): Boolean {
        val (startRow, startCol) = position
        val (endRow, endCol) = targetPosition

        if (abs(startRow - endRow) > 1 || abs(startCol - endCol) > 1)
        {
            return false // Invalid move for King
        }
        val targetPiece = board[endRow][endCol]
        if (targetPiece != null && targetPiece.color == this.color) {
            return false // Can't move to a square occupied by the same color piece
        }

        // Check if the target position is under attack by any opponent piece
        if (isUnderAttack(targetPosition, board)) {
            return false // Can't move to a position under attack
        }

        return true
    }
    private fun isUnderAttack(targetPosition: Pair<Int, Int>, board: Array<Array<Piece?>>): Boolean {
        // Check all opponent pieces to see if any can move to targetPosition
        for (row in board.indices) {
            for (col in board[row].indices) {
                val piece = board[row][col]
                if (piece != null && piece.color != this.color) {
                    // Check if this opponent piece can move to targetPosition
                    if (piece.isValidMove(targetPosition, board)) {
                        return true // The King is under attack
                    }
                }
            }
        }
        return false // The King is not under attack
    }

}
