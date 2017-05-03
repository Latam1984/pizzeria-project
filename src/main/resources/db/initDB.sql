DROP DATABASE IF EXISTS PIZZERIA;
CREATE DATABASE IF NOT EXISTS PIZZERIA
  DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE PIZZERIA;

# --Components of pizza with real price.
CREATE TABLE IF NOT EXISTS COMPONENTS (
  ID             INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  COMPONENT_NAME VARCHAR(100) NOT NULL,
  WEIGHT         INT          NOT NULL, /* GRAM */
  PRICE          FLOAT        NOT NULL /* FOR 100 GRAM */
)
  ENGINE = InnoDB;

# --Stock displays all components in stock.
CREATE TABLE IF NOT EXISTS STOCK (
  ID             INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  COMPONENT_NAME VARCHAR(100) NOT NULL,
  WEIGHT         INT          NOT NULL /* GRAM */
)
  ENGINE = InnoDB;

# --Pizza contains information about pizza name and price.
CREATE TABLE IF NOT EXISTS PIZZA (
  ID          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  PIZZA_NAME  VARCHAR(100) NOT NULL,
  PIZZA_PRICE FLOAT        NOT NULL

)
  ENGINE = InnoDB;

# --Complicate_pizza contains information about components of pizza.
CREATE TABLE IF NOT EXISTS COMPLICATE_PIZZA (
  ID           INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  ID_PIZZA     INT NOT NULL,
  ID_COMPONENT INT NOT NULL,

  FOREIGN KEY (ID_PIZZA) REFERENCES PIZZA (ID),
  FOREIGN KEY (ID_COMPONENT) REFERENCES COMPONENTS (ID)
)
  ENGINE = InnoDB;

# --Orders displays general information about order.
CREATE TABLE IF NOT EXISTS ORDERS (
  ID          INT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  DATE        TIMESTAMP NOT NULL,
  ORDER_PRICE FLOAT     NOT NULL
)
  ENGINE = InnoDB;

# --Orders displays detail of orders.
CREATE TABLE IF NOT EXISTS ORDERS_CONTAINS (
  ID          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  PIZZA_NAME  VARCHAR(100) NOT NULL,
  PIZZA_PRICE FLOAT        NOT NULL,
  ID_ORDERS   INT          NOT NULL,
  ID_PIZZA    INT          NOT NULL,
  FOREIGN KEY (ID_ORDERS) REFERENCES ORDERS (ID),
  FOREIGN KEY (ID_PIZZA) REFERENCES PIZZA (ID)
)
  ENGINE = InnoDB;

# -- Users contains information about users and their rolls.
CREATE TABLE IF NOT EXISTS USERS (
  ID       INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
  ROLE     VARCHAR(50) NOT NULL,
  LOGIN    VARCHAR(50) NOT NULL,
  PASSWORD VARCHAR(50) NOT NULL
)
  ENGINE = InnoDB;

# -- History of components delivery on stock.
CREATE TABLE IF NOT EXISTS STOCK_HISTORY (
  ID            INT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  ID_COMPONENTS INT       NOT NULL,
  DATA          TIMESTAMP NOT NULL,
  PRICE         FLOAT     NOT NULL,

  FOREIGN KEY (ID_COMPONENTS) REFERENCES STOCK (ID)
)
  ENGINE = InnoDB;


# -- User_order display information about user orders.
CREATE TABLE IF NOT EXISTS USER_ORDER (
  ID       INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  ID_USER  INT NOT NULL,
  ID_ORDER INT NOT NULL,

  FOREIGN KEY (ID_USER) REFERENCES USERS (ID),
  FOREIGN KEY (ID_ORDER) REFERENCES ORDERS (ID)
)
  ENGINE = InnoDB;


# -- Stock_components displays relations stock components with components for sale and their price.
CREATE TABLE IF NOT EXISTS STOCK_COMPONENTS(
  ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  ID_STOCK_COMPONENT INT NOT NULL,
  ID_COMPONENT INT NOT NULL,
  FOREIGN KEY (ID_STOCK_COMPONENT) REFERENCES STOCK(ID),
  FOREIGN KEY (ID_COMPONENT) REFERENCES COMPONENTS(ID)
)
  ENGINE = InnoDB;





