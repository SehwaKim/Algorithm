import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DFS01 {
    private static int[][] map;
    private static List<Integer> list = new ArrayList<>();
    private static int dfsCnt = 0;

    private final static int VISITED = 2;
    private final static int UNVISITED = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        map = new int[size][size];

        for(int i=0;i<size;i++){
            String numStr = br.readLine();
            for(int j=0;j<size;j++){
                map[i][j] = Integer.parseInt(numStr.charAt(j)+"");
            }
        }

        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(map[i][j] == UNVISITED){
                    map[i][j] = VISITED;
                    int cnt = 0;
                    list.add(cnt);
                    dfs(i, j);
                    dfsCnt++;
                }
            }
        }
        Arrays.sort(list.toArray());

        System.out.println(dfsCnt);
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }

    public static void dfs(int i, int j){
        if(i-1>=0){ // 상
            if(map[i-1][j] == UNVISITED){
                map[i-1][j] = VISITED;
                dfs(i-1, j);
            }
        }
        if(i+1<=map.length-1){ // 하
            if(map[i+1][j] == UNVISITED){
                map[i+1][j] = VISITED;
                dfs(i+1, j);
            }
        }
        if(j-1>=0){ // 좌
            if(map[i][j-1] == UNVISITED){
                map[i][j-1] = VISITED;
                dfs(i, j-1);
            }
        }
        if(j+1<=map.length-1){ // 우
            if(map[i][j+1] == UNVISITED){
                map[i][j+1] = VISITED;
                dfs(i, j+1);
            }
        }
    }
}
