package com.example.demo.object;

import org.springframework.stereotype.Component;

@Component
public class Vehicle {
    private Engine engine;

    public Vehicle(Engine engine) {
        this.engine = engine;
    }

    public void start() {
        engine.start();
        System.out.println("go");
    }
}
