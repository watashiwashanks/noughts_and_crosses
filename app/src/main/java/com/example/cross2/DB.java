package com.example.cross2;

import android.util.Pair;

import java.util.HashMap;
import java.util.List;

abstract public class DB {
    public static String currentUser;


    public static HashMap<String, String> users = new HashMap<>();
    public static HashMap<String, List<String>> results = new HashMap<>();
    public static HashMap<String, List<List<Pair<String, String>>>> stepGames = new HashMap<>();

    static {
        users.put("Stepan", "123");
        users.put("Oleg", "234");
    }
}



