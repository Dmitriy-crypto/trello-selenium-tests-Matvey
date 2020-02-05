package com.matvey.trello.manager;

import com.google.common.io.Files;
import com.matvey.trello.utils.Listener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    SessionHelper session;
    BoardHelper board;
    TeamHelper team;
    WebDriver wd;

    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);


    public void init() {
        String browser = System.getProperty("browser", BrowserType.FIREFOX);
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

    public void takeScreenshot(){
       File tmp =  ((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
       File screenShot = new File("src/test/screenshots/screen"
               +System.currentTimeMillis()+".png");
       try {
           Files.copy(tmp,screenShot);
       }catch (IOException e){
           e.printStackTrace();
       }
logger.info("\n\nCreated screenshot: "+screenShot.getAbsolutePath());



    }



}


