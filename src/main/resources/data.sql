delete from INGREDIENT_REF;
delete from TACO;
delete from TACO_ORDER;
delete from INGREDIENT;

insert into INGREDIENT (id, name, type) VALUES ('FLTO', 'Flour Tortialla', 'WRAP');
insert into INGREDIENT (id, name, type) VALUES ('COTO', 'Corn Tortilla', 'WRAP');
insert into INGREDIENT (id, name, type) VALUES ('GRBG', 'Ground Beef', 'PROTEIN');
insert into INGREDIENT (id, name, type) VALUES ('CARN', 'Carnitas', 'PROTEIN');
insert into INGREDIENT (id, name, type) VALUES ('TMTO', 'Diced Tomatoes', 'VEGGIES');
insert into INGREDIENT (id, name, type) VALUES ('LETC', 'Lettuce', 'VEGGIES');
insert into INGREDIENT (id, name, type) VALUES ('CHED', 'Cheddar', 'CHEESE');
insert into INGREDIENT (id, name, type) VALUES ('JACK', 'Monterrey Jack', 'CHEESE');
insert into INGREDIENT (id, name, type) VALUES ('SLSA', 'Salsa', 'SAUCE');
insert into INGREDIENT (id, name, type) VALUES ('SRCR', 'Sour Cream', 'SAUCE');
