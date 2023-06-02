package app;

public class FileData {
    private final String fileName;

    private final byte[] bytes;

    public FileData(String fileName, byte[] bytes) {
        this.fileName = fileName;
        this.bytes = bytes;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public String getFileName() {
        return fileName;
    }
}
