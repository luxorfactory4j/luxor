package org.luxor;

import org.junit.Assert;
import org.junit.Test;
import org.luxor.internal.ImplInfo;
import org.luxor.scenario.FoneEquipament;

import java.util.Optional;

/**
 * Created by jeremiassantos on 30/09/2018.
 */
public class FactoryImplServiceTest {

    @Test
    public void getImplInfoFoneTest() {

        Optional<ImplInfo> foneInfo = FactoryImplService.getInstance().getImplInfo("Fone");

        Assert.assertTrue(foneInfo.isPresent());

        ImplInfo implInfo = foneInfo.get();

        Assert.assertEquals(implInfo.getKey(), "Fone");
        Assert.assertEquals(implInfo.getName(), "org.luxor.scenario.FoneEquipament");
        Assert.assertEquals(implInfo.getInstance().getClass().getName(), FoneEquipament.class.getName());
    }

}