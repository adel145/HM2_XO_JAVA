import java.awt.*;
import java.util.Random;

public class SelfPlayer extends Player implements Runnable {
    Game game;
    int point = 0;
    private Random cord;

    public SelfPlayer(XO x, Game game) {
        super(x);
        this.cord = new Random();
        this.game = game;
    }
    public synchronized void makeMove(XO x) {
        while (!game.hasPlayerWon(XO.X) && !game.hasPlayerWon(XO.O) && game.getFreeCells()[0] != null) {
            while (x != game.getTurn()) {
                try {
                    this.wait(500);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }

            // Perform the move logic
            if (game.getFreeCells()[0] == null) {
                game.printBoard();
                return;
            }

            Point[] arr = game.getFreeCells();
            point = cord.nextInt(arr.length);
            game.setMove(arr[point], x);
            game.printBoard();

            // Notify other waiting threads after modifying shared state
            this.notifyAll();
        }
    }




    @Override
    public void run() {
        while (!game.isGameEnded() && game.getFreeCells()[0] != null) {
            makeMove(this.getSymbol());


            if (game.hasPlayerWon(this.getSymbol())) {
                game.setGameEnded(true);
                System.out.println("Player " + this.getSymbol() + " wins!");

                break;
            }


            if (game.getFreeCells()[0] == null) {
                game.setGameEnded(true);
                System.out.println("It's a tie!");
                break;
            }
        }
    }

}

