package src.main.kotlin.chess.pieces
import kotlin.math.abs

class Bishop(color: String, position: Pair<Int, Int>, id: String) : Piece(color, position, id) {

    override fun isValidMove(targetPosition: Pair<Int, Int>, board: Array<Array<Piece?>>): Boolean {
        val (startRow, startCol) = position
        val (endRow, endCol) = targetPosition

        //makes sure it is moving diagonally by checking the absolute value of the start - the end. ensures that they are the same if they aren't then the move isn't diagonal.
        if (abs(startRow - endRow) != abs(startCol - endCol)) {
            return false
        }

        //checks to make sure there is nothing in the way of the move
        if (!obstacleCheck(position, targetPosition, board)) {
            return false // Path is blocked
        }

        //checks for piece to capture
        val targetPiece = board[endRow][endCol]
        return (targetPiece == null || targetPiece.color != this.color)
    }
}