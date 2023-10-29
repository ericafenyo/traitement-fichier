package fr.diginamic.models;


public class Category {
    /**
     * The unique identifier for the category.
     */
    private long id;

    /**
     * The name of the category.
     */
    private String name;

    public Category() { }

    public Category(String name) {
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
     * @param id The identifier to set.
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
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
}
