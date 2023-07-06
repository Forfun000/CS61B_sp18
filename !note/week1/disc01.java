public class disc01 {
  /**
   * what does the "mystery" function return?
   */
  public static int mystery(int[] inputArray, int k) {
    int x = inputArray[k];
    int answer = k;
    int index = k + 1;
    while (index < inputArray.length) {
      if (inputArray[index] < x) {
        x = inputArray[index];
        answer = index;
      }
      index = index + 1;
    }
    return answer;
  }
}
