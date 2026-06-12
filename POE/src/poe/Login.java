

package poe;

import java.util.ArrayList;

public class Login {

    private String firstName = "";
    private String lastName = "";
    private String Usersname = "";
    private String password = "";
    private String CellNumber = "";

    private ArrayList<String[]> UserList = null;

    public Login(String firstName,
                 String lastName,
                 String username,
                 String password,
                 String cellNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.Usersname = username;
        this.password = password;
        this.CellNumber = cellNumber;

        this.UserList = new ArrayList<>();
    }

    public boolean checkUserName() {

        int tempNameLength = this.Usersname.length();

        if (tempNameLength <= 5
                && this.Usersname.contains("_")) {

            System.out.println(
                    "Username successfully captured");

            return true;

        } else {

            System.err.println(
                    "Username is not correctly formatted; "
                    + "please ensure that your username "
                    + "contains an underscore and is "
                    + "no more than five characters in length.");

            return false;
        }
    }

    public boolean checkPasswordComplexity() {

        String ALLSpecialChar =
                "^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$";

        if (this.password.matches(ALLSpecialChar)) {

            System.out.println(
                    "Password successfully captured.");

            return true;

        } else {

            System.err.println(
                    "Password is not correctly formatted; "
                    + "please ensure that the password "
                    + "contains at least eight characters, "
                    + "a capital letter, a number, and a "
                    + "special character.");

            return false;
        }
    }

    public boolean checkCellPhoneNumber() {

        int lengthNumber = this.CellNumber.length();

        if (this.CellNumber.startsWith("+27")
                && lengthNumber == 12) {

            return true;

        } else if (this.CellNumber.startsWith("0")) {

            System.err.println(
                    "The cell number does not follow "
                    + "the international format.");

            return false;

        } else {

            System.err.println("Incorrect Number");

            return false;
        }
    }

    public String registerUser() {

        if (this.checkUserName()) {

            if (this.checkCellPhoneNumber()) {

                if (this.checkPasswordComplexity()) {

                    String[] tempUser = {
                        this.firstName,
                        this.lastName,
                        this.Usersname,
                        this.password,
                        this.CellNumber
                    };

                    this.UserList.add(tempUser);

                    return "Is registed";
                }
            }
        }

        return "Not registed";
    }

    public boolean loginUser(String Username,
                             String Password) {

        boolean isFound = false;

        if (!UserList.isEmpty()) {

            for (String[] CurrentArrUser : UserList) {

                if (CurrentArrUser[2].equals(Username)
                        && CurrentArrUser[3].equals(Password)) {

                    isFound = true;

                    return true;
                }
            }
        }

        return isFound;
    }

    public String returnloginStatus(String name,
                                    String pass) {

        if (this.loginUser(name, pass)) {

            String message = "A successfull login";

            System.out.println(message);

            return message;

        } else {

            String message = "A failed login";

            System.out.println(message);

            return message;
        }
    }

    public String getFirstName() {

        return firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public String getCellNumber() {

        return this.CellNumber;
    }

    public String getUserName() {

        return Usersname;
    }

    public String getPassword() {

        return this.password;
    }
}

