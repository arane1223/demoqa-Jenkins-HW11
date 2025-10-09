package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static io.qameta.allure.Allure.step;
import static data.RegistrationData.*;

@Tag("demoqa")
@Tag("box")
@Feature("Заполнение Text Box")
@Owner("sergeyglukhov")
@DisplayName("Тест на заполнение Text Box на DEMOQA")
public class TextBoxTests extends TestBase {

    @Test
    @Story("Заполнение всех полей с помощью библиотеки Faker")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "TextBox", url = "https://demoqa.com/text-box")
    @DisplayName("Тест на заполнении Text Box формы на DEMOQA с помощью Faker")
    void fillFormTest() {

        step("Открываем страницу и удаляем рекламу", () ->
                textBox.openPage().deleteAdds());

        step("Заполняем форму и жмем на кнопку Submit", () ->
                textBox
                        .setUserName(firstName)
                        .setUserEmail(userEmail)
                        .setAllAddresses(address, secondAddress)
                        .clickOnSubmit());

        step("Проверяем, что вывелись такие же данные, которые были введены", () ->
                textBoxResults.checkResults(firstName, userEmail, address, secondAddress));
    }

    static Stream<Arguments> fillingFormWithMethodSourceParametrizeTest() {
        return Stream.of(
                Arguments.of(
                        "Alex",
                        "alex@egorov.com",
                        List.of("Some street 1", "Another street 1")),
                Arguments.of(
                        "Bob",
                        "Bob@gmail.com",
                        List.of("London", "Baker street 231"))
        );
    }

    @Story("Заполнение всех полей с помощью @MethodSource")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "TextBox", url = "https://demoqa.com/text-box")
    @MethodSource
    @ParameterizedTest(name = "Заполнение формы с именем {0}, почтой {1}, адресами {2}")
    @DisplayName("Тест на заполнение Text Box формы с помощью @MethodSource")
    void fillingFormWithMethodSourceParametrizeTest(String userName, String userEmail,
                                                    List<String> addresses) {
        step("Открываем страницу и удаляем рекламу", () ->
                textBox.openPage().deleteAdds());

        step("Вводим данные с именем, почтой, адресами, и жмем на Submit", () ->
                textBox
                        .setUserName(userName)
                        .setUserEmail(userEmail)
                        .setAllAddresses(addresses)
                        .clickOnSubmit());

        step("Проверяем, что появилось поле с такими же: именем, почтой, адресами", () ->
                textBoxResults.checkResults(userName, userEmail, addresses));
    }
}