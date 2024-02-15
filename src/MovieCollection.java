import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection {
    private ArrayList<Movie> moviesList;
    private Scanner scanner;
    public MovieCollection() {
        moviesList = new ArrayList<>();
        scanner = new Scanner(System.in);
        readData();
        menu();
    }

    private void readData() {
        try {
            File myfile = new File("src\\movies_data.csv");
            Scanner fileScanner = new Scanner (myfile);
            fileScanner.nextLine();
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                Movie movie = new Movie(splitData[0], splitData[1], splitData[2], splitData[3], Integer.parseInt(splitData[4]), Double.parseDouble(splitData[5]));
                moviesList.add(movie);
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void menu() {
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
       /*          searchCast(); */
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    private void searchTitles() {
        System.out.print("Enter a title search term: ");
        String term = scanner.nextLine();
        ArrayList<String> list = findTitles(term);
        if (list.size() == 0) {
            System.out.println("No movies titles match that search term!");
        } else {
            for (String title : list) {
                System.out.println(title);
            }
            knowMovie(list);
        }
    }

    private ArrayList<String> findTitles(String term) {
        ArrayList<String> list = new ArrayList<>();
        term = term.toLowerCase();
        int count = 1;
        for (int i = 0; i < moviesList.size(); i++) {
            String title = moviesList.get(i).getTitle();
            if (title.toLowerCase().contains(term)) {
                list.add(count + ". " +title);
                count++;
            }
        }
        return list;
    }

    private void knowMovie( ArrayList<String> list) {
        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");
        int choice = scanner.nextInt() - 1;
        scanner.nextLine();
        System.out.println();
        for (int i = 0; i < moviesList.size(); i++) {
            if (moviesList.get(i).getTitle().equals(list.get(choice).substring(3))) {
                System.out.println(moviesList.get(i));
                break;
            }
        }
    }

    private void searchCast() {
        System.out.print("Enter a person to search for (first or last name): ");
        String cast = scanner.nextLine();
        ArrayList<String> list = findCasts(cast);
    }

    private ArrayList<String> findCasts(String cast) {

    }
}
