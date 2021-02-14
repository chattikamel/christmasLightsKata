package io.home.katas;


import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MainTest {

    @Test
    void christmasLights_has_aGridOf_2dim_of_1000_Lights_test() {
        ChristmasLights christmasLights = new ChristmasLights();

        assertThat(christmasLights.getGrid()).isNotNull();
        for (int i = 0; i < 1000; i++) {
            assertThat(christmasLights.getGrid()[i].length)
                    .isEqualTo(1000);
        }
    }

    @Test
    void when_christmasLights_turnOn_theLight_in_theSpecificPosition_itShould_lightOn_test() {
        ChristmasLights christmasLights = new ChristmasLights();
        int x=5, y=6;
        christmasLights.turnOn(x, y);

        assertThat(christmasLights.getGrid()[x][y]).is(
                new Condition<>((Light l) -> l.isOn(), "Light on test"));
    }
}