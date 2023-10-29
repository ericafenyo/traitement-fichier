package fr.diginamic.entities;

/**
 * The nutritional score for a product.
 */
public enum Score {
    A,
    B,
    C,
    D,
    E,
    F,
    NA;


    /**
     * Find a nutritional score value by name.
     *
     * @param score the score to set
     */
    public static Score parse(String score) {
        try {
            return valueOf(score.toUpperCase());
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
            return NA;
        }
    }
}
