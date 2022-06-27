import java.util.Scanner;

public class Player
{
    //all of this must be moved to field.java
    public static void createUsername()
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter your name:");
        String playerName = console.nextLine();
        System.out.println("Welcome: " + playerName);
    }
}
