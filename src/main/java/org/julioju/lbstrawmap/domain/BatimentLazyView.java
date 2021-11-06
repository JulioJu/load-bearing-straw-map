package org.julioju.lbstrawmap.domain;

import org.julioju.lbstrawmap.domain.enumeration.UsageBatiment;

public interface BatimentLazyView {
    Long getId();

    String getNomBatiment();

    Float getLatitude();

    Float getLongitude();

    UsageBatiment getUsageBatiment();

    Integer getSurfacePlancher();
}
