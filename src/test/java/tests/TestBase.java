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
import static utils.RandomUtils.getRandomAddress;
import static utils.RandomUtils.getRandomBirthDay;
import static utils.RandomUtils.getRandomBirthMonth;
import static utils.RandomUtils.getRandomBirthYear;
import static utils.RandomUtils.getRandomCity;
import static utils.RandomUtils.getRandomGender;
import static utils.RandomUtils.getRandomHobbies;
import static utils.RandomUtils.getRandomNumber;
import static utils.RandomUtils.getRandomPicture;
import static utils.RandomUtils.getRandomState;
import static utils.RandomUtils.getSubjects;

public class TestBase {

    RegistrationForm registrationPage = new RegistrationForm();
    RegistrationResultsComponent registrationResults = new RegistrationResultsComponent();
    TextBox textBox = new TextBox();
    BoxResultsComponent textBoxResults = new BoxResultsComponent();
    LoginPage loginPage = new LoginPage();
    ProfilePage profilePage = new ProfilePage();

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
