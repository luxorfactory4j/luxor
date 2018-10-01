package org.luxor.scenario;

import org.luxor.annotation.FactoryImplRegister;

import java.math.BigDecimal;

/**
 * Created by jeremiassantos on 30/09/2018.
 */
@FactoryImplRegister(key = "Glove")
public class GloveEquipament implements Equipament {

    @Override
    public void process(BigDecimal valor) {

    }
}
