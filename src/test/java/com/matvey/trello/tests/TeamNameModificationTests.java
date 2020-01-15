package com.matvey.trello.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamNameModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        if (!app.getSession().isAvatarPresent()) {
            app.getSession().trelloLoginButton();
            app.getSession().fillLoginForm("meliebling@gmail.com", "7Ig%20K8");
            app.getSession().pause(5000);
        }
    }

    @Test
    public void renameLastTeam() throws InterruptedException {
       String oldTeamName = app.getTeam().getTextFromLastTeamName(By.xpath("//*[@class='_33CvMKqfH4Yf0j']/../../li"));
        app.getTeam().clickLastTeam();
        app.getTeam().pause(4000);
        app.getTeam().clickTeamSettings();
        app.getTeam().pause(4000);
        app.getTeam().clickEditTeamProfile();
        app.getTeam().type(By.xpath("//input[@name='displayName']"),"Modified teamname"+System.currentTimeMillis());
        app.getTeam().type(By.xpath("//input[@name='name']"),"Modified shortname"+System.currentTimeMillis());
        app.getTeam().clickSubmit();
        app.getTeam().pause(4000);
        String modifiedTeamName = app.getTeam().getTextFromModifiedTeamName(By.cssSelector(".u-inline"));
        app.getTeam().returnToHomePage();
        app.getTeam().pause(4000);
        String newTeamName = app.getTeam().getNewTextFromLastTeamName(By.xpath("//*[@class='_33CvMKqfH4Yf0j']/../../li"));
        Assert.assertEquals(modifiedTeamName,newTeamName);
        Assert.assertNotEquals(oldTeamName,newTeamName);

    }



}

