package com.viktor.oop;

import com.viktor.oop.bookstore.BackendApplication;
import com.viktor.oop.service.BookService;

import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class ApplicationLauncher {
    private static final BookService bookService = BookService.getInstance();

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        new Thread(() -> {
            BackendApplication.main(args);
            waitForBackend();
            startFrontend(args);
        }).start();
    }

    private static void waitForBackend() {
        System.out.println("Waiting for backend to be ready...");

        while (true) {
            if (bookService.getStatus()) {
                System.out.println("Backend is ready!");
                break;
            }
            try {
                TimeUnit.SECONDS.sleep(1); // Retry every 1 second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static void startFrontend(String[] args) {
        SwingUtilities.invokeLater(() -> FrontendApplication.main(args));
    }
}
