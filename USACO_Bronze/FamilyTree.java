/*
PROBLEM: Family Tree (USACO US Open 2018 Bronze #3)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=833

Model the tree representing the family. Use an array list containing all the cows and an array list of array lists containing the mothers of each of those cows.
There will always be at least one root with no mothers. Then go through the tree from cow a to cow b looking to see what is their relationship. First test to see
if they are siblings (same mother and same level in the tree), if b is the mother of a (b is a direct ancestor of a), if b is the aunt of a (if b is the daughter
of a grandmother), if they are cousins (they have the same root but don't match any other relationship), or if they are unrelated meaning that they have different
roots due to being in 2 separate family trees.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    static ArrayList<String> cows;
    static ArrayList<ArrayList<String>> family;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("family.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("family.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(st.nextToken());
        String a = st.nextToken();
        String b = st.nextToken();
        cows = new ArrayList<>();
        family = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            String x = st.nextToken();
            String y = st.nextToken();
            boolean found = false;
            for (int j = 0; j < cows.size(); j++) {
                if (cows.get(j).equals(x)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                cows.add(x);
                family.add(new ArrayList<>());
            }
            found = false;
            for (int j = 0; j < cows.size(); j++) {
                if (cows.get(j).equals(y)) {
                    found = true;
                    family.get(j).add(x);
                    break;
                }
            }
            if (!found) {
                cows.add(y);
                family.add(new ArrayList<>());
                family.get(family.size()-1).add(x);
            }
        }

        int levelA = getLevel(a);
        int levelB = getLevel(b);
        String low = a; String high = b;
        if (levelA > levelB) {
            low = a; high = b;
        } else if (levelA < levelB) {
            low = b; high = a;
        }

        String ans = "";
        if (levelA == levelB && family.get(cows.indexOf(a)).get(0).equals(family.get(cows.indexOf(b)).get(0))) { // siblings
            ans = "SIBLINGS";
        }
        if (ans.equals("")) { // mother
            int size = family.get(cows.indexOf(low)).size();
            String ancestor = low;
            int l = 0;
            while (size != 0) {
                if (ancestor.equals(high)) {
                    if (l == 1) ans = high + " is the " + "mother of " + low;
                    else if (l == 2) ans = high + " is the " + "grand-mother of " + low;
                    else {
                        StringBuilder great = new StringBuilder();
                        for (int i = 2; i < l; i++) {
                            great.append("great-");
                        }
                        ans = high + " is the " + great.toString() + "grand-mother of " + low;
                    }
                    break;
                }
                ancestor = family.get(cows.indexOf(ancestor)).get(0);
                size = family.get(cows.indexOf(ancestor)).size();
                l++;
            }
            if (ans.equals("") && ancestor.equals(high)) {
                StringBuilder great = new StringBuilder();
                for (int i = 2; i < l; i++) {
                    great.append("great-");
                }
                ans = high + " is the " + great.toString() + "grand-mother of " + low;
            }
        }
        if (ans.equals("")) { // aunt
            if (getLevel(low) >= 2) {
                String ancestor = family.get(cows.indexOf(low)).get(0);
                int size = family.get(cows.indexOf(ancestor)).size();
                int l = 0;
                while (size != 0) {
                    ancestor = family.get(cows.indexOf(ancestor)).get(0);
                    size = family.get(cows.indexOf(ancestor)).size();
                    for (int i = 0; i < family.size(); i++) {
                        if (family.get(i).size() == 1 && family.get(i).get(0).equals(ancestor)) {
                            if (cows.get(i).equals(high)) {
                                StringBuilder great = new StringBuilder();
                                for (int j = 0; j < l; j++) {
                                    great.append("great-");
                                }
                                ans = high + " is the " + great.toString() + "aunt of " + low;
                            }
                        }
                    }
                    l++;
                }
            }
        }

        if (ans.equals("")) { // cousins or not related
            String ancestorA = a;
            int size = family.get(cows.indexOf(ancestorA)).size();
            while (size != 0) {
                ancestorA = family.get(cows.indexOf(ancestorA)).get(0);
                size = family.get(cows.indexOf(ancestorA)).size();
            }

            String ancestorB = b;
            size = family.get(cows.indexOf(ancestorB)).size();
            while (size != 0) {
                ancestorB = family.get(cows.indexOf(ancestorB)).get(0);
                size = family.get(cows.indexOf(ancestorB)).size();
            }

            if (ancestorA.equals(ancestorB)) {
                ans = "COUSINS";
            } else {
                ans = "NOT RELATED";
            }
        }

        out.println(ans);
        out.close();
    }

    public static int getLevel(String i) {
        int level = 0;
        String ancestor = i;
        int size = family.get(cows.indexOf(ancestor)).size();
        while (size != 0) {
            ancestor = family.get(cows.indexOf(ancestor)).get(0);
            size = family.get(cows.indexOf(ancestor)).size();
            level++;
        }
        return level;
    }
}