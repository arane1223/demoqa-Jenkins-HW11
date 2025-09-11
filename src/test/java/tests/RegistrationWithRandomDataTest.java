package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Tag("demoqa")
@DisplayName("Тесты на заполнение Practice Form на DEMOQA")
public class RegistrationWithRandomDataTest extends TestBase {

    @Test
    @Feature("Заполнение Practice Form")
    @Story("Заполнение всех полей")
    @Owner("sergeyglukhov")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "PracticeForm", url = "https://demoqa.com/automation-practice-form")
    @DisplayName("При заполнении всех полей Practice Form на DEMOQA выйдет popup со значениями всех заполненных полей")
    void fullFillFormTest() {

        step("Открываем страницу и удаляем рекламу", () -> {
            registrationPage
                    .openPage()
                    .deleteAdds();
        });

        step("Заполняем все поля формы случайными значениями и жмем на кнопу Submit", () -> {
            registrationPage
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setEmail(userEmail)
                    .setGender(gender)
                    .setUserNumber(userNumber)
                    .setDayOfBirthday(birthDay, birthMonth, birthYear)
                    .setRandomSubjects(subjects)
                    .setHobbies(hobbies)
                    .setPicture(picture)
                    .setAddress(address)
                    .setStateAndCity(state, city)
                    .clickOnSubmit();
        });

        step("Проверяем, что появился popup с введенными значениями во всех полях", () -> {
            registrationResults
                    .checkFormVisible("Thanks for submitting the form")
                    .checkFormResults("Student Name", firstName + " " + lastName)
                    .checkFormResults("Student Email", userEmail)
                    .checkFormResults("Gender", gender)
                    .checkFormResults("Mobile", userNumber)
                    .checkDateOfBirth(birthDay, birthMonth, birthYear)
                    .checkFormResults("Subjects", subjects)
                    .checkFormResults("Hobbies", hobbies)
                    .checkFormResults("Picture", picture)
                    .checkFormResults("Address", address)
                    .checkFormResults("State and City", state + " " + city);
        });

    }

    @Test
    @Feature("Заполнение Practice Form")
    @Story("Заполнение обязательных полей")
    @Owner("sergeyglukhov")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "PracticeForm", url = "https://demoqa.com/automation-practice-form")
    @DisplayName("При заполнении обязательных полей Practice Form на DEMOQA выйдет popup заполненными обязательными полями")
    void minimalFillFormTest() {

        step("Открываем страницу и удаляем рекламу", () -> {
            registrationPage
                    .openPage()
                    .deleteAdds();
        });

        step("Заполняем обязательные поля и жмем на кнопу Submit", () -> {
            registrationPage
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setGender(gender)
                    .setUserNumber(userNumber)
                    .clickOnSubmit();
        });

        step("Проверяем, что появился popup с введенными значениями во всех полях", () -> {
            registrationResults //проверки
                    .checkFormVisible("Thanks for submitting the form")
                    .checkFormResults("Student Name", firstName + " " + lastName)
                    .checkFormResults("Gender", gender)
                    .checkFormResults("Mobile", userNumber);
        });
    }

    @Test
    @Feature("Заполнение Practice Form")
    @Story("Отправка пустой формы")
    @Owner("sergeyglukhov")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "PracticeForm", url = "https://demoqa.com/automation-practice-form")
    @DisplayName("При нажатии на кнопку Submit с пустыми полями, popup не появится")
    void negativeFillFormTest() {

        step("Открываем страницу и удаляем рекламу", () -> {
            registrationPage
                    .openPage()
                    .deleteAdds();
        });

        step("Жмем на кнопу Submit", () -> {
            registrationPage
                    .clickOnSubmit();
        });

        step("Проверяем, что popup не появился", () -> {
            registrationResults
                    .checkFormUnvisible();
        });

    }
}