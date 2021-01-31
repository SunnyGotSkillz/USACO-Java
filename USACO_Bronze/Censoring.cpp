/*
PROBLEM: Censoring (USACO February 2015 Bronze #1)
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=526

The greedy method in this problem is to build the answer string one character at a time and test to see if the substring matches T based on the last character of T.
*/

#include <iostream>
#include <vector>
#include <algorithm>
#include <cstring>
#include <cstdio>

using namespace std;

int main() {
  freopen("censor.in", "r", stdin);
  freopen("censor.out", "w", stdout);

  /* Read input strings. */
  string S, T;
  cin >> S >> T;

  /* Build R, the result string, one character at a time. */
  string R;
  for (int i = 0; i < S.size(); i++) {
    R += S[i];

    /* If the end of R matches T then delete it. */
    if (R.size() >= T.size() && R.substr(R.size() - T.size()) == T) {
      R.resize(R.size() - T.size()); // deletes last T characters
    }
  }

  cout << R << endl;

  return 0;
}