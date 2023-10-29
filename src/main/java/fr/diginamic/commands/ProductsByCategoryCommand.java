package fr.diginamic.commands;

import fr.diginamic.menu.EmptyInputException;
import fr.diginamic.dao.ProductRepository;
import fr.diginamic.models.Product;

import java.util.List;
import java.util.Scanner;

/**
 * This command retrieves the top products within a specific category.
 */
public class ProductsByCategoryCommand implements Command {

    private final ProductRepository repository;
    private final Scanner scanner;

    public ProductsByCategoryCommand(ProductRepository repository, Scanner scanner) {
        System.out.println("1. Display the top 10 highest-rated products in a category.");
        this.repository = repository;
        this.scanner = scanner;
    }

    @Override
    public void execute() throws Exception {
        System.out.println("Enter a product category:");
        String category = scanner.nextLine();
        if (!category.isBlank()) {
            execute(category, repository);
        } else {
            throw new EmptyInputException();
        }
    }

    private void execute(String category, ProductRepository repository) {
        List<Product> products = repository.getProductsByCategory(category, null, null, 10);
        int counter = 1;
        for (Product product : products) {
            System.out.printf("%d. %s%n", counter, product.getName());
            counter++;
        }
    }
}
