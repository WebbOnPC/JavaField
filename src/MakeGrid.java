import java.util.Scanner;

public class MakeGrid
{
    private static int gridSize;

    public static char gameGrid[][];
    private int size;
    private Scanner console;

    private Player player;

    private char symbol;

    public MakeGrid()
    {
        symbol = ' ';
        gameGrid = new char[size][size];
        console = new Scanner(System.in);
        player = new Player();
    }

    public MakeGrid(int gridSize)
    {
        this.gridSize = gridSize;
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }

    public int getGridSize() {
        return gridSize;
    }

    public char[][] getGameGrid() {
        return gameGrid;
    }

    public void setGameGrid(char[][] gameGrid) {
        this.gameGrid = gameGrid;
    }

    public void selectGridSize() // Set gameboard Size
    {
        Validation valid = new Validation();
        System.out.println("Input a Grid Size from 3 - 10:");

        gridSize = console.nextInt();
        if ((gridSize >= 3 ) || (gridSize <= 10)) // is 3 - 10
        {
            setGridSize(gridSize);
        }
        else if ((gridSize < 3 ) || (gridSize > 10)) // if not from 3 - 10
        {
            System.out.println("Please enter a valid Grid Size from 3 - 10:");
            gridSize = console.nextInt();
        }

        boolean flag = true;
        while ((gridSize < 3 ) || (gridSize > 10))
        {
            do
            {
                try
                {
                    System.out.println("Input a Grid Size from 3 - 10:");
                    gridSize = Integer.parseInt(console.nextLine().trim());
                    flag = false;
                }
                catch (Exception e)
                {
                    System.out.println("Integer only.");
                }
            }while(flag);
        }
        System.out.println("Grid Size: " + gridSize + "x" + gridSize + "\n");
    }

    public void buildGrid() // Assign values to make matrix
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

   public void placePiece(int posX, int posY,  String user) // Get user, place Character piece
   {
        String name = player.getPlayerName();
        char P = name.charAt(0);            // Players character
        if(user.equals("player"))
        {
            symbol = P;
            //    int posCount = 0;         // for positions captured
        }
        else if (user.equals("pc"))
        {
            if(P != 'C')
            {
                symbol = 'C';
            }
            else
            {
                symbol = 'E';
            }
        }
        gameGrid[posY][posX] = symbol;  // Places the symbol (character piece) on the board
        setGameGrid( gameGrid);
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
}
