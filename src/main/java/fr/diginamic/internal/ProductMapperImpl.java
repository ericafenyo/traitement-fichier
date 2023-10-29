package fr.diginamic.internal;

import fr.diginamic.dao.ProductMapper;
import fr.diginamic.entities.ProductEntity;
import fr.diginamic.models.Product;

class ProductMapperImpl implements ProductMapper {
    @Override
    public Product apply(ProductEntity entity) {
        Product product = new Product();
        product.setId(entity.getId());
        product.setName(entity.getName());
        product.setScore(entity.getScore());

        return product;
    }
}
