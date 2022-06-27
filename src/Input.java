import java.util.Scanner;

public class Input
{
    public Input()
    {

    }

    public char acceptCharInput(String displayMessage, int position)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(displayMessage);
        String input = console.nextLine();
        return input.charAt(position);
    }

    public double acceptDoubleInput(String displayMessage)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(displayMessage);
        String input = console.nextLine();
        return Double.parseDouble(input);
    }

    public int acceptIntegerInput(String displayMessage)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(displayMessage);
        int input = console.nextInt();
        return input;
    }

    public String acceptStringInput(String displayMessage)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(displayMessage);
        String input = console.nextLine();
        return input;
    }
}
