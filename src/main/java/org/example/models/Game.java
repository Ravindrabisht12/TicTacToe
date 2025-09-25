package org.example.models;

import org.example.exceptions.InvalidMoveException;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private  Board board;
    private List<Player> players;
    private GameState gameState;
    private List<Move> moves;
    private Player winner;
    private int nextPlayerMoveIndex;

    public Game(int dimension, List<Player> players) {
        this.board = new Board(dimension);
        this.players = players;
        this.gameState = GameState.IN_PROGRESS;
        this.moves = new ArrayList<>();
        this.nextPlayerMoveIndex = 0;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void displayBoard(){
        this.board.displayBoard();
    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public void makeMove() throws InvalidMoveException{
        Player currentPlayer = players.get(nextPlayerMoveIndex);
        System.out.println("This is " +
                currentPlayer.getName() +
                "'s move");
        Move move = currentPlayer.makeMove(board);


        // Game will validate the move
        if(!validateMove(move)) {
            throw new InvalidMoveException("Invalid Move!");
        }
    }

    private boolean validateMove(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        // dimension == 3
        // rows = 0, 1, 2
        // cols = 0, 1, 2
        if(row < 0 ||
                row >= this.board.getDimension() ||
                col < 0 ||
                col >= this.board.getDimension()) {
            return false;
        }

        return board.getBoard().get(row).get(col).isEmpty();
    }

    public static class Builder{
        private int dimension;
        private List<Player> players;

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Game build(){
            if(this.dimension<3){
                throw new IllegalArgumentException("Dimension should be greater than 3");
            }
            if(this.players.size()<2){
                throw new IllegalArgumentException("Players should be greater than 2");
            }
            return new Game(this.dimension, this.players);
        }
    }
}
