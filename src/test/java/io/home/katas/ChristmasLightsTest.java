package io.home.katas;


import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChristmasLightsTest {

    public static final Condition<Light> LIGHT_ON_CONDITION = new Condition<>(Light::isOn, "Light on test");
    public static final Condition<Light> LIGHT_OFF_CONDITION = new Condition<>(Light::isOff, "Light off test");

    private ChristmasLights christmasLights;

    @BeforeEach
    void setUp() {
        christmasLights = new ChristmasLights();
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
        int x=5, y=6;
        christmasLights.turnOn(x, y);

        assertLightIsOn(x, y);
    }

    @Test
    void when_christmasLights_turnOff_theLight_in_theSpecificPosition_itShould_lightOff_test() {
        int x=5, y=6;

        christmasLights.turnOn(x,y);
        christmasLights.turnOff(x, y);

        assertLightIsOff(x, y);
    }

    @Test
    void when_christmasLights_toggle_aLightOn_in_theSpecificPosition_itShould_turnItOff_test() {
        int x=5, y=6;

        christmasLights.turnOn(x,y);
        christmasLights.toggle(x, y);

        assertLightIsOff(x, y);
    }

    @Test
    void when_christmasLights_toggle_aLightOff_in_theSpecificPosition_itShould_turnItOn_test() {
        int x=5, y=6;

        christmasLights.turnOff(x,y);
        christmasLights.toggle(x, y);

        assertLightIsOn(x, y);
    }

    private void assertLightIs(int x, int y, Condition<Light> lightOnCondition) {
        assertThat(christmasLights.getGrid()[x][y]).is(
                lightOnCondition);
    }

    private void assertLightIsOn(int x, int y){
        assertLightIs(x, y, LIGHT_ON_CONDITION);
    }

    private void assertLightIsOff(int x, int y){
        assertLightIs(x, y, LIGHT_OFF_CONDITION);
    }
}