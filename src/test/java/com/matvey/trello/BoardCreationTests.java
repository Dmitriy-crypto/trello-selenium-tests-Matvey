package com.matvey.trello;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        if (!app.sessionHelper.isAvatarPresent()) {
            app.sessionHelper.trelloLoginButton();
            app.sessionHelper.fillLoginForm("meliebling@gmail.com", "7Ig%20K8");
            app.pause(5000);}
    }

    @Test//(priority = 1)
    public void testCreateBoardFromMainPage() throws InterruptedException {


        app.boardHelper.createBoardFromMainPage();
        int boardsCount = app.boardHelper.getBoardsCount();
        while(boardsCount>5){app.boardHelper.deleteLastBoard();

        }
    }

    @Test (enabled = false)
    public void testCreateBoardFromHeader() throws InterruptedException {
        int before = app.boardHelper.getBoardsCount();
        app.boardHelper.addNewBoard();
        app.boardHelper.fillBoardName("Board from header"+System.currentTimeMillis());
        app.boardHelper.chooseAddPublicBoard();
        app.boardHelper.submitCreateBoard();
        app.pause(5000);
        app.returnToHomePage();
        int after = app.boardHelper.getBoardsCount();
        Assert.assertEquals(after,before+1);

    }
}


