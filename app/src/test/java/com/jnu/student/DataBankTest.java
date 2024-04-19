package com.jnu.student;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class DataBankTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void saveAndLoadShopItems() {
        ArrayList<ShopItem> shopItems = new ArrayList<>();
        shopItems.add(new ShopItem("青椒", 1.5, R.drawable.qingjiao));
        shopItems.add(new ShopItem("baicai", 2, R.drawable.baicai));


    }
}