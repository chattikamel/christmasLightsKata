package io.home.katas;

import lombok.Data;

@Data
public class ChristmasLights {

    Light[][] grid;

    public ChristmasLights() {
        grid = new Light[1000][1000];
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                grid[i][j] = new Light();
            }
        }
    }

    public void turnOn(int x, int y) {
        grid[x][y].setOn(true);
    }

    public void turnOff(int x, int y) {
        grid[x][y].setOn(false);
    }
}
