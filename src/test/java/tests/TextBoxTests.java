package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Tag("demoqa")
@DisplayName("Тест на заполнение Text Box на DEMOQA")
public class TextBoxTests extends TestBase {

    @Test
    @Feature("Заполнение Text Box")
    @Story("Заполнение всех полей")
    @Owner("sergeyglukhov")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "TextBox", url = "https://demoqa.com/text-box")
    @DisplayName("При заполнении всех полей Text Box на DEMOQA отобразятся те же данные под формой после нажатия на Submit")
    void fillFormTest() {

        step("Открываем страницу и удаляем рекламу", () -> {
            textBox
                    .openPage()
                    .deleteAdds();
        });

        step("Заполняем форму и жмем на кнопку Submit", () -> {
            textBox
                    .setUserName(firstName)
                    .setUserEmail(userEmail)
                    .setAllAddresses(address, secondAddress)
                    .clickOnSubmit();
        });

        step("Проверяем, что вывелись такие же данные, которые были введены", () -> {
            textBoxResults
                    .checkResults(firstName, userEmail,
                            address, secondAddress);
        });
    }
}