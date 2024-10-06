package project2;

import java.io.File;

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

    
    }
    
}