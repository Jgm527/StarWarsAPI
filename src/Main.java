import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        ArgumentSwitcher argumentSwitcher = new ArgumentSwitcher();

        //if no arguments are given, start interactive mode so the program
        //also works when launched without command line parameters (eg. the VS Code run button)
        if (args.length == 0) {
            runInteractive(argumentSwitcher);
            return;
        }
        //the program manual
        if (args[0].equalsIgnoreCase("help")) {
            System.out.println("For getting all results in a category");
            System.out.println("usage: [category]");
            System.out.println("");
            System.out.println("For searching in results in a category");
            System.out.println("usage: [category]  [search]");
            System.out.println("");
            System.out.println("[category] : films, planets, starships");
            System.out.println("[search] you can search on following fields per category");
            System.out.println("");
            System.out.println("films : title");
            System.out.println("planets : name");
            System.out.println("starships : name, model");
            return;
        }
        //get all in category
        if (args.length == 1) {
            argumentSwitcher.switcher(args[0], null);
            return;
        }
        //get search in category
        if (args.length == 2) {
            argumentSwitcher.switcher(args[0], args[1]);
            return;

        }
    }

    //interactive mode: asks for commands in a loop until the user types exit
    private static void runInteractive(ArgumentSwitcher argumentSwitcher) {
        System.out.println("No parameters given, starting interactive mode.");
        printManual();
        System.out.println("Type exit to quit.");
        System.out.println("");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print("command [search] > ");
            System.out.flush();

            String line;
            try {
                line = reader.readLine();
            } catch (IOException e) {
                System.out.println("Could not read input, exiting.");
                return;
            }

            //end of input (eg. ctrl+d / closed console) also exits
            if (line == null) {
                return;
            }
            line = line.trim();
            if (line.isEmpty()) {
                continue;
            }
            if (line.equalsIgnoreCase("exit") || line.equalsIgnoreCase("quit")) {
                return;
            }
            if (line.equalsIgnoreCase("help")) {
                printManual();
                continue;
            }

            //syntax: [category] [search], the search may contain spaces
            int spaceIndex = line.indexOf(' ');
            String command;
            String searchquery = null;
            if (spaceIndex == -1) {
                command = line;
            } else {
                command = line.substring(0, spaceIndex);
                searchquery = line.substring(spaceIndex + 1).trim();
                if (searchquery.isEmpty()) {
                    searchquery = null;
                }
            }

            argumentSwitcher.switcher(command, searchquery);
            System.out.println("");
        }
    }

    private static void printManual() {
        System.out.println("For getting all results in a category");
        System.out.println("usage: [category]");
        System.out.println("");
        System.out.println("For searching in results in a category");
        System.out.println("usage: [category]  [search]");
        System.out.println("");
        System.out.println("[category] : films, planets, starships, people, vehicles, species");
        System.out.println("[search] you can search on following fields per category");
        System.out.println("");
        System.out.println("films : title");
        System.out.println("planets : name");
        System.out.println("starships : name, model");
        System.out.println("people : name");
        System.out.println("vehicles : name, model");
        System.out.println("species : name");
        System.out.println("");
    }
}
