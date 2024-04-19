package com.jnu.student.data;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jnu.student.data.DataDownload;

import java.util.ArrayList;

public class DataDownloadTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void download() {
        DataDownload dataDownload = new DataDownload();
        String fileContent = dataDownload.download("http://file.nidama.net/class/mobile_develop/data/bookstore.json");

        Assert.assertTrue(fileContent.contains("\"name\": \"明珠商业广场\""));
        Assert.assertTrue(fileContent.contains(" \"latitude\": \"22.251953\","));
        Assert.assertTrue(fileContent.contains( "\"longitude\": \"113.526421\","));
        Assert.assertTrue(fileContent.contains( "\"memo\": \"珠海二城广场\""));

    }

    @Test
    public void parseJsonObjects() {
        String fileContent = "{\n" +
                "  \"shops\": [{\n" +
                "    \"name\": \"暨大珠海\",\n" +
                "    \"latitude\": \"22.255925\",\n" +
                "    \"longitude\": \"113.541112\",\n" +
                "    \"memo\": \"暨南大学珠海校区\"\n" +
                "  },\n" +
                "    {\n" +
                "      \"name\": \"沃尔玛(前山店)\",\n" +
                "      \"latitude\": \"22.261365\",\n" +
                "      \"longitude\": \"113.532989\",\n" +
                "      \"memo\": \"沃尔玛(前山店)\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"明珠商业广场\",\n" +
                "      \"latitude\": \"22.251953\",\n" +
                "      \"longitude\": \"113.526421\",\n" +
                "      \"memo\": \"珠海二城广场\"\n" +
                "    }\n" +
                "  ]\n" +
                "}\n";

        DataDownload dataDownload = new DataDownload();
        ArrayList<ShopLocation> shopLocationArrayList = dataDownload.parseJsonObjects(fileContent);
        Assert.assertEquals(3,shopLocationArrayList.size());
        Assert.assertEquals("明珠商业广场",shopLocationArrayList.get(2).getName());
        Assert.assertEquals(22.251953,shopLocationArrayList.get(2).getLatitude(),1e-6);
        Assert.assertEquals(113.526421,shopLocationArrayList.get(2).getLongitude(),1e-6);

    }
}