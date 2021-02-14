package io.home.katas;


import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    private void assertLightIsOff(Position position, Position oppositePosition) {
        for (int i = position.x; i <= oppositePosition.x; i++) {
            for (int j = position.y; j <= oppositePosition.y; j++) {
                assertLightIsOff(i, j);
            }

        }
    }


    private void assertLightIsOn(Position position, Position oppositePosition) {
        for (int i = position.x; i <= oppositePosition.x; i++) {
            for (int j = position.y; j <= oppositePosition.y; j++) {
                assertLightIsOn(i, j);
            }

        }
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