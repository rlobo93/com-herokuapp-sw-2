package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String BaseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setup(){
        openBrowser(BaseUrl);
    }

    @Test
    public void userSholdLoginSuccessfullyWithValidCredentials(){

        //locate and Enter “SuperSecretPassword!” password
        WebElement usernameField =driver.findElement(By.id("username"));
        usernameField.sendKeys("tomsmith");

        //locate and Enter “SuperSecretPassword!” password
        WebElement passwordField =driver.findElement(By.id("password"));
        passwordField.sendKeys("SuperSecretPassword!”");

        //Click on ‘LOGIN’ button
        WebElement loginBtn = driver.findElement(By.xpath("//i[contains(text(),'Login')]"));
        loginBtn.click();

        // Verify the text “Secure Area”
        String expectedMessage = "Secure Area";
        WebElement actualMessageElement = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]/h2[1]"));
        String actualMessage = actualMessageElement.getText();
        Assert.assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void verifyTheUsernameErrorMessage(){
        //locate and Enter “tomsmith1” username
        WebElement usernameField =driver.findElement(By.id("username"));
        usernameField.sendKeys("tomsmith1");

        //locate and Enter “SuperSecretPassword!” password
        WebElement passwordField =driver.findElement(By.id("password"));
        passwordField.sendKeys("SuperSecretPassword!");

        //Click on ‘LOGIN’ button
        WebElement loginBtn = driver.findElement(By.xpath("//i[contains(text(),'Login')]"));
        loginBtn.click();

        // Verify the error message “Your username is invalid!”
        String expectedMessage = "Your username is invalid!";
        WebElement actualMessageElement=driver.findElement(By.id("flash"));
        String actualMessage = actualMessageElement.getText();
        Assert.assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void verifyThePasswordErrorMessage(){

        //locate and Enter “tomsmith” username
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("tomsmith");

        //locate and Enter “SuperSecretPassword” password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("SuperSecretPassword");

        //Click on ‘LOGIN’ button
        WebElement loginBtn = driver.findElement(By.xpath("//i[contains(text(),'Login')]"));
        loginBtn.click();
        String expectedMessage = "Your password is invalid!";
        WebElement actualMessageElement=driver.findElement(By.id("flash"));
        String actualMessage = actualMessageElement.getText();
        Assert.assertEquals(expectedMessage,actualMessage);

    }


    @After
    public void teardown(){
        closeBrowser();
    }


}
