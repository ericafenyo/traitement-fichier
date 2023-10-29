package fr.diginamic.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * A database entity that represents an allergen of food products.
 */
@Entity
@Table(name = "allergens")
public class AllergenEntity {
    /**
     * The unique identifier for the allergen.
     */
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * The name of the allergen.
     */
    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;

    @ManyToMany(mappedBy = "allergens")
    private List<ProductEntity> products = new ArrayList<>();
    ;

    /**
     * Constructs a new Allergen object.
     * This is required by JPA for table generation
     */
    public AllergenEntity() { }

    /**
     * Constructs a new Allergen object with the given name.
     *
     * @param name The allergen's name.
     */
    public AllergenEntity(String name) {
        this.name = name;
    }

    /**
     * Get the unique identifier of the allergen.
     *
     * @return The allergen's unique identifier.
     */
    public long getId() {
        return id;
    }

    /**
     * Set the unique identifier of the allergen.
     *
     * @param id The allergen's unique identifier.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the name of the allergen.
     *
     * @return The allergen's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the allergen.
     *
     * @param name The allergen's name.
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
