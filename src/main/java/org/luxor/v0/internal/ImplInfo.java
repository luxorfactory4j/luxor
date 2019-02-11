package org.luxor.v0.internal;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Created by jeremiassantos on 30/09/2018.
 */
@Builder
@Getter
public class ImplInfo {

    private String key;

    private String name;

    private Object instance;

    private List<Class<?>> type;

}
