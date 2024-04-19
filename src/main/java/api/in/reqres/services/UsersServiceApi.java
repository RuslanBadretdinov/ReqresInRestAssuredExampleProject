package api.in.reqres.services;

import api.in.reqres.annotation.Api;
import api.in.reqres.dto.user.crud.UserFormReqDTO;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

@Api
public class UsersServiceApi extends BaseServiceApiAbstract {
    private final String BASE_API_URL;
    private final String BASE_PATH;

    public UsersServiceApi() {
        BASE_API_URL = System.getProperty("base.api.url");
        BASE_PATH = "/users";
        spec(BASE_API_URL, BASE_PATH);
    }

    @Step("Отправка - 'POST' + '/users' - регистрация пользователя")
    public ValidatableResponse createUser(UserFormReqDTO userFormReqDTO) {
        return given()
                .body(userFormReqDTO).
                when()
                .post().
                then()
                .log().all();
    }
}
