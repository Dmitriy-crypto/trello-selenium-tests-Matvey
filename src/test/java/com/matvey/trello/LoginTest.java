package com.matvey.trello;

import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        if(isAvatarPresent()){
            logout();
        }
    }

    @Test

    public void testLogInWithAtlassianAcc()  {
        trelloLoginButton();


    }


    @Test(enabled = false) //negative test
    public void negativeTestLogInWithAtlassianAcc() throws InterruptedException {
        loginTrello("melieblinggg@gmail.com", "7Ig%20K8");

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
