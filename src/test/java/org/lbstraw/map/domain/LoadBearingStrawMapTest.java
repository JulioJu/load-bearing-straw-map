package org.lbstraw.map.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.lbstraw.map.web.rest.TestUtil;

class LoadBearingStrawMapTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoadBearingStrawMap.class);
        LoadBearingStrawMap loadBearingStrawMap1 = new LoadBearingStrawMap();
        loadBearingStrawMap1.setId(1L);
        LoadBearingStrawMap loadBearingStrawMap2 = new LoadBearingStrawMap();
        loadBearingStrawMap2.setId(loadBearingStrawMap1.getId());
        assertThat(loadBearingStrawMap1).isEqualTo(loadBearingStrawMap2);
        loadBearingStrawMap2.setId(2L);
        assertThat(loadBearingStrawMap1).isNotEqualTo(loadBearingStrawMap2);
        loadBearingStrawMap1.setId(null);
        assertThat(loadBearingStrawMap1).isNotEqualTo(loadBearingStrawMap2);
    }
}
