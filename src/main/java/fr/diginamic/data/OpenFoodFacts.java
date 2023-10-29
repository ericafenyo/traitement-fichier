package fr.diginamic.data;

import fr.diginamic.entities.Score;
import fr.diginamic.models.Allergen;
import fr.diginamic.models.Brand;
import fr.diginamic.models.Category;
import fr.diginamic.models.Ingredient;
import fr.diginamic.models.Product;
import fr.diginamic.util.Preconditions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OpenFoodFacts {
    /**
     * The index for product category in the data structure.
     */
    private static final int CATEGORY_INDEX = 0;

    /**
     * The index for marque in the data structure.
     */
    private static final int MARQUE_INDEX = 1;

    /**
     * The index for product name in the data structure.
     */
    private static final int PRODUCT_NAME_INDEX = 2;

    /**
     * The index for nutritional score in the data structure.
     */
    private static final int NUTRITIONAL_SCORE_INDEX = 3;

    /**
     * The index for the ingredients in the data structure.
     */
    private static final int INGREDIENTS_INDEX = 4;

    /**
     * The index for the energy score per 100g in the data structure.
     */
    private static final int ENERGY_INDEX = 5;

    /**
     * The index for the amount of fat per 100g in the data structure.
     */
    private static final int FAT_QUANTITY_INDEX = 6;

    /**
     * The index for allergens in the data structure.
     */
    private static final int ALLERGENS_INDEX = 28;

    /**
     * The index for additives in the data structure.
     */
    private static final int ADDITIVES_INDEX = 29;

    /**
     * The CSV file location
     */
    private static final String FILE_URI = "assets/open-food-facts.csv";

    private final List<Product> products;

    private final List<String> errors;

    private OpenFoodFacts(List<Product> products, List<String> errors) {
        this.products = products;
        this.errors = errors;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public List<String> getErrors() {
        return this.errors;
    }

    /**
     * Creates a singleton of OpenFoodFact.
     *
     * @return An OpenFoodFact instance.
     */
    public static OpenFoodFacts create() {
        // Path to csv file
        Path csv = Paths.get(FILE_URI);

        List<String> errors = new ArrayList<>();
        List<Product> accumulator = new ArrayList<>();
        if (Files.exists(csv)) {
            // Read all lines in the file.
            List<String> rows = null;
            try {
                rows = Files.readAllLines(csv);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // Remove the headings from the file. In CSV, the headings are on the first line.
            rows.remove(0);
            for (String row : rows) {
                Product product = buildProduct(row);
                if (product != null) {
                    accumulator.add(product);
                } else {
                    errors.add(row);
                }
            }
        }
        return new OpenFoodFacts(accumulator, errors);
    }

    private static Product buildProduct(String row) {
        // We are going to be very strict on parsing these file.
        // Only products without errors will be considered.
        // Of course, we will try to clean the fields a bit.

        // Within a row, the columns are seperated by '|'. Example, name|age|size
        String[] columns = row.split("\\|");
        try {
            // Extract the category the product belongs to
            String category = columns[CATEGORY_INDEX];
            // Check for invalid category
            if (!Preconditions.isMax(category, 50)) {
                return null;
            }

            // Extract the product brand or manufacturer
            String brand = columns[MARQUE_INDEX];
            // Check for invalid brand
            if (!Preconditions.isMax(brand, 50)) {
                return null;
            }

            // Extract the product name
            String name = columns[PRODUCT_NAME_INDEX];

            // Extract the nutritional score of the product
            String score = columns[NUTRITIONAL_SCORE_INDEX];

            // Extract the energy content in the product
            String energy = columns[ENERGY_INDEX];
            // Check for invalid energy value
            if (!Preconditions.isNumeric(energy)) {
                energy = "0.0";
            }

            // Extract the fat content in the product
            String fat = columns[FAT_QUANTITY_INDEX];
            // Check for invalid energy value
            if (!Preconditions.isNumeric(fat)) {
                fat = "0.0";
            }

            Set<Ingredient> ingredients = new HashSet<>();
            String[] ingredientChunks = columns[INGREDIENTS_INDEX].split(",");
            // Check for invalid ingredient value
            // Yes we are a bit strict
            // TODO: Reparse this error kind. Check out OpenFoodFacts#getErrors()
            if (ingredientChunks.length < 2) {
                return null;
            }

            for (String ingredientChunk : ingredientChunks) {
                // Ignore values with ':'
                if (ingredientChunk.contains(":")) {
                    continue;
                }

                // Ignore ingredient whose length is more than 100.

                String cleanedIngredient = cleanIngredient(ingredientChunk);
                if (!cleanedIngredient.isBlank() && Preconditions.isMax(cleanedIngredient, 100)) {
                    Ingredient ingredient = new Ingredient();
                    ingredient.setName(cleanedIngredient);
                    ingredients.add(ingredient);
                }
            }

            // Return fast if the ingredients are empty
            if (ingredients.isEmpty()) {
                return null;
            }

            String[] allergenChunks = columns[ALLERGENS_INDEX].split(",");
            Set<Allergen> allergens = new HashSet<>();
            for (String allergenChunk : allergenChunks) {
                String cleanedAllergen = cleanAllergen(allergenChunk);
                if (!cleanedAllergen.isBlank() && Preconditions.isMax(cleanedAllergen, 50)) {
                    Allergen allergen = new Allergen();
                    allergen.setName(cleanedAllergen);
                    allergens.add(allergen);
                }
            }

            Product product = new Product();
            product.setName(name);
            product.setScore(Score.parse(score));
            product.setEnergy(Double.parseDouble(energy));
            product.setFat(Double.parseDouble(fat));
            product.setIngredients(ingredients);
            product.setAllergens(allergens);

            product.setCategory(new Category(category));
            product.setBrand(new Brand(brand));

            return product;

        } catch (Exception exception) {
//            exception.printStackTrace();
            return null;
        }
    }

    private static String cleanAllergen(String chunk) {
        String processed = chunk.toLowerCase()
                .trim()
                .replaceAll("^[a-zA-Z]{2}:", "")
                // Remove from the end all non alphabets
                .replaceAll("[^a-zA-Z]+$", "");

        if (processed.isBlank()) {
            return "";
        }

        // Capitalize
        return processed.substring(0, 1).toUpperCase() + processed.substring(1);
    }

    private static String cleanIngredient(String chunk) {
        String processed = chunk.toLowerCase()
                // Removes every '-' character
                .replaceAll("_", "")
                // Remove from the end all non alphabets
                .replaceAll("[^a-zA-Z]+$", "")
                .trim();

        if (processed.isBlank()) {
            return "";
        }
        // Capitalize
        return processed.substring(0, 1).toUpperCase() + processed.substring(1);
    }
}
