package com.matvey.trello.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        if (!app.getSession().isAvatarPresent()) {
            app.getSession().trelloLoginButton();
            app.getSession().fillLoginForm("meliebling@gmail.com", "7Ig%20K8");
            app.getSession().pause(5000);

        }
    }

    @Test
    public void testCreateTeamFromMainPage() throws InterruptedException {
        app.getTeam().createTeamFromMainPage();

    }

    @AfterClass
    public void postActions() throws InterruptedException {
        int teamsCount = app.getTeam().getTeamsCount();
        if(teamsCount>2){
            app.getTeam().deleteLastTeam();
        }

}

}


