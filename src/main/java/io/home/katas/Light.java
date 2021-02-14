package io.home.katas;


import lombok.Data;

@Data
public class Light {

    boolean on;

    public boolean isOff() {
        return !on;
    }
}
