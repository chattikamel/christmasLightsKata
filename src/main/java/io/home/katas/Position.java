package io.home.katas;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Position {
    public int x, y;

    public static Position of(int x, int y) {
        return builder().x(x).y(y).build();
    }

    public static boolean inRange(int x1, int x2, int i) {
        return i >= x1 && i <= x2;
    }
}
