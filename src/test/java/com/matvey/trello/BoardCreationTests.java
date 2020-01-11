package com.matvey.trello;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        if (!app.isAvatarPresent()) {
            app.trelloLoginButton();
            app.fillLoginForm("meliebling@gmail.com", "7Ig%20K8");
            app.pause(5000);}
    }

    @Test//(priority = 1)
    public void testCreateBoardFromMainPage() throws InterruptedException {


        app.createBoardFromMainPage();
//        int boardsCount = getBoardsCount()
//        while(boardsCount>5){deleteBoards
//
//        }
    }

    @Test//(priority = 2)
    public void testCreateBoardFromHeader() throws InterruptedException {
        int before = app.getBoardsCount();
        app.addNewBoard();
        app.fillBoardName("Board from header"+System.currentTimeMillis());
        app.chooseAddPublicBoard();
        app.submitCreateBoard();
        app.pause(5000);
        app.returnToHomePage();
        int after = app.getBoardsCount();
        Assert.assertEquals(after,before+1);

    }
}


