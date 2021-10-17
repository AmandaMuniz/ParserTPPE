import Exceptions.ArquivoNaoEncontradoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FileManagerTest {
    FileManager fileManager = new FileManager();

    @Test
    void openTimeFileTest() {
        try {
            fileManager.openFile("src/analysisTime.out");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void openMemoryFileTest() {
        try {
            fileManager.openFile("src/analysisMemory.out");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void openFileFailTest() {
        Exception exception = assertThrows(ArquivoNaoEncontradoException.class,
                () -> fileManager.openFile("src/pathNotFound.out"));
        assertEquals("src/pathNotFound.out não encontrado", exception.getMessage());
    }
}
