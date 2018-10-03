package org.luxor;

import lombok.extern.java.Log;
import org.junit.Ignore;
import org.junit.Test;
import org.luxor.exception.ImplementationNotFoundException;
import org.luxor.exception.InvalidSuperClassImplException;
import org.luxor.factory.FactoryImplDiscovery;
import org.luxor.scenario.AbstractEquipament;
import org.luxor.scenario.BootEquipament;
import org.luxor.scenario.Equipament;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by jeremiassantos on 30/09/2018.
 */
@Ignore
@Log
public class FactoryImplDiscoveryTest {

    private FactoryImplDiscovery factoryEngine = FactoryImplDiscovery.getInstance();

    @Test
    public void foneImplDiscoveryTest() {

        Optional<Equipament> foneEquipament = factoryEngine.discovery("Fone", Equipament.class);

        assertTrue("Impl invalid!", foneEquipament.isPresent());

        Equipament equipament = foneEquipament.get();

        assertEquals(equipament.getClass().getSimpleName(), "FoneEquipament");

        equipament.process(new BigDecimal("100"));
    }

    @Test(expected = ImplementationNotFoundException.class)
    public void implementationNotFountTest() {
        factoryEngine.discoveryOrThrow("FoneOld", Equipament.class);
    }

    @Test
    public void getImplAbstractClassTest() {

        Optional<AbstractEquipament> bootEquipament = factoryEngine.discovery("Boot", AbstractEquipament.class);

        assertTrue("Impl invalid!", bootEquipament.isPresent());

        AbstractEquipament abstractEquipament = bootEquipament.get();

        assertEquals(abstractEquipament.getClass().getSimpleName(), "BootEquipament");

        abstractEquipament.process(new BigDecimal("100"));
    }

    @Test
    public void superClassInvalidTest() {
        try {
            factoryEngine.discovery("Boot", BootEquipament.class);
        } catch (InvalidSuperClassImplException e) {
            log.info(e.getMessage());

            assertTrue(e.getMessage().length() > 0);
            assertTrue(e.getMessage().contains("AbstractEquipament"));
        }
    }

}