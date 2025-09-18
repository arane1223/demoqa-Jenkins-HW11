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

@Tag("demoqa")
@DisplayName("Тест на заполнение Text Box на DEMOQA")
public class TextBoxTests extends TestBase {

    @Test
    @Feature("Заполнение Text Box")
    @Story("Заполнение всех полей с помощью библиотеки Faker")
    @Owner("sergeyglukhov")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "TextBox", url = "https://demoqa.com/text-box")
    @DisplayName("Тест на заполнении Text Box формы на DEMOQA с помощью Faker")
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

    static Stream<Arguments> fillingFormWithMethodSourceParametrizeTest(){
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

    @Feature("Заполнение Text Box")
    @Story("Заполнение всех полей с помощью @MethodSource")
    @Owner("sergeyglukhov")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "TextBox", url = "https://demoqa.com/text-box")
    @DisplayName("Тест на заполнение Text Box формы с помощью @MethodSource")
    @MethodSource
    @ParameterizedTest(name = "Заполнение формы с именем {0}, почтой {1}, адресами {2}")
    void fillingFormWithMethodSourceParametrizeTest(String userName, String userEmail,
                                                    List<String> addresses) {
        step("Открываем страницу и удаляем рекламу", () -> {
            textBox
                    .openPage()
                    .deleteAdds();
        });

        step("Вводим данные с именем, почтой, адресами, и жмем на Submit", () -> {
            textBox
                    .setUserName(userName)
                    .setUserEmail(userEmail)
                    .setAllAddresses(addresses)
                    .clickOnSubmit();
        });

        step("Проверяем, что появилось поле с такими же: именем, почтой, адресами", () -> {
            textBoxResults
                    .checkResults(userName, userEmail,
                            addresses);
        });
    }
}