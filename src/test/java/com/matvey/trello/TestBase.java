package com.matvey.trello;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class TestBase {
     static WebDriver wd;

    @BeforeSuite
    public void setUp() {
        String browser = System.getProperty("browser",BrowserType.FIREFOX);
        if(browser.equals(BrowserType.CHROME)){

        wd = new ChromeDriver();
        }
        else
            if(browser.equals(BrowserType.FIREFOX)){
            wd = new FirefoxDriver();
        }
            else
            if(browser.equals(BrowserType.EDGE)){
                wd = new EdgeDriver();
            }
        wd.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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

    @AfterSuite

    public void tearDown() {wd.quit();
    }

    public void pause(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }

    public void ifClause(String password) throws InterruptedException {
        if(wd.findElement(By.id("password")).isDisplayed()){
            pause(5000);
            typePassword(password);
        }
        wd.findElement(By.id("login")).click();
        if(isElementPresent(By.id("login-submit"))){
            wd.findElement(By.id("login-submit")).click();
            pause(5000);
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
        pause(5000);
        ifClause(password);
        pause(5000);
       // Assert.assertTrue(isAvatarPresent());
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

    public void fillBoardName(String boardName) {
        click(By.xpath("//input[@placeholder='Add board title']"));
        type(By.xpath("//input[@placeholder='Add board title']"), boardName);
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

    public void returnToHomePage() {
        click(By.name("house"));
        click(By.name("house"));
    }

    public int getBoardsCount(){
            return wd.findElements(By.xpath("//*[@class='icon-lg icon-member']/../../..//li")).size();
    }

    public boolean moreButtonPresent() {
        return isElementPresent
                (By.cssSelector(".board-menu-navigation-item-link.js-open-more"));
    }

    public void permanentlyDeleteBoard() throws InterruptedException {
        click(By.cssSelector(".board-menu-navigation-item-link.js-close-board"));
        click(By.cssSelector("[value='Close']"));
        pause(3000);
        click(By.cssSelector(".quiet.js-delete"));
        click(By.cssSelector("[value='Delete']"));
    }

    public void moreButton() {
        click(By.cssSelector(".board-menu-navigation-item-link.js-open-more"));
    }

    public void goBack() {
        click(By.cssSelector("[title='Go back.']"));
    }

    public void clickLastBoard() {
        wd.findElements(By.xpath("//*[@class='icon-lg icon-member']/../../..//li")).
                get(wd.findElements(By.xpath("//*[@class='icon-lg icon-member']/../../..//li")).size() - 2).click();
    }

    public void deleteLastBoard() throws InterruptedException {
        int before = getBoardsCount();
        if (before <= 1) {
            System.out.println("No boards found");
        } else {
            clickLastBoard();
//            if(!menuButtonPresent()){
//                moreButton();                  clickMenuButton();     add another if
//            }else
            if (!moreButtonPresent()) {
                goBack();
                moreButton();
            } else
                moreButton();
            permanentlyDeleteBoard();
            pause(3000);
            returnToHomePage();
            int actualRes = getBoardsCount();
            int expectedRes = before - 1;
            Assert.assertEquals(actualRes, expectedRes);
        }
    }

    public void clickMenuButton() {
        click(By.cssSelector(".js-show-sidebar"));
    }

    public boolean menuButtonPresent() {
        return isElementPresent(
   (By.cssSelector(".js-show-sidebar")));
    }

    public void createBoardFromMainPage() throws InterruptedException {
        int before = getBoardsCount();
        clickCreateNewBoard();
        fillBoardName("Board from main page"+System.currentTimeMillis());
        choosePublicBoard();
        submitCreateBoard();
        pause(5000);
        returnToHomePage();
        int actualRes = getBoardsCount();
        int expectedRes = before+1;
        Assert.assertEquals(actualRes,expectedRes);
    }
}

