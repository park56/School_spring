package org.example;

public class Main2 {

    public static void main(String[] args) {    // static은 static영역에 올라가 프로그램이 종료될 때 까지 지속됨ㅌㅈ

        Test t1 = Test.getInstance();
        Test t2 = new Test();   // c의 malloc java의 new는 heap영역에 생성되고 free등의 실행으로 직접 제거
        Test t3 = Test.getInstance();

        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);

        if (t1 == t3){
            System.out.println("같음");
        }
        if (t1 != t2){
            System.out.println("다름");
        }
    }
}
