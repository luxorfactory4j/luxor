package org.luxor.factory.springsupport;

import lombok.NonNull;
import org.luxor.configuration.LuxorConfiguration;
import org.luxor.factory.FactoryImplDiscovery;
import org.luxor.internal.LuxorConfigurationHandle;
import org.springframework.context.ApplicationContext;

import java.util.Optional;

/**
 * Created by jeremiassantos on 02/10/2018.
 */
public class SpringFactoryImplDiscovery {

    private final FactoryImplDiscovery factoryImplDiscovery;

    private final ApplicationContext context;

    public SpringFactoryImplDiscovery(ApplicationContext context) {
        SpringContextSupport.getInstance().setContext(context);
        this.context = context;
        factoryImplDiscovery = FactoryImplDiscovery.getInstance();
    }

    public <T> Optional<T> discoveryBean(@NonNull String key, @NonNull Class<T> superClass) {

        Optional<T> impl = factoryImplDiscovery.discovery(key, superClass);

        if(!impl.isPresent()) {
            return Optional.empty();
        }

        Object bean = context.getBean(impl.get().getClass());

        return (Optional<T>) Optional.ofNullable(bean);
    }

    public <T> T discoveryBeanOrThrow(@NonNull String key, @NonNull Class<T> superClass) {
        return context.getBean((Class<T>) factoryImplDiscovery.discoveryOrThrow(key, superClass).getClass());
    }

    public void addConfiguration(@NonNull LuxorConfiguration configuration) {
        LuxorConfigurationHandle.getInstance().setConfiguration(configuration);
    }
}
