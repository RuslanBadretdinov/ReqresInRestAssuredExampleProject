package in.reqres.smoke;

import in.reqres.contents.pages.MainPage;
import in.reqres.extensions.UIExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@Tag("@smokes")
@ExtendWith(UIExtension.class)
@DisplayName("Смок тест открытия главной страницы")
public class SmokeTest {

    private MainPage mainPage;

    @Test
    @Tag("@smoke")
    @DisplayName("Открытие страницы reqres.in")
    public void openMainPage() {
        mainPage.open()
                .isLoaded();
    }
}
