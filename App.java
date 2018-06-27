package com.practice.parallelcomputing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

	public static void main(String[] args) throws InterruptedException {

		Philosopher[] philosophers=new Philosopher[5];
		Chopstick[] chopsticks=new Chopstick[5];
		ExecutorService service=Executors.newFixedThreadPool(5);
		try{
			for(int i=0;i<5;i++){
				chopsticks[i]=new Chopstick(i+1);
			}
			
			
			for(int i=0;i<5;i++){
				philosophers[i]=new Philosopher(i+1,chopsticks[i],chopsticks[(i+1)%5]);
				service.execute(philosophers[i]);
			}
			Thread.sleep(7000);
			for(int i=0;i<5;i++){
				philosophers[i].setFull(true);
			}
		}finally{
			
			service.shutdown();
			
			while(!service.isTerminated()){
				Thread.sleep(1000);
			}
			
			for(Philosopher philosopher : philosophers ){
				System.out.println(philosopher+" eat #"+philosopher.getEatingCounter());
			}
			
		}
		
	}

}
