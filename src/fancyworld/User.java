package fancyworld;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class User {

    private List<String> collection;
    private UserPosition userPosition;
    private Scanner scanner;

    public User() {
        userPosition = UserPosition.MAIN_MENU;
        collection = new ArrayList<String>();
        scanner = new Scanner(System.in);
    }

    public UserPosition getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(UserPosition userPosition) {
        this.userPosition = userPosition;
    }

    public List<String> getCollection() {
        return collection;
    }

    public int sendCommand() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error input.System exit.");
            System.exit(-1);
        }
        return 0;
    }
}
