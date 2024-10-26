package src.main.kotlin.chess

import src.main.kotlin.chess.pieces.Piece
import src.main.kotlin.chess.pieces.Rook
import src.main.kotlin.chess.pieces.Bishop
import src.main.kotlin.chess.pieces.Knight
import src.main.kotlin.chess.pieces.King
import src.main.kotlin.chess.pieces.Queen
import src.main.kotlin.chess.pieces.Pawn

//sets up the board for the game. initializes the Piece objects.
class Board {
    val grid: Array<Array<Piece?>> = Array(8) { arrayOfNulls<Piece>(8) }
    private val whitePieces = mutableListOf<Piece>()
    private val blackPieces = mutableListOf<Piece>()


    fun setupPieces(players: List<Player>) {

        val whitePlayer = players[0] // Adjust according to your players list
        val blackPlayer = players[1]

        whitePieces.addAll(
            listOf(
                Rook("white", Pair(0, 0), "QR"),
                Knight("white", Pair(0, 1), "QN"),
                Bishop("white", Pair(0, 2), "QB"),
                Queen("white", Pair(0, 3), "WQ"),
                King("white", Pair(0, 4), "WK"),
                Bishop("white", Pair(0, 5), "KB"),
                Knight("white", Pair(0, 6), "KN"),
                Rook("white", Pair(0, 7), "KR"),
                Pawn("white", Pair(1, 0), "P1"),
                Pawn("white", Pair(1, 1), "P2"),
                Pawn("white", Pair(1, 2), "P3"),
                Pawn("white", Pair(1, 3), "P4"),
                Pawn("white", Pair(1, 4), "P5"),
                Pawn("white", Pair(1, 5), "P6"),
                Pawn("white", Pair(1, 6), "P7"),
                Pawn("white", Pair(1, 7), "P8")
            )
        )

        for (piece in whitePieces) {
            whitePlayer.addPiece(piece)//adds the pieces to the player mutable list
            grid[piece.position.first][piece.position.second] = piece
        }

        blackPieces.addAll(
            listOf(
                Rook("black", Pair(7, 0), "qr"),
                Knight("black", Pair(7, 1), "qn"),
                Bishop("black", Pair(7, 2), "qb"),
                Queen("black", Pair(7, 3), "bq"),
                King("black", Pair(7, 4), "bk"),
                Bishop("black", Pair(7, 5), "kb"),
                Knight("black", Pair(7, 6), "kn"),
                Rook("black", Pair(7, 7), "kr"),
                Pawn("black", Pair(6, 0), "p1"),
                Pawn("black", Pair(6, 1), "p2"),
                Pawn("black", Pair(6, 2), "p3"),
                Pawn("black", Pair(6, 3), "p4"),
                Pawn("black", Pair(6, 4), "p5"),
                Pawn("black", Pair(6, 5), "p6"),
                Pawn("black", Pair(6, 6), "p7"),
                Pawn("black", Pair(6, 7), "p8")
            )
        )

        for (piece in blackPieces) {
            blackPlayer.addPiece(piece) //adds the pieces to the player mutable list
            grid[piece.position.first][piece.position.second] = piece
        }
    }

    //prints a representation of the game board in the console
    fun displayBoard() {
        println("  a  b  c  d  e  f  g  h")
        for (row in 0..7) {
            print("${1 + row} ")
            for (col in 0..7) {
                val piece = grid[row][col]
                if (piece != null) {
                    print("${piece.symbol} ")
                } else {
                    print(".. ")
                }
            }
            println("${1 + row}")
        }
        println("  a  b  c  d  e  f  g  h")
    }

    //changes the position of the pieces on the game board
    fun movePiece(startPosition: Pair<Int, Int>, targetPosition: Pair<Int, Int>): Boolean {
        val piece = grid[startPosition.first][startPosition.second] ?: return false

        grid[targetPosition.first][targetPosition.second] = piece
        grid[startPosition.first][startPosition.second] = null
        piece.position = targetPosition

        val pieceList = if (piece.color == "white") whitePieces else blackPieces
        val index = pieceList.indexOfFirst { it.symbol == piece.symbol }

        if (index != -1) {
            pieceList[index].position = targetPosition // Update position in the list
        }
        return true
    }
}