package project2;

import java.util.Date;

/**
 * This class represents a single login session consisting of two Record objects
 * representing the login and logout record.
 *
 * @author Michael Hu
 * @version 9/27/2024
 */

public class Session{
    
    private Record login;
    private Record logout;


    /**
    * constructor that validates the information and creates a session object with login and logout records.
    * @param login record that matches logout record.
    * @param logout record that matches login record.
    * @throws IllegalArgumentException when login record is null.
    */
    public Session(Record login, Record logout) throws IllegalArgumentException{
        if(login != null && logout == null){
            this.login = login;
            this.logout = null;
        }
        else if(!login.getUsername().equals(logout.getUsername())){
                throw new IllegalArgumentException("Invalid pair of records: usernames do not match!")
        }
        else if(!login.getTerminal().equals(logout.getTerminal())){
                throw new IllegalArgumentException("Invalid pair of records: terminals do not match!")
        }
        else{
            this.login = login;
            this.logout = logout;
        }
    }

    /** 
    * Returns the terminal of the Session object.
    * @return The terminal of the Session object.
    */
    public int getTerminal(){
        return this.login.getTerminal();
    }
    /** 
    * Returns the login time of the Session object.
    * @return The login time of the Session object.
    */
    public Date getLoginTime(){
        return this.login.getTime()
    }
    /** 
    * Returns the logout time of the Session object.
    * @return The logout time of the Session object.
    */
    public Date getLogoutTime(){
        return this.logout.getTime();
    }
    /** 
    * Returns the username of the Session object.
    * @return The username of the Session object.
    */
    public String getUsername(){
        return this.login.getUsername();
    }
    /** 
    * returns the number of milliseconds ellapsed between the login time and logout time, 
    * or -1 if the session is still active.
    * @return the number of milliseconds ellapsed between the login time and logout time, 
    * or -1 if the session is still active.
    */
    public long getDuration(){
        if(logout == null){
            return -1;
        }
        return this.login.getLogintime() - this.logout.getLogoutTime();
    }
    
    /**
    * Returns a string representation of the Session object.
    * @returns A string represenation of the Session object.
    */
    @Override
    public String toString(){
        String line1 = String.format("      %s, terminal %d, duration %t", getUsername(), getTerminal(), getDuration());
        String line2 = String.format("  logged in: %t", getLoginTime());
        String line3 = String.format("  logged out: %t", getLogout());
        return line1 + "\n" + line2 +"\n" + line3;
    }
}
