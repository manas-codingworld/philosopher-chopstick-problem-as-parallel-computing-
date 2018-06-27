package com.practice.parallelcomputing;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {
	
	private int id;
	Lock lock;
	
	public Chopstick(int id) {
		this.id=id;
		lock=new ReentrantLock();
	}
	
	public boolean pickUp(Philosopher philospher, State state) throws InterruptedException{
		
		if(this.lock.tryLock(10, TimeUnit.MILLISECONDS)){
			System.out.println(philospher+" picked up "+state.toString()+" "+this);
			return true;
		}	
		return false;		
	}
	
	public void putDown(Philosopher philospher){
		this.lock.unlock();
		System.out.println(philospher+" put down "+this);
	}
	
	@Override
	public String toString() {
		return "Chopstick:"+this.id;
	}

}
