package study;



// ① TaskクラスにCalculatorクラスを継承させなさい。
public class Task extends Calculator{

    /**
     * タスクの実行
     */
    public void doTask() {

        // ② Calculator.javaのすべてのオーバーロードメソッド「plus」に適当な引数を与え、下記画像のよう出力されるようコーディングしなさい。
        // 尚、「どのクラスから呼び出しているか」を明確にするため、plus()には呼び出し元のキーワードを付与すること。
    	Calculator calculator = new Calculator();
    	int a = calculator.plus(10); 
    	int b = calculator.plus(11,19);
    	int c = calculator.plus(11,19,30);
    	
    	System.out.println("plusメソッドの引数が一つの場合：" + a);
    	System.out.println("plusメソッドの引数が二つの場合：" + b);
    	System.out.println("plusメソッドの引数が三つの場合：" + c);
    	}
    
    	
    }