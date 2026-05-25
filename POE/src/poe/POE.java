/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poe;

import java.util.Scanner;

public class POE {

 
    
    public static void main(String[] args) {
       
        while(true){   
        try {
         
      System.out.println("=======Register user  ============");
      System.out.println("What is your username");
      Scanner Nameinput = new Scanner(System.in);
      String username = Nameinput.nextLine();
      
      System.out.println("==============================");
      System.out.println("What is your Password");
      Scanner Passwordinput = new Scanner(System.in);
      String password = Passwordinput.nextLine();
      
       System.out.println("==============================");
      System.out.println("what is your cell Number");
      Scanner Cellinput = new Scanner(System.in);
      String cellNumber = Cellinput.nextLine();
        
      
     Login user = new Login(username,password,cellNumber);
     String registrationResult = user.registerUser();
     System.out.println(registrationResult);
     
    if(registrationResult.contains("Is registed"))
    {
     System.out.println("================Log user==============");
     System.out.println("What is your username");
     Scanner U_Name = new Scanner(System.in);
     String getName = U_Name.nextLine();
    
      System.out.println("What is your Password");
      Scanner U_password = new Scanner(System.in);
      String getPassword = U_password.nextLine();
    
     user.loginUser(getName, getPassword);
     System.out.println("##################");
     String message = user.returnloginStatus(getName, password);
     
     //After login-in
     if(message.contains("Welcome to QuickChat messages"))
     {
              boolean QuitProgram = false;
              
              
              //Run until the user chooses to quit the program
         while(QuitProgram ==false)
         {
      System.out.println("Main menu: choose the following \n a)Send Messages \n b)Show  recently sen messages \n c)Quit ");
      Scanner Scan_manu = new Scanner(System.in);
      
      //Convert the input to lower case to avoid errors
      String Get_option = Scan_manu.nextLine();
      String Manu_option = Get_option.toLowerCase(); //
      
       String number = user.getCellNumber(); //Get the user cell number
       
          MessageClass messagesHandler = new MessageClass(number);
          
          
      if(Manu_option.equals("a"))
      {
         messagesHandler.SendMessage();
          
      }
      else if(Manu_option.equals("b"))
      {
      
      }
       else if(Manu_option.equals("c"))
      {
      //The user choose to quit
          QuitProgram =true; 
      }else
       { 
           System.err.println("Chose the wrong option try again!");
           System.err.println(Manu_option);
       }
         
     }
     }
    }
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.err.println("An error occured while menu inputting");
                
	}
    }
    }
        
        
    
}
