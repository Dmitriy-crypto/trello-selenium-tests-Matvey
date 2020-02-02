package com.matvey.trello.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChangeUserAvatarTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        if (!app.getSession().isAvatarPresent()) {
            app.getSession().trelloLoginButton();
            app.getSession().fillLoginForm("meliebling@gmail.com", "7Ig%20K8");
            app.getSession().pause(5000);

        }
    }


    @Test(enabled=false)
    public void testChangeAvatar() throws InterruptedException {
        logger.info("avatar before: ");
        app.getSession().clickOnAvatar();
        app.getSession().openUserProfileFromDropDown();
        app.getSession().goToAtlassianAccount();
        app.getSession().addAvatarImageAndClose();
        logger.info("avatar after: ");
        app.takeScreenshot();



    }




}
