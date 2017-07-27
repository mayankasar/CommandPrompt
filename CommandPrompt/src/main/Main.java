package main;

import java.io.*;
import java.util.Scanner;
public class Main 
{
    public static void main(String args[])
    {
        execute("date");
    }
    
    public static void execute(String cmd)
    {
        try
        {
            System.out.println("java>"+cmd);
            Process p = Runtime.getRuntime().exec("cmd.exe /c "+cmd);
            
            StreamReader error = new StreamReader(p.getErrorStream());
            StreamReader output = new StreamReader(p.getInputStream());
            StreamWriter input = new StreamWriter(p.getOutputStream());
            
            output.start();
            error.start();
            input.start();            
        }
        catch(Exception e){
            System.out.println("Error:"+e);
        }            
    }
}

class StreamReader extends Thread
{
    InputStream in;
    
    public StreamReader(InputStream in){
        this.in = in;
    }
    
    @Override
    public void run()
    {
        try
        {
            BufferedReader bis = new BufferedReader(new InputStreamReader(in));
         
            int ch;
            while( (ch=bis.read()) != -1 )
            {
                System.out.print((char)ch);
            } 
        }
        catch(Exception e)
        {
            System.out.println("Error:"+e);
        }
    }
}

class StreamWriter extends Thread
{
    OutputStream out;
    
    public StreamWriter(OutputStream out){
        this.out = out;
    }
    
    @Override
    public void run()
    {
        try
        {
            /**
             * Take Input from KeyBoard and Print to Output Stream
             */
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter writer = new PrintWriter(out);
            
            String line = null;
            while ( (line = br.readLine()) != null)
            {
                writer.write(line);                              
            }
                
            writer.close();            
        }
        catch(Exception e)
        {
            System.out.println("Error:"+e);
        }
    }
}
