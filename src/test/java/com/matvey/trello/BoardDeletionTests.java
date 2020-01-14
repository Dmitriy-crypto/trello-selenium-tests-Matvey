package com.matvey.trello;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        if (!app.sessionHelper.isAvatarPresent()) {
            app.sessionHelper.trelloLoginButton();
            app.sessionHelper.fillLoginForm("meliebling@gmail.com", "7Ig%20K8");
            app.pause(5000);

        }
    }

    @Test
    public void testDeleteLastBoard() throws InterruptedException {
        app.boardHelper.createBoardFromMainPage();
        app.boardHelper.deleteLastBoard();
    }

    @AfterClass
    public void postActions() throws InterruptedException {
        int boardsCount = app.boardHelper.getBoardsCount();
        if(boardsCount>4){
            app.boardHelper.deleteLastBoard();
        }
}

}


