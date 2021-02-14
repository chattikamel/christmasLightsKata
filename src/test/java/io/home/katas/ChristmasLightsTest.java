package io.home.katas;


import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChristmasLightsTest {

    public static final Condition<Light> LIGHT_ON_CONDITION = new Condition<>(Light::isOn, "Light on");
    public static final Condition<Light> LIGHT_OFF_CONDITION = new Condition<>(Light::isOff, "Light off");

    private ChristmasLights christmasLights;
    private int x;
    private int y;
    private Position position;

    @BeforeEach
    void setUp() {
        christmasLights = new ChristmasLights();
        x = 5;
        position = Position.builder().x(x + 1).y(y + 2).build();
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

        christmasLights.turnOn(x,y);
        christmasLights.turnOff(x, y);

        christmasLights.turnOn(position);
        christmasLights.turnOff(position);

        assertLightIsOff(x, y);
        assertLightIsOff(position);
    }

    @Test
    void when_christmasLights_toggle_aLightOn_in_theSpecificPosition_itShould_turnItOff_test() {

        christmasLights.turnOn(x,y);
        christmasLights.toggle(x, y);

        christmasLights.turnOn(position);
        christmasLights.toggle(position);

        assertLightIsOff(x, y);
        assertLightIsOff(position);
    }

    @Test
    void when_christmasLights_toggle_aLightOff_in_theSpecificPosition_itShould_turnItOn_test() {

        christmasLights.turnOff(x,y);
        christmasLights.toggle(x, y);

        christmasLights.turnOff(position);
        christmasLights.toggle(position);

        assertLightIsOn(x, y);
        assertLightIsOn(position);
    }




    private void assertLightIs(int x, int y, Condition<Light> lightOnCondition) {
        assertThat(christmasLights.getGrid()[x][y]).is(
                lightOnCondition);
    }

    private void assertLightIsOn(int x, int y){
        assertLightIs(x, y, LIGHT_ON_CONDITION);
    }

    private void assertLightIsOn(Position p){
        assertLightIs(p.x, p.y, LIGHT_ON_CONDITION);
    }

    private void assertLightIsOff(int x, int y){
        assertLightIs(x, y, LIGHT_OFF_CONDITION);
    }

    private void assertLightIsOff(Position p){
        assertLightIs(p.x, p.y, LIGHT_OFF_CONDITION);
    }

}