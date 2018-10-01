package org.luxor.internal;

import org.junit.Test;
import org.luxor.annotation.FactoryImplRegister;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by jeremiassantos on 30/09/2018.
 */
public class ImplInfoHandleTest {

    @Test
    public void getInfoTest() {

        List<ImplInfo> implInfos = ImplInfoHandle.getInfo();

        assertTrue("Is size info invalid!", implInfos.size() > 1);
        assertTrue(implInfos.get(0).getInstance().getClass().isAnnotationPresent(FactoryImplRegister.class));
    }
}