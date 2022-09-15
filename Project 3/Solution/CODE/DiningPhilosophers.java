/**
 * Class DiningPhilosophers
 * The main starter.
 */

//Take int input for number of philosopher
import java.util.Scanner;

public class DiningPhilosophers
{
    /*
     * Data members
     */

    /**
     * This default may be overridden from the command line
     */
    private static int iPhilosophers;
    public static final int DEFAULT_NUMBER_OF_PHILOSOPHERS = 4;
    private static Scanner sc;

    /**
     * Dining "iterations" per philosopher thread
     * while they are socializing there
     */
    public static final int DINING_STEPS = 10;

    /**
     * Our shared monitor for the philosophers to consult
     */
    public static Monitor soMonitor = null;

    /*
     * -------
     * Methods
     * -------
     */

    /**
     * Main system starts up right here
     */
    public static void main(String[] argv)
    {
        try
        {       

            iPhilosophers = philosopherNumber();

            //If argument specified, attempt to set philosopher number
            if(argv.length > 0)
            {
                    try
                    {
                        iPhilosophers = Integer.parseInt(argv[0]);
                        if(iPhilosophers < 1)
                        {
                                throw new Exception("Negative or zero input");
                        }
                    }
                    catch(Exception e)
                    {
                            System.out.println(argv[0] + " is not a positive decimal integer");
                            System.out.println("Usage: java DiningPhilosophers [NUMBER_OF_PHILOSOPHERS]");
                            System.exit(1);	
                    }
            }

            // Make the monitor aware of how many philosophers there are

            soMonitor = new Monitor(iPhilosophers);

            // Space for all the philosophers
            Philosopher aoPhilosophers[] = new Philosopher[iPhilosophers];

            // Let 'em sit down
            for(int j = 0; j < iPhilosophers; j++)
            {
                    aoPhilosophers[j] = new Philosopher();
                    aoPhilosophers[j].start();
            }

            System.out.println
            (
                    iPhilosophers +
                    " philosopher(s) came in for a dinner."
            );

            // Main waits for all its children to die...
            // I mean, philosophers to finish their dinner.
            for(int j = 0; j < iPhilosophers; j++)
                    aoPhilosophers[j].join();

            System.out.println("All philosophers have left. System terminates normally.");
        }
        catch(InterruptedException e)
        {
                System.err.println("main():");
                reportException(e);
                System.exit(1);
        }
    } // main()

    /**
     * Outputs exception information to STDERR
     * @param poException Exception object to dump to STDERR
     */

    //Take input for phisopher number
    public static int philosopherNumber(){
        int input = 0;
        sc = new Scanner(System.in);
        System.out.println("Enter the number of philosocher you want.");
        try{
            if(sc.nextLine().isEmpty()){
                input = DEFAULT_NUMBER_OF_PHILOSOPHERS;
            }
            else if(sc.hasNextInt()){
                input = sc.nextInt();
            } else {
                input = -1;
            }

            while(input <= 0){
                System.out.println(input + " is not a positive integer");
                if(sc.nextLine().isEmpty()){
                    input = DEFAULT_NUMBER_OF_PHILOSOPHERS;
                }
                else if(sc.hasNextInt()){
                    input = sc.nextInt();
                } else {
                    input = -1;
                }
            }
        } catch(NumberFormatException e){
            System.out.println("The input is not valid");
        }
        return input;
    }


    public static void reportException(Exception poException)
    {
            System.err.println("Caught exception : " + poException.getClass().getName());
            System.err.println("Message          : " + poException.getMessage());
            System.err.println("Stack Trace      : ");
            poException.printStackTrace(System.err);
    }
}

// EOF