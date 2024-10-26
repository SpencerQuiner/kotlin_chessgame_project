package src.main.kotlin.chess.pieces
import src.main.kotlin.chess.Board
import src.main.kotlin.chess.Game
import kotlin.math.abs
import src.main.kotlin.chess.Player

//subclass of Piece class
class Bishop(color: String, position: Pair<Int, Int>, symbol: String) : Piece(color, position, symbol) {

    //override isValidMove function in base Piece class
    override fun isValidMove(targetPosition: Pair<Int, Int>, board: Board, currentPlayer: Player, chessGame: Game): Boolean {
        val (startRow, startCol) = position
        val (endRow, endCol) = targetPosition

        //makes sure it is moving diagonally by checking the absolute value of the start - the end. ensures that they are the same if they aren't then the move isn't diagonal.
        if (abs(startRow - endRow) != abs(startCol - endCol)) {
            return false
        }

        //checks to make sure there is nothing in the way of the move
        if (!obstacleCheck(position, targetPosition, board, chessGame)) {
            return false // Path is blocked
        }

        //checks for piece to capture
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
        return true
    }
}