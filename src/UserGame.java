import java.awt.*;
import java.util.Random;
import java.util.Scanner;
public class UserGame extends Game{
public UserGame(){
    this.player1=new UserPlayer(XO.X,this);
    this.player2=new SelfPlayer(XO.O,this);
}
}
