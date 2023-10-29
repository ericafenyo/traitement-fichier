package fr.diginamic;


import fr.diginamic.dao.ProductDao;
import fr.diginamic.data.OpenFoodFacts;
import fr.diginamic.models.Product;
import fr.diginamic.internal.ServiceLoader;

import java.util.Set;
import java.util.stream.Collectors;

public class InsertProducts {
    public static void main(String[] args) {
        OpenFoodFacts foodFacts = OpenFoodFacts.create();
        ProductDao productDao = ServiceLoader.provideProductDao();

        // Insert errors
        productDao.keepErrors(foodFacts.getErrors());


        // Insert products
        Set<Product> products = foodFacts.getProducts()
                .stream()
//                .limit(1000)
                .collect(Collectors.toSet());

        productDao.bulkInsert(products);
    }
}
