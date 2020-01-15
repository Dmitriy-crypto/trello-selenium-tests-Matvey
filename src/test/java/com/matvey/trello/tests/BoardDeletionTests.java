package com.matvey.trello.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        if (!app.getSession().isAvatarPresent()) {
            app.getSession().trelloLoginButton();
            app.getSession().fillLoginForm("meliebling@gmail.com", "7Ig%20K8");
            app.getSession().pause(5000);

        }
    }

    @Test
    public void testDeleteLastBoard() throws InterruptedException {
        app.getBoard().createBoardFromMainPage();
        app.getBoard().deleteLastBoard();
    }

    @AfterClass
    public void postActions() throws InterruptedException {
        int boardsCount = app.getBoard().getBoardsCount();
        if(boardsCount>4){
            app.getBoard().deleteLastBoard();
        }
}

}


