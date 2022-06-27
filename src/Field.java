import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Field
{
    private Instructions instructions;
    private Player player;
    private Grid grids;
    private ReadFile file;
   // private final String outputFile = "outcome.txt";
    private String name;

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> pcPositions = new ArrayList<Integer>();

    public Field()
    {
        name = "";
        instructions = new Instructions();
        player = new Player();
        grids = new Grid();
        file = new ReadFile();
    }

    public Field(Player player, Instructions instructions, Grid grids, ReadFile file)
    {
        this.instructions = instructions;
        this.player = player;
        this.grids = grids;
        this.file = file;
    }

    public static void main(String[] args)
    {
         Field start = new Field();
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
        pw.println(grids.statsDisplay());

        r.closeFileW();
       */

    }
    public void intro()
    {
        Instructions.instructions(); // Call the list of player instructions
        player.createUsername();     // Ask for the players name
        player.display();
        name = player.toString();
                                     // Display Stats
        System.out.println("\nP = " + name + ", C = Computer");
        grids.statsDisplay();
        grids.statsDisplayPC();
        System.out.print("\n");

        grids.inputGridSize();       // Create game size
        grids.makeGrid();            // Make grid
      //  grids.printGridBoard();
    }
    public void startGame()
    {
       // file.writeFile();
        intro();
        while(true)
        {
            System.out.println("----------------------Java Field-----------------------");
            grids.statsDisplay();
            grids.inputPlayerTurn();
            grids.checkWinner();

            grids.statsDisplayPC();
            grids.computerTurn();
            grids.checkWinner();
        }
       // writeFile();
       // printStats();
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
        String stat = grids.statsDisplay();
        String statPC = grids.statsDisplayPC();
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

