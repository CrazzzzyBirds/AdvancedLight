package com.heima.advancedlight.section4;

public class Test {

     class  MyRunnable implements Runnable {
        private  volatile boolean on=true;
        @Override
        public void run() {
            while (on){
                //
            }
        }

        public void cancel(){
            on=false;
        }
    }

    private void  test(){
        MyRunnable runnable=new MyRunnable();
        Thread thread=new Thread(runnable);
        runnable.cancel();

    }

    Object objectLock=new Object();
    private synchronized void testLock() throws InterruptedException {
         wait();
         notifyAll();

                                           
         synchronized (objectLock){

         }
    }


}

