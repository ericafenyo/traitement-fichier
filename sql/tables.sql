-- Additives Table
DROP TABLE IF EXISTS `additives`;
CREATE TABLE IF NOT EXISTS `additives` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL,
    CONSTRAINT `UK_additives_name` UNIQUE (`name`)
);

-- Allergens Table
DROP TABLE IF EXISTS `allergens`;
CREATE TABLE IF NOT EXISTS `allergens` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL,
    CONSTRAINT `UK_allergens_name` UNIQUE (`name`)
);

-- Brands Table
DROP TABLE IF EXISTS `brands`;
CREATE TABLE IF NOT EXISTS `brands` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL,
    CONSTRAINT `UK_brands_name` UNIQUE (`name`)
);

-- Categories Table
DROP TABLE IF EXISTS `categories`;
CREATE TABLE IF NOT EXISTS `categories` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL,
    CONSTRAINT `UK_categories_name` UNIQUE (`name`)
);

-- Errors Table
DROP TABLE IF EXISTS `errors`;
CREATE TABLE IF NOT EXISTS `errors` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` TEXT NOT NULL
);

-- Ingredients Table
DROP TABLE IF EXISTS `ingredients`;
CREATE TABLE IF NOT EXISTS `ingredients` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(100) NOT NULL,
    CONSTRAINT `UK_ingredients_name` UNIQUE (`name`)
);

-- Products Table
DROP TABLE IF EXISTS `products`;
CREATE TABLE IF NOT EXISTS `products` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `energy` DOUBLE NULL,
    `fat` DOUBLE NULL,
    `name` VARCHAR(255) NOT NULL,
    `score` VARCHAR(2) NULL,
    `brand_id` BIGINT NULL,
    `category_id` BIGINT NULL
);

CREATE INDEX `products_brand_id` ON `products` (`brand_id`);
CREATE INDEX `products_category_id` ON `products` (`category_id`);

-- Project Additive Table
DROP TABLE IF EXISTS `project_additive`;
CREATE TABLE IF NOT EXISTS `project_additive` (
    `product_id` BIGINT NOT NULL,
    `additive_id` BIGINT NOT NULL
);

CREATE INDEX `project_additive_product_id` ON `project_additive` (`product_id`);
CREATE INDEX `project_additive_additive_id` ON `project_additive` (`additive_id`);

-- Project Allergen Table
DROP TABLE IF EXISTS `project_allergen`;
CREATE TABLE IF NOT EXISTS `project_allergen` (
    `product_id` BIGINT NOT NULL,
    `allergen_id` BIGINT NOT NULL,
    PRIMARY KEY (`product_id`, `allergen_id`)
);

CREATE INDEX `project_allergen_allergen_id` ON `project_allergen` (`allergen_id`);

-- Project Ingredient Table
DROP TABLE IF EXISTS `project_ingredient`;
CREATE TABLE IF NOT EXISTS `project_ingredient` (
    `product_id` BIGINT NOT NULL,
    `ingredient_id` BIGINT NOT NULL,
    PRIMARY KEY (`product_id`, `ingredient_id`)
);

CREATE INDEX `project_ingredient_ingredient_id` ON `project_ingredient` (`ingredient_id`);
