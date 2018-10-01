package io.github.symonk.common.aop;


public class SomeThing {

    @AttachPostConstruct
    public SomeThing() {
    System.out.println("POST CONSTRUCT!!!!");
    }

    @Override
    public String toString() {
        return "overriddeeennnn!";
    }

    public void speak() {
    System.out.println("aop");
    }
}
