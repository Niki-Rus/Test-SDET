import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class FormTest {
    WebDriver driver;
    UserData user;

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(7, TimeUnit.SECONDS);
        try {
            driver.get("https://practice-automation.com/form-fields/");
        } catch (TimeoutException ignore){}
    }

    @Test
    public void Form(){
        user = new UserData(driver);
        user.inputName("NikiTest");
        user.inputPassword("pass123");
        user.setDrinks(UserData.DrinksList.Milk, UserData.DrinksList.Coffee);
        user.setColor(UserData.Colors.Yellow);
        user.likeAutomation(1);
        user.inputEmail("name@example.com");
        user.inputMessage(String.format("Количество инструментов: %d\nНаибольшее количество символов: %s", user.getAutoTools().length, user.getLongestAuto()));
        user.buttonClick();
        Alert alert = user.getAlert();
        Assertions.assertNotNull(alert);
    }
}
