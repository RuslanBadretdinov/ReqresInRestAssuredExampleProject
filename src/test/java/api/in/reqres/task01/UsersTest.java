package api.in.reqres.task01;

import api.in.reqres.dto.user.crud.UserFormReqDTO;
import api.in.reqres.dto.user.crud.UserFormRespDTO;
import api.in.reqres.services.UsersServiceApi;
import com.github.javafaker.Faker;
import in.reqres.extensions.UIExtension;
import io.qameta.allure.Allure;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static api.in.reqres.utils.AllureUtils.addAttachment;
import static api.in.reqres.utils.AllureUtils.assertValidateJSONAllureStep;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Tag("@task01")
@ExtendWith(UIExtension.class)
@DisplayName("Group - 'task01'")
public class UsersTest {

    private Faker faker = new Faker();
    private UsersServiceApi usersServiceApi = new UsersServiceApi();

    @Test
    @Tag("@users")
    @DisplayName("Регистрация аккаунта с неправильными данными")
    public void createNewUser() {
        UserFormReqDTO userFormReqDTO = UserFormReqDTO.builder()
                .name(faker.name().firstName())
                .job(faker.job().position())
                .build();

        addAttachment("DTO для 'POST:/users'", userFormReqDTO.toString());

        ValidatableResponse createUserResp = usersServiceApi.createUser(userFormReqDTO);

        Allure.step("статус запроса 'POST:/users' == 201", () -> {
            createUserResp.statusCode(addAttachment(201));
        });

        assertValidateJSONAllureStep(createUserResp, "api.in.reqres/jsonvalidation/UserFormResp.json");

        Allure.step("Сравнение полей - body 'POST:/register'", () -> {
            assertAll(
                    "Group assertions - soft assertions",
                    () -> createUserResp.body("name", equalTo(userFormReqDTO.getName())),
                    () -> createUserResp.body("job", equalTo(userFormReqDTO.getJob())),
                    () -> assertNotNull(createUserResp.extract().body().as(UserFormRespDTO.class).getId()),
                    () -> assertNotNull(createUserResp.extract().body().as(UserFormRespDTO.class).getCreatedAt())
            );
        });
    }
}
