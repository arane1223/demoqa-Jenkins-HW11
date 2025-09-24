package tests;

import data.Users;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import static io.qameta.allure.Allure.step;

@Tag("demoqa")
@Tag("login")
@Owner("sergeyglukhov")
@DisplayName("Тесты на успешную авторизацию на DEMOQA")
public class LoginTests extends TestBase {

    @DisplayName("Тесты на авторизацию с использованием @CsvSource")
    @CsvSource(value = {
            "AlexTerrible, Qwer!1234",
            "arane1223, Arane@1223"})
    @ParameterizedTest(name = "Залогиниться на DEMOQA по логину {0} и паролю {1}")
    @Feature("Вход по логину и паролю")
    @Story("Вход с использованием @CsvSource")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Login", url = "https://demoqa.com/login")
    void successfulLoginOnDemoqaWithCsvSourceTest(String userName, String password) {

        step("Открываем страницу и удаляем рекламу", () -> {
            loginPage
                    .openPage()
                    .deleteAdd();
        });

        step("Заходим по логину и паролю ", () -> {
            loginPage
                    .setUserNameAndPassword(userName, password);
        });

        step("Проверяем, что в верхнем углу логин соответствует", () -> {
            profilePage
                    .chekTableVisible()
                    .checkUsernameValue(userName);
        });
    }

    @DisplayName("Тесты на авторизацию с использованием @CsvFileSource")
    @CsvFileSource(resources = "/LoginAndPassword.csv")
    @ParameterizedTest(name = "Залогиниться на DEMOQA по логину {0} и паролю {1}")
    @Feature("Вход по логину и паролю")
    @Story("Вход с использованием @CsvFileSource")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Login", url = "https://demoqa.com/login")
    void successfulLoginOnDemoqaWithCsvFileSourceTest(String userName, String password) {

        step("Открываем страницу и удаляем рекламу", () -> {
            loginPage
                    .openPage()
                    .deleteAdd();
        });

        step("Заходим по логину и паролю", () -> {
            loginPage
                    .setUserNameAndPassword(userName, password);
        });

        step("Проверяем, что в верхнем углу логин соответствует", () -> {
            profilePage
                    .chekTableVisible()
                    .checkUsernameValue(userName);
        });
    }

    @DisplayName("Тесты на авторизацию с использованием @EnumSource")
    @EnumSource(Users.class)
    @ParameterizedTest(name = "Залогиниться на DEMOQA с данными юзера {0}")
    @Feature("Вход по логину и паролю")
    @Story("Вход с использованием @EnumSource")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Login", url = "https://demoqa.com/login")
    void successfulLoginOnDemoqaWithEnumTest(Users users) {

        step("Открываем страницу и удаляем рекламу", () -> {
            loginPage
                    .openPage()
                    .deleteAdd();
        });

        step("Заходим по логину и паролю", () -> {
            loginPage
                    .setUserNameAndPassword(users.userName, users.password);
        });

        step("Проверяем, что в верхнем углу логин соответствует", () -> {
            profilePage
                    .chekTableVisible()
                    .checkUsernameValue(users.userName);
        });
    }
}