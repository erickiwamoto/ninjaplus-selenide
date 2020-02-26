package pages;

import com.codeborne.selenide.SelenideElement;

import java.nio.channels.SelectableChannel;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SideBar {

    public SelenideElement loggedUser() {
        return  $(".user .info span");
    }
}
