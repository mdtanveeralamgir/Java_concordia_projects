import common.BaseThread;

/**
 * Class Philosopher.
 * Outlines main subroutines of our virtual philosopher.
 */
public class Philosopher extends BaseThread
{
	/**
	 * Max time an action can take (in milliseconds)
	 */
	public static final long TIME_TO_WASTE = 1000;

        /*Display a philosopher started eating
        yield
        Sleep for an interval
        yield again
        display their eating is done
        */
	public void eat()
	{
            try
            {
                System.out.println("Philosopher " + iTID + " has started eating");
                yield();
                sleep((long)(Math.random() * TIME_TO_WASTE));
                yield();
                System.out.println("Philosopher " + iTID + " has finished eating");
            }
            catch(InterruptedException e)
            {
                System.err.println("Philosopher.eat():");
                DiningPhilosophers.reportException(e);
                System.exit(1);
            }
	}

        /*Display a philosopher started thinking
        yield
        Sleep for an interval
        yield again
        display their thinking is done
        */
	public void think()
	{
            try
            {
                System.out.println("Philosopher " + iTID + " has started thinking");
                yield();
                sleep((long)(Math.random() * TIME_TO_WASTE));
                yield();
                System.out.println("Philosopher " + iTID + " has finished thinking");
            }
            catch(InterruptedException e)
            {
                System.err.println("Philosopher.thinking():");
                DiningPhilosophers.reportException(e);
                System.exit(1);
            }
	}

        /*Display a philosopher started talking
        yield
        Sleep for an interval
        yield again
        display their talking is done
        */
	public void talk()
	{
            try
            {
                System.out.println("Philosopher " + iTID + " has started talking");
                yield();
                saySomething();
                sleep((long)(Math.random() * TIME_TO_WASTE));
                yield();
                System.out.println("Philosopher " + iTID + " has finished talking");
            }
            catch(InterruptedException e)
            {
                System.err.println("Philosopher.talking():");
                DiningPhilosophers.reportException(e);
                System.exit(1);
            }
	}

	/**
	 * No, this is not the act of running, just the overridden Thread.run()
	 */
	public void run()
	{
            for(int i = 0; i < DiningPhilosophers.DINING_STEPS; i++)
            {
                DiningPhilosophers.soMonitor.pickUp(getTID());

                eat();

                DiningPhilosophers.soMonitor.putDown(getTID());

                think();

                //20% chance to want to say something
                if((int)(Math.random() * 5.0) == 1)
                {
                    DiningPhilosophers.soMonitor.requestTalk();

                    talk();

                    DiningPhilosophers.soMonitor.endTalk();

                }

                yield();
            }
	} // run()

	/**
	 * Prints out a phrase from the array of phrases at random.
	 */
	public void saySomething()
	{
		String[] astrPhrases =
		{
                    "My number is " + getTID() + ""
		};

		System.out.println
		(
                    "Philosopher " + getTID() + " says: " +
                    astrPhrases[(int)(Math.random() * astrPhrases.length)]
		);
	}
}

// EOF