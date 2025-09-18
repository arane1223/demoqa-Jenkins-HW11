package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class BoxResultsComponent {
    private SelenideElement
            nameCheck = $("#output #name"),
            emailCheck = $("#output #email"),
            currentAddressCheck = $("#output #currentAddress"),
            permanentAddressCheck = $("#output #permanentAddress");

    public BoxResultsComponent checkResults(String name, String email,
                                            String currentAddress, String permanentAddress) {
        nameCheck.shouldHave(text(name));
        emailCheck.shouldHave(text(email));
        currentAddressCheck.shouldHave(text(currentAddress));
        permanentAddressCheck.shouldHave(text(permanentAddress));
        return this;
    }
}