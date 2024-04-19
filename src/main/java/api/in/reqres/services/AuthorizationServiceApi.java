package api.in.reqres.services;

import api.in.reqres.annotation.Api;
import api.in.reqres.dto.authorization.AccountDTO;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

@Api
public class AuthorizationServiceApi {
    private final String BASE_API_URL = System.getProperty("base.api.url");
    private final String REGISTER_PATH = "/register";

    private RequestSpecification registerRS;

    public AuthorizationServiceApi() {
        this.registerRS = given()
                .baseUri(BASE_API_URL)
                .basePath(REGISTER_PATH)
                .contentType(ContentType.JSON);
    }

    public ValidatableResponse registerUser(AccountDTO accountDTO) {
        return given(this.registerRS)
                .body(accountDTO)
                .log().all().
                when()
                .post().
                then()
                .log().all();
    }
}
