import java.util.Random;
import java.util.Scanner;

public class Grid
{
    private static int gridSize;
    public char gameGrid[][];
    int playerPos = 0;
    private int size;
    private char symbol;
    private Scanner console;
    private int playerHealth;
    private int pcHealth;
    private int playerCoin;
    private int pcCoin;
    private int playerAtt;
    private int pcAtt;
    private int playerDef;
    private int pcDef;
    private int low;
    private int high;
    private Random rand;

    private boolean colMade;

  //  private int howManyPs;

    private    int tempDef;
    private    int tempAtt;
    private    int die1;
    private    int die2;
    private    int die3;
    private    int dieDef1;
    private     int dieDef2;

  //   ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    //static ArrayList<Integer> pcPositions = new ArrayList<Integer>();
   // static ArrayList<Integer> pcPositionsX = new ArrayList<Integer>();
 //   static ArrayList<Integer> pcPositionsY = new ArrayList<Integer>();
   //  ArrayList<ArrayList<Integer>> playerPositions = new ArrayList<Integer>();

    public Grid()
    {
        console = new Scanner(System.in);
        gridSize = 3;
        symbol = ' ';
        size = 0;
        gameGrid = new char[size][size];
        playerHealth = 3;
        pcHealth = 3;
        playerCoin = 3000;
        pcCoin = 10000;
        playerAtt = 5;
        pcAtt = 5;
        playerDef = 7;
        pcDef = 7;
        rand = new Random();
        colMade = false;

         low = 1;
         high = 7;
         tempDef = 0;
         tempAtt = 0;
         die1 = 0;
         die2 = 0;
         die3 = 0;
        dieDef1 = 0;
        dieDef2 = 0;
    }

    public Grid(int playerHealth, int pcHealth, int playerCoin, int pcCoin, int playerAtt, int pcAtt, int playerDef, int pcDef, int gridSize)
    {
        this.playerHealth = playerHealth;
        this.pcHealth = pcHealth;
        this.playerCoin = playerCoin;
        this.pcCoin = pcCoin;
        this.playerAtt = playerAtt;
        this.pcAtt = pcAtt;
        this.playerDef = playerDef;
        this.pcDef = pcDef;
        this.gridSize = gridSize;
    }

    public int getGridSize()
    {
        return this.gridSize;
    }

    public String checkPath()
    {    
        int howManyPs = 0;
        int tempSizeJ = 0;
        int tempSizeI = 0;
        for (int i = 0; i < gridSize; i++)
        {
            for (int j = 0; j < gridSize; j++)
            {
                if (gameGrid[tempSizeJ][tempSizeI] == 'P')
                {
                    howManyPs = howManyPs + 1;
                    if (howManyPs == gridSize)
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

    public String checkWinner()
    {   System.out.println("checking winner");
        if(pcHealth == 0)
        {
            System.out.println("YOU ARE WINNER");
            return "Congratulations you win!";
        }
        else if (playerHealth == 0)
        {
            System.out.println("YOU SUCK");
            return "The Computer wins!";
        }
        return "";
    }

    public void computerCapture()
    {
        diceRoll();
        tempAtt = die1 + die2 + die3 + pcAtt;
        System.out.println("They're attempting to Capture"); 
        System.out.println("They rolled: " + die1 + ", " + die2 + " & " + die3 + ", their Attack total is: " + tempAtt); 
        tempDef = dieDef1 + dieDef2 + pcDef;
        System.out.println("Your roll: " + dieDef1 + ", & " + dieDef2 + ", your total Defence is: " + tempDef); 
        if(tempAtt > tempDef)
        {
            int pcPosX = rand.nextInt(gridSize)*2;
            int pcPosY = rand.nextInt(gridSize)*2;
            while(gameGrid[pcPosY][pcPosX] == 'C')
            {
                pcPosX = rand.nextInt(gridSize)*2;
                pcPosY = rand.nextInt(gridSize)*2;
            }
            placePiece(gameGrid, pcPosX, pcPosY, "pc");
            System.out.println("Computer captured a Grid");
        }
        else
            System.out.println("Capture failed!"); 
    }

    public void computerStrike()
    {
         System.out.println("PC STRIKE");
         checkPath();
         if(colMade == true)
        {
            playerHealth = playerHealth - 1;
            System.out.println("They had successfully Striked");
            System.out.println("Your Lives: " + playerHealth);
        }
        else
            System.out.println("They failed to Strike your Lives");
    }

    public void computerSabotage()
    {
        int turnSab = rand.nextInt(11) + 1;
        switch(turnSab)
        {   
            case 1:
            case 2:
            case 3:
            case 4:
                low = 500;
                high = 1500;
                int cost = rand.nextInt(high-low) + low;
                pcCoin = pcCoin - cost;
                System.out.println("They're reducing your Attack");
                playerAtt = playerAtt - 2;
                System.out.println("Your Attack: " + playerAtt); 
                break;
            case 5:
            case 6:
            case 7:
            case 8: 
                low = 500;
                high = 1500;
                cost = rand.nextInt(high-low) + low;
                pcCoin = pcCoin - cost;
                System.out.println("They're reducing your Defence");
                playerDef = playerDef - 2;
                System.out.println("Your Defence: " + playerDef );
                break;
            case 9:
            case 10:
                low = 1000;
                high = 2500;
                cost = rand.nextInt(high-low) + low;
                 if(pcCoin > cost)
                {
                    pcCoin = pcCoin - cost;

                    int pcPosX = rand.nextInt(gridSize)*2;
                    int pcPosY = rand.nextInt(gridSize)*2;
                    while(!(gameGrid[pcPosY][pcPosX] == 'P'))
                    {
                        pcPosX = rand.nextInt(gridSize)*2;
                        pcPosY = rand.nextInt(gridSize)*2;
                    }
                    placePiece(gameGrid, pcPosX, pcPosY, "pc");
                    System.out.println("Computer captured a Grid");
                }
                else
                    System.out.println("Sabotage failed!"); 
                break; 
            default:
                System.out.println("Select an option between 1 and 3"); 
        }
    }

    public void computerTurn()
    {
        int turn = rand.nextInt(4-1) + 1;
        boolean turnOne = true;
        if(turnOne == true)
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
                System.out.println("Invalid Option");
        }
    }

    public void diceRoll()
    {
        die1 = rand.nextInt(high-low) + low;
        die2 = rand.nextInt(high-low) + low;
        die3 = rand.nextInt(high-low) + low;
        dieDef1 = rand.nextInt(high-low) + low;
        dieDef2 = rand.nextInt(high-low) + low;
    }

    public void inputGridSize() // Sets Grid Size
    {
        Validation valid = new Validation();
        System.out.println("Input a Grid Size from 3 - 10:");
        gridSize = console.nextInt();
        if ((gridSize < 3 ) || (gridSize > 10))
        {
            System.out.println("Please enter a valid Grid Size from 3 - 10:");
            gridSize = console.nextInt();
        }
        else if ((gridSize >= 3 ) || (gridSize <= 10))
        {
            System.out.println("Grid Size: " + gridSize + " x " + gridSize);
        }
    }

    public void inputPlayerTurn() 
    {
        System.out.println("Press 1: Capture a Grid.");
        System.out.println("Press 2: Sabotage the enemy.");
        System.out.println("Press 3: Direct Strike a heart.");
        int playerTurn = console.nextInt();
        if (playerTurn == 1) // Capture Grid
        {   
           moveCapture();
        } 
        else if (playerTurn == 2) // Sabotage the Enemy
        {
            moveSabotage();
        } 
        else if(playerTurn == 3) // Direct Strike to Heart
        {
            moveStrike();
        }else
            System.out.println("Enter a valid move:");
    }

    public void makeGrid() // Assign values to make matrix
    {
        int newSize = gridSize;
        int gameSize = newSize * 2;
        size = gameSize-1;
        gameGrid = new char[size][size];
        for(int i=0; i < size; i++) // rows
        {
            for(int j=0; j < size; j++) // columns
            {
                gameGrid[i][j] = '|'; 
            }
        }
        for(int i=0; i < size; i++) // rows
        {
            for(int j=0; j < size; j++) // columns
            {
                gameGrid[i][j] = ' '; 
                j++;
            }
        }
        for(int j=0; j < size; j++) // rows
        {
            for(int i=1; i < size; i++){
                gameGrid[i][j] = '-'; 
                i++;
            }
        }
        printGridBoard(gameGrid);
    }
   
    public void moveCapture() // MOVE - Capture a grid.
    {
        diceRoll();
        tempAtt = die1 + die2 + die3 + playerAtt;
        System.out.println("You rolled: " + die1 + ", " + die2 + ", & " + die3 + ", your Attack total is: " + tempAtt);
        tempDef = dieDef1 + dieDef2 + pcDef;
        System.out.println("Opponent rolled: " + dieDef1 + ", & " + dieDef2 + ", their total Defence is: " + tempDef);
        if(tempAtt > tempDef)
        {
            System.out.println("Enter X coordinate (1 - " + gridSize + "):");
            int playerPosX = console.nextInt();
            while ((playerPosX < 1) || (playerPosX > gridSize)) 
            {
                System.out.println("Enter X coordinate (1 - " + gridSize + "):");
                playerPosX = console.nextInt();
            }

            System.out.println("Enter Y coordinate (1 - " + gridSize + "):");
                int playerPosY = console.nextInt();
            while ((playerPosY < 1) || (playerPosY > gridSize)) 
            {
                System.out.println("Enter Y coordinate (1 - " + gridSize + "):");
                playerPosY = console.nextInt();
            }
            System.out.println("Move: (" + playerPosX + "," + playerPosY + ")");
            playerPosX = (playerPosX - 1)*2;
            playerPosY = (playerPosY - 1)*2;

            while(gameGrid[playerPosY][playerPosX] == 'P')
            {
                System.out.println("Invalid Move");
                while ((playerPosX < 1) || (playerPosX > gridSize)) 
                {
                    System.out.println("Enter X coordinate (1-" + gridSize + ")");
                    playerPosX = console.nextInt();
                }

                while ((playerPosY < 1) || (playerPosY > gridSize)) 
                {
                    System.out.println("Enter Y coordinate (1-" + gridSize + ")");
                    playerPosY = console.nextInt();
                }
                System.out.println("Move: (" + playerPosX + "," + playerPosY + ")");
                playerPosX = (playerPosX - 1)*2;
                playerPosY = (playerPosY - 1)*2;
                
            }
            placePiece(gameGrid, playerPosX, playerPosY, "player");
        }else
        {
            System.out.println("Their Defence was too strong!");
        }
    }
    public void moveSabotage() // MOVE - Sabotage computer
    {
        System.out.println("Press 1: Decrease the opponent's Attack by 2.");
        System.out.println("Press 2: Decrease the opponent's Defence by 2.");
        System.out.println("Press 3: Steal a Grid space.");
        int sabMove = console.nextInt();
        switch(sabMove)
        {
            case 1: 
                low = 500;
                high = 1500;
                int cost = rand.nextInt(high-low) + low;
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
                break;
            case 2: 
                valid = false;
                low = 500;
                high = 1500;
                cost = rand.nextInt(high-low) + low;
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
                break;
            case 3:
                valid = false;
                low = 1000;
                high = 2500;
                cost = rand.nextInt(high-low) + low;
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
                            playerCoin = playerCoin - cost;
                            System.out.println("New coin total: $" + playerCoin); 
                            //select coorinates 
                            System.out.println("Enter the opponents grid coordinates");
                            int StrikePosX = 0;
                            int StrikePosY = 0;
                            while(!(gameGrid[StrikePosY][StrikePosX] == 'C')) 
                            {
                                System.out.println("Enter X coordinate");
                                StrikePosX = console.nextInt();
                                while ((StrikePosX < 1) || (StrikePosX > gridSize)) 
                                {
                                    System.out.println("Enter X coordinate");
                                    StrikePosX = console.nextInt();
                                }

                                System.out.println("Enter Y coordinate");
                                StrikePosY = console.nextInt();
                                while ((StrikePosY < 1) || (StrikePosY > gridSize)) 
                                {
                                    System.out.println("Enter Y coordinate");
                                    StrikePosY = console.nextInt();
                                }
                                System.out.println("Move: (" + StrikePosX + "," + StrikePosY + ")");
                                StrikePosX = (StrikePosX - 1)*2;
                                StrikePosY = (StrikePosY - 1)*2;
                            }
                            placePiece(gameGrid, StrikePosX, StrikePosY, "player");
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
                break; 
            default:
                System.out.println("Select an option between 1 and 3.");
        }
    }

    public void moveStrike() // Move - Striking the computer
    {
        checkPath();
        if(colMade == true)
        {
            pcHealth = pcHealth - 1;
            System.out.println("Computer Lives: " + pcHealth);
        }
        else
            System.out.println("You're yet to reach enemy lines. Make another move.");
    }
    
    public void placePiece(char[][] gameGrid, int posX, int posY,  String user) // Get user, place Character piece
    {
        if(user.equals("player"))
        {
            symbol = 'P';          
        //    int posCount = 0;         // cant recall what i added this for
        }  
        else if (user.equals("pc"))
        {
            symbol = 'C';
        } 
        gameGrid[posY][posX] = symbol;
        printGridBoard(gameGrid);
    }

    public void printGridBoard(char[][] gameGrid)  // Display grid
    {
        switch(gridSize)  // Adds numbers to X axis
        {
            case 3:
                System.out.println(" 1 2 3 X");
                break;
            case 4:
                System.out.println(" 1 2 3 4 X");
                break;
            case 5:
                System.out.println(" 1 2 3 4 5 X");
                break;
            case 6:
                System.out.println(" 1 2 3 4 5 6 X");
                break;
            case 7:
                System.out.println(" 1 2 3 4 5 6 7 X");
                break;
            case 8:
                System.out.println(" 1 2 3 4 5 6 7 8 X");
                break;
            case 9:
                System.out.println(" 1 2 3 4 5 6 7 8 9 X");
                break;
            case 10:
                System.out.println(" 1 2 3 4 5 6 7 8 9 10 X");
                break;
            default:
                break;
        }

        int counter = 1;  // Counts rows for Y column
        char space = ' '; // Adds space between Y numbers
        int rowNum = 1;   // Row as an int

        for(char[] row : gameGrid) // Print Y column and grid squares
        {
            rowNum++;
            if(rowNum % 2 == 0 )            // If even numbered row, print number
            {
                System.out.print(counter);
                counter++;
            }else                           // If odd numbered row, blank space
                System.out.print(space);

            for(char c : row)               // Print grid squares
            {
                System.out.print(c); 
            }
            System.out.println();
        }
        System.out.println("Y");
    }

    public void setGridSize(int gridSize)
    {
        this.gridSize = gridSize;
    }

    public void statsDisplay()
    {
        System.out.println("Player   " + "Lives: " + playerHealth + "   Attack: " + playerAtt + "   Defence: " + playerDef + "   Coins: $" + playerCoin);
    }

    public void statsDisplayPC()
    {
        System.out.println("Computer Lives: " + pcHealth + "   Attack: " + pcAtt + "   Defence: " + pcDef + "   Coins: $" + pcCoin);
    }
}

