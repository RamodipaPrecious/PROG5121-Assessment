
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
}
    
    
