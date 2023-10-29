package fr.diginamic.models;


public class Ingredient {
    /**
     * The unique identifier for the ingredient.
     */
    private long id;

    /**
     * The name of the ingredient.
     */
    private String name;

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
     * @param id The identifier to set.
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
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
}
