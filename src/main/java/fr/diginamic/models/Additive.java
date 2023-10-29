package fr.diginamic.models;


public class Additive {
    /**
     * The unique identifier for the additive.
     */
    private long id;

    /**
     * The name of the additive.
     */
    private String name;

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
     * @param id The identifier to set.
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
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
}
