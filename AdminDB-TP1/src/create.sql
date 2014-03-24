CREATE TABLE Client
(
IDClient NUMBER(3) NOT NULL,
nomClient VARCHAR2(30),
PRIMARY KEY (IDClient)
);

INSERT INTO Client VALUES (2, 'Louis');
INSERT INTO Client VALUES (3, 'Jean');
INSERT INTO Client VALUES (21, 'Jacqueline');
INSERT INTO Client VALUES (34, 'Robert');
INSERT INTO Client VALUES (54, 'Isabelle');


CREATE TABLE Vendeur
(
IDVendeur NUMBER(3) NOT NULL,
nomVendeur VARCHAR2(30),
resp NUMBER(1),
PRIMARY KEY (IDVendeur)
);

INSERT INTO Vendeur VALUES (20, 'Lucien', 1);
INSERT INTO Vendeur VALUES (23, 'Julie', 0);
INSERT INTO Vendeur VALUES (45, 'Geogres', 1);


CREATE TABLE Commande
(
IDCmd NUMBER(3) NOT NULL,
Total NUMBER(7, 2),
IDClient NUMBER(3),
IDVendeur NUMBER(3),
PRIMARY KEY (IDCmd),
FOREIGN KEY (IDClient) REFERENCES Client(IDClient),
FOREIGN KEY (IDVendeur) REFERENCES Vendeur(IDVendeur)
);

INSERT INTO Commande VALUES (23, 345.00, 2, 20);
INSERT INTO Commande VALUES (4, 12.00, 3, 20);
INSERT INTO Commande VALUES (6, 1000.00, 54, 20);
INSERT INTO Commande VALUES (56, 234.54, 34, 23);
INSERT INTO Commande VALUES (76, 43.00, 21, 45);
INSERT INTO Commande VALUES (45, 12.00, 2, 23);