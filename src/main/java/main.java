import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    static void parseMemoryFile(String delimiter, String form) throws Exception {
        FileManager fileManager = new FileManager();

        File file = fileManager.openFile("src/analysisMemory.out");

        ArrayList<ArrayList<Double>> evolutions = fileManager.readFile(file);

        String formOutput = fileManager.setOutput(form);
        Character charDelimiter = fileManager.setDelimiter(delimiter);

        File outputFile = fileManager.openFile("src/analysisMemoryTab.out");

        int countEvolution = 1;

        if (formOutput.contains("linha")) {
            for (ArrayList<Double> evolution: evolutions) {
                int countMemory = 1;

                fileManager.writeOutputFile(outputFile, String.valueOf(countEvolution));
                fileManager.writeOutputFile(outputFile, charDelimiter.toString());

                for (Double memory: evolution) {
                    fileManager.writeOutputFile(outputFile, memory.toString());

                    if (evolution.size() != countMemory) {
                        fileManager.writeOutputFile(outputFile, charDelimiter.toString());
                    }

                    countMemory++;
                }

                fileManager.writeOutputFile(outputFile, "\n");
                countEvolution++;
            }
        } else {
            int evolutionSize = evolutions.size();

            for (int j = 1; j <= evolutionSize; j++) {
                fileManager.writeOutputFile(outputFile, String.valueOf(j));

                if (j != evolutionSize) {
                    fileManager.writeOutputFile(outputFile, charDelimiter.toString());
                }
            }

            fileManager.writeOutputFile(outputFile, "\n");

            for (int i = 0; i < evolutionSize - 1; i++) {
                for (int j = 0; j < evolutionSize - 1; j++) {
                    if (i < evolutions.get(j).size()) {
                        fileManager.writeOutputFile(outputFile, evolutions.get(j).get(i).toString());
                        if (j != evolutionSize - 1) {
                            fileManager.writeOutputFile(outputFile, charDelimiter.toString());
                        }
                    }
                }
                fileManager.writeOutputFile(outputFile, "\n");
            }
        }
    }

    static void parseTimeFile(String delimiter, String form) throws Exception {
        FileManager fileManager = new FileManager();

        File file = fileManager.openFile("src/analysisTime.out");

        ArrayList<ArrayList<Double>> evolutions = fileManager.readFile(file);

        String formOutput = fileManager.setOutput(form);
        Character charDelimiter = fileManager.setDelimiter(delimiter);

        File outputFile = fileManager.openFile("src/analysisTimeTab.out");

        int countEvolution = 1;

        if (formOutput.contains("linha")) {
            for (ArrayList<Double> evolution: evolutions) {
                int countMemory = 1;

                fileManager.writeOutputFile(outputFile, String.valueOf(countEvolution));
                fileManager.writeOutputFile(outputFile, charDelimiter.toString());

                for (Double memory: evolution) {
                    fileManager.writeOutputFile(outputFile, memory.toString());

                    if (evolution.size() != countMemory) {
                        fileManager.writeOutputFile(outputFile, charDelimiter.toString());
                    }

                    countMemory++;
                }

                fileManager.writeOutputFile(outputFile, "\n");
                countEvolution++;
            }
        } else {
            int evolutionSize = evolutions.size();

            for (int j = 1; j <= evolutionSize; j++) {
                fileManager.writeOutputFile(outputFile, String.valueOf(j));

                if (j != evolutionSize) {
                    fileManager.writeOutputFile(outputFile, charDelimiter.toString());
                }
            }

            fileManager.writeOutputFile(outputFile, "\n");

            for (int i = 0; i < evolutionSize - 1; i++) {
                for (int j = 0; j < evolutionSize - 1; j++) {
                    if (i < evolutions.get(j).size()) {
                        fileManager.writeOutputFile(outputFile, evolutions.get(j).get(i).toString());
                        if (j != evolutionSize - 1) {
                            fileManager.writeOutputFile(outputFile, charDelimiter.toString());
                        }
                    }
                }
                fileManager.writeOutputFile(outputFile, "\n");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Entre com o delimitador (Aceita apenas um caracter)");
        String delimiter = sc.nextLine();

        System.out.println("Entre com o formato da sequencia de valores (coluna ou linha)");
        String form = sc.nextLine();

        try {
            parseMemoryFile(delimiter, form);
            parseTimeFile(delimiter, form);
        } catch (Exception e) {
            System.out.println("ERROR: ".concat(e.getMessage()));
        }
    }
}
