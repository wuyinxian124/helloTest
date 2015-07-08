package edu.scut.procon;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Test1 

{
    private int queueSize = 12;
   // private List<Integer> queue = new ArrayList<Integer>(queueSize);
   private Queue<String> queue = new LinkedList<String>(); 
   
   
    public static void main(String[] args)  {
        Test1 test = new Test1();
        Producer producer = test.new Producer();
        Consumer consumer = test.new Consumer();
          
        producer.start();
        consumer.start();
    }
      
    class Consumer extends Thread{
          
    	private List<String> getMsg;
    	private int Priority;
    	
    	public Consumer(){
    		
    		getMsg = new ArrayList<String>();
    		Priority = 0;
    	}
        @Override
        public void run() {
        	Thread.currentThread().setName("com");
            consume();
        }
          
        private void consume() {
            while(true){
                synchronized (queue) {
                    while(queue.size() == 0){
                        try {
                            System.out.println("队列空，等待数据");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    int size = queue.size();
                    if(size>=9||Priority>5){
                    	for(int i=0;i<size;i++)
                    		getMsg.add(queue.poll());          //每次移走队首元素

                    	System.out.println("从队列取走"+size+"个元素，队列剩余"+queue.size()+"个元素");
                    }else{
                    	Priority++;
                    }
                    getMsg.clear();
                    queue.notify();
                    
                }
            }
        }
    }
      
    class Producer extends Thread{
          
        @Override
        public void run() {
        	Thread.currentThread().setName("pro");
            produce();
        }
          
        private void produce() {
            while(true){
                synchronized (queue) {
                    while(queue.size() == queueSize){
                        try {
                            System.out.println("队列满，等待有空余空间");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    queue.offer(1+"");        //每次插入一个元素
                    queue.notify();

                    System.out.println("向队列取中插入一个元素，队列剩余空间："+(queueSize-queue.size()));
                }
                try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {}
            }
        }
    }
}
