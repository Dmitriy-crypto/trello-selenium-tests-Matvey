package com.matvey.trello;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
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

    public void openSite(String url) {wd.get(url);
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

    public boolean isElementPresent(By locator){ return wd.findElements(locator).size()>0;
    }

    @AfterClass

    public void tearDown() {wd.quit();
    }

    public void pause(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }

    public void ifClause(String password) {
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

    public void click(By selector) {
        wd.findElement(selector).click();
    }


    public boolean isAvatarPresent() {
        return isElementPresent
                (By.cssSelector("[data-test-id='header-member-menu-button']"));
    }

    public void clickLogOut() {
        click(By.cssSelector("[data-test-id='header-member-menu-logout']"));
    }

    public void clickOnAvatar() {
        click(By.cssSelector("[data-test-id='header-member-menu-button']"));
    }

    public void trelloLoginButton()  {
        click(By.cssSelector("[href='/login']"));

    }

    public void fillLoginForm(String login, String password) throws InterruptedException {
        typeLogin(login);
        pause(4000);
        ifClause(password);
        pause(4000);
        Assert.assertTrue(isAvatarPresent());
    }


    public void type(By selector, String text) {
        wd.findElement(selector).click();
        wd.findElement(selector).clear();
        wd.findElement(selector).sendKeys(text);
    }

    public void clickCreateNewBoard() {
        click(By.xpath("//div[@class='board-tile mod-add']"));
    }

    public void choosePrivateBoard() {
        click(By.xpath("//button[@class='subtle-chooser-trigger unstyled-button vis-chooser-trigger']"));
        click(By.cssSelector("//div[@class='pop-over mod-no-header is-shown']//li[1]"));
    }

    public void choosePublicBoard() {
        click(By.xpath("//button[@class='subtle-chooser-trigger unstyled-button vis-chooser-trigger']"));
        click(By.xpath("//div[@class='pop-over mod-no-header is-shown']//li[2]"));
        click(By.xpath("//input[@type='submit']"));
    }

    public void fillBoardName(String text) {
        click(By.xpath("//input[@placeholder='Add board title']"));
        type(By.xpath("//input[@placeholder='Add board title']"), text);
    }

    public void submitCreateBoard() {
        click(By.cssSelector("[data-test-id='create-board-submit-button']"));    }

    public void logout() {
        clickOnAvatar();
        clickLogOut();
    }

    public void chooseAddPublicBoard() {
        click(By.cssSelector("._1Lkx3EjS3wCrs7"));
        click(By.xpath("//div[@id='layer-manager-popover']//li[2]//button[1]"));
        click(By.cssSelector("._3UeOvlU6B5KUnS._2MgouXHqRQDP_5._3ZPeWh5QQj47DA"));

    }

    public void addNewBoard() {
        click(By.xpath("//span[@name='add']"));
        click(By.xpath("//span[@name='board']/..//p"));
    }
}
