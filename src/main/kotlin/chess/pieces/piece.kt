package src.main.kotlin.chess.pieces

import kotlin.math.abs

abstract class Piece(val color: String,val position: Pair<Int, Int>,val id: String) {

    //this function is used to determine if the move is legal
    abstract fun isValidMove(targetPosition: Pair<Int, Int>, board: Array<Array<Piece?>>): Boolean

    protected fun obstacleCheck(start: Pair<Int, Int>, end: Pair<Int, Int>, board: Array<Array<Piece?>>): Boolean {
        val (startRow, startCol) = start
        val (endRow, endCol) = end



        if (startRow == endRow) { // Horizontal movement
            val direction = if (endCol > startCol) 1 else -1
            var currentCol = startCol + direction


            while (currentCol != endCol) {
                if (board[startRow][currentCol] != null) {
                    return false // Path is blocked
                }
                currentCol += direction
            }
        } else if (startCol == endCol) { // Vertical movement
            val direction = if (endRow > startRow) 1 else -1
            var currentRow = startRow + direction
            while (currentRow != endRow) {
                if (board[currentRow][startCol] != null) {
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
                if (board[currentRow][currentCol] != null) {
                    return false // Path is blocked
                }
                currentRow += rowDirection
                currentCol += colDirection
            }
        }

        return true // No obstacles
    }
}