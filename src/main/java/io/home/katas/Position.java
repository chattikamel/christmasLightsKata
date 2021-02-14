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
}
