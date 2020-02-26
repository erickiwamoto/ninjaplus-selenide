package tests;

import common.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;

public class LoginTests extends BaseTest {

    @DataProvider(name = "login-alerts")
    public Object[][] loginProvider() {
        return new Object[][]{
                {"erickiwamoto@gmail.com", "abc123", "Usuário e/ou senha inválidos"},
                {"erickiwamoto", "123456", "Usuário e/ou senha inválidos"},
                {"", "123456", "Opps. Cadê o email?"},
                {"erickiwamoto@gmail.com", "", "Opps. Cadê a senha?"}
        };
    }

    @Test
    public void shouldSeeLoggedUser() {

        login
                .open()
                .with("erickiwamoto@gmail.com", "123456");

        sideBar.loggedUser().shouldHave(text("Erick Iwamoto"));
    }

    // DDT (Data Driver Testing)
    @Test(dataProvider = "login-alerts")
    public void shouldSeeLoginAlerts(String email, String pwd, String alertMsg) {
        login
                .open()
                .with(email, pwd)
                .alert().shouldHave(text(alertMsg));
    }

    @AfterMethod
    public void cleanup() {
        login.clearSession();
    }
}
