package com.practice.parallelcomputing;

import java.util.Random;

public class Philosopher implements Runnable{
	private int id;
	private Chopstick leftChopstick;
	private Chopstick rightChopstick;
	private volatile boolean  isFull=false;
	private Random random;
	private int eatingCounter;
	


	Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick){
		this.id=id;
		this.leftChopstick=leftChopstick;
		this.rightChopstick=rightChopstick;
		this.random=new Random();
	}

	@Override
	public void run() {
		try {
			while(!isFull){
				System.out.println(this+" is thinking...");
				Thread.sleep(this.random.nextInt(1000));
				if(leftChopstick.pickUp(this, State.LEFT)){
					if(rightChopstick.pickUp(this, State.RIGHT)){
						System.out.println(this+" is eating...");
						this.eatingCounter++;
						Thread.sleep(this.random.nextInt(1000));
						rightChopstick.putDown(this);
					}
					leftChopstick.putDown(this);
				}
			} 			
		}catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
	public int getEatingCounter(){
		return this.eatingCounter;
	}
	
	public void setFull(boolean isFull){
		this.isFull = isFull;
	}
	
	@Override
	public String toString() {
		return "Philosopher:"+this.id;
	}
	
	
}
