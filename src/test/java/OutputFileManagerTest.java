import Exceptions.EscritaNaoPermitidaException;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class OutputFileManagerTest {

    OutputFileManager outputFileManager = new OutputFileManager();
    Persistence persistence = new Persistence();

    @Test
    void clearOutputTimeFileTest() {
        try {
            File file = persistence.openFile("src/analysisTimeTab.test");
            outputFileManager.clearOutputFile(file);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void clearOutputMemoryFileTest() {
        try {
            File file = persistence.openFile("src/analysisMemoryTab.test");
            outputFileManager.clearOutputFile(file);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void clearOutputFileFailTest() {
        try {
            File file = persistence.openFile("src/pathNotFound.test");
            file.setWritable(false);
            Exception exception = assertThrows(EscritaNaoPermitidaException.class,
                    () -> outputFileManager.clearOutputFile(file));
            assertEquals(file.getPath() + " nao pode ser escrito", exception.getMessage());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void writeOutputTimeFileTest() {
        try {
            File file = persistence.openFile("src/analysisTimeTab.test");
            outputFileManager.writeOutputFile(file, "teste");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void writeOutputMemoryFileTest() {
        try {
            File file = persistence.openFile("src/analysisMemoryTab.test");
            outputFileManager.writeOutputFile(file, "teste");
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
                    () -> outputFileManager.writeOutputFile(file, "teste"));
            assertEquals(file.getPath() + " nao pode ser escrito", exception.getMessage());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}