package api.in.reqres.example;

import api.in.reqres.dto.authorization.AccountDTO;
import api.in.reqres.dto.authorization.UnSuccessRegisterRespDTO;
import api.in.reqres.services.RegisterServiceApi;
import com.github.javafaker.Faker;
import in.reqres.extensions.UIExtension;
import io.qameta.allure.Allure;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static api.in.reqres.utils.AllureUtils.*;
import static org.hamcrest.Matchers.equalTo;

@Tag("@smokes")
@ExtendWith(UIExtension.class)
@DisplayName("Group - 'base.api.url'+/register")
public class ExampleApiTest {

    private Faker faker = new Faker();
    private RegisterServiceApi registerServiceApi = new RegisterServiceApi();

    @Test
    @Tag("@apiExample")
    @DisplayName("Регистрация аккаунта с неправильными данными")
    public void registerWrongNewAccount() {
        AccountDTO accountDTO = AccountDTO.builder()
                .username(faker.name().username())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .build();

        Allure.step("объект для 'POST'+'/register'", () -> {
            addAttachment(accountDTO.toString());
        });



        ValidatableResponse registerUserResponse = registerServiceApi.registerAccount(accountDTO);

        Allure.step("тело ответа запроса 'POST'+'/register'", () -> {
            addAttachment(registerUserResponse.extract().body().asString());
        });

        Allure.step("статус запроса 'POST'+'/register' == 400", () -> {
            registerUserResponse.statusCode(addAttachment(400));
        });

        Allure.step("Matchers - body 'POST' запроса '/register'", () -> {
            // Использование Matchers, например equalTo
            registerUserResponse.body(addAttachment("error"),
                    equalTo(addAttachment("Note: Only defined users succeed registration")));
        });

        Allure.step("Поиск поля по JSON Path - body 'POST' запроса '/register'", () -> {
            // Использование JSON Path, например JSON имеет большую вложенность
            //  registerUserResponse.body("store.book.category", equalTo("example text"))
        });


        // Вариант из body вытащить поле или массив полей :
        String actualError = registerUserResponse.extract().body().jsonPath().get("error").toString();
        String expectedError = "Note: Only defined users succeed registration";
//
//        Allure.step("вар №1 - body 'POST' запроса '/register'", () -> {
//            assertThat(actualError)
//                    .as("Поле Error не совпадает")
//                    .isEqualTo(expectedError);
//
//            assertEquals(expectedError, actualError);
//
//            assertAll(
//                    "Group assertions - soft assertions",
//                    ()-> assertEquals(expectedError, actualError),
//                    ()-> assertEquals(expectedError, actualError)
//            );
//        });
        assertEqualsWithAllureStep("Вар №1 - body 'POST' запроса '/register'",
                expectedError, actualError);

        UnSuccessRegisterRespDTO actualUnSuccessRegisterRespDTO = registerUserResponse.extract().body().as(UnSuccessRegisterRespDTO.class);

//        Allure.step("вар №2 - body 'POST' запроса '/register'", () -> {
//            // Вариант получения объекта DTO (ErrorResponseDTO) из запроса
//            assertEquals(expectedError, actualErrorResponseDTO.getError());
//        });
        assertEqualsWithAllureStep("Вар №2 - body 'POST' запроса '/register'",
                expectedError, actualUnSuccessRegisterRespDTO.getError());

//        Allure.step("Валидация по JSON схеме ответа 'POST' запроса '/register'", () -> {
//            // Валидация по JSON схеме
//            registerUserResponse.statusCode(400)
//                    .body(matchesJsonSchemaInClasspath("api.in.reqres/jsonvalidation/RegisterError.json"))
//                    .body("error", equalTo(expectedError));
//        });
        assertValidateJSONAllureStep(registerUserResponse, "api.in.reqres/jsonvalidation/RegisterError.json");
    }
}
