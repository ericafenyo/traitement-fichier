package fr.diginamic.menu;

import fr.diginamic.commands.Command;
import fr.diginamic.commands.ProductsByBrandCommand;
import fr.diginamic.commands.ProductsByBrandExcludingAllergenCommand;
import fr.diginamic.commands.ProductsByBrandExcludingIngredientCommand;
import fr.diginamic.commands.ProductsByCategoryCommand;
import fr.diginamic.commands.ProductsByCategoryExcludingAllergenCommand;
import fr.diginamic.commands.ProductsByCategoryExcludingIngredientCommand;
import fr.diginamic.dao.ProductRepository;
import fr.diginamic.internal.ServiceLoader;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import static fr.diginamic.util.Preconditions.isInteger;
import static fr.diginamic.util.Preconditions.withinRange;

public class Menu {
    private static final Map<Integer, Command> commands = new LinkedHashMap<>();

    private Menu() {
    }

    public static void displayOptions() throws Exception {
        Scanner scanner = new Scanner(System.in);

        bindOptions(ServiceLoader.provideProductRepository(), scanner);

        System.out.println("\nSelect a valid option:");

        int option;
        while (true) {
            String input = scanner.nextLine();
            if (isInteger(input) && withinRange(Integer.parseInt(input), 1, commands.size())) {
                option = Integer.parseInt(input);
                break;
            } else {
                System.out.println("Select a valid option:");
            }
        }

        var command = commands.get(option);
        if (command != null) {
            command.execute();
        }
    }

    private static void bindOptions(ProductRepository repository, Scanner scanner) {
        commands.put(1, new ProductsByCategoryCommand(repository, scanner));
        commands.put(2, new ProductsByBrandCommand(repository, scanner));
        commands.put(3, new ProductsByCategoryExcludingIngredientCommand(repository, scanner));
        commands.put(4, new ProductsByBrandExcludingIngredientCommand(repository, scanner));
        commands.put(5, new ProductsByCategoryExcludingAllergenCommand(repository, scanner));
        commands.put(6, new ProductsByBrandExcludingAllergenCommand(repository, scanner));
    }
}
