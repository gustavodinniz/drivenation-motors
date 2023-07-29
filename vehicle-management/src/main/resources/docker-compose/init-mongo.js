db = connect("mongodb://drivenation:drivenation@localhost:27017/admin");

db = db.getSiblingDB('vehicle');

db.vehicles.insertMany([
    {
        "model": "Model S",
        "year": 2022,
        "color": "White",
        "manufacturer": "Tesla",
        "chassisNumber": "5YJ3E1EA8JF123456",
        "price": 35000,
        "status": "FOR_SALE",
        "ownership": "DEALERSHIP"
    },
    {
        "model": "Civic",
        "year": 2020,
        "color": "Black",
        "manufacturer": "Honda",
        "chassisNumber": "2HGFA1F58AH678910",
        "price": 20000,
        "status": "FOR_SALE",
        "ownership": "DEALERSHIP"
    },
    {
        "model": "Model 3",
        "year": 2023,
        "color": "Red",
        "manufacturer": "Tesla",
        "chassisNumber": "5YJ3E1EA7KF368907",
        "price": 40000,
        "status": "FOR_SALE",
        "ownership": "DEALERSHIP"
    },
    {
        "model": "Corolla",
        "year": 2022,
        "color": "Blue",
        "manufacturer": "Toyota",
        "chassisNumber": "2T1BURHE1KC192345",
        "price": 25000,
        "status": "FOR_SALE",
        "ownership": "DEALERSHIP"
    },
    {
        "model": "Mustang",
        "year": 2021,
        "color": "Yellow",
        "manufacturer": "Ford",
        "chassisNumber": "1FATP8UH3K5151234",
        "price": 45000,
        "status": "FOR_SALE",
        "ownership": "DEALERSHIP"
    },
]);
