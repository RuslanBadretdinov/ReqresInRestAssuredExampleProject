package api.in.reqres.services;

import api.in.reqres.annotation.Api;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

@Api
public class PageServiceApi {
    private final String BASE_API_URL = System.getProperty("base.api.url");
    private final String BASE_PATH = "/";

    @Step("получены данные страницы с №{pageNum}")
    public void getPage(long pageNum) {
        given()
                .baseUri(BASE_API_URL)
                .basePath(BASE_PATH)
                .contentType(ContentType.JSON);

    }
}
