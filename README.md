From PDSA hw3
# HW3: Mafia

**Release date: 03/11 16:00
Due date: 03/20 21:00
TA hour: 03/18 17:30-18:10**

## Backstory
Following the death of the mafia's big boss in a recent police raid, a power vacuum has thrown the organization into chaos, with members fighting among themselves due to old grudges and issues. Without a clear leader, conflicts arise as former allies turn into rivals.
As an undercover agent hiding in the organization, you have gathered all the needed information among all those mafia members. Your goal is to find out what will happen in the fight. The mafia members follows a very specific rule when it comes to fighting, it's called "tradition". The rules and other information you need to know are listed on the section below.

## Description
-  To follow the tradition, the members line up in a straight line.
-  There are ${N}$ members in the fight, with an index ${i}$ denoting their position on the line.
$i \in \{0, 1, \ldots, {N-1}\}$
-  Each mafia member has two important traits: ${Level}$ and ${Range}$. So there will be two sequences containig the level and range of the members according to their positions.
$\textbf{Level} = \{\textbf{L}_0,\textbf{L}_1,\textbf{L}_2,\textbf{L}_3....\textbf{L}_{N-1}\}$
$\textbf{Range} = \{\textbf{R}_0,\textbf{R}_1,\textbf{R}_2,\textbf{R}_3....\textbf{R}_{N-1}\}$
- To determine whether a member at position ${i}$ can attack a member at position ${j}$, it must satisfy the following three conditions:
1. $\lvert j - i \rvert \leq R_i$ 
*${member_j}$ must be within the range of ${member_i}$'s range .*
2. $L_j < L_i$ 
*${member_i}$ must have higher level than ${member_j}$ .*
3. $\{L_k\} < L_i$ for all $k \in \{k \mid i + 1 \leq k \leq j - 1\}$
*${member_i}$ can't attack ${member_j}$ if there's a  ${member_k}$ with higher level between ${member_i}$ and ${member_j}$*

- Here we denote that ${a_i}$ and ${b_i}$ will be the smallest index and the largest index the ${member_i}$ can attack.
- Please determine the sequence of pairs $\{(a_0, b_0), \ldots, (a_{N-1}, b_{N-1})\}$

## Template

```java 
import java.util.Arrays; // Used to print the arrays

class member{
    int Level;
    int Range;
    int Index;
    member(int _level,int _range, int i){
        Level=_level;
        Range=_range;
        Index=i;
    }
}

class Mafia {
    public int[] result(int[] levels, int[] ranges) {
          // Given the traits of each member and output 
          // the leftmost and rightmost index of member
          // can be attacked by each member.
        return ???; 
        // complete the code by returning an int[]
        // flatten the results since we only need an 1-dimentional array.
    }

    public static void main(String[] args) {
        Mafia sol = new Mafia();
        System.out.println(Arrays.toString(
            sol.result(new int[] {11, 13, 11, 7, 15},
                         new int[] { 1,  8,  1, 7,  2})));
        // Output: [0, 0, 0, 3, 2, 3, 3, 3, 2, 4]
        //      => [a0, b0, a1, b1, a2, b2, a3, b3, a4, b4]
    }
}
```
## Test Data
`N` is the number of members
`0 <= level <= 1000000000`
`0 <= range <= M`

Time Limit: 900ms
We guarantee the array length of Level is always equal to the length of Range.

Case:

* case1: 20 points: N <= 10, M < 10
* case2: 20 points: N <= 200000, M <= 200000
* case3: 20 points: N <= 10000, M <= 5000
* case4: 20 points: N <= 400000, M <= 200000
* case5: 20 points: N <= 1000000, M <= 500000

# File download 

[TestCode](https://drive.google.com/file/d/1yVh94U3YBC50RBbKHgRBBaTTrG67y5z-/view?usp=sharing)
[TestData](https://drive.google.com/file/d/1yDRTo08srv67IAKu_ylB8T-7BUlm8zma/view?usp=sharing)

[algs4.jar library](https://algs4.cs.princeton.edu/code/algs4.jar)
[gson.jar library](https://drive.google.com/file/d/1gUhlPLTc4EA8P-R_qf3a4uynCQeR0TgH/view?usp=drive_link)


### Test command
javac Mafia_test.java
java Mafia ./Mafia.json