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
                searchCast();
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
        sortList(list);
        if (list.size() == 0) {
            System.out.println("No movies titles match that search term!");
        } else {
            int count = 1;
            for (String title : list) {
                System.out.println(count + ". " + title);
                count++;
            }
            knowMovie(list);
        }
    }

    private ArrayList<String> findTitles(String term) {
        ArrayList<String> list = new ArrayList<>();
        term = term.toLowerCase();
        for (int i = 0; i < moviesList.size(); i++) {
            String title = moviesList.get(i).getTitle();
            if (title.toLowerCase().contains(term)) {
                list.add(title);
            }
        }
        return list;
    }

    private void knowMovie(ArrayList<String> list) {
        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");
        int choice = scanner.nextInt() - 1;
        scanner.nextLine();
        System.out.println();
        for (int i = 0; i < moviesList.size(); i++) {
            if (moviesList.get(i).getTitle().equals(list.get(choice))) {
                System.out.println(moviesList.get(i));
                break;
            }
        }
    }

    private void searchCast() {
        System.out.print("Enter a person to search for (first or last name): ");
        String cast = scanner.nextLine();
        ArrayList<String> list = findCasts(cast);
        sortList(list);
        if (list.size() == 0) {
            System.out.println("No result match your search!");
        } else {
            int count = 1;
            for (String casts : list) {
                System.out.println(count + ". " + casts);
                count++;
            }
            ArrayList<String> movies = findMovies(list);
            sortList(movies);
            count = 1;
            for (String movie : movies) {
                System.out.println(count + ". " + movie);
                count++;
            }
            knowMovie(movies);
        }
    }

    private ArrayList<String> findCasts(String cast) {
        ArrayList<String> list = new ArrayList<>();
        cast = cast.toLowerCase();
        for (int i = 0; i < moviesList.size(); i++) {
            String casts = moviesList.get(i).getCasts();
            String[] splitCasts = casts.split("\\|");
            for (int f = 0; f < splitCasts.length; f++) {
                if (splitCasts[f].toLowerCase().contains(cast)) {
                    if (!list.contains(splitCasts[f])) {
                        list.add(splitCasts[f]);
                    }
                }
            }
        }
        return list;
    }

    private ArrayList<String> findMovies(ArrayList<String> castList) {
        System.out.println("Which would you like to see all movies for?");
        System.out.print("Enter Number: ");
        String cast = castList.get(scanner.nextInt() - 1);
        scanner.nextLine();
        System.out.println();
        ArrayList<String> movies = new ArrayList<>();
        for (int i = 0; i < moviesList.size(); i++) {
            if (moviesList.get(i).getCasts().contains(cast)) {
                movies.add(moviesList.get(i).getTitle());
            }
        }
        return movies;
    }

    private void sortList(ArrayList<String> list) {
        for (int i = 1; i < list.size(); i++) {
            String word = list.get(i);
            int index = i;
            while (index > 0 && word.compareTo(list.get(index - 1)) < 0) {
                list.set(index, list.set(index - 1, list.get(index)));
                index--;
            }
            list.set(index, word);
        }
    }

}
