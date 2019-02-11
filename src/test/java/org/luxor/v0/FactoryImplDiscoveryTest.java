package org.luxor.v0;

import lombok.extern.java.Log;
import org.junit.Ignore;
import org.junit.Test;
import org.luxor.v0.exception.ImplementationNotFoundException;
import org.luxor.v0.exception.InvalidSuperClassImplException;
import org.luxor.v0.factory.FactoryImplDiscovery;
import org.luxor.v0.scenario.AbstractEquipament;
import org.luxor.v0.scenario.BootEquipament;
import org.luxor.v0.scenario.Equipament;

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