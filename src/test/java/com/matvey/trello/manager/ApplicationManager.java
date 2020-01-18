package com.matvey.trello.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    SessionHelper session;
    BoardHelper board;
    TeamHelper team;
    WebDriver wd;

    public void init() {
        String browser = System.getProperty("browser", BrowserType.CHROME);
        if (browser.equals(BrowserType.CHROME)) {

            wd = new ChromeDriver();
        } else if (browser.equals(BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
        }
//            else
//            if(browser.equals(BrowserType.EDGE)){
//                wd = new EdgeDriver();
//            }
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        openSite("https://trello.com/");


        session = new SessionHelper(wd);
        board = new BoardHelper(wd);
        team = new TeamHelper(wd);
    }

    public void openSite(String url) {
        wd.get(url);
    }

    public void stop() {
        wd.quit();
    }

    public SessionHelper getSession() {
        return session;
    }

    public BoardHelper getBoard() {
        return board;
    }

    public TeamHelper getTeam() {
        return team;
    }

    //    public void pause(int millis) throws InterruptedException {
//        Thread.sleep(millis);
//    }//the copy is in helperbase
//
//    public boolean isElementPresent(By locator) {
//        return wd.findElements(locator).size() > 0;
//    }//the copy is in helperbase
//
//    public void click(By selector) {
//        wd.findElement(selector).click();
//    }//the copy is in helperbase
//
//    public void type(By selector, String text) {
//        wd.findElement(selector).click();
//        wd.findElement(selector).clear();
//        wd.findElement(selector).sendKeys(text);
//    }//the copy is in helperbase
//
//    public void returnToHomePage() {
//        click(By.name("house"));
//        click(By.name("house"));
//    }//the copy is in helperbase
//
//    public void clickSubmit() {
//        click(By.xpath("//*[@type='submit']"));
//    } // the copy is in boardHelper + helperbase
//
//
//
////where to put the code below?:
//
//    public boolean moreButtonPresent() {
//        return isElementPresent
//                (By.cssSelector(".board-menu-navigation-item-link.js-open-more"));
//    } //the copy is in helperbase
//
//    public void moreButton() {
//        click(By.cssSelector(".board-menu-navigation-item-link.js-open-more"));
//    } //the copy is in helperbase
//
//    public void goBack() {
//        click(By.cssSelector("[title='Go back.']"));
//    } //the copy is in helperbase
//
//    public void clickMenuButton() {
//        click(By.cssSelector(".js-show-sidebar"));
//    } //the copy is in helperbase
//
//    public boolean menuButtonPresent() {
//        return isElementPresent(
//                (By.cssSelector(".js-show-sidebar")));
//    } //the copy is in helperbase



}
