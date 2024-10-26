package src.main.kotlin.chess

fun main() {
    //opening game menu player chooses to start a game or quit
    println("Hello would you like to play chess?")
    println("choose a option from the menu")
    println("1. play chess")
    println("2. exit game")
    val choice = readln().toInt()

    when(choice) {
        1 -> {
            //player chooses to start a game creates an instance of the Game class
            val chessGame = Game()
            //runs the start function on the Game just created
            chessGame.setupGameBoard()
            chessGame.start()
        }
        2 -> {
            //players chooses to quit says goodbye and ends the program.
            println("Goodbye!")
            return
        }
    }

}

