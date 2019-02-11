package org.luxor.v0.internal;

import org.junit.Ignore;
import org.junit.Test;
import org.luxor.v0.annotation.FactoryImplRegister;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by jeremiassantos on 30/09/2018.
 */
@Ignore
public class ImplInfoHandleTest {

    @Test
    public void getInfoTest() {

        List<ImplInfo> implInfos = ImplInfoHandle.getInfo();

        assertTrue("Is size info invalid!", implInfos.size() > 1);
        assertTrue(implInfos.get(0).getInstance().getClass().isAnnotationPresent(FactoryImplRegister.class));
    }
}