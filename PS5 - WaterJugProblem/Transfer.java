import java.util.*;
public class Transfer{
    public static void main(String[] args){
        int[] input = new int[4];
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 4;i++){
            int next = scan.nextInt();
            input[i] = next;        
        }
        waterJugTransfer(input[0],input[1],input[2],input[3]);      
     
    }
    public static void waterJugTransfer(int fountain, int waterJug1, int waterJug2, int target){
        String result = new String();
        result += (fountain + " 0 0\n");
        boolean[][][] visited = new boolean[fountain+1][waterJug1+1][waterJug2+1];
        visited[fountain][0][0] = true; //Starting input
        State start = new State(fountain, 0,0);
        Deque<State> dq = new LinkedList<>();
        dq.add(start); //The initial state is added

        int swap = 0;
        int count = 0;
        while (dq.getLast().water != target && dq.getLast().jug1 != target && dq.getLast().jug2 != target ){
            if (swap == 0){
                if (count == 3){
                    return;
                }
                swap = 1;
                count ++;
                int pourAmount = Math.min(dq.getLast().water, waterJug1 - dq.getLast().jug1);

                State newState = new State(dq.getLast().water - pourAmount, dq.getLast().jug1 + pourAmount, dq.getLast().jug2);
                if (visited[newState.water][newState.jug1][newState.jug2] == false){
                    visited[newState.water][newState.jug1][newState.jug2] = true;
                    dq.add(newState);
                    result += (dq.getLast().water + " " + dq.getLast().jug1 + " " + dq.getLast().jug2 + "\n");
                    count = 0;
                    continue;
                }

            }
            if (swap == 1){
                if (count == 3){
                    return;
                }
                swap = 2;
                count++;
                int pourAmount = Math.min(dq.getLast().jug1,waterJug2 - dq.getLast().jug2);

                State newState = new State(dq.getLast().water, dq.getLast().jug1 - pourAmount, dq.getLast().jug2 + pourAmount);
                if (visited[newState.water][newState.jug1][newState.jug2] == false){
                    visited[newState.water][newState.jug1][newState.jug2] = true;
                    dq.add(newState);
                    result += (dq.getLast().water + " " + dq.getLast().jug1 + " " + dq.getLast().jug2 + "\n");
                    count = 0;
                    continue;
                }

            }
            if (swap == 2){
                if (count == 3){
                    return;
                }
                swap = 0;
                count++;
                int pourAmount = Math.min(dq.getLast().jug2,fountain - dq.getLast().water);

                State newState = new State(dq.getLast().water + pourAmount, dq.getLast().jug1, dq.getLast().jug2 - pourAmount);
                if (visited[newState.water][newState.jug1][newState.jug2] == false){
                    visited[newState.water][newState.jug1][newState.jug2] = true;
                    dq.add(newState);
                    result += (dq.getLast().water + " " + dq.getLast().jug1 + " " + dq.getLast().jug2 + "\n");
                    count = 0;
                    continue;
                }

            }
  
        }
        System.out.println(result.substring(0, result.length()-1));
    }
          

}


