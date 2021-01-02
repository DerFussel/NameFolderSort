import java.util.Scanner;


class Sort {
    public static void main(String[] args) {
        System.out.println("\nHello, this is my little program to sort files (espcially pictures) whose original Location is in diffrent folders.\n");
        System.out.println("Please hand me the folder to sort");
        String path = getPath();
        System.out.println("Now the folders to sort by/to.");
        String[] toSort = getToSort();
        for (int i = 0; i < 10; i++) {
            System.out.println(toSort[i]);
        }
    }
    private static String getPath() {
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        System.out.println(in);
        return in;
    }
    private static String[] getToSort() {
        String[] in = new String[10];
        for (int i = 0; i < 10; i++) {
            in[i] = "ahri";
        }
        return in;
    }
}