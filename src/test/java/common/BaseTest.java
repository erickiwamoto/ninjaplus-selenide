package common;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import pages.MoviePage;
import pages.SideBar;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import static com.codeborne.selenide.Selenide.screenshot;

public class BaseTest {
    protected static LoginPage login;
    protected static SideBar sideBar;
    protected static MoviePage movie;

    @BeforeMethod
    public void start() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "http://ninjaplus-web:5000";
        Configuration.timeout = 10000;

        login = new LoginPage();
        sideBar = new SideBar();
        movie = new MoviePage();
    }

    @AfterMethod
    public void finish() {
        String tempShot = screenshot("temp_shot");

        try {
            BufferedImage bimage = ImageIO.read(new File(tempShot));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            ImageIO.write(bimage, "png", baos);
            byte[] screenshot = baos.toByteArray();

            io.qameta.allure.Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshot));

        } catch (Exception ex) {
            System.out.println("Screenshot error: " + ex.getMessage());
        }
    }
}
