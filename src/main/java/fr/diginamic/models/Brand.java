package fr.diginamic.models;


public class Brand {
    /**
     * The unique identifier for the brand.
     */
    private long id;

    /**
     * The name of the brand.
     */
    private String name;

    public Brand(String name) {
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
     * @param id The identifier to set.
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
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
}
