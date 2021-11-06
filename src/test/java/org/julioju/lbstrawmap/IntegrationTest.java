package org.julioju.lbstrawmap;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.julioju.lbstrawmap.LbstrawmapApp;
import org.julioju.lbstrawmap.config.TestSecurityConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Base composite annotation for integration tests.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = { LbstrawmapApp.class, TestSecurityConfiguration.class })
public @interface IntegrationTest {
}
