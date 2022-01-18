public class Pyramid {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
//		Qestion ９段の星（⭐️）で出来たピラミッドを作成しましょう。

   for(int i=0; i<9; i++){
    for(int j=0; j<9-(i+1); j++){
      System.out.print(" ");

    }
    for(int k=0; k<(i+1)*2-1; k++){
      System.out.print("☆");
    }
    System.out.println();
  }
}
}