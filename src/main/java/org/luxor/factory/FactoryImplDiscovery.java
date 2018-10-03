package org.luxor.factory;

import lombok.NonNull;
import org.luxor.configuration.LuxorConfiguration;
import org.luxor.exception.ImplementationNotFoundException;
import org.luxor.exception.InvalidSuperClassImplException;
import org.luxor.internal.ImplInfo;
import org.luxor.internal.LuxorConfigurationHandle;

import java.util.Optional;

/**
 * Created by jeremiassantos on 29/09/2018.
 */
public class FactoryImplDiscovery {
    
    private static final FactoryImplDiscovery INSTANCE = new FactoryImplDiscovery();

    private final FactoryImplService factoryImplService;

    public static FactoryImplDiscovery getInstance() {
        return INSTANCE;
    }

    private FactoryImplDiscovery() {
        factoryImplService = FactoryImplService.getInstance();
    }

    public <T> Optional<T> discovery(@NonNull String key, @NonNull Class<T> superClass) {

        Optional<ImplInfo> implInfoOptional = factoryImplService.getImplInfo(key);

        if(!implInfoOptional.isPresent()) {
            return Optional.empty();
        }

        ImplInfo implInfo = implInfoOptional.get();

        if(!implInfo.getType().contains(superClass)) {
            buildMessageErrorSuperClass(implInfo, superClass);
        }

        return (Optional<T>) Optional.ofNullable(implInfo.getInstance());
    }

    public <T> T discoveryOrThrow(@NonNull String key, @NonNull Class<T> superClass) {
         return discovery(key, superClass)
                 .orElseThrow(() -> new ImplementationNotFoundException("Implementation not fount with key: " + key));
    }

    private void buildMessageErrorSuperClass(ImplInfo implInfo, Class<?> superClass) {

        StringBuilder error = new StringBuilder();

        error.append("Super class ").append(superClass.getName()).append(" invalid\n");
        error.append("super class available: \n");

        implInfo.getType().forEach(c -> error.append("[").append(c.getName()).append("]\n"));

        throw new InvalidSuperClassImplException(error.toString());
    }

    public void addConfiguration(@NonNull LuxorConfiguration configuration) {
        LuxorConfigurationHandle.getInstance().setConfiguration(configuration);
    }
}
