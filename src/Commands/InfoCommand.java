package Commands;

import Annotations.CommandInfo;
import Collection.Dragon;
import Exceptions.TokenMismatchException;
import Utils.CollectionManager;
import Utils.Colors;

import java.util.Comparator;
import java.util.List;
import java.util.Vector;

/**
 * Class, that implements console command "info"
 */

@CommandInfo(name = "info", description = "print information about the collection to standard output")
public class InfoCommand implements Command {
    @Override
    public void execute(List<String> tokens) {

        try {
            if (!tokens.isEmpty()) {
                throw new TokenMismatchException(0);
            }
        } catch (TokenMismatchException e) {
            System.out.println(e.getMessage());
            return;
        }

        CollectionManager cm = CollectionManager.getInstance();
        Vector<Dragon> collection = cm.getCollection();

        Dragon oldestDragon = collection.stream().min(Comparator.comparing(Dragon::getCreationDate)).orElse(null);
        String initLine;
        if (oldestDragon == null) {
            initLine = "Collection doesn't have any items so it's not initialized";
        } else {
            initLine = oldestDragon.getCreationDate().toString();
        }

        Dragon newestDragon = collection.stream().max(Comparator.comparing(Dragon::getCreationDate)).orElse(null);
        String newestLine;
        if (newestDragon == null) {
            newestLine = "No items had been added yet";
        } else {
            newestLine = newestDragon.getName() + " (" + newestDragon.getCreationDate().toString() + ")";

        }

        System.out.println("Collection type: Vector<Dragon>");

        System.out.println("Initialization date: " + initLine);

        System.out.println("Collection contains items: " + collection.size());

        System.out.println("Recently added item: " + newestLine);
    }
}
