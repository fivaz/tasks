package com.example.pontes_stefane_esig.myapplication.helpers;

import com.example.pontes_stefane_esig.myapplication.models.User;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class WebClient {

    private static String site = "https://www.esig-sandbox.ch/stefanepntsf/dmob/";
//    private static String site = "https://192.168.116.1/stefanepntsf/dmob/";

    public String uploadAll(String json) {
        try {
            StringBuilder response = new StringBuilder();
            URL url = new URL(site + "upload.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            connection.setDoOutput(true);
            PrintStream output = new PrintStream(connection.getOutputStream());
            output.println(json);

            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNextLine())
                response.append(scanner.nextLine());
            return response.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String downloadAll() {
        try {
            StringBuilder response = new StringBuilder();

            URL url = new URL(site + "download.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNextLine())
                response.append(scanner.nextLine());
            return response.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}