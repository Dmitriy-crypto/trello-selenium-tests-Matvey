package com.matvey.trello;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static com.matvey.trello.TestBase.app;

public class TeamHelper extends HelperBase{

    public TeamHelper(WebDriver wd) {
        super(wd);
    }

    public void createTeamFromMainPage() throws InterruptedException {
        int before = getTeamsCount();
        clickCreateTeam();
        fillTeamName();
        fillTeamDescription();
        pause(2000);
        app.clickSubmit();
        app.pause(2000);
        laterButton();
        app.pause(2000);
        app.returnToHomePage();
        int after = getTeamsCount();
        Assert.assertEquals(after, before + 1);

    }

    public int getTeamsCount() {
        return app.wd.findElements(By.xpath("//*[@class='_33CvMKqfH4Yf0j']/../../li")).size();

    }

    public void laterButton() {
        app.click(By.cssSelector(".eg0KI5SqghoOFd"));
    }

    public void fillTeamDescription() {
        app.type(By.cssSelector("._15aIJYNKhrO4vB"), "Team #" + System.currentTimeMillis());
    }

    public void fillTeamName() {
        app.type(By.cssSelector("[data-test-id='header-create-team-name-input']"), "teamFromMainMenu" + System.currentTimeMillis());
    }

    public void clickCreateTeam() {
        app.click(By.cssSelector(".icon-add"));
    }


    public void deleteLastTeam() throws InterruptedException {
        int before = getTeamsCount();
        if (before <= 1) {
            System.out.println("No teams found");
        } else
            clickLastTeam();
        app.pause(4000);
        clickTeamSettings();
        app.pause(4000);
        clickDeleteTeam();
        app.pause(2000);
        app.returnToHomePage();
        int actualRes = getTeamsCount();
        int expectedRes = before - 1;
        Assert.assertEquals(actualRes, expectedRes);
    }

    public void clickDeleteTeam() throws InterruptedException {
        app.click(By.cssSelector(".quiet-button"));
        app.pause(1000);
        clickSubmitTeamDelete();
    }

    public void clickSubmitTeamDelete() {
        app.click(By.xpath("//input[@class='js-confirm full negate']"));
    }

    public void clickTeamSettings() {
        app.click(By.cssSelector(".icon-gear"));

    }

    public void clickLastTeam() {
        app.wd.findElements(By.xpath("//*[@class='_33CvMKqfH4Yf0j']/../../li")).
                get(app.wd.findElements(By.xpath("//*[@class='_33CvMKqfH4Yf0j']/../../li")).size() - 1).click();

    }

    public String getNewTextFromLastTeamName(By selector) {
        return app.wd.findElements(selector).get(app.wd.findElements(selector).size() - 1).getText();
    }

    public String getTextFromModifiedTeamName(By selector) {
        return app.wd.findElement(selector).getText();
    }

    public String getTextFromLastTeamName(By selector) {
        return app.wd.findElements(selector).get(app.wd.findElements(selector).size() - 1).getText();
    }

    public void clickEditTeamProfile() {
        app.click(By.cssSelector(".js-edit-profile"));
    }

}
