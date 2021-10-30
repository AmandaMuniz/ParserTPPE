import Exceptions.*;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Persistence {
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

    ArrayList<ArrayList<Double>> readInputFile(File file) throws Exception {
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

    void writeOutputFile(String inputPath, String outputPath, String delimiter, String form) throws Exception {
        File file = openFile(inputPath);
        File outputFile = openFile(outputPath);

        ArrayList<ArrayList<Double>> evolutions = readInputFile(file);
        String formOutput = setOutput(form);

        Character charDelimiter = setDelimiter(delimiter);

        OutputFileManager outputFileManager = new OutputFileManager(outputFile, evolutions, charDelimiter);

        if (formOutput.contains("linha")) {
            outputFileManager.writeOutputFileInLine();
        } else {
            outputFileManager.writeOutputFileInColumn();
        }


    }

    String setOutput(String format) throws Exception {
        try {
            if (format.contains("linha") || format.contains("coluna")) {
                return format;
            } else {
                throw new FormatoInvalidoException(format);
            }
        } catch (Exception e) {
            if (format != null) {
                throw new FormatoInvalidoException(format);
            } else {
                throw new FormatoInvalidoException("Null");
            }
        }
    }

    Character setDelimiter(String delimiter) throws DelimitadorInvalidoException {
        if (delimiter.length() > 1) {
            throw new DelimitadorInvalidoException(delimiter);
        }

        return delimiter.charAt(0);
    }
}
