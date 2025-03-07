import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class UserData {
    WebDriver driver;
    public UserData(WebDriver driver) {
        this.driver = driver;
    }
    By Name = By.cssSelector("#name-input");
    By Password = By.xpath("//input[@type='password']");
    By Drinks = By.xpath("//input[@type='checkbox']");
    By Color = By.xpath("//*[@type=\"radio\"]");
    By LikeAutomation = By.id("automation");
    By AutoTools = By.xpath("//*[@id=\"feedbackForm\"]/ul/li");
    By Email = By.id("email");
    By Message = By.cssSelector("#message");
    By Button = By.id("submit-btn");

    public void inputName(String name){
        new Actions(driver)
                .moveToElement(driver.findElement(Name))
                .click()
                .sendKeys(name)
                .perform();
    }

    public void inputPassword(String password){
        new Actions(driver)
                .moveToElement(driver.findElement(Password))
                .click().sendKeys(password)
                .perform();
    }

    public enum DrinksList {
        Water(0),
        Milk(1),
        Coffee(2),
        Wine(3),
        CtrlAltDelight(4);
        private int drinks;
        DrinksList(int drinks){
            this.drinks = drinks;
        }
        public int GetNum() {
            return drinks;
        }
    }

    public enum Colors {
        Yellow (2);
        private int color;
        Colors(int color) {
            this.color = color;
        }
        public int getNum() {
            return color;
        }
    }

    public void setDrinks(DrinksList... drinks) {
        List<WebElement> boxes = driver.findElements(Drinks);
        for (DrinksList drink : drinks) {
            new Actions(driver)
                    .moveToElement(boxes.get(drink.GetNum()))
                    .click()
                    .perform();
        }
    }

    public void setColor(Colors c){
        new Actions(driver)
                .moveToElement(driver.findElements(Color).get(c.getNum()))
                .click()
                .perform();
    }

    public void likeAutomation(int num){
        new Actions(driver)
                .moveToElement(driver.findElement(LikeAutomation))
                .perform();
        (new Select(driver.findElement(LikeAutomation))).selectByIndex(num);
    }

    public void inputEmail(String s){
        new Actions(driver)
                .moveToElement(driver.findElement(Email))
                .click()
                .sendKeys(s)
                .perform();
    }

    public void inputMessage(String s){
        new Actions(driver)
                .moveToElement(driver.findElement(Message))
                .click()
                .sendKeys(s)
                .perform();
    }

    public String[] getAutoTools(){
        List<WebElement> tools = driver.findElements(AutoTools);
        String[] string_tools = new String[tools.size()];
        for(int i = 0; i < string_tools.length; i++){
            string_tools[i] = tools.get(i).getText();
        }
        return string_tools;
    }

    public String getLongestAuto(){
        String[] autos = getAutoTools();
        String longest = autos[0];
        for(int i = 1; i<autos.length; i++){
            if(autos[i].length()>longest.length())
                longest = autos[i];
        }
        return longest;
    }

    public void buttonClick(){
        new Actions(driver)
                .moveToElement(driver.findElement(Button))
                .click()
                .perform();
    }

   public Alert getAlert(){
        try{
            return driver.switchTo().alert();
        } catch (NoAlertPresentException e){
            return null;
        }
    }
}
