import java.util.ArrayList;

public class Field
{
    private Instructions instructions;
    private Player player;
    private Grid grids;
    private ReadFile file;
   // private final String outputFile = "outcome.txt";

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> pcPositions = new ArrayList<Integer>();

    public Field()
    {
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
       /* ReadFile r = new ReadFile();

        r.openFiles();
        r.readFile();
        r.closeFileX();
        */
        start.startGame();

        
      //  File file = new File("outcome.txt");
       // FileWriter fw = new FileWriter(file);
       // PrintWritter pw = new PrintWriter(fw);
        //writeFile();
       // pw.println(grids.statsDisplay());


       // r.closeFileW();
    }

    public void startGame()
    {
        Instructions.instructions(); // Call the list of player instructions
        Player.createUsername();     // Ask for the players name
        grids.inputGridSize();       // Create game size
        System.out.println("\nP = Player, C = Computer");


        grids.statsDisplay();
        grids.statsDisplayPC();

       // file.writeFile();

        grids.makeGrid();            // Make grid

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
}

