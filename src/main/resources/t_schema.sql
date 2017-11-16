
CREATE TABLE category
(
   categoryId          INT            NOT NULL,
   name  VARCHAR(255) NOT NULL,
   PRIMARY KEY (categoryId,name)
);

CREATE TABLE product
(
   productId          INT            NOT NULL AUTO_INCREMENT,
   name  VARCHAR(255) DEFAULT NULL,
   categoryId       INT DEFAULT NULL,
   price   DOUBLE,
   PRIMARY KEY (productId),
   foreign key (categoryId) references category(categoryId)

);