package tests;

import common.BaseTest;
import libs.DataBase;
import models.MovieModel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Arrays;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class MoviesTests extends BaseTest {

    private DataBase db;

    @BeforeMethod
    public void setup() {
        login
                .open()
                .with("erickiwamoto@gmail.com", "123456");

        sideBar.loggedUser().shouldHave(text("Erick Iwamoto"));
    }

    @BeforeSuite
    public void dbReset() {
        db = new DataBase();
        db.resetMovies();
    }

    @AfterMethod
    public void cleanup() {
        login.clearSession();
    }

    @Test
    public void shouldRegisterANewMovie() {
        MovieModel movieData = new MovieModel(
                "Jumanji - Próxima fase",
                "Pré-venda",
                2020,
                "16/01/2020",
                Arrays.asList("The Rock", "Jack Black", "Kevin Hart", "Karen Gillan", "Danny DeVito"),
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                "jumanji2.jpg"
        );

        movie
                .add()
                .create(movieData)
                .items().findBy(text(movieData.title)).shouldBe(visible);
    }

    @Test
    public void shouldSearchOneMovie() {
        movie.search("Coringa").items().shouldHaveSize(1);
    }
}
