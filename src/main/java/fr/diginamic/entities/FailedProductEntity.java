package fr.diginamic.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A database entity that represents an unprocessed product with error.
 */
@Entity
@Table(name = "errors")
public class FailedProductEntity {
    /**
     * The unique identifier for the product.
     */
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * The product entry which could not be parsed.
     */
    @Column(name = "entry", nullable = false, columnDefinition = "TEXT")
    private String entry;

    /**
     * Get the unique identifier of the error.
     */
    public long getId() {
        return id;
    }

    /**
     * Set the unique identifier of the error.
     *
     * @param id The error's unique identifier.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the error text.
     */
    public String getEntry() {
        return entry;
    }

    /**
     * Set the error text.
     *
     * @param entry The failed error.
     */
    public void setEntry(String entry) {
        this.entry = entry;
    }
}
