package com.company;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Main {
    //Functions
    public static void main(String[] args) {
        //Setup our variables
        boolean isCompressing = IsCompressing(args[0]);
        String pathToFile = args[1];

        //We need to compress the file
        if(isCompressing)
            Compress(pathToFile, pathToFile + ".gz");

        //Now we need to decompress the file
        if(!isCompressing)
            Decompress(pathToFile + ".gz", pathToFile + ".txt");
    }

    private static boolean IsCompressing(String areWeCompressing){
        //Check if the string told us to compress the file
        if(areWeCompressing.equals("compress")){
            return true;
        }

        //It is something else, decompress it
        return false;
    }

    private static void Decompress(String inputFile, String outputFile){
        //We need to try and open the file and decompress it
        try {
            //Get our input stream from the file input stream
            InputStream inputStream = new FileInputStream(inputFile);
            GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);

            //We need to create an output stream
            OutputStream outputStream = new FileOutputStream(outputFile);

            //We need to go through the bytes of the file
            int ch;
            while((ch = gzipInputStream.read()) > 0) {
                //Write the character to the output stream
                outputStream.write(ch);
            }

            //We need to close our shhtuff
            gzipInputStream.close();
            inputStream.close();
            outputStream.close();
        }catch(IOException ioe){
            //WE messed up, print out our stack track
            System.out.println("IOE Exception caught");
            ioe.printStackTrace();
        }
    }

    private static void Compress(String inputFile, String outputFile){
        //We need to try and open the file and decompress it
        try {
            //Get our input stream from the file input stream
            InputStream inputStream = new FileInputStream(inputFile);

            //We need to create an output stream
            OutputStream outputStream = new FileOutputStream(outputFile);
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);

            //We need to go through the bytes of the file
            int ch;
            while((ch = inputStream.read()) > 0) {
                //Write the character to the output stream
                gzipOutputStream.write(ch);
            }

            //We need to close anmd finish our shhtuff
            gzipOutputStream.finish();
            gzipOutputStream.close();
            inputStream.close();
            outputStream.close();
        }catch(IOException ioe){
            //WE messed up, print out our stack track
            System.out.println("IOE Exception caught");
            ioe.printStackTrace();
        }
    }
}
