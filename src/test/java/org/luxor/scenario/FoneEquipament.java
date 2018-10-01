package org.luxor.scenario;

import lombok.extern.java.Log;
import org.luxor.annotation.FactoryImplRegister;

import java.math.BigDecimal;

/**
 * Created by jeremiassantos on 29/09/2018.
 */
@Log
@FactoryImplRegister(key = "Fone")
public class FoneEquipament implements Equipament {

    public void process(BigDecimal valor) {
        log.info("Process with value: " + valor);
    }
}
