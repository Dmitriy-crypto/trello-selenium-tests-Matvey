package com.matvey.trello;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    public void clickLogOut() {
        click(By.cssSelector("[data-test-id='header-member-menu-logout']"));
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


    public void logout() {
        clickOnAvatar();
        clickLogOut();
    }



}