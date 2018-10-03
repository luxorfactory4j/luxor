package org.luxor.configuration;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/**
 * Created by jeremiassantos on 02/10/2018.
 */
@Getter
@Builder
public class LuxorConfiguration implements Serializable {

    private String packageScan;
}
