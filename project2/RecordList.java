package project2;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class is used to store all Record objects.
 * This class inherits all of its properties from an ArrayList<Record>.
 * adds functions that allow session search by user
 *
 * @author Michael Hu
 * @version 9/27/2024
 */

public class RecordList extends ArrayList<Record>{   
    
    /**
    * default constructor that creates an empty RecordList object.
    */
    public RecordList(){
        super();
    } 

    /** 
    * constructs and returns the first login session for the specified user. 
    * If there are multiple login sessions for the specified user, the first one is the one with the earliest login time.
    * @return the first login session for the specified user.
    * @param user String value which is the user which we are searching for their sessions.
    * @throws NoSuchElementException if user is not in the list.
    * @throws IllegalArgumentException if user is a null or empty string.
    */
    public Session getFirstSession(String user){
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User cannot be empty");
        }
        Record firstLogin = null;
        Record firstLogout = null;

        for (Record record : this) {
            if (record.getUsername().equals(user)) {
                if (record.isLogin()) {
                    if (firstLogin == null || record.getTime().before(firstLogin.getTime())) {
                        firstLogin = record;
                    }
                } else if (record.isLogout()) {
                    if (firstLogout == null || record.getTime().before(firstLogout.getTime())) {
                        firstLogout = record;
                    }
                }
            }
        }

        if (firstLogin == null) {
            throw new NoSuchElementException("No session found for user: " + user);
        }

        return new Session(firstLogin, firstLogout); // If no logout, the Session constructor will handle it
    }

    /** 
    * returns the last login session for the specified user.
    * If there are multiple login session for the specified user, the last one is the one with the latest login time.
    * @return the last login session for the specified user.
    * @param user String value which is the user which we are searching for their sessions.
    * @throws NoSuchElementException if user is not in the list.
    * @throws IllegalArgumentException if user is a null or empty string.
    */
    public Session getLastSession(String user){
        
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User cannot be empty");
        }

        Record lastLogin = null;
        Record lastLogout = null;

        for (Record record : this) {
            if (record.getUsername().equals(user)) {
                if (record.isLogin()) {
                    if (lastLogin == null || record.getTime().after(lastLogin.getTime())) {
                        lastLogin = record;
                    }
                } else if (record.isLogout()) {
                    if (lastLogout == null || record.getTime().after(lastLogout.getTime())) {
                        lastLogout = record;
                    }
                }
            }
        }

        if (lastLogin == null) {
            throw new NoSuchElementException("No session found for user: " + user);
        }

        return new Session(lastLogin, lastLogout); // If no logout, the Session constructor will handle it
    }

}     