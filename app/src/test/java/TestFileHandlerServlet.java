import app.*;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class TestFileHandlerServlet {

    private final String PATH = "src/test/resources/";
    private final String FILE_PDF = "fileToCreate.pdf";
    private final String FILE_TXT = "fileToCreate.txt";

    @Test
    public void testUploadWithValidFile() throws IOException, ServletException, SQLException, ClassNotFoundException {

        var documentManager = mock(DocumentManager.class);

        when(documentManager.saveFile(any(Document.class), any())).thenReturn(1);

        File file = new File(PATH + FILE_TXT);

        Document document = Loader.provider().create().save(new FileInputStream(file), FILE_TXT);

        verify(documentManager).saveFile(any(Document.class), any());

        assertNotNull(document);

        assertEquals(FILE_TXT, document.name());

    }

    @Test
    public void testUploadWithInValidFile() throws SQLException, ClassNotFoundException {

        var documentManager = mock(DocumentManager.class);

        when(documentManager.saveFile(any(Document.class), any())).thenReturn(1);

        File file = new File(PATH + FILE_PDF);

        assertThrows(
                IllegalArgumentException.class,
                () -> Loader.provider().create().save(new FileInputStream(file), FILE_PDF));

        verify(documentManager).saveFile(any(Document.class), any());

    }

    @Test
    public void testGetAll() throws SQLException, ClassNotFoundException {

        var documentManager = spy(DocumentManager.class);

        when(documentManager.getAllFiles()).thenReturn(new ArrayList<>());

        var documents = Loader.provider().create().getAll();

        verify(documentManager).getAllFiles();

        assertNotNull(documents);

    }

    @Test
    public void testGet() throws SQLException, ClassNotFoundException {

        var id = "689e86ec-819d-4963-becf-bad28f93ffb1";

        var fileName = "test.txt";

        var documentManager = spy(DocumentManager.class);

        when(documentManager.getFile(anyString())).thenReturn(new FileData(fileName, new byte[]{}));

        var document = Loader.provider().create().get(id);

        verify(documentManager).getFile(anyString());

        assertNotNull(document);

        assertEquals(fileName, document.name());
    }
}
