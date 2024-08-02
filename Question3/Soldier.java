import java.util.*;

class Cell {
    int x, y;

    Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}

public class Soldier {
    private static final int BOARD_SIZE = 8;
    private Cell castle;
    private Set<Cell> soldiers;
    private List<List<Cell>> paths;

    public Soldier() {
        soldiers = new HashSet<>();
        paths = new ArrayList<>();
    }

    void addSoldier(int x, int y) {
        soldiers.add(new Cell(x, y));
    }

    void setCastle(int x, int y) {
        castle = new Cell(x, y);
    }

    void findPaths() {
        List<Cell> currentPath = new ArrayList<>();
        Set<Cell> visited = new HashSet<>();
        dfs(castle, visited, currentPath);
    }

    private void dfs(Cell current, Set<Cell> visited, List<Cell> currentPath) {
        if (current.equals(castle) && !currentPath.isEmpty()) {
            paths.add(new ArrayList<>(currentPath));
            return;
        }

        visited.add(current);

        for (int i = current.x + 1; i <= BOARD_SIZE; i++) {
            Cell next = new Cell(i, current.y);
            if (soldiers.contains(next) && !visited.contains(next)) {
                currentPath.add(next);
                dfs(turnLeft(next), visited, currentPath);
                currentPath.remove(currentPath.size() - 1);
            }
        }

        visited.remove(current);
    }

    private Cell turnLeft(Cell current) {
        for (int i = current.y - 1; i >= 1; i--) {
            Cell next = new Cell(current.x, i);
            if (soldiers.contains(next)) {
                return next;
            }
        }
        return new Cell(current.x, 1);
    }

    void printPaths() {
        System.out.println("Thanks. There are " + paths.size() + " unique paths for your ‘special_castle’");
        for (int i = 0; i < paths.size(); i++) {
            System.out.println("Path " + (i + 1) + ":");
            System.out.println("Start: " + castle);
            for (Cell cell : paths.get(i)) {
                System.out.println("Kill " + cell + ". Turn Left");
            }
            System.out.println("Arrive " + castle);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Soldier game = new Soldier();

        System.out.print("Enter the number of soldiers: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer.");
            scanner.nextLine(); 
        }
        int soldierCount = scanner.nextInt();

        for (int i = 1; i <= soldierCount; i++) {
            System.out.print("Enter coordinates for soldier " + i + ": ");
            boolean validInput = false;
            while (!validInput) {
                try {
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    game.addSoldier(x, y);
                    validInput = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter integer coordinates.");
                    scanner.nextLine(); 
                }
            }
        }

        System.out.print("Enter the coordinates for your “special” castle: ");
        boolean validCastleInput = false;
        while (!validCastleInput) {
            try {
                int castleX = scanner.nextInt();
                int castleY = scanner.nextInt();
                game.setCastle(castleX, castleY);
                validCastleInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter integer coordinates.");
                scanner.nextLine(); 
            }
        }

        game.findPaths();
        game.printPaths();

        scanner.close();
    }
}
