package org.lbstraw.map;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("org.lbstraw.map");

        noClasses()
            .that()
            .resideInAnyPackage("org.lbstraw.map.service..")
            .or()
            .resideInAnyPackage("org.lbstraw.map.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..org.lbstraw.map.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
