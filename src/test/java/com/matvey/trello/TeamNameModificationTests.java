package com.matvey.trello;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamNameModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        if (!app.isAvatarPresent()) {
            app.trelloLoginButton();
            app.fillLoginForm("meliebling@gmail.com", "7Ig%20K8");
            app.pause(5000);
        }
    }

    @Test
    public void renameLastTeam() throws InterruptedException {
       String oldTeamName = app.getTextFromLastTeamName(By.xpath("//*[@class='_33CvMKqfH4Yf0j']/../../li"));
        app.clickLastTeam();
        app.pause(2000);
        app.clickTeamSettings();
        app.pause(2000);
        app.clickEditTeamProfile();
        app.type(By.xpath("//input[@name='displayName']"),"Modified teamname"+System.currentTimeMillis());
        app.type(By.xpath("//input[@name='name']"),"Modified shortname"+System.currentTimeMillis());
        app.clickSubmit();
        app.pause(2000);
        String modifiedTeamName = app.getTextFromModifiedTeamName(By.cssSelector(".u-inline"));
        app.returnToHomePage();
        String newTeamName = app.getNewTextFromLastTeamName(By.xpath("//*[@class='_33CvMKqfH4Yf0j']/../../li"));
        Assert.assertEquals(modifiedTeamName,newTeamName);
        Assert.assertNotEquals(oldTeamName,newTeamName);

    }



}

