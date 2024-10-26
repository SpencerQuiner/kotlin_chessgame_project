package src.main.kotlin.chess.pieces
import src.main.kotlin.chess.Board
import src.main.kotlin.chess.Game
import kotlin.math.abs
import src.main.kotlin.chess.Player

//subclass of Piece class
class Knight(color: String, position: Pair<Int, Int>, symbol: String) : Piece(color, position, symbol){

    //override isValidMove function in base Piece class
    override fun isValidMove(targetPosition: Pair<Int, Int>, board: Board, currentPlayer: Player, chessGame: Game): Boolean {
        val (startRow, startCol) = position
        val (endRow, endCol) = targetPosition

        val rowDiff = abs(startRow - endRow)
        val colDiff = abs(startCol - endCol)

        if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
            // Ensure the target square is either empty or contains an opponent's piece
            val targetPiece = board.grid[endRow][endCol]

            if (targetPiece != null) {

                // Check if the piece is friendly
                if (targetPiece.color == this.color) {
                    return false // Cannot capture your own piece
                } else {
                    // Capture the opponent's piece
                    val opponentPlayer =
                        if (targetPiece.color == "white") chessGame.players[0] else chessGame.players[1]
                    opponentPlayer.removePiece(targetPiece) // Remove the captured piece from the opponent's list
                }
            }
            return true
        }
        return false // Invalid move
    }

}