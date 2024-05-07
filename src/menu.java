public class menu {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            sudoku sudoku = new sudoku();
            sudoku.initVar(5);
                sudoku.createSudoku();
                sudoku.print1();
                System.out.println();
            }
    }
}
