package com.matvey.trello.tests;

import com.matvey.trello.model.BoardData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        if (!app.getSession().isAvatarPresent()) {
            app.getSession().trelloLoginButton();
            app.getSession().fillLoginForm("meliebling@gmail.com", "7Ig%20K8");
            app.getSession().pause(5000);}
    }

    @Test//(priority = 1)
    public void testCreateBoardFromMainPage() throws InterruptedException {


        app.getBoard().createBoardFromMainPage();
        int boardsCount = app.getBoard().getBoardsCount();
        while(boardsCount>5){app.getBoard().deleteLastBoard();

        }
    }

    @Test (enabled = false)
    public void testCreateBoardFromHeader() throws InterruptedException {
        int before = app.getBoard().getBoardsCount();
        app.getBoard().addNewBoard();
        app.getBoard().fillBoardName(new BoardData("Board from header" + System.currentTimeMillis()));
        app.getBoard().chooseAddPublicBoard();
        app.getBoard().submitCreateBoard();
        app.getBoard().pause(5000);
        app.getBoard().returnToHomePage();
        int after = app.getBoard().getBoardsCount();
        Assert.assertEquals(after,before+1);

    }
}


