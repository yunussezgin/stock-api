# Stock API

## Tech Stack

- Java 11
- Spring Boot 2.7.5
- Maven
- H2 DB
- Lombok

## How to start the project ?

Run the below commands in the project directory.

```
mvn clean install
```

Run spring boot application.

```
mvn spring-boot:run
```

## How to start the project with Docker ?

Run the below commands in the project directory.

```
mvn clean install
```

```
docker build --tag=stock-api:v0.0.1 .
```

```
docker run -p8085:8085 stock-api:v0.0.1
```

## Postman Collection

[src/main/resources/postmancollection/](src/main/resources/postmancollection/)

## H2 DB INFO

- H2 DB URL: http://localhost:8085/api/h2-console
- JDBC URL: jdbc:h2:mem:testdb
- Username: test
- Password: test

## Swagger UI URL

http://localhost:8085/api/swagger-ui/index.html

## Example Request & Response

`REQUEST`

<b>GET</b> /api/stocks/19a4af8c-8292-4798-b4f2-e769bb1b491c

`RESPONSE`
```
200 OK

{
    "lastUpdate": "19-12-2022 08:05:16",
    "id": "19a4af8c-8292-4798-b4f2-e769bb1b491c",
    "productCode": "B09KCNSQYN",
    "name": "BOOX Note Air2 Plus",
    "currentPrice": 518.19
}
```

`REQUEST`

<b>GET</b> /api/stocks?page=2&size=3&sort=name,desc

`RESPONSE`
```
200 OK

[
    {
        "lastUpdate": "19-12-2022 08:05:16",
        "id": "e6a15c12-d8a5-4e31-ad16-a2020e8f9d56",
        "productCode": "B09KCNSQYS",
        "name": "Philips Azur Steam iron",
        "currentPrice": 53.56
    },
    {
        "lastUpdate": "19-12-2022 08:05:16",
        "id": "1d06b6f0-4a0b-4c18-83a0-d96be6beea88",
        "productCode": "B09KCNSQYL",
        "name": "Philips Airfryer",
        "currentPrice": 82.78
    },
    {
        "lastUpdate": "19-12-2022 08:05:16",
        "id": "a06e7f67-35b2-4b1c-ae38-d1da6cf8aa48",
        "productCode": "B09KCNSQYM",
        "name": "Kindle Paperwhite",
        "currentPrice": 189.99
    }
]
```

`REQUEST`

<b>POST</b> /api/stocks

```
{
  "productCode": "B09KCNSQYX",
  "name": "Logitech Vertical Ergonomic Mouse",
  "currentPrice": 98.00
}
```

`RESPONSE`
```
201 Created

{
    "lastUpdate": "19-12-2022 08:01:03",
    "id": "a8eae43e-f6e9-4184-8577-5c04f2b4a546",
    "productCode": "B09KCNSQYX",
    "name": "Logitech Vertical Ergonomic Mouse",
    "currentPrice": 98.00
}
```

`REQUEST`

<b>PATCH</b> /api/stocks/19a4af8c-8292-4798-b4f2-e769bb1b491c

```
{
  "currentPrice": 120
}
```

`RESPONSE`
```
200 OK

{
    "lastUpdate": "19-12-2022 08:01:16",
    "id": "19a4af8c-8292-4798-b4f2-e769bb1b491c",
    "productCode": "B09KCNSQYN",
    "name": "BOOX Note Air2 Plus",
    "currentPrice": 120
}
```

`REQUEST`

<b>DELETE</b> /api/stocks/19a4af8c-8292-4798-b4f2-e769bb1b491c

```
204 No Content
```