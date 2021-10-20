import Exceptions.ArquivoNaoEncontradoException;
import Exceptions.ErroDeLeituraException;
import Exceptions.FormatoInvalidoException;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

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

    ArrayList<ArrayList<Double>> readFile(File file) throws Exception {
        try {
            Scanner scanner = new Scanner(file);
            ArrayList<ArrayList<Double>> values = new ArrayList<>();
            ArrayList<Double> listNumbers = new ArrayList<>();

            if (!scanner.hasNextLine()) {
                throw new ErroDeLeituraException(file.getPath());
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.contains("Evolution")) {
                    listNumbers = new ArrayList<>();
                    values.add(listNumbers);
                    line = scanner.nextLine();
                }

                listNumbers.add(Double.valueOf(line));
            }

            return values;
        } catch (Exception e) {
            if (file != null) {
                throw new ErroDeLeituraException(file.getPath());
            } else {
                throw new ErroDeLeituraException("Arquivo null");
            }
        }
    }

    String setOutput(String format) throws Exception {
        if (format == null) {
            throw new FormatoInvalidoException("Null");
        }
        if (format == "linha") {
            return format;
        }
        if (format == "coluna") {
            return format;
        } else {
            throw new FormatoInvalidoException(format);
        }
    }
}
