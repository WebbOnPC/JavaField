import java.util.Random;
import java.util.Scanner;

public class Moves
{
    int playerPos = 0;
    private int size;
    private Scanner console;
    private int playerHealth;
    private int pcHealth;
    private int playerCoin;
    private int pcCoin;
    private int playerAtt;
    private int pcAtt;
    private int playerDef;
    private int pcDef;
    private Random rand;

    private MakeGrid makeGrid;

    private boolean colMade;

    // Stats
    private int captured; // captured grids
    private int lost;     // Grids lost
    private int capturedPC; // PC captured grids
    private int lostPC; // PC grids lost

    private    int tempDef;
    private    int tempAtt;
    private    int die1;
    private    int die2;
    private    int die3;
    private    int dieDef1;
    private     int dieDef2;

    public Moves()
    {
        console = new Scanner(System.in);
        size = 0;
        playerHealth = 3;
        pcHealth = 3;
        playerCoin = 3000;
        makeGrid = new MakeGrid();
        pcCoin = 10000;
        playerAtt = 5;
        pcAtt = 5;
        playerDef = 7;
        pcDef = 7;
        rand = new Random();
        colMade = false;
         tempDef = 0;
         tempAtt = 0;
         die1 = 0;
         die2 = 0;
         die3 = 0;
        dieDef1 = 0;
        dieDef2 = 0;
    }

    public Moves(int playerHealth, int pcHealth, int playerCoin, int pcCoin, int playerAtt, int pcAtt, int playerDef, int pcDef, MakeGrid makeGrid)
    {
        this.playerHealth = playerHealth;
        this.pcHealth = pcHealth;
        this.playerCoin = playerCoin;
        this.pcCoin = pcCoin;
        this.playerAtt = playerAtt;
        this.pcAtt = pcAtt;
        this.playerDef = playerDef;
        this.pcDef = pcDef;
        this.makeGrid = makeGrid;
    }

    public String checkPath()   // Checks path to see if can Strike
    {    
        int howManyPs = 0;
        int tempSizeJ = 0;
        int tempSizeI = 0;
        for (int i = 0; i < makeGrid.getGridSize(); i++)
        {
            for (int j = 0; j < makeGrid.getGridSize(); j++)
            {
                char grids[][] = makeGrid.getGameGrid();
                if (grids[tempSizeJ][tempSizeI] == 'P')
                {
                    howManyPs = howManyPs + 1;
                    if (howManyPs == makeGrid.getGridSize())
                    {
                        colMade = true;
                        howManyPs = 0;
                        tempSizeI = 0;
                        tempSizeJ = 0;
                        break;
                    }
                    tempSizeJ = tempSizeJ + 2;
                }
                else   
                {
                    howManyPs = 0;
                    tempSizeI = 0;
                    tempSizeJ = 0;
                    break;
                }     
            }
            tempSizeI = tempSizeI + 2;
        }      
        return "";
    }

    public String checkWinner() // Check if we have a winner
    {
        if(pcHealth == 0)
        {
            System.out.println("---------------------------------------------------------\n");
            return "Congratulations you win! XD";
        }
        else if (playerHealth == 0)
        {
            System.out.println("---------------------------------------------------------\n");
            return "The Computer won.. -_-";
        }
        return "";
    }

    public void computerCapture() // Computer Move - Capture grid
    {
        diceRoll();
        tempAtt = die1 + die2 + die3 + pcAtt;
        System.out.println("They're attempting to Capture.");
        System.out.println("They rolled: " + die1 + ", " + die2 + " & " + die3 + ", their Attack total is: " + tempAtt); 
        tempDef = dieDef1 + dieDef2 + pcDef;
        System.out.println("Your roll: " + dieDef1 + ", & " + dieDef2 + ", your total Defence is: " + tempDef); 
        if(tempAtt > tempDef)
        {
            int pcPosX = rand.nextInt(makeGrid.getGridSize())*2;
            int pcPosY = rand.nextInt(makeGrid.getGridSize())*2;

            char grids[][] = makeGrid.getGameGrid();
            while(grids[pcPosY][pcPosX] == 'C')
            {
                pcPosX = rand.nextInt(makeGrid.getGridSize())*2;
                pcPosY = rand.nextInt(makeGrid.getGridSize())*2;
            }

            makeGrid.placePiece(pcPosX, pcPosY, "pc");
            System.out.println("Computer captured a Grid!");
        }
        else
            System.out.println("Capture failed!");
    }

    public void computerStrike() // Computer Move - Strike Health
    {
         // System.out.println("PC STRIKE");
         checkPath();
         if(colMade == true) // If player has completed a path
        {
            playerHealth = playerHealth - 1;
            System.out.println("They have successfully striked!");
            System.out.println("Your Lives: " + playerHealth);
        }
        else
            System.out.println("They failed to Strike your Lives");
    }

    public void computerSabotage() // Computer Move - Sabotage
    {
        int turnSab = rand.nextInt(10) + 1; // Select random number to make move

        if(colMade == true) // If Player has a completed path, attack path.
        {
            turnSab = 10;
            colMade = false;
        }

        switch(turnSab)
        {   
            case 1:
            case 2:
            case 3:
            case 4: // Attacking attack
                int cost = rand.nextInt(1000) + 500;
                pcCoin = pcCoin - cost;
                System.out.println("They're reducing your Attack");
                playerAtt = playerAtt - 2;
                System.out.println("Your Attack: " + playerAtt);
                break;
            case 5:
            case 6:
            case 7:
            case 8: // Attacking Defence
                cost = rand.nextInt(1000) + 500;
                pcCoin = pcCoin - cost;
                System.out.println("They're reducing your Defence");
                playerDef = playerDef - 2;
                System.out.println("Your Defence: " + playerDef );
                break;
            case 9:
            case 10: // Attack Players Grid
                cost = rand.nextInt(1500) + 1000;
                if(pcCoin > cost && ((pcCoin - cost) >= 0)) // If computer has funds
                {
                    System.out.println("They're capturing your Grid!");

                    pcCoin = pcCoin - cost;
                    System.out.println("Costing them: " + pcCoin);

                    int pcPosX = rand.nextInt(makeGrid.getGridSize())*2;
                    int pcPosY = rand.nextInt(makeGrid.getGridSize())*2;

                    char grids[][] = makeGrid.getGameGrid();
                    while(!(grids[pcPosY][pcPosX] == 'P'))
                    {
                        pcPosX = rand.nextInt(makeGrid.getGridSize())*2;
                        pcPosY = rand.nextInt(makeGrid.getGridSize())*2;
                    }
                    System.out.println("Computer captured a Grid.");
                    ++lost;
                   // placePiece(gameGrid, pcPosX, pcPosY, "pc");
                    makeGrid.placePiece(pcPosX, pcPosY, "pc");
                }
                else
                    computerCapture(); // Tries to capture instead
                break; 
            default:
                computerSabotage();
        }
    }

    public void computerTurn() // Computer Move
    {
        int turn = rand.nextInt(4-1) + 1;
        boolean turnOne = true;
        if(turnOne == true) // If turn 1, capture grid
        {
            turnOne = false;
            turn = 1;
        }
        
        switch(turn)
        {
            case 1:
                computerCapture();
                break;
            case 2:
                computerStrike();
                break;
            case 3:
                computerSabotage();
                break;
            default:
                computerTurn();
        }
    }

    public void diceRoll() // Dice
    {
        die1 = rand.nextInt(6) + 1;
        die2 = rand.nextInt(6) + 1;
        die3 = rand.nextInt(6) + 1;
        dieDef1 = rand.nextInt(6) + 1;
        dieDef2 = rand.nextInt(6) + 1;
    }

    public void inputPlayerTurn() // Player Move
    {
        System.out.println("Press 1: Capture a Grid.");
        System.out.println("Press 2: Sabotage the enemy.");
        System.out.println("Press 3: Direct Strike a heart.");
        int playerTurn = console.nextInt();
        switch(playerTurn) {
            case 1: // Capture Grid
                moveCapture();
                break;
            case 2: // Sabotage the Enemy
                moveSabotage();
                break;
            case 3: // Direct Strike to Heart
                moveStrike();
                break;
            default: // Else
                inputPlayerTurn();
                break;
        }
    }

    public void moveCapture() // Player Move - Capture a grid.
    {
        diceRoll();

        tempAtt = die1 + die2 + die3 + playerAtt;
        System.out.println("You rolled: " + die1 + ", " + die2 + ", & " + die3 + ", your Attack total is: " + tempAtt);
        tempDef = dieDef1 + dieDef2 + pcDef;
        System.out.println("Opponent rolled: " + dieDef1 + ", & " + dieDef2 + ", their total Defence is: " + tempDef);
        if(tempAtt > tempDef) // If Attack > Defence
        {
            System.out.println("Enter X coordinate (1 - " + makeGrid.getGridSize() + "):"); // Get X coord
            int playerPosX = console.nextInt();
            while ((playerPosX < 1) || (playerPosX > makeGrid.getGridSize()))
            {
                System.out.println("Enter X coordinate (1 - " + makeGrid.getGridSize() + "):");
                playerPosX = console.nextInt();
            }

            System.out.println("Enter Y coordinate (1 - " + makeGrid.getGridSize() + "):"); // Get Y coord
                int playerPosY = console.nextInt();
            while ((playerPosY < 1) || (playerPosY > makeGrid.getGridSize()))
            {
                System.out.println("Enter Y coordinate (1 - " + makeGrid.getGridSize() + "):");
                playerPosY = console.nextInt();
            }
            System.out.println("Move: (" + playerPosX + "," + playerPosY + ")");
            playerPosX = (playerPosX - 1)*2; // Line up X with grid
            playerPosY = (playerPosY - 1)*2; // Line up Y with grid
            /* // If grid is already taken - Needs loop update
            while(gameGrid[playerPosY][playerPosX] == 'P') // If coordinates are already taken by player, ask again.
            {
                System.out.println("Invalid Move");
                while ((playerPosX < 1) || (playerPosX > makeGrid.getGridSize()))
                {
                    System.out.println("Enter X coordinate (1-" + makeGrid.getGridSize() + ")");
                    playerPosX = console.nextInt();
                }

                while ((playerPosY < 1) || (playerPosY > makeGrid.getGridSize()))
                {
                    System.out.println("Enter Y coordinate (1-" + makeGrid.getGridSize() + ")");
                    playerPosY = console.nextInt();
                }
                System.out.println("Move: (" + playerPosX + "," + playerPosY + ")");
                playerPosX = (playerPosX - 1)*2; // Line up X with grid
                playerPosY = (playerPosY - 1)*2; // Line up Y with grid
            }
            */
            makeGrid.placePiece(playerPosX, playerPosY, "player");
        }else // Defence > Attack
        {
            System.out.println("Their Defence was too strong!");
        }
    }
    public void moveSabotage() // Player Move - Sabotage computer
    {
        System.out.println("Press 1: Decrease the opponent's Attack by 2.");
        System.out.println("Press 2: Decrease the opponent's Defence by 2.");
        System.out.println("Press 3: Steal a Grid space.");
        int sabMove = console.nextInt();
        switch(sabMove)
        {
            case 1:
                moveSabotage1(); // Option 1
                break;
            case 2: 
                moveSabotage2(); // Option 2
                break;
            case 3:
                moveSabotage3(); // Option 3
                break; 
            default:
                System.out.println("Select an option between 1 and 3.");
                moveSabotage();
        }
    }

    public void moveSabotage1() // Player Move - Sabotage option 1
    {
        int cost = rand.nextInt(1000) + 500;
        System.out.println("This will cost you: $" + cost);
        boolean valid = false;
        do {
            System.out.print("Do you accept? Yes or No: ");
            String ans = console.next();
            switch (ans)
            {
                case "Y":
                case "y":
                case "YES":
                case "Yes":
                case "ok":
                case "Ok":
                case "OK":
                case "Okay":
                case "OKAY":
                    playerCoin = playerCoin - cost;
                    System.out.println("New coin total: $" + playerCoin);
                    pcAtt = pcAtt - 2;
                    System.out.println("Opponents Attack: " + pcAtt);
                    valid = true;
                    break;
                case "n":
                case "N":
                case "NO":
                case "No":
                    System.out.println("You selected No.");
                    valid = true;
                    break;
                default:
                    System.out.println("Invalid Option.");
            }
        }while(! valid);
    }

    public void moveSabotage2() // Player Move - Sabotage option 2
    {
        boolean valid = false;
        int cost = rand.nextInt(1000) + 500;
        System.out.println("This will cost you: $" + cost);
        do
        {
            System.out.print("Do you accept? Yes or No: ");
            String ans2 = console.next();
            switch (ans2)
            {
                case "Y":
                case "y":
                case "YES":
                case "Yes":
                case "ok":
                case "Ok":
                case "OK":
                case "Okay":
                case "OKAY":
                    playerCoin = playerCoin - cost;
                    System.out.println("New coin total: $" + playerCoin);
                    pcDef = pcDef - 2;
                    System.out.println("Opponents Defence: " + pcDef );
                    valid = true;
                    break;
                case "n":
                case "N":
                case "NO":
                case "No":
                    System.out.println("You selected No.");
                    //inputPlayerTurn();
                    valid = true;
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        }while(! valid);
    }

    public void moveSabotage3() // Player Move - Sabotage option 3
    {
        boolean valid = false;
        int cost = rand.nextInt(1500) + 1000;
        System.out.println("This will cost you: $" + cost);
        do
        {
            System.out.print("Do you accept? Yes or No: ");
            String ans3 = console.next();
            switch (ans3)
            {
                case "Y":
                case "y":
                case "YES":
                case "Yes":
                case "ok":
                case "Ok":
                case "OK":
                case "Okay":
                case "OKAY":
                    playerCoin = playerCoin - cost;
                    System.out.println("New coin total: $" + playerCoin);
                    //select coorinates
                    System.out.println("Enter the opponents grid coordinates");
                    int StrikePosX = 0;
                    int StrikePosY = 0;
                    while(!(makeGrid.gameGrid[StrikePosY][StrikePosX] == 'C'))
                     //   while(!(gameGrid[StrikePosY][StrikePosX] == 'C'))
                    {
                        System.out.println("Enter X coordinate");
                        StrikePosX = console.nextInt();
                        while ((StrikePosX < 1) || (StrikePosX > makeGrid.getGridSize()))
                        {
                            System.out.println("Enter X coordinate");
                            StrikePosX = console.nextInt();
                        }

                        System.out.println("Enter Y coordinate");
                        StrikePosY = console.nextInt();
                        while ((StrikePosY < 1) || (StrikePosY > makeGrid.getGridSize()))
                        {
                            System.out.println("Enter Y coordinate");
                            StrikePosY = console.nextInt();
                        }
                        System.out.println("Move: (" + StrikePosX + "," + StrikePosY + ")");
                        StrikePosX = (StrikePosX - 1)*2;
                        StrikePosY = (StrikePosY - 1)*2;
                    }
                    //placePiece(gameGrid, StrikePosX, StrikePosY, "player"); // original
                    makeGrid.placePiece(StrikePosX, StrikePosY, "player"); // sorta worked but couldnt place

                    valid = true;
                    break;
                case "n":
                case "N":
                case "NO":
                case "No":
                    System.out.println("You selected No.");
                    valid = true;
                    break;
                default:
                    System.out.println("Invalid Option.");
            }
        }while(! valid);
    }

    public void moveStrike() // Player Move - Striking the computer
    {
       // checkPath(); // add this method back in later
        if(colMade == true)
        {
            pcHealth = pcHealth - 1;
            System.out.println("Computer Lives: " + pcHealth);
        }
        else
            System.out.println("You're yet to reach enemy lines. Make another move.");
    }

    public void statsDisplay()
    {
        System.out.println("Player   " + "Lives: " + playerHealth + "   Attack: " + playerAtt + "   Defence: " + playerDef + "   Coins: $" + playerCoin);
    }
    /*   // More stats
        public String statsDisplay() // Display player stats
    {
        player.toString();
        String name = player.toString();
        return "Player " + name + "Lives: " + playerHealth + "   Attack: " + playerAtt + "   Defence: " + playerDef + "   Coins $" + playerCoin + "   Captured " + captured+ "   Lost " + lost + "\n";
    }
     */

    public void statsDisplayPC()
    {
        System.out.println("Computer Lives: " + pcHealth + "   Attack: " + pcAtt + "   Defence: " + pcDef + "   Coins: $" + pcCoin);
    }
    /*  //// updated stats
    public String statsDisplayPC() // Display PC stats
    {
        return "Computer Lives: " + pcHealth + "   Attack: " + pcAtt + "   Defence: " + pcDef + "   Coins: $" + pcCoin + "   Captured " + capturedPC+ "   Lost " + lostPC +"\n";
    }
     */
}
