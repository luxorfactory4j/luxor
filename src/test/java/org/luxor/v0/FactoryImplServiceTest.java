package org.luxor.v0;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.luxor.v0.factory.FactoryImplService;
import org.luxor.v0.internal.ImplInfo;
import org.luxor.v0.scenario.FoneEquipament;

import java.util.Optional;

/**
 * Created by jeremiassantos on 30/09/2018.
 */
@Ignore
public class FactoryImplServiceTest {

    @Test
    public void getImplInfoFoneTest() {

        Optional<ImplInfo> foneInfo = FactoryImplService.getInstance().getImplInfo("Fone");

        Assert.assertTrue(foneInfo.isPresent());

        ImplInfo implInfo = foneInfo.get();

        Assert.assertEquals(implInfo.getKey(), "Fone");
        Assert.assertEquals(implInfo.getName(), "FoneEquipament");
        Assert.assertEquals(implInfo.getInstance().getClass().getName(), FoneEquipament.class.getName());
    }

}