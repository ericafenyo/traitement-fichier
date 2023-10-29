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
 * A database entity that represents an additive of food products.
 */
@Entity
@Table(name = "additives")
public class AdditiveEntity {
    /**
     * The unique identifier for the additive.
     */
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * The name of the additive.
     */
    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;

    @ManyToMany(mappedBy = "additives")
    private List<ProductEntity> products = new ArrayList<>();

    /**
     * Constructs a new Additive object.
     * This is required by JPA for table generation
     */
    public AdditiveEntity() { }

    /**
     * Constructs a new Additive object with the given name.
     * @param name The additive's name.
     */
    public AdditiveEntity(String name) {
        this.name = name;
    }

    /**
     * Get the unique identifier of the additive.
     *
     * @return The additive's unique identifier.
     */
    public long getId() {
        return id;
    }

    /**
     * Set the unique identifier of the additive.
     *
     * @param id The additive's unique identifier.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the name of the additive.
     *
     * @return The additive's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the additive.
     *
     * @param name The additive's name.
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
