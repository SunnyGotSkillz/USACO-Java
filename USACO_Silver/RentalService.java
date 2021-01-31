/*
PROBLEM: Rental Service (USACO January 2018 Silver #2)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=787

We want to sell to shops that pay more per gallon of milk over shops that pay less per gallon of milk. We want to rent cows that produce less milk over cows that produce
more milk. Let f(i) be the maximum amount of money we can make just by using the milk from the i cows that produce the most milk, and let g(j) be the maximum 
amount of money we can make just by renting the j cows that produce the least milk. We want to compute the maximum possible value of f(i)+g(N−i).g(j) is easy to 
compute in general, we just sort the rental prices in decreasing order and sum the j highest values. We can compute all g(j) in linear time by maintaining a prefix sum.
To compute f(i), we have to scan both the cows and the shops. For a cow, we greedily process the shops in decreasing price order and sell as much milk to them as possible, until either the cow runs out of milk to sell or the shop no longer wants to buy milk.

Each element in the maxProfit prefix sum will represent a single case. First, we will go through the shops and sell a certain amount of milk and keep adding on. Then
we will go backwards through the array and rent a certain number of cows. For example the last element of maxProfit represents using all the cow milk possible and 
rentingno cows. The second to last element of maxProfit represents using n-1 cows for selling milk and renting one cow. All of this is done greedily to ensure that 
we are always getting the max profit.
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("rental.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("rental.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int[] milkProduced = new int[n];
        for(int i = 0; i < n; i++) {
            milkProduced[i] = Integer.parseInt(br.readLine());
        }
        sort(milkProduced);
        Shop[] shops = new Shop[m];
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            shops[i] = new Shop(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(shops);
        long[] maxProfit = new long[n+1];
        {
            int index = 0;
            for(int i = 0; i < n; i++) {
                maxProfit[i+1] = maxProfit[i];
                while(index < m && milkProduced[i] > 0) {
                    int use = Math.min(milkProduced[i], shops[index].quantity);
                    maxProfit[i+1] += use * (long)shops[index].price;
                    milkProduced[i] -= use;
                    shops[index].quantity -= use;
                    if(shops[index].quantity == 0) {
                        index++;
                    }
                }
            }
        }
        int[] rental = new int[r];
        for(int i = 0; i < r; i++) {
            rental[i] = Integer.parseInt(br.readLine());
        }
        sort(rental);
        {
            int a = n-1;
            int rI = 0;
            long rentalSoFar = 0;
            while(a >= 0 && rI < r) {
                rentalSoFar += rental[rI];
                maxProfit[a] += rentalSoFar;
                rI++;
                a--;
            }
        }
        long ret = 0;
        for(long out: maxProfit) {
            ret = Math.max(ret, out);
        }
        pw.println(ret);
        pw.close();
    }

    // sort an int array by descending order
    public static void sort(int[] l) {
        Arrays.sort(l);
        for(int i = 0; i < l.length-1-i; i++) {
            l[i] ^= l[l.length-1-i];
            l[l.length-1-i] ^= l[i];
            l[i] ^= l[l.length-1-i];
        }
    }

    static class Shop implements Comparable<Shop> {
        public int quantity, price;
        public Shop(int a, int b) {
            quantity=a;
            price=b;
        }
        public int compareTo(Shop s) {
            return Integer.compare(s.price, price); // sort by decreasing
        }
    }
}