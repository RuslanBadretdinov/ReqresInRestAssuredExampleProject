package api.in.reqres.services;

import api.in.reqres.annotation.Api;
import api.in.reqres.dto.user.crud.UserFormReqDTO;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.fail;

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

    @Step("Отправка - '{responseType}' + '/users'+'/{id}'")
    public ValidatableResponse putPatchDeleteUser(String responseType, int id, UserFormReqDTO userFormReqDTO) {
        RequestSpecification rs = given()
                .basePath("/" + id)
                .body(userFormReqDTO);
        Response response = null;
        switch (responseType) {
            case "PUT": {
                response = given(rs)
                        .put();
                break;
            }
            case "PATCH": {
                response = given(rs)
                        .patch();
                break;
            }
            case "DELETE": {
                response = given(rs)
                        .delete();
                break;
            }
            default: {
                fail("Было введено не верное название запроса");
            }
        }
        return response.then()
                .log().all();
    }
}
