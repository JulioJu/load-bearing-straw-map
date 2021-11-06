package org.lbstraw.map.domain;

import org.lbstraw.map.domain.enumeration.UsageBatiment;

public interface BatimentsLazyView {
    Long getId();

    String getNomBatiment();

    Float getLatitude();

    Float getLongitude();

    UsageBatiment getUsageBatiment();

    Integer getSurfacePlancher();
}
