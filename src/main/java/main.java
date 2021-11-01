import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Persistence persistence = new Persistence();
        Scanner sc = new Scanner(System.in);

        System.out.println("Entre com o delimitador (Aceita apenas um caracter)");
        String delimiter = sc.nextLine();

        System.out.println("Entre com o formato da sequencia de valores (coluna ou linha)");
        String form = sc.nextLine();

        try {
            persistence.writeOutputFile("src/analysisTime.out", "src/analysisTimeTab.out", delimiter, form);
            persistence.writeOutputFile("src/analysisMemory.out", "src/analysisMemoryTab.out", delimiter, form);
        } catch (Exception e) {
            System.out.println("ERROR: ".concat(e.getMessage()));
        }
    }
}
