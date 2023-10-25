package ru.bokalysha.Prac4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.bokalysha.Prac4.config.SpringConfig;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        TUI menu = context.getBean(TUI.class);
        menu.start();
        context.close();
    }
}