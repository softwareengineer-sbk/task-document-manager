package app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

public class Loader {

    private static final String DEFAULT_PROVIDER = "app.LocalDocumentManagerProvider";

    public static List<DocumentManagerProvider> providers() {
        List<DocumentManagerProvider> providers = new ArrayList<>();
        ServiceLoader<DocumentManagerProvider> loader = ServiceLoader.load(DocumentManagerProvider.class);
        for (DocumentManagerProvider fileManagerProvider : loader) {
            providers.add(fileManagerProvider);
        }
        return providers;
    }

    public static DocumentManagerProvider provider() {
        return provider(DEFAULT_PROVIDER);
    }

    public static DocumentManagerProvider provider(String name) {
        ServiceLoader<DocumentManagerProvider> loader = ServiceLoader.load(DocumentManagerProvider.class);
        Iterator<DocumentManagerProvider> it = loader.iterator();
        while (it.hasNext()) {
            DocumentManagerProvider provider = it.next();
            if (provider.getClass().getName().equals(name)) {
                return provider;
            }
        }
        throw new IllegalArgumentException("Provider not found");
    }
}
