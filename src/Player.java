import java.util.Scanner;

/**
 * Store player details
 *
 * @author Matt Webb
 * @version v1.0
 */

public class Player
{
    private static String playerName;

    public Player()
    {
        playerName = "";
    }

    public Player(String playerName)
    {
        this.playerName = playerName;
    }

    public void display()
    {
        System.out.println("Welcome: " + playerName);
    }

    public void createUsername()
    {
        boolean flag = true;
        Scanner console = new Scanner(System.in);
        while((playerName.length() < 3)||(playerName.length() > 12)) // Get name
        {
            do
            {
                try
                {
                    System.out.println("\nPlease enter your name (3-12 characters):");
                    playerName = console.nextLine().trim();
                    flag = false;
                }
                catch (Exception e)
                {
                    System.out.println("Please enter a name between 3-12 characters.");
                }
            }while (flag);
        }

        // Capitalise the first letter in the players names
        playerName = " " + playerName; // To capitalise the very first letter
        String f = "";
        for (int i =0; i < playerName.length(); i++)
        {
            char ch = playerName.charAt(i);
            if(ch == ' ')
            {
                 f = f + ch;
                 i++;
                 ch = playerName.charAt(i);
                 f =  f + Character.toUpperCase(ch);
            }
            else
            {
                f = f + ch;
            }
        }
        playerName = f.trim();
    }

    public String getPlayerName()
    {
        return playerName;
    }

    public void setPlayerName()
    {
        this.playerName = playerName;
    }

    public String toString()
    {
        return playerName;
    }
}
