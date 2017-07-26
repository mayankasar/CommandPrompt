
package main;

import java.io.*;
import java.util.Scanner;
public class Main 
{
    public static void main(String args[])
    {
        try{
            ProcessBuilder builder = new ProcessBuilder("cmd.exe");
            Process p = builder.start();
            
            
            PrintWriter out = new PrintWriter(p.getOutputStream());
            out.println("dir");
            out.flush();
            
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            Scanner sc = new Scanner(r);
            String line;
            while (sc.hasNext()) {
                line = sc.nextLine();
                System.out.println(line);
            }
            
            out.println("exit");
            out.flush();
            out.close();
            sc.close();
        }
        catch(Exception e){
            System.out.println("Error:"+e);
        }
    }
}
