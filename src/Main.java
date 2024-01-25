import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice =0;




            while(choice!=3) {
                menu();
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        playSelfGame();
                        break;
                    case 2:
                        playUserGame();
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please choose again.");
                        break;
                }

            }
        }


        private static void playSelfGame () {
            SelfGame selfGame = new SelfGame();
            Thread thread1 = new Thread(selfGame.player1);
            Thread thread2 = new Thread(selfGame.player2);

            thread1.start();
            thread2.start();

        }

        private static void playUserGame () {
            Game game2 = new UserGame();
            Thread thread1 = new Thread(game2.player1);
            Thread thread2 = new Thread(game2.player2);
            thread1.start();
            thread2.start();


        }
        public static void menu(){
            System.out.println("Choose the game type:\n1.self game.\n2.user game\n3.exit");
        }

}
