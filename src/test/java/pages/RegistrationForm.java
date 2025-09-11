package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationForm {
    private SelenideElement
            userForm = $("#userForm"),
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            celendarInput = $("#dateOfBirthInput"),
            subjectInput = $("#subjectsInput"),
            hobbiesWrapper = $("#hobbiesWrapper"),
            uploadPicture = $("#uploadPicture"),
            adressInput = $("#currentAddress"),
            stateInput = $("#react-select-3-input"),
            cityInput = $("#react-select-4-input"),
            submitInput = $("#submit");

    public RegistrationForm openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        return this;
    }

    public RegistrationForm deleteAdds(){
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public RegistrationForm setFirstName(String value){
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationForm setLastName(String value){
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationForm setEmail(String email){
        userEmailInput.setValue(email);
        return this;
    }

    public RegistrationForm setGender(String gender){
        genderWrapper.$(byText(gender)).click();
        return this;
    }

    public RegistrationForm setUserNumber(String number){
        userNumberInput.setValue(number);
        return this;
    }

    public RegistrationForm setDayOfBirthday(String day, String month, String year){
        celendarInput.click();
        CalendarComponent celendarComponent = new CalendarComponent();
        celendarComponent.setDate(day, month, year);
        return this;
    }

    public RegistrationForm setSubjects(String subject){
        subjectInput.setValue(subject).pressEnter();
        return this;
    }

    // сеттеры для поля Subjects в уроке 7
    public RegistrationForm setRandomSubjects(String value) {
        if (value == null || value.isBlank()) return this;

        for (String raw : value.split(",")) {
            String label = raw.trim();
            if (label.isEmpty()) continue;

            userForm.scrollTo();
            subjectInput.setValue(label);
            subjectInput.pressEnter();
        }
        return this;
    }

    public RegistrationForm setHobbies(String hobbiesOne, String hobbiesTwo, String hobbiesThree){
        hobbiesWrapper.$(byText(hobbiesOne)).click();
        hobbiesWrapper.$(byText(hobbiesTwo)).click();
        hobbiesWrapper.$(byText(hobbiesThree)).click();
        return this;
    }

    public RegistrationForm setRandomHobbies(String hobbies){
        hobbiesWrapper.$(byText(hobbies)).click();
        return this;
    }

    // сеттеры для поля hobbies в уроке 7
    public RegistrationForm setHobbies(String value) {
        String[] hobbies = value.split(",");
        for (String hobby : hobbies) {
            hobbiesWrapper.$(byText(hobby.trim())).click();
        }
        return this;
    }

    public RegistrationForm setPicture(String fileName){
        uploadPicture.uploadFromClasspath(fileName);
        return this;
    }

    public RegistrationForm setAddress(String adress){
        adressInput.setValue(adress);
        return this;
    }

    public RegistrationForm setStateAndCity (String state, String city){

        stateInput.setValue(state).pressEnter();
        cityInput.setValue(city).pressEnter();
        return this;
    }

    public RegistrationForm clickOnSubmit(){
        submitInput.click();
        return this;
    }
}
