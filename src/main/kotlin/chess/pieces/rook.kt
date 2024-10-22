package src.main.kotlin.chess.pieces

class Rook(color: String, position: Pair<Int, Int>, id: String) : Piece(color, position, id) {

    override fun isValidMove(targetPosition: Pair<Int, Int>, board: Array<Array<Piece?>>): Boolean {
        val (startRow, startCol) = position
        val (endRow, endCol) = targetPosition

        if (startRow != endRow && startCol != endCol) {
            return false
        }
        if (!obstacleCheck(position, targetPosition, board)) {
            return false // Path is blocked
        }

        val targetPiece = board[endRow][endCol]
        return (targetPiece == null || targetPiece.color != this.color)
    }
}