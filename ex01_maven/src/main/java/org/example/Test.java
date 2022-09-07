package org.example;

public class Test {     // class명 file명 동일 필요
    private static Test test;

    public static Test getInstance(){
        if (test == null) {
            test = new Test();
        }
        return Test.test;
    }
}
