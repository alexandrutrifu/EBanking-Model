package org.poo.cb;

import App.EBankingApp;

public class Main {
    public static void main(String[] args) {
        if(args == null) {
            System.out.println("Running Main");
        } else {
            EBankingApp eBankingApp = EBankingApp.getInstance();

            eBankingApp.resetLogs();
            eBankingApp.setInputFiles(args);
            eBankingApp.init();
        }
    }
}