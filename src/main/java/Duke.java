import java.util.Scanner;

public class Duke {
    private static String line = "\t____________________________________________________________";

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        String input = null;
        Scanner scanner = new Scanner(System.in);

        printHelloMsg();

        while (scanner.hasNext()) {
            input = readInput(scanner);

            if (!input.equals("bye")) {
                echo(input);
            }
            else {
                break;
            }
        }

        printByeMsg();
    }

    private static void printLine() {
        System.out.println(line);
    }

    private static void printHelloMsg() {
        printLine();
        System.out.println("\tHello! I'm Duke\n" + "\tWhat can I do for you?");
        printLine();
    }

    private static void printByeMsg() {
        printLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printLine();
    }

    private static String readInput(Scanner scanner) {
        String commands = scanner.nextLine();
        return commands;
    }

    private static void echo(String input) {
        printLine();
        System.out.println("\t" + input);
        printLine();
    }

}
