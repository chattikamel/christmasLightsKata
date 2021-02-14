package io.home.katas;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MainTest {

    @Test
    void create_christmasLights_test() {

        ChristmasLights christmasLights = new ChristmasLights();

        assertThat(christmasLights).isNotNull();

    }

    @Test
    void christmasLights_has_aGridOf_2dim_of_1000_Lights_test() {
        ChristmasLights christmasLights = new ChristmasLights();

        assertThat(christmasLights.getGrid()).isNotNull();
        for (int i = 0; i < 1000; i++) {
            assertThat(christmasLights.getGrid()[i].length).isEqualTo(1000);
        }
    }
}