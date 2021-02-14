package io.home.katas;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Position {
    public int x, y;
}
