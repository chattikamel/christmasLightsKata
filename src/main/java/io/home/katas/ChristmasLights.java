package io.home.katas;

import lombok.Data;

import java.util.function.Function;

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

    public void turnOn(Position position) {
        grid[position.x][position.y].setOn(true);

    }

    public void turnOff(int x, int y) {
        grid[x][y].setOn(false);
    }

    public void turnOff(Position position) {
        grid[position.x][position.y].setOn(false);
    }

    public void toggle(int x, int y) {
        grid[x][y].setOn(grid[x][y].isOff());
    }

    public void toggle(Position position) {
        grid[position.x][position.y].setOn(grid[position.x][position.y].isOff());
    }

    public void turnOn(Position position, Position oppositePosition) {
        turn_(position, oppositePosition, Light::on);
    }

    public void turnOff(Position position, Position oppositePosition) {
        turn_(position, oppositePosition, Light::off);
    }

    public void turn_(Position position, Position oppositePosition, Function<Light, Light> mapper) {
        for (int i = position.x; i <= oppositePosition.x; i++) {
            for (int j = position.y; j <= oppositePosition.y; j++) {
                grid[i][j] = mapper.apply(grid[i][j]);
            }

        }
    }
}
