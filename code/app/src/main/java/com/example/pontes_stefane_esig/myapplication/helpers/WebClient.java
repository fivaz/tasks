package com.example.pontes_stefane_esig.myapplication.helpers;

import com.example.pontes_stefane_esig.myapplication.models.User;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class WebClient {
    public void uploadAll(String json) {
        try {
            URL url = new URL("https://www.esig-sandbox.ch/stefanepntsf/dmob/upload.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            connection.setDoOutput(true);
            PrintStream output = new PrintStream(connection.getOutputStream());
            output.println(json);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String downloadAll() {
        try {
            URL url = new URL("https://www.esig-sandbox.ch/stefanepntsf/dmob/download.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            connection.setDoOutput(true);

            Scanner scanner = new Scanner(connection.getInputStream());
            return scanner.nextLine();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}