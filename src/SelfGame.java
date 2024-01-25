import java.awt.*;
import java.util.Random;

public class SelfGame extends Game {
    int point = 0;
    private Random cord;


   public SelfGame(){
       this.cord = new Random();
       this.player1=new SelfPlayer(XO.X,this);
       this.player2=new SelfPlayer(XO.O,this);
   }
}
