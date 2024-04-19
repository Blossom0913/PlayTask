package com.jnu.student;


import android.content.Context;
import android.util.Log;

import com.jnu.student.PlayTask.Bill;
import com.jnu.student.PlayTask.Reward;
import com.jnu.student.PlayTask.Task;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class DataBank {
    final String DATA_FILENAME = "shopitems.data";

    final String TASK_FILENAME = "taskitems.data";

    final String SCORE_FILENAME = "score.data";

    final String REWARD_FILENAME = "rewarditems.data";

    final String BILL_FILENAME = "billitems.data";


    public int LoadScore(Context context) {
        int data = 40;


        try {
            FileInputStream fileIn = context.openFileInput(SCORE_FILENAME);
            // Read the data from the file
            byte[] buffer = new byte[fileIn.available()];
            fileIn.read(buffer);

            // Convert the data to an integer
            data = ByteBuffer.wrap(buffer).getInt();
        } catch (IOException e) {
            // Handle the exception
            e.printStackTrace();
        }

        return data;
    }

    public ArrayList<ShopItem> LoadShopItems(Context context) {
        ArrayList<ShopItem> data = new ArrayList<>();
        try {
            FileInputStream fileIn = context.openFileInput(DATA_FILENAME);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            data = (ArrayList<ShopItem>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            //Log.d("Serialization", "Data loaded successfully.item count" + data.size());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ArrayList<Bill> LoadBillItems(Context context) {
        ArrayList<Bill> data = new ArrayList<>();
        try {
            FileInputStream fileIn = context.openFileInput(BILL_FILENAME);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            data = (ArrayList<Bill>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            //Log.d("Serialization", "Data loaded successfully.item count" + data.size());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ArrayList<Reward> LoadRewardItems(Context context) {
        ArrayList<Reward> data = new ArrayList<>();
        try {
            FileInputStream fileIn = context.openFileInput(REWARD_FILENAME);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            data = (ArrayList<Reward>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            //Log.d("Serialization", "Data loaded successfully.item count" + data.size());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }


    public ArrayList<com.jnu.student.PlayTask.Task> LoadTaskItems(Context context) {
        ArrayList<com.jnu.student.PlayTask.Task> data = new ArrayList<>();
        try {
            FileInputStream fileIn = context.openFileInput(TASK_FILENAME);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            data = (ArrayList<Task>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            //Log.d("Serialization", "Data loaded successfully.item count" + data.size());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void SaveShopItems(Context context, ArrayList<ShopItem> shopItems) {
        try {
            FileOutputStream fileOut = context.openFileOutput(DATA_FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(shopItems);
            out.close();
            fileOut.close();
            //Log.d("Serialization", "Data is serialized and saved.");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void SaveRewardItems(Context context, ArrayList<Reward> rewardItems){
        try {
            FileOutputStream fileOut = context.openFileOutput(REWARD_FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(rewardItems);
            out.close();
            fileOut.close();
            //Log.d("Serialization", "Data is serialized and saved.");
        }catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void SaveBillItems(Context context, ArrayList<Bill> billItems) {
        try {
            FileOutputStream fileOut = context.openFileOutput(BILL_FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(billItems);
            out.close();
            fileOut.close();
            //Log.d("Serialization", "Data is serialized and saved.");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void SaveTaskItems(Context context, ArrayList<Task> taskItems) {
        try {
            FileOutputStream fileOut = context.openFileOutput(TASK_FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(taskItems);
            out.close();
            fileOut.close();
            //Log.d("Serialization", "Data is serialized and saved.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SaveScore(Context context, int score) {


        try {
            // Convert the score to a byte array
            FileOutputStream fileOut = context.openFileOutput(SCORE_FILENAME, Context.MODE_PRIVATE);
            byte[] buffer = ByteBuffer.allocate(4).putInt(score).array();

            // Write the data to the file
            fileOut.write(buffer);
        } catch (IOException e) {
            // Handle the exception
            e.printStackTrace();
        }
    }
}