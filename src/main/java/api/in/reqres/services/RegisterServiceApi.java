package api.in.reqres.services;

import api.in.reqres.annotation.Api;
import api.in.reqres.dto.authorization.AccountDTO;
import api.in.reqres.specs.BaseSpecs;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

@Api
public class RegisterServiceApi {
    private String BASE_API_URL;
    private String BASE_PATH;
    private RequestSpecification requestSpec;

    public RegisterServiceApi() {
        BASE_API_URL = System.getProperty("base.api.url");
        BASE_PATH = "/register";
        spec();
    }

    @Step("Отправка - 'POST' + '/register' - регистрация пользователя")
    public ValidatableResponse registerUser(AccountDTO accountDTO) {
        return given(this.requestSpec)
                .body(accountDTO).
                when()
                .post().
                then()
                .log().all();
    }

    private void spec() {
        this.requestSpec = BaseSpecs.requestSpec(this.BASE_API_URL, this.BASE_PATH);
        BaseSpecs.installSpec(this.requestSpec, BaseSpecs.responseSpec());
    }
}
