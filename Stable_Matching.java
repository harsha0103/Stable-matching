/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stable_matching;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author nirma
 */
public class Stable_Matching {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();

        System.out.println("Gale Shapley Marriage Algorithm\n");
        /**
         * list of men *
         */

        Scanner Input = new Scanner(System.in);

        System.out.println("Please enter");
        System.out.println("1: If you want to give your own input");
        System.out.println("");
        System.out.println("2: If you want to generate the random input");
        System.out.println("");
        System.out.println("3: If the input should be taken from file");
        int option = Input.nextInt();
//int option =2;
//        Create a Random file.
        if (option == 2) {
            System.out.println("Enter a new N value");
            int n = Input.nextInt();

            String[] m = new String[n];
            String[] w = new String[n];
            int[] ma = new int[n];
            String[] wa = new String[n];

            for (int i = 0; i < n; i++) {
                m[i] = "M" + (i + 1);
                w[i] = "W" + (i + 1);
                ma[i] = 0;
                wa[i] = "0";
            }
            /**
             * men preference *
             */

            String[][] mp = new String[n + 1][n + 1];
            String[][] wp = new String[n + 1][n + 1];
            System.out.println("Men preference list");
            for (int j = 0; j < n;) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                for (int i = 1; i < (n + 1); i++) {
                    list.add(new Integer(i));

                }
                Collections.shuffle(list);
                System.out.print("M" + (j + 1) + "  ");
                for (int i = 0; i < n;) {
                    mp[j][i] = "W" + list.get(i);
                    System.out.print("W" + list.get(i) + " ");
                    i++;
                }
                System.out.println("");
                j++;
            }
            System.out.println("");
            System.out.println("Woman preference list");

            for (int j = 0; j < n;) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                for (int i = 1; i < (n + 1); i++) {
                    list.add(new Integer(i));
                }
                Collections.shuffle(list);
                System.out.print("W" + (j + 1) + "  ");
                for (int i = 0; i < n;) {
                    wp[j][i] = "M" + list.get(i);
                    System.out.print("M" + list.get(i) + " ");
                    i++;
                }
                System.out.println("");
                j++;
            }
            try {
                PrintStream out = new PrintStream(new FileOutputStream("Input.txt"));
                out.println("Here is Input Given");
                for (int i = 0; i < m.length; i++) {
                    out.print("M" + (i + 1) + "-");
                    for (int j = 0; j < m.length; j++) {
                        out.print(" " + mp[i][j]);
                    }
                    out.println();
                }
                out.println();

                for (int i = 0; i < w.length; i++) {
                    out.print("W" + (i + 1) + "-");
                    for (int j = 0; j < w.length; j++) {
                        out.print(" " + wp[i][j]);
                    }
                    out.println();
                }

                out.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Matching gs = new Matching(m, w, mp, wp, ma, wa, startTime);
        } //   Previously created input file 
        else if (option == 1) {
            System.out.println("Enter a new N value");

            int n = Input.nextInt();
            Input.nextLine();
            String[] m = new String[n];
            String[] w = new String[n];
            int[] ma = new int[n];
            String[] wa = new String[n];

            for (int i = 0; i < n; i++) {
                m[i] = "M" + (i + 1);
                w[i] = "W" + (i + 1);
                ma[i] = 0;
                wa[i] = "0";
            }
            String[][] mp = new String[n + 1][n + 1];
            String[][] wp = new String[n + 1][n + 1];
            /**
             * men preference *
             */

            for (int i = 0; i < n; i++) {
                System.out.print("Please enter the Men " + (i + 1) + " preference list");
                System.out.println(" Please enter index only");
                for (int j = 0; j < n; j++) {
                    mp[i][j] = "W" + Input.nextLine();
                }
            }

            System.out.println("Men preference list");
            for (int i = 0; i < n; i++) {
                System.out.print("M" + (i + 1) + "  ");
                for (int j = 0; j < n; j++) {
                    System.out.print(mp[i][j] + " ");

                }
                System.out.println(" ");
            }

            /**
             * women preference *
             */
            System.out.println("Please enter the Women preference list");
            for (int i = 0; i < n; i++) {
                System.out.print("Please enter the Women " + (i + 1) + " preference list");
                System.out.println(" Please enter index only");
                for (int j = 0; j < n; j++) {
                    wp[i][j] = "M" + Input.nextLine();
                }
            }

            System.out.println("Women preference list");
            for (int i = 0; i < n; i++) {
                System.out.print("W" + (i + 1) + "  ");
                for (int j = 0; j < n; j++) {
                    System.out.print(wp[i][j] + " ");

                }
                System.out.println(" ");

            }
            try {
                PrintStream out = new PrintStream(new FileOutputStream("Input.txt"));
                out.println("Here is Input Given");
                for (int i = 0; i < m.length; i++) {
                    out.print("M" + (i + 1) + "-");
                    for (int j = 0; j < m.length; j++) {
                        out.print(" " + mp[i][j]);
                    }
                    out.println();
                }
                out.println();

                for (int i = 0; i < w.length; i++) {
                    out.print("W" + (i + 1) + "-");
                    for (int j = 0; j < w.length; j++) {
                        out.print(" " + wp[i][j]);
                    }
                    out.println();
                }

                out.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Matching gs = new Matching(m, w, mp, wp, ma, wa, startTime);
        } 
        
        else if (option == 3) {
            BufferedReader br = null;

            try {

                String sCurrentLine;
                int n;
                br = new BufferedReader(new FileReader("Data.txt"));
                n = Integer.parseInt(br.readLine());
                System.out.println(n);
                String[] m = new String[n];
                String[] w = new String[n];
                int[] ma = new int[n];
                String[] wa = new String[n];

                for (int i = 0; i < n; i++) {
                    m[i] = "M" + (i + 1);
                    w[i] = "W" + (i + 1);
                    ma[i] = 0;
                    wa[i] = "0";
                }
                String[][] mp = new String[n + 1][n + 1];
                String[][] wp = new String[n + 1][n + 1];
                for (int i = 0; i < n; i++) {
                    sCurrentLine = br.readLine();

//                    System.out.println(sCurrentLine);
                    int j = 0;
                    for (String retval : sCurrentLine.split(" ")) {
                        mp[i][j] = retval;
                        System.out.print(mp[i][j] + " ");
                        j++;
                    }
                    System.out.println("");

                }
                System.out.println("");
                for (int i = 0; i < n; i++) {
                    sCurrentLine = br.readLine();

//                    System.out.println(sCurrentLine);
                    int j = 0;
                    for (String retval : sCurrentLine.split(" ")) {
                        wp[i][j] = retval;
                        System.out.print(wp[i][j] + " ");
                        j++;
                    }
                    System.out.println("");

                }
                try {
                PrintStream out = new PrintStream(new FileOutputStream("Input.txt"));
                out.println("Here is Input Given");
                for (int i = 0; i < m.length; i++) {
                    out.print("M" + (i + 1) + "-");
                    for (int j = 0; j < m.length; j++) {
                        out.print(" " + mp[i][j]);
                    }
                    out.println();
                }
                out.println();

                for (int i = 0; i < w.length; i++) {
                    out.print("W" + (i + 1) + "-");
                    for (int j = 0; j < w.length; j++) {
                        out.print(" " + wp[i][j]);
                    }
                    out.println();
                }

                out.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
                Matching gs = new Matching(m, w, mp, wp, ma, wa, startTime);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
