import java.awt.*;
import java.util.Scanner;

public class UserPlayer extends Player implements Runnable{
    Game game;
    public UserPlayer(XO x,Game game){
        super(x);
        this.game=game;
    }
    Scanner scan=new Scanner(System.in);
    int point = 0;
    public synchronized void makeMove(XO x) {
        if (!game.hasPlayerWon(XO.X) || !game.hasPlayerWon(XO.O)) {
            while (x != game.getTurn()) {
                try {
                    this.wait(500);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }



            Point[] arr = game.getFreeCells();
            for(int i=0;i<arr.length;i++){
                System.out.print(i+"."+arr[i]+",");
            }
            System.out.print("please choose the place you want to place(row,col): ");
            point= scan.nextInt();
            game.setMove(arr[point], x);
            game.printBoard();

            this.notifyAll();
        }
    }
    public synchronized void run() {
        while (!game.hasPlayerWon(this.getSymbol()) && game.getFreeCells()[0] != null) {
            makeMove(this.getSymbol());


            if (game.hasPlayerWon(this.getSymbol())) {
                System.out.println("Player " + this.getSymbol() + " wins!");
                break;
            }

            // Check for a tie
            if (game.getFreeCells()[0] == null) {
                System.out.println("It's a tie!");
                break;
            }
        }
    }
}



