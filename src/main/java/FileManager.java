import Exceptions.ArquivoNaoEncontradoException;
import java.io.File;

public class FileManager {
    File openFile(String path) throws Exception {
        try {
            File file = new File(path);

            if (!file.exists()) {
                throw new ArquivoNaoEncontradoException();
            }

            return file;
        } catch (Exception e) {
            throw new ArquivoNaoEncontradoException();
        }
    }
}
