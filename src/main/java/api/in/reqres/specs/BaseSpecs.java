package api.in.reqres.specs;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseSpecs {
    private BaseSpecs() {
    }

    public static RequestSpecification requestSpec(String baseUri, int port, String basePath) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setPort(port)
                .setBasePath(basePath)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .addFilter(new AllureRestAssured())
                .build();
    }

    public static RequestSpecification requestSpec(String baseUri, String basePath) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(basePath)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .addFilter(new AllureRestAssured())
                .build();
    }

    public static ResponseSpecification responseSpec() { return new ResponseSpecBuilder().log(LogDetail.ALL).build(); }

    public static void installSpec(RequestSpecification requestSpec, ResponseSpecification responseSpec) {
        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }
}
