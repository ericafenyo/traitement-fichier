package fr.diginamic.dao;

import fr.diginamic.entities.IngredientEntity;
import fr.diginamic.models.Ingredient;

/**
 * A DAO (Data Access Object) interface specifically for handling operations related to product ingredients.
 */
public interface IngredientDao extends IterableDao<Ingredient, IngredientEntity> { }
