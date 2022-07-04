# Spring Boot "type-rest-api" Example Project

Springboot Rest API Assessment

## About the Service

The service is just a types get REST service. It uses an in-memory database (H2) to store the data.
As there is just GET(http://localhost:8080/api/v1/types) endpoint supported it will save three record when
application bootstrap so when we hit the get endpoint we have list of response.

### Retrieve a list of types

```
http://localhost:8080/api/v1/types

Response: HTTP 200
Content-Type: application/json

Sample Response
[
    {
        "annotation": "SUBJID_temp",
        "operation": "tag",
        "comments": true,
        "segmentation": "both",
        "patientSegmentLengthInParagraphs": 2000,
        "patientSegmentTableRows": true,
        "displayName": "SUBJID_temp",
        "highlightColour": "Red",
        "defaultMaskValue": "[****]",
        "highlightColourHexCode": "#FF0D0D",
        "classification": "DI"
    }
]

<img width="1011" alt="image" src="https://user-images.githubusercontent.com/44820853/177214413-9db8fe84-5252-4df3-bf6f-7b4778a2f25c.png">


```

### Currently it support GET Only so if we try to do insert or update a type resource

```
POST /api/v1/types
Accept: application/json
Content-Type: application/json

{
    "annotation": "WEIGHT_KG",
    "operation": "tag",
    "comments": true,
    "segmentation": "both",
    "patientSegmentLengthInParagraphs": 2000,
    "patientSegmentTableRows": true,
    "displayName": "WEIGHT_KG",
    "highlightColour": "Green",
    "defaultMaskValue": "[**]",
    "highlightColourHexCode": "#8AC926",
    "classification": "QI"
}

Response: HTTP 405 (Method Not Allowed)
Content-Type: application/json
Response 
{
    "message": "Request method 'POST' not supported",
    "status": "METHOD_NOT_ALLOWED",
    "timestamp": "2022-07-04T16:32:44.904716"
}
<img width="584" alt="image" src="https://user-images.githubusercontent.com/44820853/177214476-ec4250d9-d7ad-4403-a58d-9d81679d74ea.png">

```

### To view OpenAPI 3 API docs

Run the server and browse to http://localhost:8080/swagger-ui.html
<img width="1440" alt="image" src="https://user-images.githubusercontent.com/44820853/177214338-c70129f9-4af0-4af1-9424-0c8d59beca0e.png">

### To view H2 in-memory datbase

Run the server and browse to http://localhost:8080/h2-ui
JDBC URL: jdbc:h2:mem:iqiviadb
Default username is 'sa' with a blank password
<img width="440" alt="image" src="https://user-images.githubusercontent.com/44820853/177214214-3f212e19-44e3-47ce-9a86-3508e594b646.png">
