package com.matvey.trello.tests;

import com.matvey.trello.model.TeamData;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TeamCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]>validTeams(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"name DP ","description DP "});
        list.add(new Object[]{"DPn",""});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]>validTeamsCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader= new BufferedReader(new FileReader(new File("src/test/resources/teamsPositive.csv")));
        String line = reader.readLine();
        while(line!=null){
            String[] split = line.split(",");
            list.add(new Object[]{new TeamData().withTeamName(split[0]).withTeamDescription(split[1])});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        if (!app.getSession().isAvatarPresent()) {
            app.getSession().trelloLoginButton();
            app.getSession().fillLoginForm("meliebling@gmail.com", "7Ig%20K8");
            app.getSession().pause(5000);

        }
    }
    @Test(dataProvider = "validTeamsCSV")
    public void testCreateTeamFromMainPageWithCSV(TeamData team) throws InterruptedException {
        int before = app.getTeam().getTeamsCount();
        app.getTeam().pause(2000);
        app.getTeam().clickCreateTeam();
        app.getTeam().pause(2000);
        app.getTeam().fillTeamCreationForm(team);
        app.getTeam().pause(3000);
        app.getTeam().clickSubmit();
        app.getTeam().pause(3000);
        app.getTeam().laterButton();
        app.getTeam().pause(3000);
        app.getTeam().returnToHomePage();
        app.getTeam().pause(2000);
        int after = app.getTeam().getTeamsCount();
        Assert.assertEquals(after, before + 1);
    }








//    @Test(dataProvider = "validTeams")
//    public void testCreateTeamFromMainPageWithDP(String teamName, String teamDescription) throws InterruptedException {
//        int before = app.getTeam().getTeamsCount();
//        app.getTeam().pause(2000);
//        app.getTeam().clickCreateTeam();
//        app.getTeam().pause(2000);
//        app.getTeam().fillTeamCreationForm(new TeamData()
//                .withTeamName(teamName)
//                .withTeamDescription(teamDescription));
//        app.getTeam().pause(3000);
//        app.getTeam().clickSubmit();
//        app.getTeam().pause(3000);
//        app.getTeam().laterButton();
//        app.getTeam().pause(3000);
//        app.getTeam().returnToHomePage();
//        app.getTeam().pause(2000);
//        int after = app.getTeam().getTeamsCount();
//        Assert.assertEquals(after, before + 1);
//    }



    @Test(enabled=false)
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


