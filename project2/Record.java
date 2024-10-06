package project2;

import java.util.Date;

/**
 * This class represents an individual record from the input log file consisting of
 * a terminal, time, and username.
 *
 *
 * @author Michael Hu
 * @version 9/27/2024
 */

public class Record{
  
    private int terminal;
    private boolean login;
    private String username;
    private Date time;
    
    /**
    * Constructor that validates the information and creates the record object with specified
    * terminal, login, username, and time components.
    * @param terminal positive integer value which is the terminal for the record.
    * @param login boolean value that is true when it is a login record and false when it is a logout record.
    * @param username String value which is the username for the record.
    * @param time Date value which is the date and time at which the user logged in or logged out.
    * @throws IllegalArgumentException if terminal value is not a positive integer.
    */
    public Record(int terminal, boolean login, String username, Date time) throws IllegalArgumentException{
        if (terminal <= 0) {
            throw new IllegalArgumentException("Terminal value is invalid");
        }
        this.terminal = terminal;
        this.login = login;
        this.username = username;
        this.time = time;
    }
    /**
    * Returns the terminal of this Record object.
    * @return The terminal of this Record object.
    */
    public int getTerminal(){
        return terminal;
    }
    /**
    * Returns true if the Record is a login.
    * @return true if the Record is login.
    */
    public boolean isLogin(){
        return login;
    }
    /**
    * Returns true if the Record is a logout.
    * @return true if the Record is a logout.
    */
    public boolean isLogout(){
        return !login;
    }
    /**
    * Returns the username of this Record object.
    * @return the username of this Record object.
    */
    public String getUsername(){
        return username;
    }
    /**
    * Returns the time of this Record object.
    * @return the time of this Record object.
    */
    public Date getTime(){
        return time;
    }



}