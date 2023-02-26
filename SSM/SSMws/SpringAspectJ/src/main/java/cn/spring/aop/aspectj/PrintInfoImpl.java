package cn.spring.aop.aspectj;

public class PrintInfoImpl implements IPrintInfo{
    @Override
    public void printMessage(String str) {
        System.out.println(str + " say hello" );
    }

    @Override
    public void showMessage() {
        System.out.println("show hello string");
    }

    @Override
    public void editMessage() {
        System.out.println("edit hello string");
    }
}
