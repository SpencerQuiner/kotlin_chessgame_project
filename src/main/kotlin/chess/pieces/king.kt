package src.main.kotlin.chess.pieces

import src.main.kotlin.chess.Game
import src.main.kotlin.chess.Player
import kotlin.math.abs
import src.main.kotlin.chess.Board

//subclass of Piece class
class King(color: String, position: Pair<Int, Int>, symbol: String) : Piece(color, position, symbol) {

    //override isValidMove function in base Piece class
    override fun isValidMove(targetPosition: Pair<Int, Int>, board: Board, currentPlayer: Player, chessGame: Game): Boolean {
        val (startRow, startCol) = position
        val (endRow, endCol) = targetPosition


        if (abs(startRow - endRow) > 1 || abs(startCol - endCol) > 1)
        {
            return false // Invalid move for King
        }

        val targetPiece = board.grid[endRow][endCol]

        if (targetPiece != null) {

            // Check if the piece is friendly
            if (targetPiece.color == this.color) {
                return false // Cannot capture your own piece
            } else {
                // Capture the opponent's piece
                val opponentPlayer = if (targetPiece.color == "white") chessGame.players[0] else chessGame.players[1]
                opponentPlayer.removePiece(targetPiece) // Remove the captured piece from the opponent's list
            }
        }

        // Check if the target position is under attack by any opponent piece
        if (isUnderAttack(targetPosition, board, currentPlayer, chessGame)) {
            return false // Can't move to a position under attack
        }

        return true
    }

    //this function checks if the king is under attack
    private fun isUnderAttack(targetPosition: Pair<Int, Int>, board: Board, currentPlayer: Player, chessGame: Game): Boolean {
        // Check all opponent pieces to see if any can move to targetPosition
        for (row in board.grid.indices) {
            for (col in board.grid[row].indices) {
                val piece = board.grid[row][col]

                if (piece != null && piece.color != this.color) {
                    // Check if this opponent piece can move to targetPosition
                    if (piece.isValidMove(targetPosition, board, currentPlayer, chessGame)) {
                        return true // The King is under attack
                    }
                }
            }
        }
        return false // The King is not under attack
    }

}
