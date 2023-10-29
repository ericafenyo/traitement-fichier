package fr.diginamic.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A database entity that represents an ingredient of food products.
 */
@Entity
@Table(name = "ingredients")
public class IngredientEntity {
    /**
     * The unique identifier for the ingredient.
     */
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * The name of the ingredient.
     */
    @Column(name = "name", unique = true, length = 100, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "ingredients")
    private List<ProductEntity> products = new ArrayList<>();
    ;

    /**
     * Constructs a new Ingredient object.
     * This is required by JPA for table generation
     */
    public IngredientEntity() { }

    /**
     * Constructs a new Ingredient object with the given name.
     *
     * @param name The ingredient's name.
     */
    public IngredientEntity(String name) {
        this.name = name;
    }

    /**
     * Get the unique identifier of the ingredient.
     *
     * @return The ingredient's unique identifier.
     */
    public long getId() {
        return id;
    }

    /**
     * Set the unique identifier of the ingredient.
     *
     * @param id The ingredient's unique identifier.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the name of the ingredient.
     *
     * @return The ingredient's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the ingredient.
     *
     * @param name The ingredient's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}
