package com.matvey.trello;

import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        if(app.sessionHelper.isAvatarPresent()){
            app.sessionHelper.logout();
        }
    }

    @Test

    public void testLogInWithAtlassianAcc() throws InterruptedException {
        app.sessionHelper.trelloLoginButton();
        app.sessionHelper.fillLoginForm("meliebling@gmail.com", "7Ig%20K8");
app.pause(5000);
Assert.assertTrue(app.sessionHelper.isAvatarPresent());
    }


    @Test(enabled = false) //negative test
    public void negativeTestLogInWithAtlassianAcc() throws InterruptedException {
        app.sessionHelper.trelloLoginButton();
        app.sessionHelper.fillLoginForm("melieblinggg@gmail.com", "7Ig%20K8");
        app.pause(8000);
        Assert.assertTrue(app.sessionHelper.isAvatarPresent());

    }
}


//Trello buttons to create a board:

//.board-tile mod-add - Create new board button or //span[@name='add'] and then ???

////input[@placeholder='Add board title'] - Add board title


////button[@class='subtle-chooser-trigger unstyled-button vis-chooser-trigger'] -
//Dropdown menu to choose public or private

//.icon-sm.icon-public - Public button

////input[@type='submit'] - Yes, make board public

////button[@type='submit'] - Create board
