class MyDoublyLinkedList {
    private Node head;
    private Node tail;
    private Node currentPlaying; // Uchovává aktuálně přehrávanou píseň pro volby 6 a 7

    // Pomocná metoda pro přehrání písně a inkrementaci počtu přehrání
    private void playSongNode(Node node) {
        if (node == null) return;
        currentPlaying = node;
        Song song = node.getData();
        song.incrementPlayCount();
        System.out.println("Přehrávám: " + song.getTitle() + " - " + song.getArtist() + " [Stopáž: " + song.getDuration() + "]");
    }

    // a. Přidej song na konec
    public void addLast(Song song) {
        Node newNode = new Node(song);
        if (tail == null) {
            head = newNode;
            tail = newNode;
            return;
        }
        tail.setNext(newNode);
        newNode.setPrev(tail);
        tail = newNode;
    }

    // b. Vypiš názvy všech písní
    public void printAllTitles() {
        if (head == null) {
            System.out.println("Playlist je prázdný.");
            return;
        }
        System.out.println("--- Názvy písní v playlistu ---");
        Node current = head;
        int index = 1;
        while (current != null) {
            Song song = current.getData();
            System.out.println(index + ". " + song.getTitle() + " - " + song.getArtist() + " [" + song.getDuration() + "]");
            current = current.getNext();
            index++;
        }
    }

    // c. Přidej song na začátek
    public void addFirst(Song song) {
        Node newNode = new Node(song);
        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }
        newNode.setNext(head);
        head.setPrev(newNode);
        head = newNode;
    }

    // d. Přehraj první song
    public void playFirst() {
        if (head == null) {
            System.out.println("Playlist je prázdný.");
            return;
        }
        playSongNode(head);
    }

    // e. Přehraj song na určité pozici (1-based)
    public void playAt(int position) {
        if (head == null) {
            System.out.println("Playlist je prázdný.");
            return;
        }
        if (position < 1) {
            System.out.println("Pozice musí být číslo 1 nebo vyšší.");
            return;
        }

        Node current = head;
        int currentPos = 1;
        while (current != null && currentPos < position) {
            current = current.getNext();
            currentPos++;
        }

        if (current == null) {
            System.out.println("Píseň na pozici " + position + " neexistuje.");
        } else {
            playSongNode(current);
        }
    }

    // f. Přepni a přehraj na další song
    public void playNext() {
        if (head == null) {
            System.out.println("Playlist je prázdný.");
            return;
        }
        if (currentPlaying == null) {
            playSongNode(head);
            return;
        }
        if (currentPlaying.getNext() == null) {
            System.out.println("Jste na konci playlistu. Žádný další song tu není.");
        } else {
            playSongNode(currentPlaying.getNext());
        }
    }

    // g. Přepni a přehraj na předchozí song
    public void playPrevious() {
        if (head == null) {
            System.out.println("Playlist je prázdný.");
            return;
        }
        if (currentPlaying == null) {
            playSongNode(head);
            return;
        }
        if (currentPlaying.getPrev() == null) {
            System.out.println("Jste na začátku playlistu. Žádný předchozí song tu není.");
        } else {
            playSongNode(currentPlaying.getPrev());
        }
    }

    // h. Odeber první song
    public void removeFirst() {
        if (head == null) {
            System.out.println("Playlist je prázdný.");
            return;
        }
        if (currentPlaying == head) {
            currentPlaying = head.getNext();
        }
        head = head.getNext();
        if (head == null) {
            tail = null;
        } else {
            head.setPrev(null);
        }
        System.out.println("První song byl úspěšně odebrán.");
    }

    // i. Odeber píseň z určité pozice (1-based)
    public void removeAt(int position) {
        if (head == null) {
            System.out.println("Playlist je prázdný.");
            return;
        }
        if (position < 1) {
            System.out.println("Pozice musí být číslo 1 nebo vyšší.");
            return;
        }
        if (position == 1) {
            removeFirst();
            return;
        }

        Node current = head;
        int currentPos = 1;
        while (current != null && currentPos < position) {
            current = current.getNext();
            currentPos++;
        }

        if (current == null) {
            System.out.println("Píseň na pozici " + position + " neexistuje.");
            return;
        }

        if (current == currentPlaying) {
            currentPlaying = null;
        }

        if (current.getPrev() != null) {
            current.getPrev().setNext(current.getNext());
        }
        if (current.getNext() != null) {
            current.getNext().setPrev(current.getPrev());
        } else {
            tail = current.getPrev();
        }
        System.out.println("Song na pozici " + position + " byl odebrán.");
    }

    // j. Přidej píseň na určitou pozici (1-based)
    public void addAt(int position, Song song) {
        if (position <= 1 || head == null) {
            addFirst(song);
            System.out.println("Song byl úspěšně přidán na pozici 1.");
            return;
        }

        Node current = head;
        int currentPos = 1;
        while (current.getNext() != null && currentPos < position - 1) {
            current = current.getNext();
            currentPos++;
        }

        if (current.getNext() == null) {
            addLast(song);
            System.out.println("Pozice byla vyšší než počet prvků, song byl přidán na konec.");
        } else {
            Node newNode = new Node(song);
            newNode.setNext(current.getNext());
            newNode.setPrev(current);
            current.getNext().setPrev(newNode);
            current.setNext(newNode);
            System.out.println("Song byl úspěšně přidán na pozici " + position + ".");
        }
    }

    // k. Vymaž píseň s určitým názvem
    public void removeByTitle(String title) {
        if (head == null) {
            System.out.println("Playlist je prázdný.");
            return;
        }

        Node current = head;
        while (current != null) {
            if (current.getData().getTitle().equalsIgnoreCase(title.trim())) {
                if (current == head) {
                    removeFirst();
                } else {
                    if (current == currentPlaying) {
                        currentPlaying = null;
                    }
                    if (current.getPrev() != null) {
                        current.getPrev().setNext(current.getNext());
                    }
                    if (current.getNext() != null) {
                        current.getNext().setPrev(current.getPrev());
                    } else {
                        tail = current.getPrev();
                    }
                    System.out.println("Píseň '" + title + "' byla úspěšně odebrána.");
                }
                return;
            }
            current = current.getNext();
        }
        System.out.println("Píseň s názvem '" + title + "' nebyla nalezena.");
    }

    // l. Zahraj náhodnou píseň
    public void playRandom() {
        if (head == null) {
            System.out.println("Playlist je prázdný.");
            return;
        }
        int size = 0;
        Node current = head;
        while (current != null) {
            size++;
            current = current.getNext();
        }

        Random random = new Random();
        int randomIndex = random.nextInt(size);

        current = head;
        for (int i = 0; i < randomIndex; i++) {
            current = current.getNext();
        }
        System.out.print("[Náhodný výběr] ");
        playSongNode(current);
    }

    // m. Vypiš název nejhranější písně tvého playlistu
    public void printMostPlayed() {
        if (head == null) {
            System.out.println("Playlist je prázdný.");
            return;
        }

        Node current = head;
        Node maxPlayedNode = null;
        int maxPlays = 0;

        while (current != null) {
            if (current.getData().getPlayCount() > maxPlays) {
                maxPlays = current.getData().getPlayCount();
                maxPlayedNode = current;
            }
            current = current.getNext();
        }

        if (maxPlayedNode == null || maxPlays == 0) {
            System.out.println("Zatím nebyla přehrána žádná píseň.");
        } else {
            Song song = maxPlayedNode.getData();
            System.out.println("Nejhranější píseň: " + song.getTitle() + " od " + song.getArtist() + " (přehráno " + maxPlays + "x)");
        }
    }

    // n. Seřaď playlist – 1: podle názvu, 2: podle interpreta, 3: podle stopáže
    public void sortPlaylist(int criteria) {
        if (head == null || head.getNext() == null) {
            System.out.println("Playlist má méně než 2 písně, není co řadit.");
            return;
        }

        boolean swapped;
        do {
            swapped = false;
            Node current = head;
            while (current.getNext() != null) {
                Song s1 = current.getData();
                Song s2 = current.getNext().getData();
                boolean shouldSwap = false;

                switch (criteria) {
                    case 1: // Podle názvu
                        shouldSwap = s1.getTitle().compareToIgnoreCase(s2.getTitle()) > 0;
                        break;
                    case 2: // Podle interpreta
                        shouldSwap = s1.getArtist().compareToIgnoreCase(s2.getArtist()) > 0;
                        break;
                    case 3: // Podle stopáže
                        shouldSwap = s1.getDuration().compareToIgnoreCase(s2.getDuration()) > 0;
                        break;
                    default:
                        System.out.println("Neplatný kritérium řazení.");
                        return;
                }

                if (shouldSwap) {
                    current.setData(s2);
                    current.getNext().setData(s1);
                    swapped = true;
                }
                current = current.getNext();
            }
        } while (swapped);

        System.out.println("Playlist byl úspěšně seřazen.");
    }

    // o. Exportuj seznam písní do souboru (txt, csv, xml, json)
    public void exportToFile(String format, String filename) {
        if (head == null) {
            System.out.println("Playlist je prázdný, nelze exportovat.");
            return;
        }

        format = format.toLowerCase().trim();
        String fullFilename = filename.toLowerCase().endsWith("." + format) ? filename : filename + "." + format;

        try (PrintWriter writer = new PrintWriter(new FileWriter(fullFilename))) {
            Node current = head;

            switch (format) {
                case "txt":
                    int idx = 1;
                    while (current != null) {
                        Song s = current.getData();
                        writer.println(idx + ". " + s.getTitle() + " - " + s.getArtist() + " [" + s.getDuration() + "]");
                        current = current.getNext();
                        idx++;
                    }
                    break;

                case "csv":
                    writer.println("Title,Artist,Duration");
                    while (current != null) {
                        Song s = current.getData();
                        writer.println("\"" + s.getTitle() + "\",\"" + s.getArtist() + "\",\"" + s.getDuration() + "\"");
                        current = current.getNext();
                    }
                    break;

                case "xml":
                    writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                    writer.println("<playlist>");
                    while (current != null) {
                        Song s = current.getData();
                        writer.println("  <song>");
                        writer.println("    <title>" + s.getTitle() + "</title>");
                        writer.println("    <artist>" + s.getArtist() + "</artist>");
                        writer.println("    <duration>" + s.getDuration() + "</duration>");
                        writer.println("  </song>");
                        current = current.getNext();
                    }
                    writer.println("</playlist>");
                    break;

                case "json":
                    writer.println("[");
                    while (current != null) {
                        Song s = current.getData();
                        writer.print("  {\n    \"title\": \"" + s.getTitle() + "\",\n    \"artist\": \"" + s.getArtist() + "\",\n    \"duration\": \"" + s.getDuration() + "\"\n  }");
                        if (current.getNext() != null) {
                            writer.println(",");
                        } else {
                            writer.println();
                        }
                        current = current.getNext();
                    }
                    writer.println("]");
                    break;

                default:
                    System.out.println("Nepodporovaný formát. Zvolte txt, csv, xml nebo json.");
                    return;
            }

            System.out.println("Playlist byl úspěšně exportován do souboru: " + fullFilename);
        } catch (IOException e) {
            System.out.println("Chyba při zápisu do souboru: " + e.getMessage());
        }
    }

    // p. Vyhledávání podle části názvu písně
    public void searchByTitlePart(String query) {
        if (head == null) {
            System.out.println("Playlist je prázdný.");
            return;
        }

        Node current = head;
        int index = 1;
        boolean found = false;
        System.out.println("--- Výsledky vyhledávání pro výraz: '" + query + "' ---");

        while (current != null) {
            if (current.getData().getTitle().toLowerCase().contains(query.toLowerCase().trim())) {
                Song s = current.getData();
                System.out.println(index + ". " + s.getTitle() + " - " + s.getArtist() + " [" + s.getDuration() + "]");
                found = true;
            }
            current = current.getNext();
            index++;
        }

        if (!found) {
            System.out.println("Nebyly nalezeny žádné odpovídající písně.");
        }
    }
}
