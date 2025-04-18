/** Class for comparing words based on their sorted versions.
*/

public class StringPair implements Comparable<StringPair> {
	private String word;
	private String sortedWord;

	public StringPair(String wd) {
		word = wd;
		char[] ca = wd.toCharArray();
                // sort ca using insertion sort
                for (int i = 1; i < ca.length; i++)
                  for (int j = i; (j > 0) && (ca[j] < ca[j - 1]); j--) {
                    char tmp = ca[j];
                    ca[j] = ca[j-1];
                    ca[j-1] = tmp;
                  }
		sortedWord = new String(ca);
	}

	
	public String getSorted(){
		return sortedWord;
	}
	
	public String getUnsorted(){
		return word;
	}

        public int compareTo(StringPair wp){
                return sortedWord.compareTo(wp.sortedWord);
        }

        public String toString(){
                //return sortedWord+" "+word;
                return sortedWord;
        }
}
