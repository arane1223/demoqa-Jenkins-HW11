package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RegistrationForm;
import pages.components.RegistrationResultsComponent;

import java.util.Map;

import static utils.RandomUtils.*;
import static utils.RandomUtils.getAddress;
import static utils.RandomUtils.getBirthDay;
import static utils.RandomUtils.getBirthMonth;
import static utils.RandomUtils.getBirthYear;
import static utils.RandomUtils.getCity;
import static utils.RandomUtils.getGender;
import static utils.RandomUtils.getHobbies;
import static utils.RandomUtils.getNumber;
import static utils.RandomUtils.getPicture;
import static utils.RandomUtils.getState;
import static utils.RandomUtils.getSubjects;

public class TestBase {

    RegistrationForm registrationPage = new RegistrationForm();
    RegistrationResultsComponent registrationResults = new RegistrationResultsComponent();

    String
            firstName = getFirstName(),
            lastName = getLastName(),
            userEmail = getEmail(),
            gender = getGender(),
            userNumber = getNumber(),
            birthDay = getBirthDay(getBirthMonth()),
            birthMonth = getBirthMonth(),
            birthYear = getBirthYear(),
            subjects = getSubjects(),
            hobbies = getHobbies(),
            picture = getPicture(),
            address = getAddress(),
            state = getState(),
            city = getCity(state);

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }
}
