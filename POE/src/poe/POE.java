
package poe;

import java.util.Scanner;

public class POE {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        while (true) {

            try {

                // =========================
                // REGISTER USER
                // =========================
                System.out.println("======= Register User =======");

                System.out.print("Enter First Name: ");
                String firstName = input.nextLine();

                System.out.print("Enter Last Name: ");
                String lastName = input.nextLine();

                System.out.print("Enter Username: ");
                String username = input.nextLine();

                System.out.print("Enter Password: ");
                String password = input.nextLine();

                System.out.print("Enter Cell Number: ");
                String cellNumber = input.nextLine();

                Login user = new Login(
                        firstName,
                        lastName,
                        username,
                        password,
                        cellNumber
                );

                String registrationResult = user.registerUser();

                System.out.println(registrationResult);

                if (registrationResult.contains("Is registed")) {

                    // =========================
                    // LOGIN USER
                    // =========================
                    System.out.println("\n======= Login User =======");

                    System.out.print("Enter Username: ");
                    String getName = input.nextLine();

                    System.out.print("Enter Password: ");
                    String getPassword = input.nextLine();

                    String loginMessage = user.returnloginStatus(getName, getPassword);

                    if (loginMessage.contains("A successfull login")) {
                        System.out.println(
                         "\nWelcome "
                         + user.getFirstName()
                         + " "
                         + user.getLastName()
                         + ". It is great to see you again.");

                        System.out.println("\nWelcome to QuickChat");

                        MessageClass messagesHandler =
                                new MessageClass(user.getCellNumber());

                        boolean quitProgram = false;

                        while (!quitProgram) {

                            System.out.println(
                                    "\nMain Menu"
                                    + "\n a) Send Messages"
                                    + "\n b) Show Recently Sent Messages"
                                    + "\n c) Stored Messages"
                                    + "\n d) Quit"
                            );

                            String menuOption = input.nextLine().toLowerCase();

                            // =========================
                            // OPTION A - SEND MESSAGE
                            // =========================
                            if (menuOption.equals("a")) {

                                System.out.println(
                                        messagesHandler.sendMessage(input)
                                );
                            }

                            // =========================
                            // OPTION B - RECENT MESSAGES (FINAL FIX)
                            // =========================
                            else if (menuOption.equals("b")) {

                                messagesHandler.showRecentMessages();
                            }

                            // =========================
                            // OPTION C - STORED MESSAGES MENU
                            // =========================
                            else if (menuOption.equals("c")) {

                                boolean storedMenu = true;

                                while (storedMenu) {

                                    System.out.println(
                                            "\nStored Messages Menu"
                                            + "\n1. Display Stored Messages"
                                            + "\n2. Display Longest Message"
                                            + "\n3. Search By Message ID"
                                            + "\n4. Search By Recipient"
                                            + "\n5. Delete Message By Hash"
                                            + "\n6. Display Report"
                                            + "\n7. Back"
                                    );

                                    String choice = input.nextLine();

                                    if (choice.equals("1")) {

                                        messagesHandler.displayStoredMessages();

                                    } else if (choice.equals("2")) {

                                        System.out.println(
                                                messagesHandler.displayLongestMessage()
                                        );

                                    } else if (choice.equals("3")) {

                                        System.out.print("Enter Message ID: ");
                                        String id = input.nextLine();

                                        messagesHandler.searchByMessageID(id);

                                    } else if (choice.equals("4")) {

                                        System.out.print("Enter Recipient Number: ");
                                        String recipient = input.nextLine();

                                        messagesHandler.searchByRecipient(recipient);

                                    } else if (choice.equals("5")) {

                                        System.out.print("Enter Message Hash: ");
                                        String hash = input.nextLine();

                                        messagesHandler.deleteMessage(hash);

                                    } else if (choice.equals("6")) {

                                        messagesHandler.displayReport();

                                    } else if (choice.equals("7")) {

                                        storedMenu = false;

                                    } else {

                                        System.out.println("Invalid option.");
                                    }
                                }
                            }

                            // =========================
                            // OPTION D - QUIT
                            // =========================
                            else if (menuOption.equals("d")) {

                                System.out.println("Thank you for using QuickChat.");
                                quitProgram = true;

                            } else {

                                System.out.println("Invalid option.");
                            }
                        }

                    } else {

                        System.out.println("Login Failed.");
                    }
                }

            } catch (Exception e) {

                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }
}

