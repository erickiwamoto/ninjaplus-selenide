package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class LoginPage {

    public LoginPage open() {
        Selenide.open("/login");
        return this;
    }

    public LoginPage with(String email, String pwd) {
        $("#emailId").setValue(email);
        $("#passId").setValue(pwd);
        $("#login").click();
        return this;
    }

    public SelenideElement alert() {
        return $(".alert span");
    }

    public void clearSession() {
        executeJavaScript("localStorage.clear();");
    }
}
