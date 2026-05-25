/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class MessageClass {
    private String MessageID ;
    private String userCellNumber ="";
    private String message ="";
    
     //JSONObject details = new JSONObject();
    private ArrayList<String> userMessages = null;
    
    public MessageClass(String cell)
    {
        this.userCellNumber = cell;
        userMessages = new ArrayList<>();
    }
    
    public boolean checkMessageID()
    {
        if(MessageID.length() < 10) //If the ID length is not long enough
        {
            return false;
        }else if(MessageID.length() == 0) //If it does not have a message ID
        {
            return false;
        }else
        {
        return true;
        }
                
    }
    
    public String checkRecipientCell()
    {
        if(!this.userCellNumber.isEmpty()) //IF the cell number is stored
        {
            if(userCellNumber.startsWith("+27")) //Starts with a code
            {
                int CellLength =userCellNumber.length()-3; //Count starting after +27
                if(CellLength <10) //Cell number is no more than 10 charecters
                {
                    return "Approved Cell number";
                }
            }   
        }else
        {
            //Number not available.
            return "Not Approved Cell number";
        }
            return "Not Approved Cell number";
    }
    public String SendMessage()   
    {
        
       //Get number of messages the user wants to send message send
        System.out.println("How many message do you want to send.");
         Scanner messageLoop = new Scanner(System.in);
         String getLoops = messageLoop.nextLine();
          
         int numberOfLoops = Integer.parseInt(getLoops); //Convert into a number.
         
         for(int index =0; index <numberOfLoops; index++)
         {
             //Send User input message
             int messageNumber =index+1;
         System.out.println("Type message sent number "+messageNumber );
         Scanner SendMessage = new Scanner(System.in);
         String Temp_message = SendMessage.nextLine();
         
                 
         //If the mesage format does not meet req
          if(Temp_message.length() <=250)
         {   
            int ID = this.RandomGenerator(10);
            MessageID = String.valueOf(ID);
            
            //Check if the mesage ID meet the requirements
            if(this.checkMessageID() ==true)
            {
             String Messagedata = String.valueOf(ID)+":"+Temp_message;
             this.userMessages.add(Messagedata); //temporary store the message send
             System.out.println("Message successfully send.");
            }else
            {
                System.err.println("Message Unsuccessfully send.");
            }
            
             
         //Chose what to do with the message
       System.out.println("Choose which message option \n 1.Store message \n 2.Diregard message");       
       Scanner ChooseOption = new Scanner(System.in); 
        String getOption = ChooseOption.nextLine();
             
        if(getOption.equals("1")) //Store message
        {
      this.storeMessage();
       
           
        }else if(getOption.equals("2")) //Diregard message
        {
            System.out.println("Press 0 to delete message.");
           Scanner Sc_delete  = new Scanner(System.in); 
            String DeleteMessage = Sc_delete.nextLine();
            
            do{
                if(DeleteMessage.equals("0"))
                {        
                    
                    message = ""; 
                }
            }while(!DeleteMessage.equals("0"));
            
           
        }else
        {
            System.err.println("Choose the wrong option, try again" );
        }
             
      }else
     {
             System.err.println("Please eneter a message of less than 250 characters.");
     } 
      }
   
        return "Chose incorrect option, Try again";
    }
    private boolean WriteToFile(String fileName)
    {
        //Write the dat to the file
         String DataFormat ="";
         
         //Read every line of the mesage before storing the data
    for(String MessageLine: this.userMessages)
    {
        DataFormat += MessageLine +"\n"; //each message is stored on seperate lines
    }
         
           try (FileWriter USerData = new FileWriter(fileName)) {
          
               //Store the data
            USerData.write(DataFormat);
            return true;

        } catch (IOException e) {
                return false;

        }
    }

private String ReadFromFile(String fileName)
{
    String FileData = "";    
    
     try (BufferedReader Reader = new BufferedReader(new FileReader(fileName))) {

            String line;
      
            
            //While the is more data in the next line
            while ((line = Reader.readLine()) != null) {
                //Sepearte the data read by a charecter
                FileData += line +"@";
            }
            //IF all the data is stored
            return FileData;

        } catch (IOException e) {
            System.err.println("Error when retrieving the messages");
            return "Error reading";
        }  
     
}

public void storeMessage()
{
    //"userMessages.JSON is the file that will store the data
            boolean isStored = this.WriteToFile("userMessages.JSON");
            if(isStored ==true)
            {
               System.out.println("Message succesfully stored."); 
            }else
            {
                System.err.println("Message Unsuccesfully stored.");
            }
}

public String printMessages()
{
    //Read the file data for messages
    String AllMessages = this.ReadFromFile("userMessages.JSON");
    
    //Slit the messages apart
     String[] arrMesage = AllMessages.split("@");
     
     for(String Current_Message : arrMesage)
     {
         System.out.println(Current_Message);
         
     }
    return AllMessages;
    
}

public int returnTotalMessages()
{
    //Read the file data for messages
    String AllMessages = this.ReadFromFile("userMessages.JSON");
    
    int NumberOfMessages = 0;
    //Slit the messages apart
     String[] arrMesage = AllMessages.split("@");
     
     //Count the messages 
     int trackloop =0;
     while(trackloop <arrMesage.length)
     {
       NumberOfMessages++;
       trackloop++;
     }
 
    return NumberOfMessages;
    
}


private int RandomGenerator(int Number)
{
    //randomly generate a number from the given range
    Random range = new Random(Number);
    return range.nextInt();
}

}
        