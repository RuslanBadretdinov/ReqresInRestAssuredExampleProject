package api.in.reqres.services;

import api.in.reqres.annotation.Api;
import api.in.reqres.dto.user.UserDTO;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

@Api
public class UsersServiceApi {
    private final String BASE_API_URL = System.getProperty("base.api.url");
    private final String BASE_PATH = "/users";

    @Step("получены данные страницы с №{pageNum}")
    public void putPage(long id, UserDTO userDTO) {
        given()
                .baseUri(BASE_API_URL)
                .basePath(BASE_PATH);
//                .
//                .contentType(ContentType.JSON);

    }
}
