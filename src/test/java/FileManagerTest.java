import Exceptions.*;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;

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
        assertEquals("src/pathNotFound.out nao encontrado", exception.getMessage());
    }

    @Test
    void readTimeFileTest() {
        try {
            File file = fileManager.openFile("src/analysisTime.out");
            ArrayList<ArrayList<Double>> values = fileManager.readFile(file);

            assertFalse(values.isEmpty());
            assertFalse(values.get(0).isEmpty());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void readMemoryFileTest() {
        try {
            File file = fileManager.openFile("src/analysisMemory.out");
            ArrayList<ArrayList<Double>> values = fileManager.readFile(file);

            assertFalse(values.isEmpty());
            assertFalse(values.get(0).isEmpty());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void readNullFileFailTest() {
        Exception exception = assertThrows(ErroDeLeituraException.class,
                () -> fileManager.readFile(null));
        assertEquals("Arquivo null nao pode ser lido", exception.getMessage());
    }

    @Test
    void readFileFailTest() {
        try {
            File file = fileManager.openFile("src/wrong.out");
            Exception exception = assertThrows(ErroDeLeituraException.class,
                    () -> fileManager.readFile(file));
            assertEquals("src/wrong.out nao pode ser lido", exception.getMessage());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void writeOutputTimeFileTest() {
        try {
            File file = fileManager.openFile("src/analysisTimeTab.test");
            fileManager.writeOutputFile(file, "teste");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void writeOutputMemoryFileTest() {
        try {
            File file = fileManager.openFile("src/analysisMemoryTab.test");
            fileManager.writeOutputFile(file, "teste");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void writeOutputFileFailTest() {
        try {
            File file = fileManager.openFile("src/pathNotFound.test");
            file.setWritable(false);
            Exception exception = assertThrows(EscritaNaoPermitidaException.class,
                    () -> fileManager.writeOutputFile(file, "teste"));
            assertEquals("src/pathNotFound.test nao pode ser escrito", exception.getMessage());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void setOutputNullTest() {
        try {
            String format = null;
            Exception exception = assertThrows(FormatoInvalidoException.class,
                    () -> fileManager.setOutput(format));
            assertEquals("Null e um formato invalido", exception.getMessage());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void setOutputValidTest() {
        try {
            String lineFormat = "linha";
            String columnFormat = "coluna";
            assertEquals(lineFormat, fileManager.setOutput(lineFormat));
            assertEquals(columnFormat, fileManager.setOutput(columnFormat));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void setOutputInvalidTest() {
        try {
            Exception exception = assertThrows(FormatoInvalidoException.class,
                    () -> fileManager.setOutput("asdasff"));
            assertEquals("asdasff e um formato invalido", exception.getMessage());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void setDelimiterTest() {
        try {
            fileManager.setDelimiter(";");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void setInvalidDelimiterTest() {
        try {
            Exception exception = assertThrows(DelimitadorInvalidoException.class,
                    () -> fileManager.setDelimiter("invalid"));
            assertEquals("invalid nao é válido como delimitador", exception.getMessage());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
