import Exceptions.EscritaNaoPermitidaException;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class OutputFileManager {

    File outputFile;
    ArrayList<ArrayList<Double>> evolutions;
    Character charDelimiter;

    OutputFileManager(
            File outputFile,
            ArrayList<ArrayList<Double>> evolutions,
            Character charDelimiter
    ) {
        this.outputFile = outputFile;
        this.evolutions = evolutions;
        this.charDelimiter = charDelimiter;
    };

    OutputFileManager(){};

    void clearOutputFile(File file) throws EscritaNaoPermitidaException {
        try {
            PrintWriter fileWriter = new PrintWriter(file);
            fileWriter.print("");
            fileWriter.close();
        } catch (Exception e) {
            throw new EscritaNaoPermitidaException(file.getPath());
        }
    }

    void writeOutputFileInLine() throws Exception {
        int countEvolutions = 1;

        for (ArrayList<Double> evolution: this.evolutions) {
            int countValues = 1;

            writeOutputFile(this.outputFile, String.valueOf(countEvolutions));
            writeOutputFile(this.outputFile, this.charDelimiter.toString());

            for (Double value : evolution) {
                writeOutputFile(this.outputFile, value.toString());

                if (evolution.size() != countValues) {
                    writeOutputFile(this.outputFile, this.charDelimiter.toString());
                }

                countValues++;
            }

            writeOutputFile(this.outputFile, "\n");
            countEvolutions++;
        }
    }

    void writeOutputFileInColumn() throws Exception {
        int evolutionSize = this.evolutions.size();

        for (int j = 1; j <= evolutionSize; j++) {
            writeOutputFile(this.outputFile, String.valueOf(j));

            if (j != evolutionSize) {
                writeOutputFile(this.outputFile, this.charDelimiter.toString());
            }
        }

        writeOutputFile(this.outputFile, "\n");

        for (int i = 0; i < evolutionSize - 1; i++) {
            for (int j = 0; j < evolutionSize - 1; j++) {
                if (i < this.evolutions.get(j).size()) {
                    writeOutputFile(this.outputFile, this.evolutions.get(j).get(i).toString());
                    if (j != evolutionSize - 1) {
                        writeOutputFile(this.outputFile, this.charDelimiter.toString());
                    }
                }
            }
            writeOutputFile(this.outputFile, "\n");
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
}
