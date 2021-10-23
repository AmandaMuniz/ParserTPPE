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

    void writeOutputFile(String inputPath, String outputPath, String form, String delimiter) throws Exception {
        File file = openFile(inputPath);

        ArrayList<ArrayList<Double>> evolutions = readInputFile(file);

        String formOutput = setOutput(form);
        Character charDelimiter = setDelimiter(delimiter);

        File outputFile = openFile(outputPath);

        int countEvolution = 1;

        if (formOutput.contains("linha")) {
            for (ArrayList<Double> evolution: evolutions) {
                int countMemory = 1;

                writeOutputFile(outputFile, String.valueOf(countEvolution));
                writeOutputFile(outputFile, charDelimiter.toString());

                for (Double memory: evolution) {
                    writeOutputFile(outputFile, memory.toString());

                    if (evolution.size() != countMemory) {
                        writeOutputFile(outputFile, charDelimiter.toString());
                    }

                    countMemory++;
                }

                writeOutputFile(outputFile, "\n");
                countEvolution++;
            }
        } else {
            int evolutionSize = evolutions.size();

            for (int j = 1; j <= evolutionSize; j++) {
                writeOutputFile(outputFile, String.valueOf(j));

                if (j != evolutionSize) {
                    writeOutputFile(outputFile, charDelimiter.toString());
                }
            }

            writeOutputFile(outputFile, "\n");

            for (int i = 0; i < evolutionSize - 1; i++) {
                for (int j = 0; j < evolutionSize - 1; j++) {
                    if (i < evolutions.get(j).size()) {
                        writeOutputFile(outputFile, evolutions.get(j).get(i).toString());
                        if (j != evolutionSize - 1) {
                            writeOutputFile(outputFile, charDelimiter.toString());
                        }
                    }
                }
                writeOutputFile(outputFile, "\n");
            }
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

    void writeOutputFile(File outputFile, String text) throws EscritaNaoPermitidaException {
        try {
            FileWriter fileWriter = new FileWriter(outputFile, true);

            fileWriter.write(text);
            fileWriter.close();
        } catch (Exception e) {
            throw new EscritaNaoPermitidaException(outputFile.getPath());
        }
    }

    Character setDelimiter(String delimiter) throws DelimitadorInvalidoException {
        if (delimiter.length() > 1) {
            throw new DelimitadorInvalidoException(delimiter);
        }

        return delimiter.charAt(0);
    }
}
