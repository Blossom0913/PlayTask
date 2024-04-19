package com.jnu.student;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;


import junit.framework.TestCase;

import org.junit.Assert;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

public class PlayTaskDataBankTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    @Config(sdk =34)
    public void testLoadScore() {
        DataBank dataBank = new DataBank();
        //dataBank.SaveScore(InstrumentationRegistry.getInstrumentation().getTargetContext(),40);
        //int score = dataBank.LoadScore(InstrumentationRegistry.getInstrumentation().getTargetContext());
        //Assert.assertEquals(score,40);
    }

    public void testLoadBillItems() {
    }

    public void testLoadRewardItems() {
    }

    public void testLoadTaskItems() {
    }

    public void testSaveRewardItems() {
    }

    public void testSaveBillItems() {
    }

    public void testSaveTaskItems() {
    }


}