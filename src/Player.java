import java.util.Scanner;

/**
 * Store player details
 *
 * @author Matt Webb
 * @version v1.0
 */

public class Player
{
    private String playerName;

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
        while((playerName.length() < 3)||(playerName.length() > 12))
        {
            do
            {
                try
                {
                    System.out.println("Please enter your name (3-12 characters):");
                    playerName = console.nextLine().trim();
                    flag = false;
                }
                catch (Exception e)
                {
                    System.out.println("Please enter a name between 3-12 characters.");
                }
            }while (flag);
        }
        playerName = playerName.substring(0,1).toUpperCase() + playerName.substring(1).toLowerCase();
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



    /*
    //all of this must be moved to field.java ... but why?
    public static void createUsername()
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter your name:");
        String playerName = console.nextLine();
        System.out.println("Welcome: " + playerName);
    }

     */
}
