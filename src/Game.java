import java.awt.*;

public abstract class Game {
    private  XO[][] gameBoard=new XO[5][5];
    private boolean gameEnded = false;
Player player1;
Player player2;


    public  synchronized void printBoard() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(gameBoard[i][j]);
                if (j < 4) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < 4) {
                System.out.println("---------");
            }
        }
        System.out.println();
    }

    public  void setMove(Point x,XO y) {
        this.gameBoard[x.x][x.y] = y;
    }

    public  Point[] getFreeCells() {
        int count = 0;
        if (countFilledCells()==25) {
            return new Point[1];
        }
        Point arr[] = new Point[25 - countFilledCells()];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (gameBoard[i][j] == null) {
                    arr[count]=new Point(i,j);
                    count++;
                }
            }
        }
        return arr;
    }
    public  int countFilledCells() {
        int count = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (gameBoard[i][j] != null) {
                    count++;
                }
            }
        }
        return count;
    }
    public  XO getTurn() {
        if (!isGameEnded()) {
            int filledCells = countFilledCells();

            return (filledCells % 2 == 0) ? XO.X : XO.O;
        }
        return null;
    }

    public  boolean isValidMove(int row,int col){
        if(gameBoard[row][col]!=null||row>gameBoard.length-1||col>gameBoard[0].length-1)
            return false;
        else
            return true;
    }


    public boolean hasPlayerWon(XO playerSymbol) {
        return checkRows(playerSymbol) || checkColumns(playerSymbol) || checkDiagonals(playerSymbol);
    }
    private boolean checkRows(XO playerSymbol) {
        for (int row = 0; row < 5; row++) {
            for (int startCol = 0; startCol <= 1; startCol++) {
                boolean win = true;
                for (int col = startCol; col < startCol + 4; col++) {
                    if (gameBoard[row][col] != playerSymbol) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkColumns(XO playerSymbol) {
        for (int col = 0; col < 5; col++) {
            for (int startRow = 0; startRow <= 1; startRow++) {
                boolean win = true;
                for (int row = startRow; row < startRow + 4; row++) {
                    if (gameBoard[row][col] != playerSymbol) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonals(XO playerSymbol) {
        // Check main diagonal
        for (int start = 0; start <= 1; start++) {
            boolean win = true;
            for (int i = 0; i < 4; i++) {
                if (gameBoard[start + i][start + i] != playerSymbol) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return true;
            }
        }

        // Check anti-diagonal
        for (int start = 0; start <= 1; start++) {
            boolean win = true;
            for (int i = 0; i < 4; i++) {
                if (gameBoard[start + i][4 - start - i] != playerSymbol) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return true;
            }
        }

        return false;
    }


    public synchronized void setGameEnded(boolean gameEnded) {
        this.gameEnded = gameEnded;
    }

    public synchronized boolean isGameEnded() {
        return gameEnded;
    }







}
