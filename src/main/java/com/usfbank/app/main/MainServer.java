package com.usfbank.app.main;

import com.usfbank.app.presentation.Server;

public class MainServer {
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}