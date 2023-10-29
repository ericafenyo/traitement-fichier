package fr.diginamic.entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A database entity that represents a category of food products.
 */
@Entity
@Table(name = "categories")
public class CategoryEntity {
    /**
     * The unique identifier for the category.
     */
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * The name of the category.
     */
    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;

    /**
     * The products in this category.
     */
    @OneToMany(mappedBy = "category")
    private List<ProductEntity> products = new ArrayList<>();

    /**
     * Constructs a new Category object.
     * This is required by JPA for table generation
     */
    public CategoryEntity() { }

    /**
     * Constructs a new Category object with the given name.
     * @param name The category's name.
     */
    public CategoryEntity(String name) {
        this.name = name;
    }

    /**
     * Get the unique identifier of the category.
     *
     * @return The category's unique identifier.
     */
    public long getId() {
        return id;
    }

    /**
     * Set the unique identifier of the category.
     *
     * @param id The category's unique identifier.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the name of the category.
     *
     * @return The category's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the category.
     *
     * @param name The category's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the products in this category.
     *
     * @return the products
     */
    public List<ProductEntity> getProducts() {
        return products;
    }

    /**
     * Set the products in this category.
     *
     * @param products the products
     */
    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}
