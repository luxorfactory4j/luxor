package org.luxor.v0.factory.springsupport;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.context.ApplicationContext;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpringContextSupport {

    private static final SpringContextSupport INSTANCE = new SpringContextSupport();

    private ApplicationContext context;

    public static SpringContextSupport getInstance() {
        return INSTANCE;
    }

    public ApplicationContext getContext() {
        return context;
    }

    public boolean isContextPresent() {
        return context != null;
    }

    public synchronized void setContext(@NonNull ApplicationContext context) {
        if(this.context == null) {
            this.context = context;
        }
    }
}
