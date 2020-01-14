package com.matvey.trello;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamNameModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        if (!app.sessionHelper.isAvatarPresent()) {
            app.sessionHelper.trelloLoginButton();
            app.sessionHelper.fillLoginForm("meliebling@gmail.com", "7Ig%20K8");
            app.pause(5000);
        }
    }

    @Test
    public void renameLastTeam() throws InterruptedException {
       String oldTeamName = app.teamHelper.getTextFromLastTeamName(By.xpath("//*[@class='_33CvMKqfH4Yf0j']/../../li"));
        app.teamHelper.clickLastTeam();
        app.pause(4000);
        app.teamHelper.clickTeamSettings();
        app.pause(4000);
        app.teamHelper.clickEditTeamProfile();
        app.type(By.xpath("//input[@name='displayName']"),"Modified teamname"+System.currentTimeMillis());
        app.type(By.xpath("//input[@name='name']"),"Modified shortname"+System.currentTimeMillis());
        app.clickSubmit();
        app.pause(4000);
        String modifiedTeamName = app.teamHelper.getTextFromModifiedTeamName(By.cssSelector(".u-inline"));
        app.returnToHomePage();
        app.pause(4000);
        String newTeamName = app.teamHelper.getNewTextFromLastTeamName(By.xpath("//*[@class='_33CvMKqfH4Yf0j']/../../li"));
        Assert.assertEquals(modifiedTeamName,newTeamName);
        Assert.assertNotEquals(oldTeamName,newTeamName);

    }



}

