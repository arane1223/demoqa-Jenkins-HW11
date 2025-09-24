package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.LoginPage;
import pages.ProfilePage;
import pages.RegistrationForm;
import pages.TextBox;
import pages.components.BoxResultsComponent;
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
    TextBox textBox = new TextBox();
    BoxResultsComponent textBoxResults = new BoxResultsComponent();
    LoginPage loginPage = new LoginPage();
    ProfilePage profilePage = new ProfilePage();

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
            secondAddress = getSecondAddress(),
            state = getState(),
            city = getCity(state);

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = System.getProperty("browserSize");
        Configuration.browser = System.getProperty("browser");
        Configuration.browserVersion = System.getProperty("browserVersion");
        Configuration.baseUrl = System.getProperty("baseUrl");
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = System.getProperty("remoteUrl");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void addAllureListener() {
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
