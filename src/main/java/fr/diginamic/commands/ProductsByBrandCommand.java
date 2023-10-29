package fr.diginamic.commands;

import fr.diginamic.menu.EmptyInputException;
import fr.diginamic.dao.ProductRepository;
import fr.diginamic.models.Product;

import java.util.List;
import java.util.Scanner;

/**
 * This command retrieves the top products associated with a particular brand.
 */
public class ProductsByBrandCommand implements Command {
    private final ProductRepository repository;
    private final Scanner scanner;

    public ProductsByBrandCommand(ProductRepository repository, Scanner scanner) {
        System.out.println("2. Display the top 10 highest-rated products of a brand.");
        this.repository = repository;
        this.scanner = scanner;
    }

    @Override
    public void execute() throws Exception {
        System.out.println("Enter a product brand:");
        String brand = scanner.nextLine();
        if (!brand.isBlank()) {
            execute(brand, repository);
        } else {
            throw new EmptyInputException();
        }
    }

    private void execute(String brand, ProductRepository repository) {
        List<Product> products = repository.getProductsByBrand(brand, null, null, 10);
        int counter = 1;
        for (Product product : products) {
            System.out.printf("%d. %s%n", counter, product.getName());
            counter++;
        }
    }
}
