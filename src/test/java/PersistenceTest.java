import Exceptions.*;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PersistenceTest {
    Persistence persistence = new Persistence();

    @Test
    void openTimeFileTest() {
        try {
            persistence.openFile("src/analysisTime.out");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void openMemoryFileTest() {
        try {
            persistence.openFile("src/analysisMemory.out");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void openFileFailTest() {
        Exception exception = assertThrows(ArquivoNaoEncontradoException.class,
                () -> persistence.openFile("src/pathNotFound.out"));
        assertEquals("src/pathNotFound.out nao encontrado", exception.getMessage());
    }

    @Test
    void readTimeFileTest() {
        try {
            File file = persistence.openFile("src/analysisTime.out");
            ArrayList<ArrayList<Double>> values = persistence.readInputFile(file);

            assertFalse(values.isEmpty());
            assertFalse(values.get(0).isEmpty());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void readMemoryFileTest() {
        try {
            File file = persistence.openFile("src/analysisMemory.out");
            ArrayList<ArrayList<Double>> values = persistence.readInputFile(file);

            assertFalse(values.isEmpty());
            assertFalse(values.get(0).isEmpty());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void readNullFileFailTest() {
        Exception exception = assertThrows(ErroDeLeituraException.class,
                () -> persistence.readInputFile(null));
        assertEquals("Arquivo null nao pode ser lido", exception.getMessage());
    }

    @Test
    void readFileFailTest() {
        try {
            File file = persistence.openFile("src/wrong.out");
            Exception exception = assertThrows(ErroDeLeituraException.class,
                    () -> persistence.readInputFile(file));
            assertEquals("src/wrong.out nao pode ser lido", exception.getMessage());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void writeOutputTimeFileTest() {
        try {
            File file = persistence.openFile("src/analysisTimeTab.test");
            persistence.writeOutputFile(file, "teste");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void writeOutputMemoryFileTest() {
        try {
            File file = persistence.openFile("src/analysisMemoryTab.test");
            persistence.writeOutputFile(file, "teste");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void writeOutputFileFailTest() {
        try {
            File file = persistence.openFile("src/pathNotFound.test");
            file.setWritable(false);
            Exception exception = assertThrows(EscritaNaoPermitidaException.class,
                    () -> persistence.writeOutputFile(file, "teste"));
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
                    () -> persistence.setOutput(format));
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
            assertEquals(lineFormat, persistence.setOutput(lineFormat));
            assertEquals(columnFormat, persistence.setOutput(columnFormat));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void setOutputInvalidTest() {
        try {
            Exception exception = assertThrows(FormatoInvalidoException.class,
                    () -> persistence.setOutput("asdasff"));
            assertEquals("asdasff e um formato invalido", exception.getMessage());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void setDelimiterTest() {
        try {
            persistence.setDelimiter(";");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void setInvalidDelimiterTest() {
        try {
            Exception exception = assertThrows(DelimitadorInvalidoException.class,
                    () -> persistence.setDelimiter("invalid"));
            assertEquals("invalid nao é válido como delimitador", exception.getMessage());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
