package src.main.kotlin.chess.pieces
import kotlin.math.abs

class Knight(color: String, position: Pair<Int, Int>, id: String) : Piece(color, position, id){

    override fun isValidMove(targetPosition: Pair<Int, Int>, board: Array<Array<Piece?>>): Boolean {
        val (startRow, startCol) = position
        val (endRow, endCol) = targetPosition

        val rowDiff = abs(startRow - endRow)
        val colDiff = abs(startCol - endCol)

        if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
            // Ensure the target square is either empty or contains an opponent's piece
            val targetPiece = board[endRow][endCol]
            if (targetPiece == null || targetPiece.color != this.color) {
                return true // Valid move
            }
        }

        return false // Invalid move
    }

}