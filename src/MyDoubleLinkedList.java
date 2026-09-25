class MyDoublyLinkedList {
    private Node head;
    private Node tail;

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

    public void removeFirst() {
        if (head == null) {
            return;
        }
        head = head.getNext();
        if (head == null) {
            tail = null;
            return;
        }
        head.setPrev(null);
        head.setNext(null);
    }

    public void removeAt(int index) {
        if (head == null) {
            System.out.println("Seznam je prázdný.");
            return;
        }
        if (index < 0) {
            System.out.println("Index nemůže být záporný.");
            return;
        }

        if (index == 0) {
            head = head.getNext();
            if (head == null) {
                tail = null;
            } else {
                head.setPrev(null);
            }
            return;
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            if (current == null) {
                System.out.println("Index je mimo rozsah.");
                return;
            }
            current = current.getNext();
        }

        if (current == null) {
            System.out.println("Index je mimo rozsah.");
            return;
        }

        if (current.getPrev() != null) {
            current.getPrev().setNext(current.getNext());
        }

        if (current.getNext() != null) {
            current.getNext().setPrev(current.getPrev());
        } else {
            tail = current.getPrev();
        }
    }

    public void printAllTitles() {
        if (head == null) {
            System.out.println("Playlist je prázdný.");
            return;
        }
        System.out.println("\n--- Názvy písní v playlistu ---");
        Node current = head;
        int index = 1;
        while (current != null) {
            System.out.println(index + ". " + current.getData().getTitle());
            current = current.getNext();
            index++;
        }
    }

    public void playFirst() {
        if (head == null) {
            System.out.println("Playlist je prázdný.");
            return;
        }
        Song song = head.getData();
        System.out.println("Přehrávám první song: " + song.getTitle() + " [Stopáž: " + song.getDuration() + "]");
    }

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
            Song song = current.getData();
            System.out.println("Přehrávám song na pozici " + position + ": " + song.getTitle() + " [Stopáž: " + song.getDuration() + "]");
        }
    }
}