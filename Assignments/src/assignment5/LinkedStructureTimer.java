package assignment5;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

import assignment5.AlgorithmTimer.TimeComplexity;


/**
 * 
 * @author Maks Cegielski-Johnson
 * @author William Guss
 * 
 * Timing class for the two Linked Structures, MyLinkedList and MyStack, timing in particular 
 * addFirst(), remove(i), get(i), and the 3 main MyStack methods, push(), pop(), peek().
 *
 */
public class LinkedStructureTimer {

	public static void main(String[] args){

		final MyLinkedList<Integer> link = new MyLinkedList<Integer>(); //MyLinkedList
		final ArrayList<Integer> arr = new ArrayList<Integer>(); //Java's ArrayList
		final LinkedList<Integer> list = new LinkedList<Integer>(); //Java's LinkedList
		
		AlgorithmTimer myAddTimer = new AlgorithmTimer("custLinkedAdd",
				new Process(){
					public void generateData(int n, TimeComplexity complexity){
						link.clear();
						for(int i = 0; i<n; i++)
							link.addFirst(i);
					}
					
					@Override
					public void run(int n) {
						link.addFirst(0);
					}
				},1000L);
		
		AlgorithmTimer javaAddTimer = new AlgorithmTimer("javaLinkedAdd",
				new Process(){
			
					@Override
					public void generateData(int n, TimeComplexity complexity)
					{
						list.clear();
						for(int i = 0; i<n; i++)
							list.addFirst(i);
					};
					
					@Override
					public void run(int n) {
						list.addFirst(0);
					}
				},1000L);
		
		
		AlgorithmTimer arrAddTimer = new AlgorithmTimer("javaArrAdd",
				new Process(){
					@Override
					public void generateData(int n, TimeComplexity complexity)
					{
						arr.clear();
						for(int i = 0; i<n; i++)
							arr.add(i);
					};
					@Override
					public void run(int n) {
							arr.add(0,0);
					}
				},1000L);
		
		
		
		//_--------------------------------------------------------------------------------------------------
		//--------------------------  GET
		//---------------------------------------------------------------------------------------------------
		
		AlgorithmTimer myGetTimer= new AlgorithmTimer("custLinkedGet",
				new Process(){
					public void generateData(int n, TimeComplexity complexity){
						link.clear();
						for(int i = 0; i<n; i++)
							link.addFirst(i);

					}
					
					@Override
					public void run(int n) {
							link.get(n);
						
					}
				},1000L);
		
		AlgorithmTimer javaGetTimer = new AlgorithmTimer("javaLinkedGet",
				new Process(){
					public void generateData(int n, TimeComplexity complexity){
						list.clear();
							for(int i = 0; i<n; i++)
								list.addFirst(i);
					}
					
					@Override
					public void run(int n) {
						list.get(n);
						
					}
				},1000L);
		
		
		AlgorithmTimer arrGetTimer = new AlgorithmTimer("javaArrGet",
				new Process(){
					@Override
					public void generateData(int n, TimeComplexity complexity)
					{
						arr.clear();
						for(int i = 0; i<n; i++)
							arr.add(i);
					};
					@Override
					public void run(int n) {
						arr.get(n);
					}
				},1000L);
		
		
		//_--------------------------------------------------------------------------------------------------
		//--------------------------  REMOVE
		//---------------------------------------------------------------------------------------------------
		
		AlgorithmTimer myRemoveTimer= new AlgorithmTimer("custLinkedRemove",
				new Process(){
					public void generateData(int n, TimeComplexity complexity){
						link.clear();
						for(int i = 0; i<n; i++)
							link.addFirst(i);

					}
					
					@Override
					public void run(int n) {
							link.remove(n/2);
						
					}
				},1000L);
		
		AlgorithmTimer javaRemoveTimer = new AlgorithmTimer("javaLinkedRemove",
				new Process(){
					public void generateData(int n, TimeComplexity complexity){
						list.clear();
							for(int i = 0; i<n; i++)
								list.addFirst(i);
					}
					
					@Override
					public void run(int n) {
						list.remove(n/2);
						
					}
				},1000L);
		
		
		AlgorithmTimer arrRemoveTimer = new AlgorithmTimer("javaArrRemove",
				new Process(){
					@Override
					public void generateData(int n, TimeComplexity complexity)
					{
						arr.clear();
						for(int i = 0; i<n; i++)
							arr.add(i);
					};
					@Override
					public void run(int n) {
						arr.remove(0);
					}
				},1000L);

		
//		arrRemoveTimer.generateAnalysis(1, 100000);
//		myRemoveTimer.generateAnalysis(1, 100000);
//		javaRemoveTimer.generateAnalysis(1, 100000);
		
		final MyStack<Integer> stack = new MyStack<Integer>();
		
		AlgorithmTimer pushTimer = new AlgorithmTimer("stackPush",
				new Process(){
					@Override
					public void generateData(int n, TimeComplexity complexity)
					{stack.clear(); for(int i = 0; i < n; i++) stack.push(n);};

					@Override
					public void run(int n) {
						// TODO Auto-generated method stub
						stack.push(n);
					}
		
		},4000L);
		
		AlgorithmTimer popTimer = new AlgorithmTimer("stackPop",
				new Process(){
					@Override
					public void generateData(int n, TimeComplexity complexity)
					{stack.clear(); for(int i = 0; i < n; i++) stack.push(n);};

					@Override
					public void run(int n) {
						// TODO Auto-generated method stub
						stack.pop();
						
					}
		
		},4000L);
		
		AlgorithmTimer peekTimer = new AlgorithmTimer("stackPeek",
				new Process(){
					@Override
					public void generateData(int n, TimeComplexity complexity)
					{stack.clear(); for(int i = 0; i < n; i++) stack.push(n);};

					@Override
					public void run(int n) {
						stack.peek();
					}
		
		},4000L);

		pushTimer.generateAnalysis(100000, 1000000);
		peekTimer.generateAnalysis(100000, 1000000);
		popTimer.generateAnalysis(100000, 1000000);
	}
}
