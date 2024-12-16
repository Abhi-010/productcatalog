CREATE TABLE categories
(
    id   BINARY(16)   NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE locations
(
    id        BINARY(16)   NOT NULL,
    name      VARCHAR(255) NULL,
    area_code INT NOT NULL,
    landmark  VARCHAR(255) NULL,
    comment   VARCHAR(255) NULL,
    CONSTRAINT pk_locations PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id                   BINARY(16) NOT NULL,
    shipping_location_id BINARY(16) NULL,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

CREATE TABLE product_orders
(
    order_id   BINARY(16) NOT NULL,
    product_id BINARY(16) NOT NULL
);

CREATE TABLE products
(
    id            BINARY(16)   NOT NULL,
    title         VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    image         VARCHAR(255) NULL,
    currency      SMALLINT NULL,
    price DOUBLE NOT NULL,
    cat_id        BINARY(16)   NOT NULL,
    inven_count   INT NOT NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_SHIPPINGLOCATION FOREIGN KEY (shipping_location_id) REFERENCES locations (id);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_CAT FOREIGN KEY (cat_id) REFERENCES categories (id);

ALTER TABLE product_orders
    ADD CONSTRAINT fk_proord_on_order FOREIGN KEY (order_id) REFERENCES orders (id);

ALTER TABLE product_orders
    ADD CONSTRAINT fk_proord_on_product FOREIGN KEY (product_id) REFERENCES products (id);