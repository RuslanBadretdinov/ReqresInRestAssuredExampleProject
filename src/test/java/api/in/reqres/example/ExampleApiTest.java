package api.in.reqres.example;

import api.in.reqres.dto.authorization.AccountDTO;
import api.in.reqres.dto.authorization.ErrorResponseDTO;
import api.in.reqres.services.AuthorizationServiceApi;
import com.github.javafaker.Faker;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExampleApiTest {

    private Faker faker = new Faker();
    private AuthorizationServiceApi authorizationServiceApi = new AuthorizationServiceApi();

    @Tag("@apiExample")
    @Test
    public void registerWrongNewAccount() {
        AccountDTO accountDTO = AccountDTO.builder()
                .username(faker.name().username())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .build();

        ValidatableResponse registerUserResponse = authorizationServiceApi.registerUser(accountDTO);
        registerUserResponse.statusCode(400);

        // Использование Matchers
        registerUserResponse.body("error", equalTo("Note: Only defined users succeed registration"));

        // Использование JSON Path, например JSON имеет большую вложенность
        //  registerUserResponse.body("store.book.category", equalTo("example text"))

        // Вариант из body вытащить поле или массив полей :
        String actualError = registerUserResponse.extract().body().jsonPath().get("error").toString();
        String expectedError = "Note: Only defined users succeed registration";

        assertThat(actualError)
                .as("Поле Error не совпадает")
                .isEqualTo(expectedError);

        assertEquals(expectedError, actualError);

        assertAll(
                "Group assertions - soft assertions",
                ()-> assertEquals(expectedError, actualError),
                ()-> assertEquals(expectedError, actualError)
        );

        // Вариант получения объекта DTO (ErrorResponseDTO) из запроса
        ErrorResponseDTO actualErrorResponseDTO = registerUserResponse.extract().body().as(ErrorResponseDTO.class);

        assertEquals(expectedError, actualErrorResponseDTO.getError());

        // Валидация по JSON схеме
        registerUserResponse.statusCode(400)
                .body(matchesJsonSchemaInClasspath("api.in.reqres/jsonvalidation/RegisterError.json"))
                .body("error", equalTo(expectedError));
    }
}
