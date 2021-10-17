package org.lbstraw.map.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.lbstraw.map.web.rest.TestUtil;

class BatimentsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Batiments.class);
        Batiments batiments1 = new Batiments();
        batiments1.setId(1L);
        Batiments batiments2 = new Batiments();
        batiments2.setId(batiments1.getId());
        assertThat(batiments1).isEqualTo(batiments2);
        batiments2.setId(2L);
        assertThat(batiments1).isNotEqualTo(batiments2);
        batiments1.setId(null);
        assertThat(batiments1).isNotEqualTo(batiments2);
    }
}
