package trainingxyz;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

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
        var response =
                given().
                        queryParam("id", 2).
                when().
                        get(endpoint).
                    then();
        response.log().body();
    }
}
