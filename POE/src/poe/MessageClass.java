
       package poe;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MessageClass {

    private String MessageID;
    private final String userCellNumber;
    private String message;

    private final ArrayList<String> userMessages;

    // Message tracking
    private final ArrayList<String> sentMessages = new ArrayList<>();
    private final ArrayList<String> disregardedMessages = new ArrayList<>();

    // Stored messages
    private final ArrayList<String> storedMessages = new ArrayList<>();
    private final ArrayList<String> storedMessageIDs = new ArrayList<>();
    private final ArrayList<String> storedMessageHashes = new ArrayList<>();
    private final ArrayList<String> storedRecipients = new ArrayList<>();
    private final ArrayList<String> storedSenders = new ArrayList<>();

    public MessageClass(String userCellNumber) {
        this.userCellNumber = userCellNumber;
        this.userMessages = new ArrayList<>();
    }

    // =========================
    // RECIPIENT VALIDATION
    // =========================
    public boolean checkRecipientCell(String recipient) {

        if (recipient.startsWith("+27") && recipient.length() == 12) {

           
            System.out.println("Cell phone number captured");

            return true;

        } else {

           
            System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");

            return false;
        }
    }

    // =========================
    // ID GENERATOR
    // =========================
    private String generateMessageID() {
        Random rand = new Random();
        int id = 100000000 + rand.nextInt(900000000);
        return String.valueOf(id);
    }

    // =========================
    // HASH GENERATOR
    // =========================
    public String createMessageHash(int messageNumber, String message) {

        if (message == null || message.trim().isEmpty()) {
            return "INVALID_HASH";
        }

        String[] words = message.split(" ");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        if (MessageID == null || MessageID.length() < 2) {
            return "INVALID_HASH";
        }

        return (MessageID.substring(0, 2)
                + ":" + messageNumber
                + ":" + firstWord + lastWord).toUpperCase();
    }

    // =========================
    // SEND MESSAGE
    // =========================
    public String sendMessage(Scanner input) {

        System.out.print("How many messages would you like to send? ");
        int numMessages = Integer.parseInt(input.nextLine());

        for (int i = 1; i <= numMessages; i++) {

            System.out.println("\n===== MESSAGE " + i + " =====");

            // RECIPIENT VALIDATION LOOP
            String recipient;
            do {
                System.out.print("Enter recipient number: ");
                recipient = input.nextLine();
            } while (!checkRecipientCell(recipient));

            // MESSAGE INPUT
            System.out.print("Enter message: ");
            message = input.nextLine();

            if (message.length() > 250) {
                int excess = message.length() - 250;
                System.out.println("Message exceeds 250 characters by " + excess);
                continue;
            }

            MessageID = generateMessageID();

            System.out.println("\n1. Send Message");
            System.out.println("2. Store Message");
            System.out.println("3. Disregard Message");

            String option = input.nextLine();

            String hash = createMessageHash(i, message);

            String details =
                    "Message ID: " + MessageID +
                    "\nMessage Hash: " + hash +
                    "\nRecipient: " + recipient +
                    "\nMessage: " + message;

            // SEND
            if (option.equals("1")) {

                userMessages.add(details);
                sentMessages.add(message);

                System.out.println("\n===== Message successfully sent =====");
                System.out.println(details);
            }

            // STORE
            else if (option.equals("2")) {

                storeMessage(recipient);

                userMessages.add(details);
                storedMessages.add(message);
                storedMessageIDs.add(MessageID);
                storedMessageHashes.add(hash);
                storedRecipients.add(recipient);
                storedSenders.add("Current User");

                System.out.println("Message successfully stored.");
            }

            // DISREGARD
            else if (option.equals("3")) {

                disregardedMessages.add(message);
                System.out.println("Message disregarded.");
            }

            else {
                System.out.println("Invalid option.");
            }
        }

        return "Total Messages Sent: " + returnTotalMessages();
    }

    // =========================
    // RECENT MESSAGES
    // =========================
    public void showRecentMessages() {

        if (sentMessages.isEmpty()) {
            System.out.println("No messages have been sent yet.");
            return;
        }

        System.out.println("\n===== RECENT MESSAGES =====");

        int start = Math.max(0, sentMessages.size() - 5);

        for (int i = start; i < sentMessages.size(); i++) {
            System.out.println((i + 1) + ". " + sentMessages.get(i));
        }
    }

    // =========================
    // STORE TO FILE
    // =========================
    public void storeMessage(String recipient) {

        try {
            FileWriter writer = new FileWriter("messages.json", true);

            writer.write("{\n");
            writer.write("\"MessageID\":\"" + MessageID + "\",\n");
            writer.write("\"Recipient\":\"" + recipient + "\",\n");
            writer.write("\"Message\":\"" + message + "\"\n");
            writer.write("}\n");

            writer.close();

            System.out.println("Message stored in messages.json");

        } catch (IOException e) {
            System.out.println("Error storing message.");
        }
    }

    // =========================
    // DISPLAY STORED MESSAGES
    // =========================
    public void displayStoredMessages() {

        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages found.");
            return;
        }

        for (int i = 0; i < storedMessages.size(); i++) {

            System.out.println("Sender: " + storedSenders.get(i));
            System.out.println("Recipient: " + storedRecipients.get(i));
            System.out.println("Message: " + storedMessages.get(i));
            System.out.println("--------------------------");
        }
    }

    // =========================
    // LONGEST MESSAGE
    // =========================
    public String displayLongestMessage() {

        if (storedMessages.isEmpty()) {
            return "No stored messages available.";
        }

        String longest = storedMessages.get(0);

        for (String msg : storedMessages) {
            if (msg.length() > longest.length()) {
                longest = msg;
            }
        }

        return longest;
    }

    // =========================
    // SEARCH BY ID
    // =========================
    public void searchByMessageID(String id) {

        for (int i = 0; i < storedMessageIDs.size(); i++) {

            if (storedMessageIDs.get(i).equals(id)) {

                System.out.println("Recipient: " + storedRecipients.get(i));
                System.out.println("Message: " + storedMessages.get(i));
                return;
            }
        }

        System.out.println("Message not found.");
    }

    // =========================
    // SEARCH BY RECIPIENT
    // =========================
    public void searchByRecipient(String recipient) {

        boolean found = false;

        for (int i = 0; i < storedRecipients.size(); i++) {

            if (storedRecipients.get(i).equals(recipient)) {

                System.out.println("Message ID: " + storedMessageIDs.get(i));
                System.out.println("Message: " + storedMessages.get(i));
                found = true;
            }
        }

        if (!found) {
            System.out.println("No messages found.");
        }
    }

    // =========================
    // DELETE MESSAGE
    // =========================
    public void deleteMessage(String hash) {

        int index = storedMessageHashes.indexOf(hash);

        if (index != -1) {

            storedMessages.remove(index);
            storedMessageIDs.remove(index);
            storedMessageHashes.remove(index);
            storedRecipients.remove(index);
            storedSenders.remove(index);

            System.out.println("Message deleted.");
        } else {
            System.out.println("Message hash not found.");
        }
    }

    // =========================
    // REPORT
    // =========================
    public void displayReport() {

        System.out.println("\n===== STORED MESSAGE REPORT =====");

        for (int i = 0; i < storedMessages.size(); i++) {

            System.out.println("Message ID: " + storedMessageIDs.get(i));
            System.out.println("Message Hash: " + storedMessageHashes.get(i));
            System.out.println("Sender: " + storedSenders.get(i));
            System.out.println("Recipient: " + storedRecipients.get(i));
            System.out.println("Message: " + storedMessages.get(i));
            System.out.println("--------------------------------");
        }
    }

    // =========================
    // TOTAL SENT
    // =========================
    public int returnTotalMessages() {
        return sentMessages.size();
    }
}