package com.matvey.trello.manager;

import com.matvey.trello.model.TeamData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class TeamHelper extends HelperBase{

    public TeamHelper(WebDriver wd) {
        super(wd);
    }

    public void createTeamFromMainPage() throws InterruptedException {
        int before = getTeamsCount();
        clickCreateTeam();
        fillTeamCreationForm(new TeamData()
                        .withTeamName("teamFromMainMenu"+ System.currentTimeMillis())
                .withTeamDescription("Team #" + System.currentTimeMillis()));
        pause(2000);
        clickSubmit();
        pause(2000);
        laterButton();
        pause(2000);
        returnToHomePage();
        int after = getTeamsCount();
        Assert.assertEquals(after, before + 1);

    }

    public void fillTeamCreationForm(TeamData teamData) {
        type(By.cssSelector("[data-test-id='header-create-team-name-input']"), teamData.getTeamName());
        type(By.cssSelector("._15aIJYNKhrO4vB"), teamData.getTeamDescription());
    }

    public int getTeamsCount() {
        return wd.findElements(By.xpath("//*[@class='_33CvMKqfH4Yf0j']/../../li")).size();

    }

    public void laterButton() {
        click(By.cssSelector(".eg0KI5SqghoOFd"));
    }

    public void clickCreateTeam() {
        click(By.cssSelector(".icon-add"));
    }


    public void deleteLastTeam() throws InterruptedException {
        int before = getTeamsCount();
        if (before <= 1) {
            System.out.println("No teams found");
        } else
            clickLastTeam();
        pause(4000);
        clickTeamSettings();
        pause(4000);
        clickDeleteTeam();
        pause(2000);
        returnToHomePage();
        int actualRes = getTeamsCount();
        int expectedRes = before - 1;
        Assert.assertEquals(actualRes, expectedRes);
    }

    public void clickDeleteTeam() throws InterruptedException {
        click(By.cssSelector(".quiet-button"));
        pause(1000);
        clickSubmitTeamDelete();
    }

    public void clickSubmitTeamDelete() {
        click(By.xpath("//input[@class='js-confirm full negate']"));
    }

    public void clickTeamSettings() {
        click(By.cssSelector(".icon-gear"));

    }

    public void clickLastTeam() {
        wd.findElements(By.xpath("//*[@class='_33CvMKqfH4Yf0j']/../../li")).
                get(wd.findElements(By.xpath("//*[@class='_33CvMKqfH4Yf0j']/../../li")).size() - 1).click();

    }

    public String getNewTextFromLastTeamName(By selector) {
        return wd.findElements(selector).get(wd.findElements(selector).size() - 1).getText();
    }

    public String getTextFromModifiedTeamName(By selector) {
        return wd.findElement(selector).getText();
    }

    public String getTextFromLastTeamName(By selector) {
        return wd.findElements(selector).get(wd.findElements(selector).size() - 1).getText();
    }

    public void clickEditTeamProfile() {
        click(By.cssSelector(".js-edit-profile"));
    }

}
