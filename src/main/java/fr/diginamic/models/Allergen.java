package fr.diginamic.models;


public class Allergen {
    /**
     * The unique identifier for the allergen.
     */
    private long id;

    /**
     * The name of the allergen.
     */
    private String name;

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
     * @param id The identifier to set.
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
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
}
