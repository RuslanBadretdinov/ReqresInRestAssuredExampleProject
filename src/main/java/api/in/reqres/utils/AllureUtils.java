package api.in.reqres.utils;

import io.qameta.allure.Allure;
import io.restassured.response.ValidatableResponse;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AllureUtils {

    private AllureUtils() {
    }

    public static <T> T addAttachment(T ob) {
        Allure.addAttachment("Register Response", "application/json",
                ob.toString());
        return ob;
    }

    public static void addAttachment(String name, String attachedText) {
        Allure.addAttachment(name, "application/json",
                attachedText);
    }

    public static void assertEqualsWithAllureStep(String name, Object expectedValue, Object actualValue) {
        Allure.step(name, () -> {
            Allure.step("№1 - expected value, №2 - actual value");
            assertEquals(addAttachment(expectedValue), addAttachment(actualValue));
        });
    }

    public static void assertValidateJSONAllureStep(ValidatableResponse response, String validateJsonPath) {
        Allure.step(String.format("Валидация по JSON схеме ответа '%s'", response.extract().contentType()), () -> {
            Allure.step(String.format("путь к JSON файлу : '%s'", validateJsonPath));
            response.body(matchesJsonSchemaInClasspath(validateJsonPath));
        });
    }
}
