package src.main.kotlin.chess

import chess.pieces.Piece


class Player(val color: string){
    private val pieces: MutableList<Piece> = mutableListOf()

    fun addPiece(piece: Piece){
        pieces.add(piece)
    }

    fun removePiece(piece: Piece){
        pieces.remove(piece)
    }
}
