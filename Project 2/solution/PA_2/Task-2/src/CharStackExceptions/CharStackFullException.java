/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CharStackExceptions;

public class CharStackFullException extends Exception
{
           public CharStackFullException()
          {
                  super ("Char Stack has reached its capacity of CharStack.MAX_SIZE.");
           }
}
