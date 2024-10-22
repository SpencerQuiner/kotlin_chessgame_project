package src.main.kotlin.chess

import chess.Board
import chess.Player


class Game{
    private val board = Board()
    private val players =listof(Player(White), Player(Black))
    private var currentPlayerIndex = 0

    fun start() {
        while (true) {
            val currentPlayer = players[currentPlayerIndex]
            // Ask the current player for their move, e.g., (startPosition, targetPosition)
            // Example:
            val startPosition = Pair(1, 0)  // You would get this from user input
            val targetPosition = Pair(2, 0)

            if (board.movePiece(startPosition, targetPosition)) {
                println("${currentPlayer.color} moves from $startPosition to $targetPosition")
            } else {
                println("Invalid move!")
            }

            currentPlayerIndex = (currentPlayerIndex + 1) % 2 // Switch player
        }
    }

}