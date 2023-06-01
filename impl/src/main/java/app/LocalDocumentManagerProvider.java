package app;

public class LocalDocumentManagerProvider implements DocumentManagerProvider {
    @Override
    public DocumentManager create() {
        return new LocalDocumentManagerImpl();
    }
}
