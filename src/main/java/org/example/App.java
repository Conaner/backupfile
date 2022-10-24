package org.example;

import org.example.views.MainFrame;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) {
        // 显示应用 GUI
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame.initAndShow();
            }
        });
    }
}
