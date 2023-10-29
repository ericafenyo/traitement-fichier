package fr.diginamic.models;

import fr.diginamic.entities.Score;

import java.util.Set;

/**
 * This class represents a food product.
 */
public class Product {
    /**
     * The unique identifier for the product.
     */
    private long id;
    /**
     * The name of the product.
     */
    private String name;

    /**
     * The nutritional score associated with the product.
     */

    private Score score;

    /**
     * The amount of energy content in the product per 100g.
     */
    private double energy;

    /**
     * The amount of fat content in the product per 100g.
     */
    private double fat;

    /**
     * The category to which the product belongs.
     */
    private Category category;

    /**
     * The brand or manufacturer of the product.
     */
    private Brand brand;

    /**
     * The ingredients in the product.
     */
    private Set<Ingredient> ingredients;

    /**
     * The allergens associated with the product.
     */
    private Set<Allergen> allergens;

    /**
     * The additives associated with the product.
     */
    private Set<Additive> additives;

    /**
     * Get the unique identifier of the product.
     *
     * @return The product's unique identifier.
     */
    public long getId() {
        return id;
    }

    /**
     * Set the unique identifier of the product.
     *
     * @param id The unique identifier to set.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Returns the name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the product.
     *
     * @param name The unique identifier to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the nutritional score associated with the product.
     */
    public Score getScore() {
        return score;
    }

    /**
     * Set the nutritional score associated with the product.
     *
     * @param score The nutritional score to set.
     */
    public void setScore(Score score) {
        this.score = score;
    }

    /**
     * Returns the amount of energy content in the product per 100g.
     */
    public double getEnergy() {
        return energy;
    }

    /**
     * Set the amount of energy content in the product per 100g.
     *
     * @param energy The amount of energy content to set.
     */
    public void setEnergy(double energy) {
        this.energy = energy;
    }

    /**
     * Returns the amount of fat content in the product per 100g.
     */
    public double getFat() {
        return fat;
    }

    /**
     * Set the amount of fat content in the product per 100g.
     *
     * @param fat The amount of fat content to set.
     */
    public void setFat(double fat) {
        this.fat = fat;
    }

    /**
     * Returns the category to which the product belongs.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Set the category to which the product belongs.
     *
     * @param category The category to set.
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Returns the brand or manufacturer of the product.
     */
    public Brand getBrand() {
        return brand;
    }

    /**
     * Set the brand or manufacturer of the product.
     *
     * @param brand The brand or manufacturer to set.
     */
    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    /**
     * Returns the ingredients in the product.
     */
    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * Set the ingredients in the product.
     *
     * @param ingredients The ingredients to set.
     */
    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Returns the allergens associated with the product.
     */
    public Set<Allergen> getAllergens() {
        return allergens;
    }

    /**
     * Set the allergens associated with the product.
     *
     * @param allergens The allergens to set.
     */
    public void setAllergens(Set<Allergen> allergens) {
        this.allergens = allergens;
    }

    /**
     * Returns the additives associated with the product.
     */
    public Set<Additive> getAdditives() {
        return additives;
    }

    /**
     * Set the additives associated with the product.
     *
     * @param additives The additives to set.
     */
    public void setAdditives(Set<Additive> additives) {
        this.additives = additives;
    }
}
