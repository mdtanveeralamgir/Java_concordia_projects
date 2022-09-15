/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CharStackExceptions;

public class CharStackInvalidSizeException extends Exception
{
          public CharStackInvalidSizeException()
          {
                  super("Invalid stack size specified.");
          }
          public CharStackInvalidSizeException (int piStackSize)
          {
                  super ("Invalid stack size specified: " + piStackSize);
           }
}
