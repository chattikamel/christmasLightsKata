package io.home.katas;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Light {

    boolean on;

    public boolean isOff() {
        return !on;
    }

    public Light on() {
        return new Light(true);
    }

    public Light off() {
        return new Light(false);
    }
}
