package com.cb.Upholstery;

import com.cb.Tasks.MailTask;
import com.cb.ThreadMachine.ThreadStroke;


/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ThreadStroke.strokeToEnemy(new MailTask(),10);

    }
}
