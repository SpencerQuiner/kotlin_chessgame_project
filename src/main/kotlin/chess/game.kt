package src.main.kotlin.chess

import kotlin.system.exitProcess


class Game{
    //creates the chess board
    private val board = Board()
    //creates the list of players
    val players =listOf(Player("white"), Player("black"))
    private var currentPlayerIndex = 0
    // sets whose turn it is
    private val currentPlayer: Player
    get() = players[currentPlayerIndex]

    fun setupGameBoard() {
        println("setting up the game board...")
        board.setupPieces(players) // Pass the player objects
    }

    //starts the game
    fun start() {
        // game loop keeps going until someone quits or wins
        while (true) {
            // shows the board
            board.displayBoard()
            //tells whose turn it is.
            println("${currentPlayer.color}'s turn")
            //asks the player for the position of the piece they want to move and where they want to move it too.
            val (startPosition, targetPosition) = requestMove()
            // gets which piece is being moved
            val piece = board.grid[startPosition.first][startPosition.second]
            if (piece != null) {
                piece.printClassName()
                println("${piece.color} ${piece.position}")
            }

            //error  handling for choosing the wrong color piece or an empty square.
            if (piece == null || piece.color != currentPlayer.color) {
                println("Invalid piece selection. Please try again.")
                continue // Skip turn change, retry input
            }

            //checks to ensure the move is a legal move.
            if (piece.isValidMove(targetPosition, board, currentPlayer,this)){

                //moves the piece to its new position
                board.movePiece(startPosition, targetPosition)
                //states the move.
                println("${currentPlayer.color} moves from $startPosition to $targetPosition")

            //if the move isn't valid it lets the player know.
            } else {
                println("Invalid move!")
                continue
            }
            //changes player
            currentPlayerIndex = (currentPlayerIndex + 1) % 2 // Switch player
        }
    }

    //requests move information from player
    private fun requestMove(): Pair<Pair<Int, Int>, Pair<Int, Int>> {
        println("Type 'exit' at anytime to quit the game")
        println("Enter your move (e.g., 'a2 a3'):")
        val input = readlnOrNull() ?: return requestMove() // Read input and handle null input
        if (input != "exit") {

            // Parse the input (assume input like 'a2 a3')
            val move = input.split(" ")
            if (move.size != 2) {
                println("Invalid input format. Please try again.")
                return requestMove()
            }

            // Convert chess notation (e.g., 'a2') into board coordinates (e.g., (1, 0))
            val startPosition = parseMove(move[0])
            val targetPosition = parseMove(move[1])

            if (startPosition == null || targetPosition == null) {
                println("Invalid move. Please try again.")
                return requestMove()
            }

            return Pair(startPosition, targetPosition)
        } else {
            //if they typed exit ends the game.
            exitProcess(0)
        }
    }

    //converts the letter-number notation the player provides into grid coordinates.
    private fun parseMove(position: String): Pair<Int, Int>? {
        if (position.length != 2) return null
        val file = position[0] - 'a'  // Convert 'a'-'h' to 0-7
        val rank = position[1] - '1'  // Convert '1'-'8' to 0-7

        if (file in 0..7 && rank in 0..7) {
            return Pair(rank, file)
        }
        return null
    }

    /* The following two functions were attempts at more advanced functions, but they cause the game to go into an infinite loop.
    I am commenting them out because I don't have enough time fix them and the program does what it needed to do without them.
    They would be part of future features.

    fun isKingInCheck(player: Player, startPosition: Pair<Int, Int>, targetPosition: Pair<Int, Int>, board: Board): Boolean {
        val movingPiece = board.grid[startPosition.first][startPosition.second]
        val targetPiece = board.grid[targetPosition.first][targetPosition.second]

        // Move the piece
        board.grid[targetPosition.first][targetPosition.second] = movingPiece
        board.grid[startPosition.first][startPosition.second] = null

        val kingPosition = board.getKingPosition(player)

        val opponentPlayer = if (player.color == "white") players[1] else players[0]
        val opponentPieces = opponentPlayer.pieces

        // Check if any opponent piece can move to the king's position
        for (piece in opponentPieces) {
            if (piece.isValidMove(kingPosition, board, opponentPlayer, this)) {
                // Restore the original board state
                board.grid[startPosition.first][startPosition.second] = movingPiece
                board.grid[targetPosition.first][targetPosition.second] = targetPiece
                return true
            }
        }
        board.grid[startPosition.first][startPosition.second] = movingPiece
        board.grid[targetPosition.first][targetPosition.second] = targetPiece // Restore target piece
        return false
    }

    //check to see if you have put the king in check
    private fun isOpponentKingInCheck(player: Player): Boolean {
        val opponentColor = if (player.color == "white") "black" else "white"
        val kingPosition = board.getKingPosition(Player(opponentColor))

        // Get the pieces of the current player
        val playerPieces = player.pieces

        // Loop through all the current player's pieces
        for (piece in playerPieces) {
            // If this player's piece can move to the opponent's king position, return true
            if (piece.isValidMove(kingPosition, board, currentPlayer, this)) {
                return true
            }
        }
        return false
    }*/

    //debugging function
    fun waitForPlayer() {
        println("Press Enter to continue...")
        readlnOrNull() // Pauses until the player presses Enter
    }
}