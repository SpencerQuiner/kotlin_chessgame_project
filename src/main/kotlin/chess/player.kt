package src.main.kotlin.chess

import src.main.kotlin.chess.pieces.Piece

//Player class
class Player(val color: String){
    val pieces: MutableList<Piece> = mutableListOf()

    fun addPiece(piece: Piece){
        pieces.add(piece)
    }

    fun removePiece(piece: Piece){
        pieces.remove(piece)
    }
}
