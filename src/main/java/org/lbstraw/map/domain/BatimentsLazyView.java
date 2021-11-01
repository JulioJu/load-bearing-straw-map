package org.lbstraw.map.domain;

import org.lbstraw.map.domain.enumeration.UsageBatiment;

public interface BatimentsLazyView {
    Long getId();

    String getNom();

    Float getLatitude();

    Float getLongitude();

    UsageBatiment getUsage();

    Integer getSurface();
}
