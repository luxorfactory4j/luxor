package org.luxor.internal;

import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.luxor.annotation.FactoryImplRegister;
import org.luxor.exception.InstanceImplException;
import org.luxor.factory.springsupport.SpringContextSupport;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.stream.Collectors;

/**
 * Created by jeremiassantos on 30/09/2018.
 */
@Log
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImplInfoHandle {

    public static List<ImplInfo> getInfo() {

        LuxorConfigurationHandle configuration = LuxorConfigurationHandle.getInstance();

        Reflections reflections;

        if(configuration.isConfigurationPresent()) {
            reflections = new Reflections(configuration.getConfiguration().getPackageScan());
        } else {
            reflections = new Reflections();
        }

        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(FactoryImplRegister.class);

        return annotated.stream().map(ImplInfoHandle::getInfo).collect(Collectors.toList());
    }

    public static ImplInfo getInfo(@NonNull Class<?> aClass) {

        Preconditions.checkArgument(aClass.isAnnotationPresent(FactoryImplRegister.class),
                "Annotation {FactoryImplRegister} is required!");

        FactoryImplRegister registerAnnotation = aClass.getAnnotation(FactoryImplRegister.class);

        String key = registerAnnotation.key();

        String name = getName(registerAnnotation, aClass);

        Object instance = getInstance(aClass);

        return ImplInfo.builder()
                .key(key)
                .name(name)
                .instance(instance)
                .type(findSuperClass(instance.getClass()))
                .build();
    }

    private static Object getInstance(Class<?> aClass) {
        try {

            if(SpringContextSupport.getInstance().isContextPresent()) {
                return SpringContextSupport.getInstance().getContext().getBean(aClass);
            }

            return aClass.newInstance();
        } catch (Exception e) {
            String error = "Error in initialize instance: " + aClass.getName() + " cause: " + e.getMessage();
            log.log(Level.SEVERE, error, e);
            throw new InstanceImplException(error);
        }
    }

    private static String getName(FactoryImplRegister registerAnnotation, Class<?> aClass) {

        if(StringUtils.isNotBlank(registerAnnotation.value())) {
            return registerAnnotation.value();
        }

        return aClass.getName();
    }

    private static List<Class<?>> findSuperClass(Class<?> aClass) {

        List<Class<?>> classes = new ArrayList<>();

        Class<?> superClass = aClass.getSuperclass();

        if(!superClass.getName().equals("java.lang.Object")) {
            classes.add(superClass);
        }

        classes.addAll(Arrays.asList(aClass.getInterfaces()));

        return classes;
    }
}
