import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MyDoublyLinkedList playlist = new MyDoublyLinkedList();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("=== MENU PLAYLISTU ===");
            System.out.println("1 Přidat song na konec");
            System.out.println("2 Vypsat názvy všech písní");
            System.out.println("3 Přidat song na začátek");
            System.out.println("4 Přehrát první song");
            System.out.println("5 Přehrát song na určité pozici");
            /*System.out.println("6 Přepni a přehraj na další song - vypiš název a stopáž");
            System.out.println("7 Přepni a přehraj na předchozí song - vypiš název a stopáž");*/
            System.out.println("8 Odeber první song");
            System.out.println("9 Odeber píseň z určité pozice");
            /*System.out.println("10 Přidej píseň na určitou pozici");
            System.out.println("11 Vymaž píseň s určitým názvem");
            System.out.println("12 Zahraj náhodnou píseň - vypiš název a stopáž");
            System.out.println("13 Vypiš název nejhranější písně tvého playlistu");
            System.out.println("14 Seřaď playlist – uživatel si může vybrat, zda chce řadit podle názvu, interpreta nebo stopáže");
            System.out.println("15 Exportuj seznam písní do souboru – můžeš si vybrat formát (txt, csv, xml, json)");
            System.out.println("16 Vyhledávání podle části názvu písně");*/
            System.out.println("0 Ukončit");

            String choice = scanner.nextLine().trim().toLowerCase();

            switch (choice) {
                case "1": {
                    Song song = createSongInput(scanner);
                    playlist.addLast(song);
                    System.out.println("Song byl úspěšně přidán na konec.");
                    break;
                }
                case "2":
                    playlist.printAllTitles();
                    break;
                case "3": {
                    Song song = createSongInput(scanner);
                    playlist.addFirst(song);
                    System.out.println("Song byl úspěšně přidán na začátek.");
                    break;
                }
                case "4":
                    playlist.playFirst();
                    break;
                case "5":
                    System.out.print("Zadej pozici (1, 2, 3...): ");
                    try {
                        int pos = Integer.parseInt(scanner.nextLine().trim());
                        playlist.playAt(pos);
                    } catch (NumberFormatException e) {
                        System.out.println("Zadaná hodnota musí být číslo.");
                    }
                    break;
                case "8":
                    playlist.removeFirst();
                    break;
                case "9":
                    System.out.println("Zadej index.");
                    int index = scanner.nextInt();
                    scanner.nextLine();
                    playlist.removeAt(index);
                    break;
                case "0":
                    running = false;
                    System.out.println("Aplikace ukončena.");
                    break;
                default:
                    System.out.println("Neplatná volba, zkuste to znovu.");
            }
        }

        scanner.close();
    }

    private static Song createSongInput(Scanner scanner) {
        System.out.print("Zadej název písně: ");
        String title = scanner.nextLine();
        System.out.print("Zadej interpreta: ");
        String artist = scanner.nextLine();
        System.out.print("Zadej délku stopáže: ");
        String duration = scanner.nextLine();
        return new Song(title, artist, duration);
    }
}