import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;

public class ReadFile
{
    /*
    private Scanner x;
    private String fileName;

    public ReadFile()
    {
        fileName = "";
    }

    public ReadFile(String newFileName)
    {
        fileName = newFileName;
    }
    
    public void closeFileX()
    {
        x.close();
    }

    public void closeFileW()
    {
        w.close();
    }

    public String getFileName()
    {
        return fileName;
    }

    public void openFiles()
    {
        try
        {
            x = new Scanner(new File("boosts.txt"));
            w = new Scanner(new File("outcome.txt"));
        }
        catch(Exception e)
        {
            System.out.println("Could not find file.");
        }
    }

    public void readFile()
    {
        while (x.hasNextLine())
        {
            String[] lineContents = x.nextLine().split(",");
            try
            {
                int dmg = Integer.parseInt(lineContents[0]);
                int def = Integer.parseInt(lineContents[1]);
                int gold = Integer.parseInt(lineContents[2]);
                System.out.println("Damage: " + dmg + " Defence: " + def + " Coins: " + gold);
            }
            catch (Exception e)
            {
                 System.out.println("Error in reading integers.");
                 continue;
            } 
        }
    }

    public void setFileName(String newFileName)
    {
        fileName = newFileName;
    }

    public void writeFile(String contents)
    {
        try
        {
            if(fileName.trim().length() > 0)
            {
                PrintWriter outputFile = new PrintWriter(fileName);
                outputFile.println(contents);
                outputFile.close();
            }
            else
                System.out.println("Please Enter a FileName");
        }
        catch(IOException exception)
        {
            System.out.println("An error was encountered while trying to write the data to the " + fileName + " file.");
        }
    */
}
