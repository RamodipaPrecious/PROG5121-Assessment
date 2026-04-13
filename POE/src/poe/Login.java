
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
    public boolean checkPasswordComplexity()
    {
       //Store the checked information.
        int CountLength = this.password.length();
        
        //The conditions that are checked.
        //----------------------------------------------------
        //1. Check the length of the password
        //2. Contains acapital number 
        //3. Contains a number 
        //4. Check if it has special Charecters
        
        //Using regex pattern to read the password
        String ALLSpecialChar ="^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$";
       
       boolean Allconditions_Met =false; //Check if All conditions are true
              
      
      //Check all the condition if meet the min requiremets
          if(this.password.matches(ALLSpecialChar)) //If true
          {
                  Allconditions_Met =true;
                  System.out.println("Password successfully captured.");
                  return Allconditions_Met; 
              
          }else
          {
              System.err.println("Password is not correctly formatted; please ensure that the password "
                      + "contains at least eight characters; a capital letter, a number, and a special"
                      + " character.");
              return Allconditions_Met;
          }
     
    }
    public boolean checkCellPhoneNumber()
    {
       
   
        int lengthNumber = this.CellNumber.length(); //Length of cell number.
        
        if(this.CellNumber.startsWith("+27") && lengthNumber == 12)
        {
            return true;
        }
        else if(this.CellNumber.startsWith("0"))
        {
            System.err.println("The cell number does not follow the of the local number");
             return false;
        }
        else
        {
            System.err.println("Incorrect Number");
            return false;
        }
        
  
    }
   