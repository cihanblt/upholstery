package com.cb.ThreadMachine;

/**
 * Created by cihan on 10.02.2016.
 */
public class ThreadStroke {
    public static void strokeToEnemy(Runnable object,int amountOfStroke){
        for (int i = 0 ; i < amountOfStroke ; i++){
            new Thread(object).start();
        }
    }
}
