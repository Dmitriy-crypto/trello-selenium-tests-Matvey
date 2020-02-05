package com.matvey.trello.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        if (!app.getSession().isAvatarPresent()) {
            app.getSession().trelloLoginButton();
            app.getSession().fillLoginForm("meliebling@gmail.com", "7Ig%20K8");
            app.getSession().pause(5000);

        }
    }

    @Test
    public void testDeleteLastTeam() throws InterruptedException {
        app.getTeam().createTeamFromMainPage();
        app.getTeam().pause(4000);
        app.getTeam().deleteLastTeam();

    }

    @AfterClass
    public void postActions() throws InterruptedException {
        int teamsCount = app.getTeam().getTeamsCount();
        app.getTeam().pause(4000);
        if(teamsCount>2){
            app.getTeam().deleteLastTeam();
        }
    }

    }

