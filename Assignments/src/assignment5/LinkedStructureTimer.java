package assignment5;

import java.util.ArrayList;
import java.util.LinkedList;

public class LinkedStructureTimer {

	public static void main(String[] args){
		MyLinkedList<Integer> link = new MyLinkedList<Integer>(); //MyLinkedList
		ArrayList<Integer> arr = new ArrayList<Integer>(); //Java's ArrayList
		LinkedList<Integer> list = new LinkedList<Integer>(); //Java's LinkedList
		
		for(int i = 0; i<1000; i++)
			continue;
		
		long end;
		long start = System.nanoTime();
		
		//Time add first:
		
		for(int i = 0; i<20000; i++)
			link.addFirst(i);
		end = System.nanoTime();
		
		System.out.println("My Linked List " + (end - start));
		
		start = System.nanoTime();
		
		for(int i = 0; i<20000; i++)
			arr.add(0,i);
		end = System.nanoTime();
		System.out.println("ArrayList " + (end - start));
		
		start = System.nanoTime();
		for(int i = 0; i<20000; i++)
			list.addFirst(i);
		end = System.nanoTime();
		System.out.println("Java Linked List " + (end - start));
		
		System.out.println("\nGET METHOD: \n");
		
		//Time get:
		
		
		start = System.nanoTime();
		link.get(0);
		link.get(10000);
		link.get(19999);
		
		
		end = System.nanoTime();
		System.out.println("My Linked List " + (end - start));
		
		start = System.nanoTime();
		
		
		arr.get(10000);
		arr.get(0);
		arr.get(19999);
		
		end = System.nanoTime();
		System.out.println("ArrayList " + (end - start));
		
		start = System.nanoTime();
		
		
		list.get(0);
		list.get(10000);
		list.get(19999);
		end = System.nanoTime();
		System.out.println("Java Linked List " + (end - start));
		
		System.out.println("\n REMOVE METHOD:  \n");
		int k;
//		Time remove:
		for(int i = 0; i<1000; i++)
			k = i;
		
		start = System.nanoTime();
		link.remove(0);
		link.remove(9998);
		link.remove(19997);
		end = System.nanoTime();
		System.out.println("My Linked List " + (end - start));
		
		start = System.nanoTime();
		
		arr.remove(0);
		arr.remove(9998);
		arr.remove(19997);
		end = System.nanoTime();
		System.out.println("ArrayList " + (end - start));
		
		start = System.nanoTime();
		
		
		list.remove(0);
		list.remove(9998);
		list.remove(19997);
		end = System.nanoTime();
		System.out.println("Java Linked List " + (end - start));
		
		
		
		//Time stack:
		MyStack<Integer> stack = new MyStack<Integer>();
		
		for(int i = 0; i<20000; i++)
			stack.push(i);
		
		stack.peek();
		
		for(int i = 0; i <20000; i++)
			stack.pop();
	}
}
