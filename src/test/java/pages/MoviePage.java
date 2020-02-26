package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import models.MovieModel;
import org.openqa.selenium.Keys;

import java.io.File;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class MoviePage {

    public MoviePage add() {
        $(".movie-add").click();
        return this;
    }

    public MoviePage search (String value) {
        $("input[placeholder^=Pesquisar]").setValue(value);
        $("#search-movie").click();
        return this;
    }

    public MoviePage create(MovieModel movie) {
        $("input[name=title]").setValue(movie.title);
        this.selectStatus(movie.status);
        $("input[name=year]").setValue(movie.year.toString());
        $("input[name=release_date]").setValue(movie.releaseDate);
        this.inputCast(movie.cast);
        $("textarea[name=overview]").setValue(movie.plot);
        this.upload(movie.cover);
        $("#create-movie").click();
        return this;
    }

    private void selectStatus(String status) {
        $("input[placeholder=Status]").click();
        $$(".el-scrollbar__view .el-select-dropdown__item span").findBy(text(status)).click();
    }

    private void inputCast(List<String> cast) {
        SelenideElement elementCast = $(".cast");

        for (String actor : cast) {
            elementCast.setValue(actor);
            elementCast.sendKeys(Keys.TAB);
        }
    }

    private void upload(File cover) {
        String jsScript = "document.getElementById('upcover').classList.remove('el-upload__input');";
        executeJavaScript(jsScript);
        $("#upcover").uploadFile(cover);
    }

    public ElementsCollection items() {
        return $$("table tbody tr");
    }
}
