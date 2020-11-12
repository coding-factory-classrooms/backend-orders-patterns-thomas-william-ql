package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class LogSystem {

    private final List<String> logs = new ArrayList<>();

    public void addLog(String message) {
        logs.add(message);
        System.out.println(message);
    }
}
