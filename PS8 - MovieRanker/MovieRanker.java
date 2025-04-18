import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collections;
import java.io.BufferedReader;

/* Starter code for PS8.
 */

public class MovieRanker {

	public static void main(String[] args) {
                File file = new File("ratings.tsv");

		ArrayList<MovieRating> movies = new ArrayList<MovieRating>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
                String[] tkns = line.split("\\t"); // tabs separate tokens
                MovieRating movie = new MovieRating(Integer.parseInt(tkns[0]), Integer.parseInt(tkns[1]), tkns[2]);
				movies.add(movie);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		int minVotes = 1;
		int numRecords = 1;
		Scanner input = new Scanner(System.in);
		while (true) {
			System.out.println();
			System.out.println("Enter minimum vote threshold and number of records:");
			minVotes = input.nextInt();
			numRecords = input.nextInt();
			if (minVotes * numRecords == 0)
				break;
			long startTime = System.currentTimeMillis();

/* Fill in code to determine the top numRecords movies that have at least
 * minVotes votes. For each record mr, in decreasing order of average rating,
 * execute a System.out.println(mr).
 * Do not sort the movie list!
 */

			MinHeap<MovieRating> topMovies = new MinHeap<MovieRating>();
			
			for (MovieRating movie: movies){
				if (topMovies.heapsize() != numRecords && movie.getVotes() >= minVotes){
						topMovies.insert(movie);
				}
				if (topMovies.heapsize() == numRecords && movie.getVotes() >= minVotes && movie.getRating() >= topMovies.peek().getRating()){
					topMovies.removemax(); //Removes minimum of minHeap
					topMovies.insert(movie);     
				}
					
			}

			ArrayList<MovieRating> topMoviesArray = new ArrayList<MovieRating>();

			for (int i = 0; i < numRecords; i++){
				topMoviesArray.add(topMovies.removemax());
			}
			Collections.reverse(topMoviesArray);

			for (int i = 0; i < topMoviesArray.size(); i++){
				System.out.println(topMoviesArray.get(i));
			}
			
			

			System.out.println();			
			System.out.println("Time: "+(System.currentTimeMillis()-startTime)+" ms");
		}
	}
}
