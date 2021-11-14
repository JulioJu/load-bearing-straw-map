package org.julioju.lbstrawmap.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.julioju.lbstrawmap.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AuthHistoryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AuthHistory.class);
        AuthHistory authHistory1 = new AuthHistory();
        authHistory1.setId(1L);
        AuthHistory authHistory2 = new AuthHistory();
        authHistory2.setId(authHistory1.getId());
        assertThat(authHistory1).isEqualTo(authHistory2);
        authHistory2.setId(2L);
        assertThat(authHistory1).isNotEqualTo(authHistory2);
        authHistory1.setId(null);
        assertThat(authHistory1).isNotEqualTo(authHistory2);
    }
}
