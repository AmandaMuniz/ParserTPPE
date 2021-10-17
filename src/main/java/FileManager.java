import Exceptions.ArquivoNaoEncontradoException;
import Exceptions.ErroDeLeituraException;

import java.io.File;
import java.util.ArrayList;

public class FileManager {
    File openFile(String path) throws Exception {
        try {
            File file = new File(path);

            if (!file.exists()) {
                throw new ArquivoNaoEncontradoException(path);
            }

            return file;
        } catch (Exception e) {
            throw new ArquivoNaoEncontradoException(path);
        }
    }

    ArrayList<ArrayList<Integer>> readFile(File file) throws Exception {
        if (file == null) {
            throw new ErroDeLeituraException("");
        }

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        ArrayList<ArrayList<Integer>> values = new ArrayList<>();
        values.add(list);

        return values;
    }
}
