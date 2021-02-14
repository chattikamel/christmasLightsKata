package io.home.katas;


import com.google.common.collect.Streams;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static io.home.katas.Position.of;
import static org.assertj.core.api.Assertions.assertThat;

class ChristmasLightsTest {

    public static final Condition<Light> LIGHT_ON_CONDITION = new Condition<>(Light::isOn, "Light on");
    public static final Condition<Light> LIGHT_OFF_CONDITION = new Condition<>(Light::isOff, "Light off");

    private ChristmasLights christmasLights;
    private int x;
    private int y;
    private Position position;
    private Position oppositePosition;

    @BeforeEach
    void setUp() {
        christmasLights = new ChristmasLights();
        x = 5;
        y = 6;
        position = of(x + 1, y + 2);
        oppositePosition = of(position.x + 100, position.y + 100);
    }

    @Test
    void christmasLights_has_aGridOf_2dim_of_1000_Lights_test() {
        assertThat(christmasLights.getGrid()).isNotNull();
        for (int i = 0; i < 1000; i++) {
            assertThat(christmasLights.getGrid()[i].length)
                    .isEqualTo(1000);
        }
    }

    @Test
    void when_christmasLights_turnOn_theLight_in_theSpecificPosition_itShould_lightOn_test() {
        christmasLights.turnOn(x, y);
        christmasLights.turnOn(position);

        assertLightIsOn(x, y);
        assertLightIsOn(position);
    }

    @Test
    void when_christmasLights_turnOff_theLight_in_theSpecificPosition_itShould_lightOff_test() {

        christmasLights.turnOn(x, y);
        christmasLights.turnOff(x, y);

        christmasLights.turnOn(position);
        christmasLights.turnOff(position);

        assertLightIsOff(x, y);
        assertLightIsOff(position);
    }

    @Test
    void when_christmasLights_toggle_aLightOn_in_theSpecificPosition_itShould_turnItOff_test() {

        christmasLights.turnOn(x, y);
        christmasLights.toggle(x, y);

        christmasLights.turnOn(position);
        christmasLights.toggle(position);

        assertLightIsOff(x, y);
        assertLightIsOff(position);
    }

    @Test
    void when_christmasLights_toggle_aLightOff_in_theSpecificPosition_itShould_turnItOn_test() {

        christmasLights.turnOff(x, y);
        christmasLights.toggle(x, y);

        christmasLights.turnOff(position);
        christmasLights.toggle(position);

        assertLightIsOn(x, y);
        assertLightIsOn(position);
    }

    @Test
    void when_christmasLights_turnOn_aRectangle_itShould_lightOn_the_selected_area_test() {
        christmasLights.turnOn(position, oppositePosition);

        assertLightIsOn(position, oppositePosition);
    }

    @Test
    void when_christmasLights_turnOff_aRectangle_itShould_lightOff_the_selected_area_test() {
        christmasLights.turnOn(position, oppositePosition);

        christmasLights.turnOff(position, oppositePosition);

        assertLightIsOff(position, oppositePosition);
    }

    @Test
    void when_christmasLights_toggle_aRectangle_itShould_makes__evrey_light_of_the_selected_area_turns_toOpposite_state_test() {
        christmasLights.turnOn(position, oppositePosition);

        Stream<Integer> xRandStream = new Random().ints(position.x, oppositePosition.x).boxed();
        Stream<Integer> yRandSteam = new Random().ints(position.y, oppositePosition.y).boxed();

        Streams.zip(xRandStream, yRandSteam, Position::of)
                .limit(30)
                .peek(System.out::println)
                .forEach(christmasLights::turnOn);

        ChristmasLights backUp = clone(christmasLights);


        this.christmasLights.toggle(position, oppositePosition);

        assertLightIsToggled(position, oppositePosition, backUp);
    }


    private void assertLightIsToggled(Position p0, Position p1, ChristmasLights backUp) {
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                if (i >= p0.x && i <= p1.x && j >= p0.y && j <= p1.y) {
                    assertThat(christmasLights.grid[i][j] == backUp.grid[i][j])
                            .as("The light must be turn to opposite state !")
                            .isFalse();
                } else {
                    assertThat(christmasLights.grid[i][j] == backUp.grid[i][j])
                            .as("The light must remain in the same state !")
                            .isTrue();
                }

            }

        }
    }


    private void assertLightIsOff(Position position, Position oppositePosition) {
        assertLightIs_(position, oppositePosition, this::assertLightIsOff);
    }


    private void assertLightIsOn(Position position, Position oppositePosition) {
        assertLightIs_(position, oppositePosition, this::assertLightIsOn);
    }

    private void assertLightIs_(Position position, Position oppositePosition, Consumer<Position> consumer) {
        for (int i = position.x; i <= oppositePosition.x; i++) {
            for (int j = position.y; j <= oppositePosition.y; j++) {
                consumer.accept(of(i, j));
            }

        }
    }

    private ChristmasLights clone(ChristmasLights christmasLights) {
        ChristmasLights clone = new ChristmasLights();
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                clone.grid[i][j] = christmasLights.grid[i][j];
            }

        }
        return clone;
    }


    private void assertLightIs(int x, int y, Condition<Light> lightOnCondition) {
        assertThat(christmasLights.getGrid()[x][y]).is(
                lightOnCondition);
    }

    private void assertLightIsOn(int x, int y) {
        assertLightIs(x, y, LIGHT_ON_CONDITION);
    }

    private void assertLightIsOn(Position p) {
        assertLightIs(p.x, p.y, LIGHT_ON_CONDITION);
    }

    private void assertLightIsOff(int x, int y) {
        assertLightIs(x, y, LIGHT_OFF_CONDITION);
    }

    private void assertLightIsOff(Position p) {
        assertLightIs(p.x, p.y, LIGHT_OFF_CONDITION);
    }

}