package src.main.kotlin.chess.pieces

import src.main.kotlin.chess.Board
import src.main.kotlin.chess.Game
import kotlin.math.abs
import src.main.kotlin.chess.Player

abstract class Piece(val color: String, var position: Pair<Int, Int>, val symbol: String) {

    //this function is used to determine if the move is legal
    abstract fun isValidMove(targetPosition: Pair<Int, Int>, board: Board, currentPlayer: Player, chessGame: Game): Boolean

    //this function check to make sure there are no pieces in the way of the move.
    protected fun obstacleCheck(start: Pair<Int, Int>, end: Pair<Int, Int>, board: Board, chessGame: Game): Boolean {
        val (startRow, startCol) = start
        val (endRow, endCol) = end

        if (startRow == endRow) { // Horizontal movement
            val direction = if (endCol > startCol) 1 else -1
            var currentCol = startCol + direction

            while (currentCol != endCol) {
                if (board.grid[startRow][currentCol] != null) {
                    return false // Path is blocked
                }
                currentCol += direction
            }

        } else if (startCol == endCol) { // Vertical movement
            val direction = if (endRow > startRow) 1 else -1
            var currentRow = startRow + direction


            while (currentRow != endRow) {
                if (board.grid[currentRow][startCol] != null) {
                    return false // Path is blocked
                }
                currentRow += direction
            }

        } else if (abs(startRow - endRow) == abs(startCol - endCol)) { // Diagonal movement
            val rowDirection = if (endRow > startRow) 1 else -1
            val colDirection = if (endCol > startCol) 1 else -1
            var currentRow = startRow + rowDirection
            var currentCol = startCol + colDirection

            while (currentRow != endRow && currentCol != endCol) {
                if (board.grid[currentRow][currentCol] != null) {
                    return false // Path is blocked
                }
                currentRow += rowDirection
                currentCol += colDirection
            }
        }

        return true // No obstacles
    }
    //funtion to print the name of the class to the console
    fun printClassName() {
        println("Class name: ${this.javaClass.simpleName}")
    }
}