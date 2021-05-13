/*
Sebastian Castro
Created: 5/12/2021
Code can be used for reference
 */


import java.util.*;


public class connect4 {
    public static void menu(){
        System.out.println("Welcome player");
        //System.out.println("Please type in where you would like to make your move");
    }
    public static void main(String[] args){
        int k = 0;
        System.out.println("Welcome players to my TicTacToe game, there are nine spots");
        System.out.println("Player 1, please input your name");
        Scanner s1 = new Scanner(System.in);
        String p1 = s1.nextLine();
        System.out.println("Player 2, please input your name");
        Scanner s2 = new Scanner(System.in);
        String p2 = s2.nextLine();
        board b = new board(p1,p2);
        do{
            menu();
            b.printBoard();
            b.printNumyBoard();
            Scanner s = new Scanner(System.in);
            b.printName(k);
            int n = s.nextInt();
            if(!b.checkMove(n))
                continue;
            b.makeMove(n);
            if(k==0)
                k++;
            else
                k--;
            b.setk(k);
        }while(b.getCount()!= 36 && b.checkWin()!= 1 && b.checkWin() != 2);


    }
}

class player{
    public int [] sum;
    public String name;
    public player(String name){
        this.name = name;
    }
    public String toString(){
        return "Player: " + name ;
    }
}

class board {
    private int count = 0;
    private int k = 0;
    private int move = 0;
    private player p1;
    private player p2;
    private player[] p = new player[2];
    private String[][] b = new String[6][6];
    private int[][] bn = new int[6][6];
    HashMap<Integer, Integer[]> ho = new HashMap<>();

    public board(String p1a, String p2a) {
        int i,j;
        int k = 0;
        count = 0;
        this.p1 = new player(p1a);
        this.p2 = new player(p2a);
        p[0] = p1;
        p[1] = p2;
        for(j=0; j<=5; j++){
            for(i = 1 + 6*j; i <= 6 + 6*j; i++){
                ho.put(i, new Integer[]{j,i-6*j-1});
                //System.out.println(ho.get(i));
                b[j][i-6*j-1] = "-";
                bn[j][i-6*j-1] = i;
            }
        }
    }

    public void setk(int s){
        this.k = s;
    }

    public void printBoard() {
        System.out.println(" -------------------------");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(" | " + b[i][j]);
            }
            System.out.println(" |");
        }
        System.out.println(" -------------------------");
        System.out.println();
    }

    public void printNumyBoard() {
        System.out.println(" -------------------------");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(" | " + bn[i][j]);
            }
            System.out.println(" |");
        }
        System.out.println(" -------------------------");
    }

    public void setCount() {
        count++;
    }

    public int getCount() {
        if(count == 36)
            System.out.println("There seems to be a tie");
        return count;
    }

    public boolean checkMove(int s) {
        if (s > 36 || s < 1) {
            System.out.println("The number you entered is out of bounds");
            return false;
        }
        int[] coor = new int[2];
        Integer[] c = ho.get(s);
        for (int i = 0; i < 2; i++) {
            coor[i] = c[i];
        }
        if (b[coor[0]][coor[1]] == "X" || b[coor[0]][coor[1]] == "O") {
            System.out.println("The number with the slot you have selected is already full, try again");
            return false;
        } else {
            return true;
        }

    }

    public void makeMove(int s) {
        int[] coor = new int[2];
        Integer[] c = ho.get(s);
        for (int i = 0; i < 2; i++) {
            coor[i] = c[i];
        }
        b[coor[0]][coor[1]] = (k == 0) ? "X" : "O";
        //k++;
        System.out.println("Thank you for making a move!\n");
        setCount();
    }

    public void printName(int s){
        if(s==0){
            System.out.println(p1 + " , it is your turn, please pick a number between 1 and 36, see the map below");
        }
        else
            System.out.println(p2 + " , it is your turn, please pick a number between 1 and 36, see the map below");
    }

    public int checkWin(){
        // 3 if tie, 1 if p1, 2 if p2
        int i,j,k;
        for(i=0; i < 6; i++){
            for(j=0; j <= 2; j++){
                int xc1 = 0, oc1 = 0;
                int xc2 = 0, oc2 = 0;
                for(k=0 + j; k <= 3 + j; k++){
                    if(b[i][k] == "X")
                        xc1++;
                    else if(b[i][k] == "O")
                        oc1++;
                    if(b[k][i] == "X")
                        xc2++;
                    else if(b[k][i] == "O")
                        oc2++;
                }
                if(xc1 == 4 || xc2 == 4){
                    System.out.println("You have won: " + p1.name);
                    return 1;
                }
                else if(oc1 == 4 || oc2 == 4){
                    System.out.println("You have won: " + p2.name);
                    return 2;
                }
            }
        }
        ///////////////////////////////////for the 4 in a row
        {
            int x = 0, o = 0,x1 = 0, o1 = 0;
            int i1;
            for(i1 = 0; i1 <= 3; i1++){
                if(b[i1][3-i1].equals("X"))
                    x++;
                else if(b[i1][3-i1].equals("O"))
                    o++;
                if(b[i1+2][3-i1+2].equals("X"))
                    x1++;
                else if(b[i1+2][3-i1+2].equals("O"))
                    o1++;
            }
            if(x == 4 || x1 ==4){
                System.out.println("You have won: " + p1.name);
                return 1;
            }
            else if(o == 4 || o1 == 4){
                System.out.println("You have won: " + p2.name);
                return 2;
            }
        }
        ////////////////////////////// for the 5 in a row
        {
            int j2;
            for(j2 = 0; j2 <= 1; j2++){
                int x = 0, o = 0,x1 = 0, o1 = 0;
                int i1;
                for(i1 = 0 + j2; i1 <= 3 + j2; i1++){

                    if(b[i1][4-i1].equals("X"))
                        x++;
                    else if(b[i1][4-i1].equals("O"))
                        o++;
                    if(b[i1+1][4-i1+1].equals("X"))
                        x1++;
                    else if(b[i1+1][4-i1+1].equals("O"))
                        o1++;
                }
                if(x == 4 || x1 ==4){
                    System.out.println("You have won: " + p1.name);
                    return 1;
                }
                else if(o == 4 || o1 == 4){
                    System.out.println("You have won: " + p2.name);
                    return 2;
                }

            }

        }

        //////////////////// for the 6 in a row
        {
            int j2;
            for(j2 = 0; j2 <= 2; j2++){
                int x = 0, o = 0;
                int i1;
                for(i1 = 0 + j2; i1 <= 3 + j2; i1++){
                    if(b[i1][5-i1].equals("X"))
                        x++;
                    else if(b[i1][5-i1].equals("O"))
                        o++;
                }
                if(x == 4){
                    System.out.println("You have won: " + p1.name);
                    return 1;
                }
                else if(o == 4){
                    System.out.println("You have won: " + p2.name);
                    return 2;
                }

            }
        }
        ////////////////// for reverse 4 in a row
        {
            int x = 0, o = 0,x1 = 0, o1 = 0;
            int i1;
            for(i1 = 0; i1 <= 3; i1++){
                if(b[i1][i1+2].equals("X"))
                    x++;
                else if(b[i1][i1+2].equals("O"))
                    o++;
                if(b[i1+2][i1].equals("X"))
                    x1++;
                else if(b[i1+2][i1].equals("O"))
                    o1++;
            }
            if(x == 4 || x1 ==4){
                System.out.println("You have won: " + p1.name);
                return 1;
            }
            else if(o == 4 || o1 == 4){
                System.out.println("You have won: " + p2.name);
                return 2;
            }
        }
        ////////////////////////////// for the 5 in a row reverse
        {
            int j2;
            for(j2 = 0; j2 <= 1; j2++){
                int x = 0, o = 0,x1 = 0, o1 = 0;
                int i1;
                for(i1 = 0 + j2; i1 <= 3 + j2; i1++){
                    if(b[i1][i1+1].equals("X"))
                        x++;
                    else if(b[i1][i1+1].equals("O"))
                        o++;
                    if(b[i1+1][i1].equals("X"))
                        x1++;
                    else if(b[i1+1][i1].equals("O"))
                        o1++;
                }
                if(x == 4 || x1 ==4){
                    System.out.println("You have won: " + p1.name);
                    return 1;
                }
                else if(o == 4 || o1 == 4){
                    System.out.println("You have won: " + p2.name);
                    return 2;
                }

            }

        }
        ////////////////////////////// for 6 in a row reversal
        {
            int j2;
            for(j2 = 0; j2 <= 2; j2++){
                int x = 0, o = 0;
                int i1;
                for(i1 = 0 + j2; i1 <= 3 + j2; i1++){
                    if(b[i1][i1].equals("X"))
                        x++;
                    else if(b[i1][i1].equals("O"))
                        o++;
                }
                if(x == 4){
                    System.out.println("You have won: " + p1.name);
                    return 1;
                }
                else if(o == 4){
                    System.out.println("You have won: " + p2.name);
                    return 2;
                }

            }
        }
        //////////////////
        return 3;
    }
}
