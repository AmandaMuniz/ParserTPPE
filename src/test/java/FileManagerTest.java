import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FileManagerTest {
    FileManager fileManager = new FileManager();

    @Test
    void openFileTest() {
        try {
            fileManager.openFile();
        } catch (Exception e) {
            Assertions.fail("File don't open");
        }
    }
}
