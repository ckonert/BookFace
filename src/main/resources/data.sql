ALTER TABLE author ALTER COLUMN id RESTART WITH 5000;
ALTER TABLE book ALTER COLUMN id RESTART WITH 5000;

INSERT INTO author (id, lastname, firstname, image_url) VALUES (1001, 'Tolkien', 'JRR', 'https://m.media-amazon.com/images/I/417CVz75bDS._AC_UF1000,1000_QL80_.jpg');
INSERT INTO author (id, lastname, firstname, image_url) VALUES (1002, 'Martin', 'George RR', 'https://upload.wikimedia.org/wikipedia/commons/thumb/1/16/George_R._R._Martin_by_Gage_Skidmore_2.jpg/1200px-George_R._R._Martin_by_Gage_Skidmore_2.jpg');
INSERT INTO author (id, lastname, firstname, image_url) VALUES (1003, 'Herbert', 'Frank', 'https://media.snl.no/media/190799/standard_compressed_Frank_Herbert.jpg');
INSERT INTO author (id, lastname, firstname, image_url) VALUES (1004, 'Clavell', 'James', 'https://cdn.britannica.com/14/255414-050-4EC1222C/author-James-Clavell.jpg');
INSERT INTO author (id, lastname, firstname, image_url) VALUES (1005, 'Dahl', 'Roald', 'https://hips.hearstapps.com/hmg-prod/images/roald_dahl_getty_images_tony_evans:getty_images_108874289_cropped.jpg?resize=1200:*');
INSERT INTO book (id, title, isbn13, author_id, image_url) VALUES (2001, 'A Game of Thrones', '0553808044', 1002, 'https://media.s-bol.com/YxjqzWY2Dl30/np8R9E/805x1200.jpg');
INSERT INTO book (id, title, isbn13, author_id, image_url) VALUES (2002, 'The Lord of the Rings', '9780008471286', 1001, 'https://m.media-amazon.com/images/I/71-k7upkQjL._SL1500_.jpg');
INSERT INTO book (id, title, isbn13, author_id, image_url) VALUES (2003, 'Shogun', '1982603844', 1004, 'https://m.media-amazon.com/images/I/81FyuFYZjsL._SL1500_.jpg');
INSERT INTO book (id, title, isbn13, author_id, image_url) VALUES (2004, 'Dune', '059309932X', 1003, 'https://media.s-bol.com/mg4MK70JNg4r/PNmDzl1/550x830.jpg');
INSERT INTO book (id, title, isbn13, author_id, image_url) VALUES (2005, 'The BFG', '0141371145', 1005, 'https://m.media-amazon.com/images/I/71eGIx9AwvL._SL1200_.jpg');

