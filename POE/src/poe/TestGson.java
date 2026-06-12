/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package poe;

import com.google.gson.Gson;

public class TestGson {

    public static void main(String[] args) {

        Gson gson = new Gson();

        String json = gson.toJson("Hello Gson");

        System.out.println(json);
    }
}
    
