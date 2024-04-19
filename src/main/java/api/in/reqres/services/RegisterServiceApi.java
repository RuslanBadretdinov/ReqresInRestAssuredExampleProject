package api.in.reqres.services;

import api.in.reqres.annotation.Api;
import api.in.reqres.dto.authorization.AccountDTO;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

@Api
public class RegisterServiceApi extends BaseServiceApiAbstract {
    private String BASE_API_URL;
    private String BASE_PATH;
    private RequestSpecification requestSpec;

    public RegisterServiceApi() {
        BASE_API_URL = System.getProperty("base.api.url");
        BASE_PATH = "/register";
        spec(BASE_API_URL, BASE_PATH);
    }

    @Step("Отправка - 'POST' + '/register' - регистрация аккаунта")
    public ValidatableResponse registerAccount(AccountDTO accountDTO) {
        return given(this.requestSpec)
                .body(accountDTO).
                when()
                .post().
                then()
                .log().all();
    }
}
