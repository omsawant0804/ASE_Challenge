# Inventory Management System API [Matarial](https://drive.google.com/drive/folders/1I5Eq8_lPv4PFglTmFF7GxTig3hDh1NDr?usp=drive_link)
[Demo Video](https://drive.google.com/file/d/1jFbLcPSvwYXktzb_JCWoGdV_3sqs8JkQ/view?usp=drive_link)\
[API Collection (JSON File)](https://drive.google.com/file/d/1dmoqX3FsJynZ6ukQbMsFt52qPEylwxEz/view?usp=drive_link)

### Tech Stack : Spring Boot/Java + SQL/MySQL

I built an Inventory Management System with CRUD APIs to manage products. Along with that, I added APIs to increase or decrease the stock of existing products. There’s also an endpoint to fetch products whose stock is below a given threshold. Every endpoint has proper validations and handles edge cases, returning a 400 Bad Request when needed.

### APIs Overview
1. **addProduct** : To Add New Product in Inventory.
2. **GetAllProducts** : To get all Products from inventory.
3. **updateProduct** : To Update product detailes (can update single filed, combination of fileds, all at onces).
4. **GetPerProduct** : Get perticular product detailes.
5. **deleteProduct** : Delete Product From Inventory.
6. **increaseStock** : Add Stock to existed Product.
7. **decreaseStock** : Decrease The Stock from the existed Product.
8. **belowThreShold** : Get List of Products Which Stocks are Below Threashold.

### Database
- databaseName : inventory_management
- tableName : tbl_products
- table Colum overview
<img width="724" height="128" alt="image" src="https://github.com/user-attachments/assets/5564dff7-c4fe-42bd-b00d-fc00aa4aad60" />

## Project SetUp
(setup included in demo video) (https://drive.google.com/file/d/1jFbLcPSvwYXktzb_JCWoGdV_3sqs8JkQ/view?usp=drive_link)
### Prerequisites
1. Java JDK (preferably 17 or 21) installed and set in PATH.
2. Maven installed.
3. MySQL Server installed and running.

### Steps to run the Project
1. Clone the Repo 
   ``` git clone https://github.com/omsawant0804/ASE_Challenge.git ```
2. Open in any IDE
3. Open ```application.properties``` file located in ```ASE_Challenge\InventoryManagement\ASE_Challenge\src\main\resources\``` \
Check the Configration. Setup the Server Port or MySQL port/user id/password according to your system.
<img width="1279" height="593" alt="image" src="https://github.com/user-attachments/assets/eb6e6163-3c59-40ab-8598-5a550d2de643" />

4. Open MySQL WorkBench and Create the database ```Create database if not exists inventory_management;```
5. Open IDE Terminal
6. Run Command ```mvn clean install``` it will clean and install dependencies
<img width="1463" height="411" alt="image" src="https://github.com/user-attachments/assets/bcba6aeb-d5d0-41c3-8045-a5c3e3ac5bab" />

7. After Build Sucess to run or Start the Server run command ```mvn spring-boot:run``` this will start the server
<img width="1816" height="553" alt="image" src="https://github.com/user-attachments/assets/bf19b2d8-8f04-4d9f-b17d-a2b18a920b44" />

8. We are free to hit the APIs **(Note : First Add the Products to perform any operations)**

## Test the APIs 
(testing included in demo video) (https://drive.google.com/file/d/1jFbLcPSvwYXktzb_JCWoGdV_3sqs8JkQ/view?usp=drive_link)
 
 - Open PostMan and Import the JSON File [API Collection (JSON File)](https://drive.google.com/file/d/1dmoqX3FsJynZ6ukQbMsFt52qPEylwxEz/view?usp=drive_link)

### 1. POST : addProduct API ```http://localhost:8080/add-product```
**Note : If you have changed server port in application.properties change here too ```8080--> you port```. If you are using server address change ```localhost-->your ip address```**

Body :
```
{
    "productName":"ELM2",
    "productDescription":"Washing product",
    "quantity":200,
    "threShold":100
}
```
Response :
```
{
    "success": true,
    "message": "Product Saved Successfully",
    "data": [
        {
            "productName": "ELM2",
            "productDescription": "Washing product",
            "quantity": 200,
            "threShold": 100
        }
    ]
}
```

### 2. GET : GetAllProducts ```http://localhost:8080/get-all-products```
**Note : If you have changed server port in application.properties change here too ```8080--> you port```. If you are using server address change ```localhost-->your ip address```**

Response :
```
{
    "success": true,
    "message": "Products Fetched",
    "data": [
        {
            "id": "43709236-ef09-4126-8312-f29a860e9bc9",
            "productName": "ELM2",
            "productDescription": "Washing product",
            "stockQuantity": 200,
            "threShold": 100
        },
        {
            "id": "6ecd0323-5a51-402d-a123-6c1f63c716bb",
            "productName": "VIM",
            "productDescription": "Washing product",
            "stockQuantity": 0,
            "threShold": 50
        },
        {
            "id": "868843f8-5bbb-42ce-a046-4db13a15e78e",
            "productName": "ELM2",
            "productDescription": "Washing product",
            "stockQuantity": 0,
            "threShold": 100
        },
        {
            "id": "9679512f-69b4-4708-b1ed-e34c3dc24fcb",
            "productName": "WELL",
            "productDescription": "Washing product",
            "stockQuantity": 100,
            "threShold": 10
        }
    ]
}
```

 ### 3.PATCH : updateProduct ```http://localhost:8080/update-product/{pass here product id to update}```
 **Note : If you have changed server port in application.properties change here too ```8080--> you port```. If you are using server address change ```localhost-->your ip address```**
Body :
```
{
    "productName":"Saas",
    "productDescription":"Washing powder nirma",
    "quantity":10000,
    "threShold":1500
}
```
Response :
```
{
    "success": true,
    "message": "Product updated successfully",
    "data": [
        {
            "productName": "Saas",
            "productDescription": "Washing powder nirma",
            "quantity": 10000,
            "threShold": 1500
        }
    ]
}
```

### 4. GET : Get Perticular Product Detailes ```http://localhost:8080/get-product/{pass id}```
**Note : If you have changed server port in application.properties change here too ```8080--> you port```. If you are using server address change ```localhost-->your ip address```**
Response :
```
{
    "success": true,
    "message": "Product Fetched",
    "data": [
        {
            "productName": "Saas",
            "productDescription": "Washing powder nirma",
            "quantity": 10000,
            "threShold": 1500
        }
    ]
}
```

### 5. DELETE : DeleteProduct ```http://localhost:8080/delete-product/{pass id}```
**Note : If you have changed server port in application.properties change here too ```8080--> you port```. If you are using server address change ```localhost-->your ip address```**

Response :
```
{
    "success": true,
    "message": "Product Deleted",
    "data": [
        {
            "productName": "Saas",
            "productDescription": "Washing powder nirma",
            "quantity": 10000,
            "threShold": 1500
        }
    ]
}
```

### 6. POST : IncreaseStock ```http://localhost:8080/inventory/increase-stock/{pass id}```
**Note : If you have changed server port in application.properties change here too ```8080--> you port```. If you are using server address change ```localhost-->your ip address```**
Body
```
{
    "quantity":10
}
```
Response :
```
{
    "success": true,
    "message": "Product Stock Increased Successfully",
    "data": [
        {
            "productName": "WELL",
            "productDescription": "Washing product",
            "quantity": 110,
            "threShold": 10
        }
    ]
}
```

### 7. POST : DecreaseStock ```http://localhost:8080/inventory/decrease-stock/{pass id}```
**Note : If you have changed server port in application.properties change here too ```8080--> you port```. If you are using server address change ```localhost-->your ip address```**
Body :
```
{
    "quantity":10
}
```
Response :
```
{
    "success": true,
    "message": "Product Stock Decreased Successfully",
    "data": [
        {
            "productName": "WELL",
            "productDescription": "Washing product",
            "quantity": 100,
            "threShold": 10
        }
    ]
}
```

### 8. GET : Products below Threshold ```http://localhost:8080/inventory/below-threshold```
**Note : If you have changed server port in application.properties change here too ```8080--> you port```. If you are using server address change ```localhost-->your ip address```**
Response :
```
{
    "success": true,
    "message": "Product fetched below threshold",
    "data": [
        {
            "productName": "VIM",
            "productDescription": "Washing product",
            "quantity": 0,
            "threShold": 50
        }
    ]
}
```

## Struture I follow
### For Controller layer
similar for every function in controller
```
@PostMapping("/add-product")
    public ResponseEntity<Response<ProductResponse>> addProduct(@RequestBody ProductRequest ProductRequest) {
        try {
            Response<ProductResponse> res = productService.addProduct(ProductRequest);
            if (res.isSuccess()) {
                return ResponseEntity.ok(res);
            }
            return ResponseEntity.badRequest().body(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response<>(false,e.toString(),null));
        }
    }
```

### For Service layer
similar for every function in service layer
```
    public Response<ProductResponse> getBelowThreShouldProducts(){
        try{
            List<Product> products=productRepository.getProductsBelowThreShold();
            if(products==null || products.isEmpty()){
                return new Response<>(true,"No Products Below ThreShold",null);
            }
            return new Response<>(true,"Product fetched below threshold",products.stream().map(ProductResponse::new).toList());
        } catch (Exception e) {
            return new Response<>(false,"Something Went Wrong while fetching",null);
        }
    }
```

### For Response
similar for evey response as we observe 
```
public class Response<T> {
// We are using this Response bcoz we have to standardize the structure we send to frontend
   private boolean success; // it holds true or false : true means success
   private String message; // here we are using this bcoz we want some time to send custom message
   private List<T> data; // if its post we can send our data here if not send null
}
```

   
   


