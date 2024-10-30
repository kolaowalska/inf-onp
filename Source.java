import java.util.Scanner;

public class Source {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int setNumber = scanner.nextInt();

        for (int i = 0; i < setNumber; i++) {
            String type = scanner.next();
            String phrase = scanner.nextLine().trim();
            if(type.equals("INF:")) System.out.println("ONP: " + Converter.INFtoONP(phrase));
            else if(type.equals("ONP:")) System.out.println("INF: " + Converter.ONPtoINF(phrase));
            else System.out.println("error");
        }
        scanner.close();
    }
}
