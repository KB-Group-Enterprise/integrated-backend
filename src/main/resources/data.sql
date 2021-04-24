-- dummy database for entity testing
insert into Brands (id,name) values (1,'Toyota');
insert into Brands (id,name) values (2,'BMW');
insert into Brands (id,name) values (3,'Mercedes Benz');
insert into Brands (id,name) values (4,'Tesla');
insert into Brands (id,name) values (5,'Mitsubishi');

insert into Colors (id,name) values (1,'red');
insert into Colors (id,name) values (2,'green');
insert into Colors (id,name) values (3,'blue');
insert into Colors (id,name) values (4,'silver');
insert into Colors (id,name) values (5,'yellow');
insert into Colors (id,name) values (6,'orange');
insert into Colors (id,name) values (7,'black');
insert into Colors (id,name) values (8,'white');

insert into Images(id,name,cars_id) values (1,'bmw-1.jpg',1);
insert into Images(id,name,cars_id) values (2,'bmw-2.jpg',1);
insert into Images(id,name,cars_id) values (3,'benz-1.jpg',2);

INSERT INTO Cars (id,name,price,description,releasedate,brands_id) VALUES (1,'BMW 2 Series Coupe',2990000.00,'The BMW 2 Series Coupé thrills from every angle. Whether it’s the powerful rear-drive performance or the chic sporty presence, it’s ready to set pulses racing. Add to this the impressive interior comforts, and you have the complete driving experience.','2021-02-10',2);
INSERT INTO Cars (id,name,price,description,releasedate,brands_id) VALUES (2,'E 220 d Sport Saloon',4990000.00,'The design will captivate you. The infotainment system knows you like a good friend. And the comfort lets you enjoy every second of the journey. Perfect for mastering daily challenges not only in style, but also dynamically and comfortably.','2020-02-21',3);

insert into CarColors(cars_id,colors_id) values (1,1);
insert into CarColors(cars_id,colors_id) values (1,2);
insert into CarColors(cars_id,colors_id) values (1,5);
insert into CarColors(cars_id,colors_id) values (1,7);
insert into CarColors(cars_id,colors_id) values (1,8);
insert into CarColors(cars_id,colors_id) values (2,2);
insert into CarColors(cars_id,colors_id) values (2,4);
insert into CarColors(cars_id,colors_id) values (2,6);
insert into CarColors(cars_id,colors_id) values (2,7);
insert into CarColors(cars_id,colors_id) values (2,8);
