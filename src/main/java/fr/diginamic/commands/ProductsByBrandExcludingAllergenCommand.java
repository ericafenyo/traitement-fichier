package fr.diginamic.commands;

import fr.diginamic.menu.EmptyInputException;
import fr.diginamic.dao.ProductRepository;
import fr.diginamic.models.Product;

import java.util.List;
import java.util.Scanner;

/**
 * This command retrieves the top products associated with a brand while excluding a specific allergen.
 */
public class ProductsByBrandExcludingAllergenCommand implements Command {

    private final ProductRepository repository;
    private final Scanner scanner;

    public ProductsByBrandExcludingAllergenCommand(ProductRepository repository, Scanner scanner) {
        System.out.println("6. Display the top 10 highest-rated products of a brand without a given allergen.");
        this.repository = repository;
        this.scanner = scanner;
    }

    @Override
    public void execute() throws Exception {
        System.out.println("Enter a product brand:");
        String brand = scanner.nextLine();
        if (brand.isBlank()) {
            throw new EmptyInputException();
        }

        System.out.println("Enter the allergen to exclude:");
        String allergen = scanner.nextLine();
        if (allergen.isBlank()) {
            throw new EmptyInputException();
        }

        execute(brand, allergen, repository);
    }

    private void execute(String brand, String allergen, ProductRepository repository) {
        List<Product> products = repository.getProductsByBrand(brand, allergen, null, 10);
        int counter = 1;
        for (Product product : products) {
            System.out.printf("%d. %s%n", counter, product.getName());
            counter++;
        }
    }
}
