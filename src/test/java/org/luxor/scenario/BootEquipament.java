package org.luxor.scenario;

import lombok.extern.java.Log;
import org.luxor.annotation.FactoryImplRegister;

import java.math.BigDecimal;

/**
 * Created by jeremiassantos on 30/09/2018.
 */
@Log
@FactoryImplRegister(key = "Boot")
public class BootEquipament extends AbstractEquipament {

    @Override
    public void process(BigDecimal value) {
        log.info("Boot impl value: " + value);
    }
}
