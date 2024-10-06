package project2;

import java.io.File;
import java.util.Date;
import java.lang.Math;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

/**
 * This class is the program that displays queries.
 * This program is interactive.
 * When the program is executed the name of the input file containing the list of all the records.
 * The data in this file serves as a database of all records
 * To interact with the program, the user can run three possible queries: first USERNAME, last USERNAME, or quit.
 * first USERNAME displays the information about the first login session for the user.
 * last USERNAME displays the informaton about the last login session for the user.
 * When the user types "quit" the progam will terminate
 *
 * @author Michael Hu
 * @version 9/27/2024
 */

public class LoginStats{

    public static void main(String[] args){
        
        
        if (args.length == 0 ) {
            System.err.println("Usage Error: the program expects file name as an argument.\n");
            System.exit(1);
        }

        //verify that command line argument contains a name of an existing file
        File recordFile = new File(args[0]);
        if (!recordFile.exists()) {
            System.err.println("Error: the file "+recordFile.getAbsolutePath()+" does not exist.\n");
            System.exit(1);
        }
        if (!recordFile.canRead()) {
            System.err.println("Error: the file "+recordFile.getAbsolutePath()+
                               " cannot be opened for reading.\n");
            System.exit(1);
        }

 //open the file for reading
        Scanner inRecords = null;

        try {
            inRecords = new Scanner (recordFile ) ;
        } catch (FileNotFoundException e) {
            System.err.println("Error: the file "+recordFile.getAbsolutePath()+
                               " cannot be opened for reading.\n");
            System.exit(1);
        }

        //read the content of the file and save the data in a list of named colors
        RecordList list = new RecordList();
        String line = null;
        Scanner parseLine = null;
        int terminal = 0;
        boolean login = true;
        String username = null;
        String timeString = null;
        Record current = null;
        Date time = null;
        while (inRecords.hasNextLine()) {
            try {
                line = inRecords.nextLine();
                parseLine = new Scanner(line);
                parseLine.useDelimiter(" ");
                terminal = parseLine.nextInt(); 
                timeString = parseLine.next();
                username = parseLine.next();
                time = new Date(Long.parseLong(timeString));
                System.out.println(terminal);
            }
            catch (NoSuchElementException ex ) {
                //caused by an incomplete or miss-formatted line in the input file
                System.err.println(line);
                continue;
            }
            if(terminal < 0){
                login = false;
            }
            else{
                login = true;
            }
            terminal = Math.abs(terminal);
            try {
                System.out.print(terminal);
                System.out.print(login);
                System.out.print(username);
                System.out.print(time.toString());
                current = new Record (terminal, login, username, time);
                list.add(current);
            }
            catch (IllegalArgumentException ex ) {
                //ignore this exception and skip to the next line
            }
        }

        //interactive mode:

        Scanner userInput  = new Scanner (System.in);
        String userValue = "";
        String firstLast = "";
        String inputStr = "";
        do {
            System.out.println("Enter first USERNAME or last USERNAME for the first or last session of that user. Type \"quit\" to exit");
            //get value of from the user
            inputStr = userInput.nextLine();
            String[] splitStr = inputStr.trim().split(" ");
            firstLast = splitStr[0];
            userValue = splitStr[1];
            System.out.println(firstLast);
            if (!firstLast.equalsIgnoreCase("quit")) {
                if(firstLast.equalsIgnoreCase("first")){
                        Session c = list.getFirstSession(userValue);
                        System.out.println(c.toString());
                }

                if(firstLast.equalsIgnoreCase("last")){
                        Session c = list.getLastSession(userValue) ;
                        System.out.println(c.toString());
                }
            }
        } while (!firstLast.equalsIgnoreCase("quit"));

        userInput.close();

    }
}

    
