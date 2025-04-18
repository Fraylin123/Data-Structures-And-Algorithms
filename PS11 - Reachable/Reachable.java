import java.util.*;
import java.io.*;

public class Reachable {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("web.txt"));
        String currLine;
        ArrayList<Integer> From = new ArrayList<Integer>();
        ArrayList<Integer> To = new ArrayList<Integer>();
        int max = 0;
        while ((currLine = br.readLine()) != null) {
            String[] split = currLine.trim().split("\\s+");
            int v = Integer.parseInt(split[0]);
            int w = Integer.parseInt(split[1]);
            if (v > max)
                max = v;
            if (w > max)
                max = w;
            From.add(v);
            To.add(w);
        }
        br.close();
        Graphl graph = new Graphl(max + 1);
        int n = max;
        for (int i = 0; i < From.size(); ++i) {
            graph.setEdge(From.get(i), To.get(i), 1);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Enter source and destination:");
            String[] split = reader.readLine().trim().split("\\s+");
            int s = Integer.parseInt(split[0]);
            int d = Integer.parseInt(split[1]);
            if (s < 0 || d < 0) {
                break;
            }
            boolean reachable = BFS(graph, s, d);
            if (reachable){
                System.out.println(d + " is reachable from " + s);
            }
            else{
                System.out.println(d + " is unreachable from " + s);
            }
        }
    }

    public static boolean BFS(Graphl G, int source, int dest) {
        boolean[] marked = new boolean[G.n()];
        Queue<Integer> queue = new LinkedList<Integer>();
        marked[source] = true;
        queue.add(source);

        while (!queue.isEmpty()) {
            int v = queue.poll();
            if (v == dest) {
                return true;
            }
            for (int w : G.neighbors(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    queue.add(w);
                }
            }
        }
        return false;
    }

}
