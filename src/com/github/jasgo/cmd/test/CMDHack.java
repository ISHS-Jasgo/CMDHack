package com.github.jasgo.cmd.test;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class CMDHack {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command;
        try {
            while ((command = reader.readLine()) != null) {
                Runtime runtime = Runtime.getRuntime();
                String[] cmd = command.split(" ");
                Process process = runtime.exec(cmd);
                BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
                String s;
                while ((s = input.readLine()) != null) {
                    System.out.println(s);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
