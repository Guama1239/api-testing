package trainingxyz;


import models.Product;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.replaceFiltersWith;

public class ApiTest {
// I had problems with localhost because the instructor uses a macbook and port 8888 for first
    // two methods. In my xps dell, I found on the MAMP FAQ that I have to use port 80 for Apache
    @Test
    public void getCategories(){
        String endpoint = "http://localhost:80/api_testing/category/read.php";
        var response = given().when().get(endpoint).then();
        response.log().body();
    }

    @Test
    void getProduct(){
        String endpoint = "http://localhost:80/api_testing/product/read_one.php";
        //var response =
                given().
                        queryParam("id", 2).
                when().
                        get(endpoint).
                        //then().log().body();
                    then(). // Section 3 of the course added 2 the lines of code beyond the then();
                    assertThat().
                        statusCode(200);
        //response.log().body();
    }

    @Test
    public void createProduct(){
        String endpoint = "http://localhost:80/api_testing/product/create.php";
        // Instructor is working with Java 15 so she uses """ """
        // I will use a more primitive approach to build the body with JSON, variable body failed, but body2 does the job!
        String body = "{'name': 'Water Bottle', 'description': 'Blue water bottle. Holds 64 ounces', 'price':'12', 'category_id': '3'}";
        String body2 = "{\"name\":\"Water Bottle\"," +
                " \"description\":\"Blue water bottle. Holds 64 ounces\"," +
                " \"price\":\"12\"," +
                " \"category_id\":\"3\"}";
        var response = given().body(body2).when().post(endpoint).then();
        response.log().body();
    }

    @Test
    void updateProduct(){
        String endpoint = "http://localhost:80/api_testing/product/update.php";
        String body2 = "{\"id\":\"1001\", \"name\":\"Water Bottle\", \"description\":\"Blue water bottle. Holds 64 ounces\", \"price\":\"15\", \"category_id\":\"3\"}";
        var response = given().body(body2).when().put(endpoint).then();
        response.log().body();
    }

    @Test
    public void deleteProduct(){
        String endpoint = "http://localhost:80/api_testing/product/delete.php";
        String body2 = "{\"id\":\"1001\"}";
        var response = given().body(body2).when().delete(endpoint).then();
        response.log().body();
    }

    @Test
    public void createSerializedProduct(){
        String endpoint = "http://localhost:80/api_testing/product/create.php";
        Product product = new Product("Water Bottle",
                "Blue water bottle, Holds 64 ounces",
                12,
                3
        );
        var response = given().body(product).when().post(endpoint).then();
        response.log().body();
    }

    @Test
    public void createProduct2(){
        String endpoint = "http://localhost:80/api_testing/product/create.php";
        // Instructor is working with Java 15 so she uses """ """
        // I will use a more primitive approach to build the body with JSON, variable body failed, but body2 does the job!
        String body = "{'name': 'Water Bottle', 'description': 'Blue water bottle. Holds 64 ounces', 'price':'12', 'category_id': '3'}";
        String body2 = "{\"name\":\"Sweatband\"," +
                " \"description\":\"Sweat Baby Sweat\"," +
                " \"price\":\"2\"," +
                " \"category_id\":\"3\"}";
        var response = given().body(body2).when().post(endpoint).then();
        response.log().body();
    }

    @Test
    void updateProduct2(){
        String endpoint = "http://localhost:80/api_testing/product/update.php";
        String body2 = "{\"id\":\"1004\", \"price\":\"6\"}";
        var response = given().body(body2).put(endpoint).then();
        response.log().body();
    }

    @Test
    public void getProductDetails(){
        String endpoint = "http://localhost:80/api_testing/product/read_one.php";
        String body = "{\"id\":\"1003\"}";
        // Use queryParam
        var response = given().queryParam("id", 1002).when().get(endpoint).then();
        response.log().body();
    }

    @Test
    public void deleteProductById(){
        String endpoint = "http://localhost:80/api_testing/product/delete.php";
        String body = "{\"id\":\"1002\"}";
        // Next line does not do the delete operation, I had to use the var body instead
        //var response = given().queryParam("id", 1004).when().delete(endpoint).then();
        // using String body
        var response2 = given().body(body).when().delete(endpoint).then();

        response2.log().body();
    }

}
