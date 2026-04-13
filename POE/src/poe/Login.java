
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
    public String registerUser()
    {
     if(this.checkUserName()) //Validate if the username is correct
     {
         
         if(this.checkCellPhoneNumber()) //Validate if the Cell number is correct and is local
         {
             if(this.checkPasswordComplexity())
             {
                //Save the user information
                 String[] tempUser ={this.Usersname,this.password,this.CellNumber};
                 this.UserList.add(tempUser); //then Store in the database
                 return "Cell phone number succesfully added.";
             }
         }
     }
     return "Cell phone number incorrectly formatted or does not contain international code. " ;
     
    
    }
    
    public boolean loginUser(String Username,String Password)
    {
        String[] userArray =new String[4];
        boolean isFound =false; //validation flag.
        
        if(!UserList.isEmpty()) //Error 
        {
              for(String[] CurrentArrUser : UserList ) //Loop every entry of the arraylist
        {
            if(CurrentArrUser[0].contentEquals(Username) && CurrentArrUser[1].contentEquals(Password))
            {
               userArray = CurrentArrUser; //Retrive the user.
               isFound =true;
               return isFound; //User is found
            }
        }
        }
        
        return isFound; //Will return false if user is not found

      
    }
    
   
