import java.util.Random;

public class sudoku {

    private   int[][] a;
    private boolean[][] Row;
    private boolean[][] Col;
    private boolean[][] Cell;
    private int num;

    public void initVar(int num){
        this.num = num;
        a = new int[num][num];
        Row = new boolean[num][num];
        Col = new boolean[num][num];
        Cell = new boolean[num][num];
    }

    public  void print1(){
        int n=a.length;
        for (int i=1;i<n;i++) {
            for (int j = 1; j < n; j++) {
                System.out.print(a[i][j] + " ");
                if (j == n-1) System.out.println();
            }
        }
    }

    public static int getCell(int x, int y, int n){
        if(n==4) return (int)(x/3*2+y/3+1);
        else if(n==6) return (int)((x-1)/2*2+y/4+1);
        else if(n==9) return (int)((x-1)/3*3+(y-1)/3+1);
        return -1;
    }

    public static void Record(int x, int y, int[][] a, boolean[][] Row, boolean[][] Col, boolean[][] Cell){
        Row[x][a[x][y]]=true;
        Col[y][a[x][y]]=true;
        Cell[getCell(x,y,a.length-1)][a[x][y]]=true;
    }

    public static void deRecord(int x, int y, int[][] a, boolean[][] Row, boolean[][] Col, boolean[][] Cell){
        Row[x][a[x][y]]=false;
        Col[y][a[x][y]]=false;
        Cell[getCell(x,y,a.length-1)][a[x][y]]=false;
        a[x][y]=0;
    }

    public static void initSudoku(int[][] a, boolean[][] Row, boolean[][] Col, boolean[][] Cell){
        for(int i=1;i<a.length;i++){
            for(int j=1;j<a[i].length;j++){
                    a[i][j]=0;
                    deRecord(i,j,a,Row,Col,Cell);
            }
        }
    }

    public  void randSudoku(int[][] a, boolean[][] Row, boolean[][] Col, boolean[][] Cell){
        Random rand = new Random();
        for(int i=1;i<a.length;i++){
            for(int j=1;j<a.length;j++){
                if(rand.nextInt(100)<10) {
                    int n = rand.nextInt(num-1) + 1;
                    if (!Row[i][n] && !Col[j][n] && !Cell[getCell(i, j, a.length - 1)][n]) {
                        a[i][j] = n;
                        Record(i, j, a, Row, Col, Cell);
                    }
                }
            }
        }
    }

    public static boolean backTrack(int x, int y, int[][] a, boolean[][] Row, boolean[][] Col, boolean[][] Cell){
        boolean b = x == a.length - 1 && y == a.length - 1;
        if(a[x][y]!=0){
            if(b) return true;
            else if(y==a.length-1) return backTrack(x+1,1,a,Row,Col,Cell);
            else return backTrack(x,y+1,a,Row,Col,Cell);
        }
        else{
            for(int i=1;i<a.length;i++){
                if(!Row[x][i]&&!Col[y][i]&&!Cell[getCell(x,y,a.length-1)][i]){
                    a[x][y] = i;
                    Record(x,y,a,Row,Col,Cell);
                    if(b) return true;
                    else if(y==a.length-1){
                        if(!backTrack(x+1,1,a,Row,Col,Cell)){
                            deRecord(x,y,a,Row,Col,Cell);
                            continue;
                        }
                    }
                    else{
                        if(!backTrack(x,y+1,a,Row,Col,Cell)){
                            deRecord(x,y,a,Row,Col,Cell);
                            continue;
                        }
                    }
                    return true;
                }
            }
            return false;
        }
    }


    public  void createSudoku(){
        initSudoku(a,Row,Col,Cell);
        randSudoku(a,Row,Col,Cell);
        for(int i=0;i<10;i++) {
            if (backTrack(1, 1, a, Row, Col, Cell)) {
                break;
            }
            initSudoku(a, Row, Col, Cell);
            randSudoku(a, Row, Col, Cell);
            System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        }
    }
}