package com.viktor.oop;

import com.viktor.oop.bookstore.BackendApplication;

public class ApplicationLauncher {
    public static void main(String[] args) {
        new Thread(() -> BackendApplication.main(args)).start();
        new Thread(() -> FrontendApplication.main(args)).start();
    }
}