public class Instructions {
    public static void instructions()
    {
        System.out.println("---------------------------------------------------------");
        System.out.println("------------------Welcome to Java Field------------------");
        System.out.println("---------------------------------------------------------");
        System.out.println("OBJECTIVE:");
        System.out.println("Destroy all 3 of your opponents hearts.\n");
        System.out.println("RULES:");
        System.out.println("Both Players will be given an Attack Damage of 5, and Defence of 7.");
        System.out.println("You will receive $3000 Coins.");
        System.out.println("The Computer will receive $10,000.\n");
        System.out.println("HOW TO PLAY:");
        System.out.println("You will be given 3 options:");
        System.out.println("     1. Capture a Grid." );
        System.out.println("          To capture a Grid, the player will roll 3 dice \n"+
                           "          providing you with a number between 1 and 6, the \n"+
                           "          computer will roll 2 dice. If your total Attack is \n" +
                           "          higher than their Defence, the grid is captured.");
        System.out.println("\n   2. Sabotage The Enemy:\n"+
                           "          To capture a Grid Spot, the player will roll a die\n"+
                           "          providing you with a number between 1 and 6, the computer \n"+ 
                           "          will roll 2 dice. ");
        System.out.println("\n   3. Direct Strike at Heart ");
        System.out.println("          To direct strike a heart, you must capture enough \n"+
                           "          grid spaces to complete a straight path to the end of the field");
        System.out.println("---------------------------------------------------------");
        System.out.println("---------------------------------------------------------");
    }
}

