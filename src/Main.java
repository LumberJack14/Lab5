import Utils.CollectionManager;

import java.io.File;

/**
 * @author pozdo
 *
 * Main class, which is an entry point to the program
 */

public class Main {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Missing path to collection. Provide it as a command line argument.");
            return;
        }
        String path = args[0];

        CollectionManager.getInstance().initializeCollection(path, new File(path));

        CLI cli = new CLI();
        cli.run();
    }
}
