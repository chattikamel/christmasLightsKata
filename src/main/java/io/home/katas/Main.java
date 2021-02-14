package io.home.katas;

import org.javatuples.Triplet;

import java.util.Arrays;
import java.util.function.BiConsumer;

import static io.home.katas.Position.of;
import static java.util.Arrays.asList;

public class Main {

    public static void main(String[] args) {
        ChristmasLights christmasLights = new ChristmasLights();
        asList(
//        turn on 887,9 through 959,629
                Triplet.<BiConsumer<Position, Position>, Position, Position>with(christmasLights::turnOn, of(887, 9), of(959, 629)),
//        turn on 454,398 through 844,448
                Triplet.<BiConsumer<Position, Position>, Position, Position>with(christmasLights::turnOn, of(454, 398), of(844, 448)),
//        turn off 539,243 through 559,965
                Triplet.<BiConsumer<Position, Position>, Position, Position>with(christmasLights::turnOff, of(539, 243), of(559, 965)),
//        turn off 370,819 through 676,868
                Triplet.<BiConsumer<Position, Position>, Position, Position>with(christmasLights::turnOff, of(370, 819), of(676, 868)),
//        turn off 145,40 through 370,997
                Triplet.<BiConsumer<Position, Position>, Position, Position>with(christmasLights::turnOff, of(145, 40), of(370, 997)),
//        turn off 301,3 through 808,453
                Triplet.<BiConsumer<Position, Position>, Position, Position>with(christmasLights::turnOff, of(301, 3), of(808, 453)),
//        turn on 351,678 through 951,908
                Triplet.<BiConsumer<Position, Position>, Position, Position>with(christmasLights::turnOn, of(351, 678), of(951, 908)),
//        toggle 720,196 through 897,994
                Triplet.<BiConsumer<Position, Position>, Position, Position>with(christmasLights::toggle, of(720, 196), of(897, 994)),
//        toggle 831,394 through 904,860
                Triplet.<BiConsumer<Position, Position>, Position, Position>with(christmasLights::toggle, of(831, 394), of(904, 860)))

                .forEach(t -> t.getValue0().accept(t.getValue1(), t.getValue2()));

        System.out.println(christmasLights.litLights());
    }
}
