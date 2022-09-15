// Source code for semaphore class:   

class Semaphore 
{
         private int value;
         public Semaphore(int value)
         {
            this.value = value;
         }
        public Semaphore()
        {
                 this(0);
         }
        public synchronized void Wait()
        {
            this.value--; 
            //The semaphore value has to be at the beginning so the threads 
            //decrement the value first before waiting
            if (this.value < 0)
            //replaced while loop with if statement.
            //It will prevent same process waiting again
            //Also the this.value has to be < 0. 
            //Because 0 means at the start it was 1 and the current process made it 0
            {
                try
               {
                      wait();
                }
               catch(InterruptedException e)
               {
                        System.out.println ("Semaphore::Wait() - caught InterruptedException: " + e.getMessage() );
                        e.printStackTrace();
                   }
              }
           }
           public synchronized void Signal()
           {
                ++this.value;
                notify();
           }
           public synchronized void P()
           {
                this.Wait();
           }
          public synchronized void V()
          {
                this.Signal();
          }
}