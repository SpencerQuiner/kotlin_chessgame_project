package src.main.kotlin.chess.pieces

import src.main.kotlin.chess.Board
import src.main.kotlin.chess.Game
import kotlin.math.abs
import src.main.kotlin.chess.Player

//subclass of Piece class
class Pawn(color: String, position: Pair<Int, Int>, symbol: String) : Piece(color, position, symbol) {

    //override isValidMove function in base Piece class
    override fun isValidMove(targetPosition: Pair<Int, Int>, board: Board, currentPlayer: Player, chessGame: Game): Boolean {
        val direction = if (color == "white") 1 else -1
        val (startRow, startCol) = position
        val (endRow, endCol) = targetPosition

        if (startCol == endCol && endRow == startRow + direction && board.grid[endRow][endCol] == null) {
            return true
        }

        if (startCol == endCol && endRow == startRow + 2 * direction && board.grid[startRow + direction][startCol] == null && board.grid[endRow][endCol] == null && (startRow == 1 || startRow == 6)) {
            return true
        }
        println("Piece start position: $position")
        println("Piece start position: $targetPosition")
        if (!obstacleCheck(position, targetPosition, board, chessGame)) {
            return false // Path is blocked
        }

        if (abs(startCol - endCol) == 1 && endRow == startRow + direction && board.grid[endRow][endCol]?.color != color && board.grid[endRow][endCol] != null) {
            val targetPiece = board.grid[endRow][endCol]
            if (targetPiece != null) {
                // Capture the opponent's piece
                val opponentPlayer = if (targetPiece.color == "white") chessGame.players[0] else chessGame.players[1]
                opponentPlayer.removePiece(targetPiece) // Remove the captured piece from the opponent's list
            }
            return true // Valid capture move
        }
        return false
    }
}