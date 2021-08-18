package com.trab.ufc.concorrente;

import java.util.Iterator;
import java.util.Vector;

public class ProdutorConsumidor {
	private static Vector<Object> buffer = new Vector<Object>();
 
	public static void main(String[] args) {
		new Produtor().start();
		new Consumidor().start();
	}
 
	public static class Consumidor extends Thread {
		Consumidor() {
			super("Consumidor");
		}
 
		@Override
		public void run() {
			while(true){
				try {
					Thread.sleep(1000);
					System.out.println("Buffer Vazio ~~~~~~~~~~~~~~~");
				} catch (Exception e) {
					e.printStackTrace();
				}
 
				synchronized (buffer) {
					Iterator it = buffer.iterator();
					while (it.hasNext())
						it.next();
				}
			}
		}
	}
 
	public static class Produtor extends Thread {
		Produtor() {
			super("Produtor");
		}
 
		@Override
		public void run() {
			while(true){
				try {
					Thread.sleep(1000);
					System.out.println("Buffer Cheio @@@@@@@@@@@@@@@");
				} catch (Exception e) {
					e.printStackTrace();
				}
				buffer.add(new Object());
				if (buffer.size() > 1000)
					buffer.remove(buffer.size() - 1);
			}
		}
	}
}
