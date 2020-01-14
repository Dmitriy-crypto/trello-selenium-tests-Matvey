package com.matvey.trello;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        if (!app.sessionHelper.isAvatarPresent()) {
            app.sessionHelper.trelloLoginButton();
            app.sessionHelper.fillLoginForm("meliebling@gmail.com", "7Ig%20K8");
            app.pause(5000);

        }
    }

    @Test
    public void testDeleteLastTeam() throws InterruptedException {
        app.teamHelper.createTeamFromMainPage();
        app.teamHelper.deleteLastTeam();

    }

    @AfterClass
    public void postActions() throws InterruptedException {
        int teamsCount = app.teamHelper.getTeamsCount();
        if(teamsCount>2){
            app.teamHelper.deleteLastTeam();
        }
    }

    }

