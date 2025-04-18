import java.util.LinkedList;
import java.util.Scanner;

public class MovieRating implements Comparable<MovieRating> {
    private int votes;
	private int rating;
	private String title;
	private int code;

	MovieRating(int v, int r, String t) {
                votes = v;
                rating = r;
                title = t;
	}

	public int getVotes() {
		return votes;
	}

	public float getRating() {
		return rating;
	}

	public String getTitle() {
		return title;
	}

	public String toString() {
		String s = Integer.toString(votes)+ "\t" +
                        Integer.toString(rating) + "\t" + title;
		return s;
	}

        public int compareTo(MovieRating m) {
          if(this.rating - m.rating < 0.0)
             return -1;  // for best movies
          else if(this.rating - m.rating > 0.0)
             return 1;
          else if(this.title.compareTo(m.title) > 0.0)
             return -1;
          else if(this.title.compareTo(m.title) < 0.0)
             return 1;
          else
             return 0;
        }
}
