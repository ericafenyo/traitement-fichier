package fr.diginamic.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * A database entity that represents a brand of food products.
 */
@Entity
@Table(name = "brands")
public class BrandEntity {
    /**
     * The unique identifier for the brand.
     */
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * The name of the brand.
     */
    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;

    /**
     * The products for this brand.
     */
    @OneToMany(mappedBy = "brand")
    private List<ProductEntity> products = new ArrayList<>();

    /**
     * Constructs a new Brand object.
     * This is required by JPA for table generation
     */
    public BrandEntity() { }

    /**
     * Constructs a new Brand object with the given name.
     *
     * @param name The brand's name.
     */
    public BrandEntity(String name) {
        this.name = name;
    }

    /**
     * Get the unique identifier of the brand.
     *
     * @return The brand's unique identifier.
     */
    public long getId() {
        return id;
    }

    /**
     * Set the unique identifier of the brand.
     *
     * @param id The brand's unique identifier.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the name of the brand.
     *
     * @return The brand's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the brand.
     *
     * @param name The brand's name.
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
