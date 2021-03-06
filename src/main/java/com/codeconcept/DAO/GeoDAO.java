package com.codeconcept.DAO;

import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
@Component
public class GeoDAO {

    public String request(String endpoint) throws Exception {
        StringBuilder sb = new StringBuilder();
//        "http://resources.codeconcept.pl/api/distance/"
        URL url = new URL(endpoint);

//        Otwarcie połączenia do URL.
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
//        Czyta tylko bity.
            InputStream inputStream = urlConnection.getInputStream();

//        Wczytuje podobnie jak InputStream, ale do bufora, czyli do lokacji w pamięci.
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

//        Odczytanie jako znaki.
            InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

//        Czytanie jednej lini na raz.
            String inputLine = bufferedReader.readLine();
            while (inputLine != null) {

//            Dodanie do wyjścia.
                sb.append(inputLine);

//            Czytanie kolejnej linii.
                inputLine = bufferedReader.readLine();

            }
        } finally {
            urlConnection.disconnect();
        }

        return sb.toString();
    }
}
