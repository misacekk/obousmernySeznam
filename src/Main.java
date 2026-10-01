public class Main {
    public static void main(String[] args) {
        MyDoublyLinkedList playlist = new MyDoublyLinkedList();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== MENU PLAYLISTU ===");
            System.out.println("1 Přidat song na konec");
            System.out.println("2 Vypsat názvy všech písní");
            System.out.println("3 Přidat song na začátek");
            System.out.println("4 Přehrát první song");
            System.out.println("5 Přehrát song na určité pozici");
            System.out.println("6 Přepni a přehraj na další song");
            System.out.println("7 Přepni a přehraj na předchozí song");
            System.out.println("8 Odeber první song");
            System.out.println("9 Odeber píseň z určité pozice");
            System.out.println("10 Přidej píseň na určitou pozici");
            System.out.println("11 Vymaž píseň s určitým názvem");
            System.out.println("12 Zahraj náhodnou píseň");
            System.out.println("13 Vypiš název nejhranější písně tvého playlistu");
            System.out.println("14 Seřaď playlist (podle názvu, interpreta nebo stopáže)");
            System.out.println("15 Exportuj seznam písní do souboru (txt, csv, xml, json)");
            System.out.println("16 Vyhledávání podle části názvu písně");
            System.out.println("0 Ukončit");
            System.out.print("Vaše volba: ");

            String choice = scanner.nextLine().trim();

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

                case "6":
                    playlist.playNext();
                    break;

                case "7":
                    playlist.playPrevious();
                    break;

                case "8":
                    playlist.removeFirst();
                    break;

                case "9":
                    System.out.print("Zadej pozici pro odebrání (1, 2, 3...): ");
                    try {
                        int pos = Integer.parseInt(scanner.nextLine().trim());
                        playlist.removeAt(pos);
                    } catch (NumberFormatException e) {
                        System.out.println("Zadaná hodnota musí být číslo.");
                    }
                    break;

                case "10": {
                    System.out.print("Zadej pozici, na kterou chceš vkládat (1, 2, 3...): ");
                    try {
                        int pos = Integer.parseInt(scanner.nextLine().trim());
                        Song song = createSongInput(scanner);
                        playlist.addAt(pos, song);
                    } catch (NumberFormatException e) {
                        System.out.println("Zadaná hodnota musí být číslo.");
                    }
                    break;
                }
                case "11":
                    System.out.print("Zadej přesný název písně ke smazání: ");
                    String titleToRemove = scanner.nextLine();
                    playlist.removeByTitle(titleToRemove);
                    break;

                case "12":
                    playlist.playRandom();
                    break;

                case "13":
                    playlist.printMostPlayed();
                    break;

                case "14":
                    System.out.println("Podle čeho chceš řadit? (1 - Název, 2 - Interpret, 3 - Stopáž)");
                    System.out.print("Volba: ");
                    try {
                        int criteria = Integer.parseInt(scanner.nextLine().trim());
                        playlist.sortPlaylist(criteria);
                    } catch (NumberFormatException e) {
                        System.out.println("Zadaná hodnota musí být číslo.");
                    }
                    break;

                case "15":
                    System.out.print("Zadej požadovaný formát (txt, csv, xml, json): ");
                    String format = scanner.nextLine();
                    System.out.print("Zadej název výstupního souboru (např. muj_playlist): ");
                    String filename = scanner.nextLine();
                    playlist.exportToFile(format, filename);
                    break;

                case "16":
                    System.out.print("Zadej část názvu hledané písně: ");
                    String query = scanner.nextLine();
                    playlist.searchByTitlePart(query);
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
