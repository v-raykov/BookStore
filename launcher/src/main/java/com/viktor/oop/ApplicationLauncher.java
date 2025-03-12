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
        while (!bookService.getStatus()) {
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
