import java.util.LinkedList;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
//import java.util.Collections;

public class WebGraph {
    public static void main(String[] args) {

        int n;
        long seed = 0;
        if(args.length != 1) {
            System.out.println("Must supply a random number seed.");
            System.exit(0);
        } else seed = Long.parseLong(args[0]);
        Random rng = new Random(seed);


        ArrayList<Integer> From = new ArrayList<Integer>();
        ArrayList<Integer> To = new ArrayList<Integer>();
        int max = 0;
        File file = new File("web.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextInt()) {
                //String line = scanner.nextLine();
                //int inc = Integer.parseInt(line);
                //AA.add(inc);
                int src = scanner.nextInt();
                int dst = scanner.nextInt();
                if(src>max) max=src;
                if(dst>max) max=dst;
                From.add(src);
                To.add(dst);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Lengths: "+From.size()+" "+To.size());
        System.out.println("max: "+max);
        Graphl graph = new Graphl(max+1);

        n = max;
        for(int i=0; i<From.size(); ++i) {
            graph.setEdge(From.get(i), To.get(i),1);
        }
        System.out.println("Built graph");

        // use DFS to determine reachability
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Enter source and destination:");
            int s = input.nextInt();
            int d = input.nextInt();
            if(s+d==0) break;
            DFS(graph, s);
            if(graph.getMark(d)>0)
                System.out.println(s+", "+d+" are connected");
            else
                System.out.println(s+", "+d+" are not connected");
        }
        input.close();

System.exit(1);
// use BFS to identify connected components
int[] component = new int[n];
for(int i=0; i<n; ++i) component[i] = 0;
int count = 1;
long start = System.currentTimeMillis();
for(int source = 0; source < n; ++source) {
    //for(int source = 0; source < 1000;++source){
    if((source % 1000)==0) System.out.println("processing source "+source);
    if(component[source]>0) continue;
    for(int i=0; i<n; ++i) graph.setMark(i,0);
    BFS(graph, source);
    for(int i=0; i<n; ++i) if(graph.getMark(i)>0) component[i] = count;
    ++count;
}
long end = System.currentTimeMillis();
System.out.println("Time: "+(end-start));


System.out.println("Graph has "+(count-1)+" connected components.");


// Compute matrix of distances between pairs of vertices.
// If dest is not reachable from source, set dist = -1.
int[][] dist = new int[n][n];
for(int source = 0; source < n; ++source) {
    for(int i=0; i<n; ++i) graph.setMark(i,0);
    BFS(graph, source);
    for(int dest = 0; dest < n; ++dest) {
        if(graph.getMark(dest)>0)
            dist[source][dest] = graph.getMark(dest)-1;
        else
            dist[source][dest] = -1;
    }
}

System.out.println("Distances (x means unreachable):");
for(int i=0; i<n; ++i) {
    for(int j=0; j<n; ++j) {
        if(dist[i][j] >= 0)
            System.out.print(dist[i][j]+" ");
        else
            System.out.print("x ");
    }
    System.out.println();
}
}


/** Depth first search */
static void DFS(Graph G, int v) {
    PreVisit(G, v); // Take appropriate action
    G.setMark(v, 1);
    for (int w = G.first(v); w < G.n(); w = G.next(v, w))
        if (G.getMark(w) == 0)
            DFS(G, w);
    PostVisit(G, v); // Take appropriate action
}

/** Breadth first (queue-based) search */
static void BFS(Graph G, int start) {
    LinkedList<Integer> Q = new LinkedList<Integer>();
    Q.addLast(start);
    G.setMark(start, 1);
    int d = 0;
    while (Q.size() > 0) { // Process each vertex on Q
        ++d;
        int v = Q.removeFirst();
        PreVisit(G, v); // Take appropriate action
        for (int w = G.first(v); w < G.n(); w = G.next(v, w))
            if (G.getMark(w) == 0) { // Put neighbors on Q
                G.setMark(w, G.getMark(v) + 1);
                Q.addLast(w);
            }
        PostVisit(G, v); // Take appropriate action
    }
}

static void PreVisit(Graph G, int v) {
    //System.out.println("");
}

static void PostVisit(Graph G, int v) {
    //System.out.println("");
}
}
