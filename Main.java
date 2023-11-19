import java.util.Arrays;

class Main {
    static int count = 0;

    public static void main(String[] args) {
        // interfaceName.super().method
        int[] a = { 1, 2, 4, 65, 20, 3, 2, 10 };
        System.out.println("Minimum of 3 among (2,1,6)"+new Main().minimum(2,6,1));
        System.out.println("Ways to reach nth stair: "+new Main().reachNstair(4));
        System.out.println("Non consecutive max sum : " + new Main().maxNonAdjSum(a));
        System.out.println(new Main().fibonacci(6));
        int[][] matrix = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
        int[][] res = setMatrix(matrix);
        System.out.println(Arrays.deepToString(res));
        // List<String> dates = new ArrayList<>();
        // dates.add("2023-10-29");
        // dates.add("2023/10/30");
        // dates.add("2023\\10\\31");
        // UnaryOperator<String> replaceDashes=str -> ((String) str).replaceAll("/",
        // "-");
        // // Replace all slashes in the dates list
        // dates.replaceAll(replaceDashes);
        // //https://levelup.gitconnected.com/dont-just-leetcode-follow-the-coding-patterns-instead-4beb6a197fdb
        // //Sliding Window

        // // Print the updated dates list
        // System.out.println(dates);

        // try{
        // System.out.println("A");
        // badMethod();
        // System.out.println("B");
        // }catch(Exception ex){
        // System.out.println("C");
        // }finally{
        // System.out.println("D");
        // }
    }

    public static void badMethod() {
        throw new Error();
    }

    // if (count < 3) {
    // count++;
    // main(null);
    // } else {
    // return;

    // }
    // System.out.println("Shreem!!");

    // Given an m*n matrix, if an element is 0 set the entire row and column to 0
    public static int[][] setMatrix(int[][] a) {
        // int[][] res = new int[a.length][a[0].length];
        // for (int r = 0; r < a.length; r++)
        //     for (int c = 0; c < a[0].length; c++) {
        //         res[r][c] = a[r][c];
         //   }
          int[][]  res=Arrays.copyOf(a,a.length);

        int i, j;
        for (int r = 0; r < a.length; r++)
            for (int c = 0; c < a[0].length; c++) {
                // int[][]a= {{1,1,1},{1,0,1},{1,1,1}};
                if (a[r][c] == 0) {
                    i = 0;
                    while (i < a[0].length) {
                        res[i++][c] = 0;
                    }
                    i--;
                    while (i > -1) {
                        res[i--][c] = 0;
                    }
                    j = 0;
                    while (j < a.length) {
                        res[r][j++] = 0;
                    }
                    j--;
                    while (j > -1) {
                        res[r][j--] = 0;
                    }
                }
            }
        return res;
    }

    // class AddPadding {

    // public static void addPadding(File file) throws IOException {
    // BufferedReader reader = new BufferedReader(new FileReader(file));
    // List<String> records = new ArrayList<>();
    // String record;
    // while ((record = reader.readLine()) != null) {
    // records.add(record);
    // }
    // reader.close();

    // for (int i = 0; i < records.size(); i++) {
    // records.set(i, records.get(i) + " ");
    // }

    // BufferedWriter writer = new BufferedWriter(new FileWriter(file));
    // for (String r : records) {
    // writer.write(r);
    // writer.newLine();
    // }
    // writer.close();
    // }

    // public void addPaddingToMyFile() throws IOException {
    // File file = new File("my-file.txt");
    // AddPadding.addPadding(file);
    // }

    // maximum sum of non-adjacent elements
    public int maxNonAdjSum(int[] a) {
        int[] dp = new int[a.length];
        dp[0] = 0;// dp[0] means array has only one element
        dp[1] = a[0] > a[1] ? a[0] : a[1];
        for (int i = 2; i < a.length; i++)
            dp[i] = dp[i - 1] > (dp[i - 2] + a[i]) ? dp[i - 1] : a[i] + dp[i - 2];

        return dp[a.length - 1];
    }

    // fibonacci 1,1,2,3,5,8,13,24
    public int fibonacci(int n) {
        // if (n<2) return n;
        // return fibanacci(n-1)+fibanacci(n-2);
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[0] = 0;
        for (int i = 2; i <= n; i++)
            dp[i] = dp[i - 1] + dp[i - 2];
        return dp[n];
    }

    // no. of ways to reach the nth stair where only 1/2/3 steps allowed
    public int reachNstair(int n) {
        // int[] dp=new int [n+1];
        // dp[0]=1; dp[1]=1; dp[2]=2;
        // for (int i=3;i<=n;i++){
        // dp[i]=dp[i-3]+dp[i-2]+dp[i-1];//if i=3; dp[3]=dp[0]+dp[1]+dp[2]=1+1+2=4
        // }
        // return dp[n];
        // }
        int a = 1, b = 1, c = 2;
        int d;
        for (int i = 3; i <= n; i++) {
            d = a + b + c;
            a = b;
            b = c;
            c = d;
        }
        return c;
    }
    //minimum no. of steps to reach nth stair where 1/2/3 steps are allowed
    public int minReachNthStair(int n){
        int a=1, b=1,c=1;int d;
        for (int i=3;i<=n;i++)
        {   d= 1+minimum(a,b,c);
            a=b;
            b=c;
            c=d;}
            return c;        
    }

    private int minimum(int a, int b, int c) {
        return a<b?a<c?a:c:b<c?b:c;
    }

    //longest increasing subsequence of a string
    

}