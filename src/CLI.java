import Commands.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * CLI class that serves for reading console input from a user, parsing tokens and provoking command execution
 */

public class CLI {
    public  void run() {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        CommandManager commandManager = new CommandManager();
        Command[] commands = {
                new HelpCommand(),
                new AddCommand(),
                new ExitCommand(),
                new HistoryCommand(),
                new SaveCommand(),
                new InfoCommand(),
                new ShowCommand(),
                new UpdateCommand(),
                new RemoveById(),
                new ClearCommand(),
                new FilterStartsWithDescriptionCommand(),
                new MaxByNameCommand(),
                new ExecuteScriptCommand(),
                new RemoveFirstCommand(),
                new RemoveLowerCommand(),
                new RemoveAllByDescriptionCommand(),
        };
        commandManager.registerCommands(commands);

        while (true) {
            System.out.print("\nEnter a command \n>>> ");
            try {
                List<String> tokens = new ArrayList<>(List.of(bufferedReader.readLine().trim().split("\\s+")));
                commandManager.executeCommand(tokens);
            } catch (IOException e) {
                System.out.println("Error reading the string: " + e.getMessage());
            }
        }
    }
}
