/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stable_matching;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 *
 * @author nirma
 */
class Matching extends Stable_Matching {

    int r = 0;

    public Matching(String[] m, String[] w, String[][] mp, String[][] wp, int[] ma, String[] wa, long startTime) {
        while (r < ma.length) {

            // check which men is wakent
            int i = 0;
            for (i = 0; i < ma.length; i++) {
                if (ma[i] == 0) {
                    break;
                }
            }

            for (int j = 0; j < ma.length && ma[i] == 0; j++) {
                //finding    the index of woman
                int index = 0;
                for (; index < w.length;) {
                    if (w[index].equals(mp[i][j])) {
                        index = index;
                        break;
                    }
                    index++;
                }

                if (wa[index] == "0") {
                    wa[index] = m[i];
                    ma[i] = 1;
                    r++;
                } else {
                    String cp = wa[index];

                    for (int ch = 0; ch < wp[index].length; ch++) {
                        if (wp[index][ch].equalsIgnoreCase(cp)) {
                            wa[index] = cp;
                            ma[i] = 0;

                            break;
                        }
                        if (wp[index][ch].equalsIgnoreCase(m[i])) {
                            wa[index] = m[i];
                            ma[i] = 1;
                            for (int f = 0; f < m.length; f++) {
                                if (m[f].equalsIgnoreCase(cp)) {
                                    ma[f] = 0;
                                }
                            }
                            break;
                        }

                    }

                }
            }

        }
        System.out.println("");

        System.out.println("Here is the matching of the input");
        for (int i = 0; i < wa.length; i++) {
        System.out.println("W" + (i + 1) + " " + wa[i]);
//        Print Output in  a file
        try {
      PrintStream out = new PrintStream(new FileOutputStream("OutFile.txt"));
      out.println("Here is the Stable matched output");
      for (int j = 0; j < wa.length; j++)
      {
        out.println("W" + (j + 1) + " " + wa[j]);}
         out.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
        }
        //    exitra
        //Check if the List is stable or not.
        int Stable = 0;
        int trial = 0;
        for (int i = 0; i < w.length; i++) {
            int index1 = 0;
            //finding the index of Woman that is matched
            for (; index1 < w.length;) {
                if (wa[index1].equals(m[i])) {
                    index1 = index1;
                    break;
                }
                index1++;
            }

            for (int j = 0; j < ma.length; j++) {
                //finding    the index of woman in
                int index = 0;
                int Menindex = 0;
                //findingg the index of  
                for (; index < w.length;) {
                    if (w[index].equals(mp[i][j])) {
                        index = index;
                        break;
                    }
                    index++;

                }
                trial++;
                if (index1 == index) {
                    break;

                } //Finding the man index of the woman which is currently matched.
                else if (index < index1) {
                    for (int k = 0; k < m.length; k++) {
                        if (wp[index].equals(m[k])) {
                            Menindex = k;
                        }

                    }

                    if (Menindex > i) {
                        Stable = 1;
                        System.out.println("It is unstable");
                        System.exit(0);
                    }
                }

            }

        }
        if (Stable == 0) {
            System.out.println("It is stable");

        }
        System.out.println("Number of loops to check if it is stable or not  is =" + trial);
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Time takesn in nano seconds =" + totalTime / 1000000000.00);
    }
}
