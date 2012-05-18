import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TouchPad {
    private Scanner scanner;
    private PrintStream printStream;

    public TouchPad() {
        this.scanner = new Scanner(System.in);
        printStream = new PrintStream(System.out);
    }

    public TouchPad(Scanner scanner, PrintStream printStream) {
        this.scanner = scanner;
        this.printStream = printStream;
    }

    public int waitForTouch() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error input.System exit.");
            System.exit(-1);
        }
        return 0;
    }

    public void show(List<String> bookCollection) {
        for (String book : bookCollection) {
            printStream.println(book);
        }
    }

    public void show(String information) {
        printStream.println(information);
    }

    public void show(Map<Integer, String> information) {
        for (Map.Entry<Integer, String> note : information.entrySet()) {
            printStream.println(note.getKey() + "." + note.getValue());
        }
        printStream.println("0.Exit");
    }
}
