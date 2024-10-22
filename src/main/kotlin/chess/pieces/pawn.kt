package src.main.kotlin.chess.pieces

class Pawn(color: String, position: Pair<Int, Int>, id: String) : Piece(color, position, id) {

    override fun isValidMove(targetPosition: Pair<Int, Int>, board: Array<Array<Piece?>>): Boolean {
        val direction = if (color == "white") 1 else -1
        val (startRow, startCol) = position
        val (endRow, endCol) = targetPosition

        if (startCol == endCol && endRow == startRow + direction && board[endRow][endCol] == null) {
            return true
        }

        if (startCol == endCol && endRow == startRow + 2 * direction && board[startRow + direction][startCol] == null && board[endRow][endCol] == null && (startRow == 1 || startRow == 6)) {
            return true
        }
        if (!obstacleCheck(position, targetPosition, board)) {
            return false // Path is blocked
        }

        if (Math.abs(startCol - endCol) == 1 && endRow == startRow + direction && board[endRow][endCol]?.color != color && board[endRow][endCol] != null) {
            return true
        }

        return false
    }
}