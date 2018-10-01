package org.luxor;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.luxor.internal.ImplInfo;
import org.luxor.internal.ImplInfoHandle;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by jeremiassantos on 29/09/2018.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FactoryImplService {

    private static final FactoryImplService INSTANCE = new FactoryImplService();

    private static Map<String, ImplInfo> implsInstanceCache = new HashMap<>();

    static {
        register();
    }

    public static FactoryImplService getInstance() {
        return INSTANCE;
    }

    private static void register() {
        ImplInfoHandle.getInfo().forEach(info ->
            implsInstanceCache.put(info.getKey(), info)
        );
    }

    public Optional<ImplInfo> getImplInfo(@NonNull String key) {
        return Optional.ofNullable(implsInstanceCache.get(key));
    }

}
