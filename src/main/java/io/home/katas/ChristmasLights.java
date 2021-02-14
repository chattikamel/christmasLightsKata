package io.home.katas;

import lombok.Data;

@Data
public class ChristmasLights {

    Object[][] grid;

    public ChristmasLights() {
        grid = new Object[1000][1000];
    }
}
