
package poe;

import java.util.ArrayList;

public class Login {
    
    private String Usersname ="";
    private String password = "";
    private String CellNumber = "";
    private ArrayList<String[]> UserList = null;

    public Login(String name,String password, String CellNumber)
    {
        //Initialise the global variables
        this.CellNumber = CellNumber;
        this.Usersname = name;
        this.password = password;
        this.UserList = new ArrayList<>();
    }
    
    public boolean checkUserName()
    {
        int tempNameLength = this.Usersname.length(); //The length of the username
        
        if(tempNameLength <= 5 &&this.Usersname.contains("_") ==true)
        {
            System.out.println("Username  successfully captured.");
            return true; //Conditions been met.
        }else
        {
            System.err.println("Username is not correctly formatted; please ensure that "
                    + "your username contains an underscore and is no more than five characters in length.");
            return false; //not met the min requirements
        }
    }
   