package com.matvey.trello;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class TestBase {
    WebDriver wd;

    @BeforeClass
    public void setUp() {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        openSite("https://trello.com/");
    }

    public void openSite(String url) {
        wd.get(url);
    }

    public void fillLoginForm(String login, String password) throws InterruptedException {
        typeLogin(login);
        Thread.sleep(5000);
        if(wd.findElement(By.id("password")).isDisplayed()){
            typePassword(password);
        }
        wd.findElement(By.id("login")).click();
        if(isElementPresent(By.id("login-submit"))){
            wd.findElement(By.id("login-submit")).click();
            typePassword("7Ig%20K8");
            wd.findElement(By.id("login-submit")).click();
        }
    }

    public void typePassword(String password) {
        wd.findElement(By.id("password")).click();
        wd.findElement(By.id("password")).clear();
        wd.findElement(By.id("password")).sendKeys(password);
    }

    public void typeLogin(String login) {
        wd.findElement(By.id("user")).click();
        wd.findElement(By.id("user")).clear();
        wd.findElement(By.id("user")).sendKeys(login);
    }

    public void click(String cssSelector) {
        wd.findElement(By.cssSelector(cssSelector)).click();
    }

    public boolean isElementPresent(By locator){ return wd.findElements(locator).size()>0;
    }

    @AfterMethod
    public void tearDown() {wd.quit();
    }
}
