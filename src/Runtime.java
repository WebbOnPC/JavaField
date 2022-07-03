import java.io.IOException;
import java.util.ArrayList;

public class Runtime
{
    private Instructions instructions;
    private Player player;
    private Moves moves;
    private MakeGrid makeGrid;
    private Validation validation;
    private ReadFile file;
   // private final String outputFile = "outcome.txt";
    private String name;

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> pcPositions = new ArrayList<Integer>();

    public Runtime()
    {
        name = "";
        instructions = new Instructions();
        player = new Player();
        moves = new Moves();
        makeGrid = new MakeGrid();
        validation = new Validation();
        file = new ReadFile();
    }

    public Runtime(Player player, Instructions instructions, Moves moves, ReadFile file)
    {
        this.instructions = instructions;
        this.player = player;
        this.moves = moves;

        this.file = file;
    }

    public static void main(String[] args)
    {
        Runtime start = new Runtime();
        /*
        ReadFile r = new ReadFile();

        r.openFiles();
        r.readFile();
        r.closeFileX();
        */
        start.startGame();

        /*
        File file = new File("outcome.txt");
        FileWriter fw = new FileWriter(file);
        PrintWritter pw = new PrintWriter(fw);
        writeFile();
        pw.println(moves.statsDisplay());

        r.closeFileW();
       */

    }
    public void intro()
    {
         // Working Introduction, Character letter still needs to be updated
        Instructions.instructions(); // Call the list of player instructions
        player.createUsername();     // Ask for the players name
        player.display();
        name = player.toString();
                                     // Display Stats
        System.out.println("\nP = " + name + ", C = Computer");
        moves.statsDisplay();
        moves.statsDisplayPC();
        System.out.print("\n");
                                     // Create Board
        makeGrid.selectGridSize();   // Create game size
        makeGrid.buildGrid();        // Make grid
        validation.continueOn();

    }
    public void startGame()
    {
       // file.writeFile();
        intro();
        while(true) // Game running
        {
            playerTurn(); // Players Turn

            String result = moves.checkWinner();

            if(result.length() > 0)
            {
                System.out.println(result);
                break;
            }
            computerTurn();// Computers turn
            result = moves.checkWinner();
            if(result.length() > 0)
            {
                System.out.println(result);
                break;
            }
        }
       // writeFile();
       // printStats();
    }
    public void playerTurn()
    {
        System.out.println("\n-----------------------Player Turn------------------------");
        moves.statsDisplay();
        moves.inputPlayerTurn();
        validation.continueOn();
       // moves.checkWinner();
    }

    public void computerTurn()
    {
        System.out.println("\n---------------------Computer Turn------------------------");
        moves.statsDisplayPC();
        moves.computerTurn();
        validation.continueOn();
       // moves.checkWinner();
    }

    /*
    private void writeFile()
    {
        String temp = "";
        StringBuffer buffer = new StringBuffer(temp);
        for (int x = 0; x < enrolments.size(); x++)
        {
            buffer.append(enrolments.get(x).display());
            //buffer.append(enrolments.get(x));
        }
        
        FileIO io = new FileIO(outputFile);
        io.writeFile(buffer.toString());
    }
*/
    public void closeFile()
    {
   //     ReadFile.close();
    }

    public void printStats() throws IOException
    {
        /*
        System.out.println("Final Scores \n");
        String stat = moves.statsDisplay();
        String statPC = moves.statsDisplayPC();
        System.out.println(name);
        System.out.println(stat);
        System.out.println(statPC);
        try
        {
            File file1 = new File("dataTables/outcome.txt");
            PrintWriter pw = new PrintWriter(file1);
            pw.println(name);
            pw.println(stat);
            pw.println(statPC);
            pw.close();
        }
        catch (IOException ex)
        {
            System.out.println("File not found");
        }

         */
    }
}

