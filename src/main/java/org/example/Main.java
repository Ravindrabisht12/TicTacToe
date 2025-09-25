package org.example;

import org.example.controllers.GameController;
import org.example.models.*;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        GameController gameController = new GameController();

        List<Player> players = new ArrayList<>();
        players.add(new Player("Player 1", new Symbol('0')));
        players.add(new Bot("Player 2", new Symbol('0'), BotDifficultyLevel.EASY));

        int dimension = 3;
        Game game = gameController.startGame(players, dimension);

        while(game.getGameState() == GameState.IN_PROGRESS){
            gameController.displayBoard(game);
            gameController.makeMove(game);
        }
    }
}