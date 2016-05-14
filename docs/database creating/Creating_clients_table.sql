CREATE TABLE Clients (
    id          INTEGER PRIMARY KEY AUTOINCREMENT
                        NOT NULL
                        UNIQUE,
    name        STRING  NOT NULL,
    contactInfo STRING
);
