CREATE TABLE Devices (
    id       INTEGER PRIMARY KEY AUTOINCREMENT
                     UNIQUE,
    device   STRING  NOT NULL,
    owner    INTEGER CONSTRAINT device_client REFERENCES Clients (id) 
                     NOT NULL,
    date_in  DATE    NOT NULL,
    date_out DATE,
    complete BOOLEAN DEFAULT (0),
    defect   STRING
);
