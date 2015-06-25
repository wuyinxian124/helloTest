package edu.scut.wusir.demo1;

public class Client {

    public static void main(String[] args) {
        //��װ������
        Handler handler1 = new ConcreteHandler();
        Handler handler2 = new ConcreteHandler();
        handler1.setSuccessor(handler2);
        //�ύ����
        handler1.handleRequest();
    }

    
    
}