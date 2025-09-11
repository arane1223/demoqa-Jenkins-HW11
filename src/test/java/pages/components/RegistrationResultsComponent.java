package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationResultsComponent {
    private SelenideElement
            resultTitle = $("#example-modal-sizes-title-lg"),
            tableResponsive = $(".table-responsive");

    public RegistrationResultsComponent checkFormVisible(String title){
        resultTitle.shouldBe(visible).shouldHave(text(title));
        tableResponsive.scrollTo();
        return this;
    }

    public RegistrationResultsComponent checkFormResults (String key, String resultValue){
        tableResponsive.$(byText(key)).parent()
                .shouldHave(text(resultValue));
        return this;
    }

    public RegistrationResultsComponent checkFormUnvisible (){
        resultTitle.shouldNotBe(visible);
        return this;
    }

    public RegistrationResultsComponent checkDateOfBirth (String day, String month, String year){
        tableResponsive.$(byText("Date of Birth")).parent()
                .shouldHave(text(day+" "+month+","+year));
        return this;
    }
}