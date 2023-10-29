package fr.diginamic.commands;

import fr.diginamic.menu.EmptyInputException;
import fr.diginamic.dao.ProductRepository;
import fr.diginamic.models.Product;

import java.util.List;
import java.util.Scanner;

/**
 * This command retrieves the top products within a category while excluding a specific ingredient.
 */
public class ProductsByCategoryExcludingIngredientCommand implements Command {
    private final ProductRepository repository;
    private final Scanner scanner;

    public ProductsByCategoryExcludingIngredientCommand(ProductRepository repository, Scanner scanner) {
        System.out.println("3. Display the top 10 highest-rated products in a category without a given ingredient.");
        this.repository = repository;
        this.scanner = scanner;
    }

    @Override
    public void execute() throws Exception {
        System.out.println("Enter a product category:");
        String category = scanner.nextLine();
        if (category.isBlank()) {
            throw new EmptyInputException();
        }

        System.out.println("Enter the ingredient to exclude:");
        String ingredient = scanner.nextLine();
        if (ingredient.isBlank()) {
            throw new EmptyInputException();
        }

        execute(category, ingredient, repository);
    }

    private void execute(String category, String ingredient, ProductRepository repository) {
        List<Product> products = repository.getProductsByCategory(category, null, ingredient, 10);
        int counter = 1;
        for (Product product : products) {
            System.out.printf("%d. %s%n", counter, product.getName());
            counter++;
        }
    }
}
