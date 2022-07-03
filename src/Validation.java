import java.lang.String;
import java.util.*;

public class Validation
{
    public Validation()
    {

    }

    public boolean isBlank(String value) // value =  "      "
    {
        boolean blank = false;
        if(value.trim().length() == 0) // value = "sdf   fdss"
            blank = true;
        return blank;
    }

    public boolean stringLengthWithinRange(String value, int min, int max) // value = "IT04131" 4,6
    {
        boolean withinRange = false;
        if ((value.trim().length() >= min) && (value.trim().length() <= max))
            withinRange = true;
        return withinRange;
    }

    public void continueOn() // Press anything to continue
    {
        try
        {
            System.out.println("Press Enter to continue.");
            System.in.read();
        }
        catch (Exception e)
        {
            System.out.println("Please press Enter");
        }
    }
}
