package fancyworld;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;

public class DisplayScreen {
    private PrintStream printStream;

    public DisplayScreen() {
        printStream = new PrintStream(System.out);
    }

    public DisplayScreen(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void display(List<String> bookCollection) {
        for (String book : bookCollection) {
            printStream.println(book);
        }
    }

    public void display(String information) {
        printStream.println(information);
    }

    public void display(Map<Integer, String> information) {
        for (Map.Entry<Integer, String> note : information.entrySet()) {
            printStream.println(note.getKey() + "." + note.getValue());
        }
    }
}
