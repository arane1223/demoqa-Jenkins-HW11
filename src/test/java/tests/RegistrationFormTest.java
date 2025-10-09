package tests;

import data.RegistrationData;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Tag("demoqa")
@Tag("registration")
@Feature("Заполнение Practice Form")
@Owner("sergeyglukhov")
@DisplayName("Тесты на заполнение Practice Form на DEMOQA")
public class RegistrationFormTest extends TestBase {

    @Test
    @Story("Заполнение всех полей")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "PracticeForm", url = "https://demoqa.com/automation-practice-form")
    @DisplayName("При заполнении всех полей Practice Form на DEMOQA выйдет popup со значениями всех заполненных полей")
    void fullFillFormTest() {
        RegistrationData data = new RegistrationData();

        step("Открываем страницу и удаляем рекламу", () ->
                registrationPage.openPage().deleteAdds());

        step("Заполняем все поля формы случайными значениями и жмем на кнопу Submit", () ->
                registrationPage
                        .setFirstName(data.firstName)
                        .setLastName(data.lastName)
                        .setEmail(data.userEmail)
                        .setGender(data.gender)
                        .setUserNumber(data.userNumber)
                        .setDayOfBirthday(data.birthDay, data.birthMonth, data.birthYear)
                        .setRandomSubjects(data.subjects)
                        .setHobbies(data.hobbies)
                        .setPicture(data.picture)
                        .setAddress(data.address)
                        .setStateAndCity(data.state, data.city)
                        .clickOnSubmit());

        step("Проверяем, что появился popup с введенными значениями во всех полях", () ->
                registrationResults
                        .checkFormVisible("Thanks for submitting the form")
                        .checkFormResults("Student Name", data.firstName + " " + data.lastName)
                        .checkFormResults("Student Email", data.userEmail)
                        .checkFormResults("Gender", data.gender)
                        .checkFormResults("Mobile", data.userNumber)
                        .checkDateOfBirth(data.birthDay, data.birthMonth, data.birthYear)
                        .checkFormResults("Subjects", data.subjects)
                        .checkFormResults("Hobbies", data.hobbies)
                        .checkFormResults("Picture", data.picture)
                        .checkFormResults("Address", data.address)
                        .checkFormResults("State and City", data.state + " " + data.city));
    }

    @Test
    @Story("Заполнение обязательных полей")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "PracticeForm", url = "https://demoqa.com/automation-practice-form")
    @DisplayName("При заполнении обязательных полей Practice Form на DEMOQA выйдет popup заполненными обязательными полями")
    void minimalFillFormTest() {
        RegistrationData data = new RegistrationData();

        step("Открываем страницу и удаляем рекламу", () ->
                registrationPage.openPage().deleteAdds());

        step("Заполняем обязательные поля и жмем на кнопу Submit", () ->
                registrationPage
                        .setFirstName(data.firstName)
                        .setLastName(data.lastName)
                        .setGender(data.gender)
                        .setUserNumber(data.userNumber)
                        .clickOnSubmit());

        step("Проверяем, что появился popup с введенными значениями во всех полях", () ->
                registrationResults
                        .checkFormVisible("Thanks for submitting the form")
                        .checkFormResults("Student Name", data.firstName + " " + data.lastName)
                        .checkFormResults("Gender", data.gender)
                        .checkFormResults("Mobile", data.userNumber));
    }

    @Test
    @Story("Отправка пустой формы")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "PracticeForm", url = "https://demoqa.com/automation-practice-form")
    @DisplayName("При нажатии на кнопку Submit с пустыми полями, popup не появится")
    void negativeFillFormTest() {

        step("Открываем страницу и удаляем рекламу", () ->
                registrationPage.openPage().deleteAdds());

        step("Жмем на кнопу Submit", () ->
                registrationPage.clickOnSubmit());

        step("Проверяем, что popup не появился", () ->
                registrationResults.checkFormUnvisible());

    }
}