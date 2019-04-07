package com.example.pontes_stefane_esig.myapplication.helpers;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class WebClient {
    public String post(String json) {
        try {
            URL url = new URL("https://www.esig-sandbox.ch/stefanepntsf/dmob/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            connection.setDoOutput(true);
//            connection.setDoInput(true);
            PrintStream output = new PrintStream(connection.getOutputStream());
            output.println(json);

            Scanner scanner = new Scanner(connection.getInputStream());
            return scanner.next();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}