/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
import java.util.*;
import java.io.*;
import java.math.*;


class Problems{
    public static void main(String []argh)throws Exception
    {



        Scanner sc = new Scanner(System.in);
        int t=sc.nextInt();

        for(int i=0;i<t;i++)
        {

            try
            {
                long x=sc.nextLong();
                System.out.println(x+" can be fitted in:");
                if(x>=-128 && x<=127)System.out.println("* byte");
                if(x>=-32768 && x<=32767)System.out.println("* short");
                if(x>= (-1)*Math.pow(2,32) && x<=Math.pow(2,31)-1)System.out.println("* int");
                if(x>=(-1)*Math.pow(2,63) && x<=Math.pow(2,63)-1)System.out.println("* long");
                if(x>Math.pow(2,63)&& x<(-1)*Math.pow(2,63))throw new Exception("can't be fitted anywhere.");
                //Complete the code
            }
            catch(Exception e)
            {
                System.out.println(sc.next()+" can't be fitted anywhere.");
            }

        }
    }
}