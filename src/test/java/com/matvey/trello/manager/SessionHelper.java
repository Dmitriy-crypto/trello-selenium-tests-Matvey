package com.matvey.trello.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.util.ArrayList;

public class SessionHelper extends HelperBase{


    public SessionHelper(WebDriver wd) {
        super(wd);
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

    public void ifClause(String password) throws InterruptedException {
        if (wd.findElement(By.id("password")).isDisplayed()) {
            pause(5000);
            typePassword(password);
        }
        wd.findElement(By.id("login")).click();
        if (isElementPresent(By.id("login-submit"))) {
            wd.findElement(By.id("login-submit")).click();
            pause(5000);
            typePassword("7Ig%20K8");
            wd.findElement(By.id("login-submit")).click();
        }
    }


    public boolean isAvatarPresent() {
        return isElementPresent
                (By.cssSelector("[data-test-id='header-member-menu-button']"));
    }

    public void clickLogOut() throws InterruptedException {
        click(By.cssSelector("[data-test-id='header-member-menu-logout']"));
        pause(8000);
    }

    public void clickOnAvatar() {
        click(By.cssSelector("[data-test-id='header-member-menu-button']"));
    }

    public void trelloLoginButton() {
        click(By.cssSelector("[href='/login']"));

    }

    public void fillLoginForm(String login, String password) throws InterruptedException {
        typeLogin(login);
        pause(5000);
        ifClause(password);
        pause(5000);
        // Assert.assertTrue(isAvatarPresent());
    }


    public void logout() throws InterruptedException {
        clickOnAvatar();
        clickLogOut();
    }


    public void openUserProfileFromDropDown() throws InterruptedException {
        click(By.cssSelector("[data-test-id='header-member-menu-profile']"));
        pause(5000);


    }

    public void goToAtlassianAccount() throws InterruptedException {
        click(By.cssSelector("[href $= manage-profile]"));
        String trellow = wd.getWindowHandle();
        ArrayList<String>availableWindows = new ArrayList(wd.getWindowHandles());
        if(availableWindows.isEmpty()){
            wd.switchTo().window(availableWindows.get(1));
            pause(5000);
        }



    }

    public void addAvatarImageAndClose() throws InterruptedException {
        new Actions(wd)
                .moveToElement(wd.findElement(By.cssSelector(".sc-kjoXOD.iWbaSk"))).perform();
        click(By.cssSelector("[data-test-selector='profile-hover-info']"));
        if (isElementPresent(By.cssSelector("[role=menu]"))) {
            click(By.xpath("//*[@role='menu']//span[@role='menuitem'][1]"));
        }
        attach(By.id("image-input"), new File("C:/Users/Matvey/Desktop/for tests avatar trello/boat.png"));
        click(By.xpath("//*[contains(text(),'Upload')]"));
        pause(5000);
        wd.close();
        pause(5000);
        ArrayList<String> availableWindows = new ArrayList(wd.getWindowHandles());
        if (!availableWindows.isEmpty()) {
            wd.switchTo().window(availableWindows.get(0));
            pause(5000);
            wd.navigate().refresh();
            pause(5000);
        }
    }

    }
