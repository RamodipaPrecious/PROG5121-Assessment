/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poe;

import java.util.Scanner;

public class POE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            
      System.out.println("=======Input user data  ============");
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
     
        
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.err.println("An error occured while inputting");
                
	}
         
    }    
    
}
