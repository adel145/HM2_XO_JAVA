public abstract class Player implements Runnable {
    private XO symbol;
    public  abstract void makeMove(XO x) throws InterruptedException;

    public Player(XO x){
        this.symbol=x;
    }
    public XO getSymbol(){
        return this.symbol;
    }
}
