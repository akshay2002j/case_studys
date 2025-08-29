package org.example.queue;

import org.example.map.LinkedHashMapPractice;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class QueuePractice {


    public static void main(String[] args) {
        Deque<Integer> queue = new LinkedList<>();

        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.offer(50); //-->Incase of arrayblocking queue it gives false
        System.out.println(queue);
//        System.out.println(queue.poll()); //->poll does not give excep it gives null
//        System.out.println(queue.peek()); //-->return null if queue is empty
//        System.out.println(queue.remove()); //gives Excep id empty
//        System.out.println(queue.element()); //only head of queue is retrived throws exception if empty
        System.out.println(queue.spliterator().characteristics());
        System.out.println(queue.peekFirst());
        System.out.println(queue.peekLast());
//        System.out.println(queue.pollFirst());
//        System.out.println(queue.pollLast());
        //Iterator<Integer> iterator =  queue.descendingIterator(); //-->return iterator in reversed order
        Iterator<Integer> iterator = queue.iterator();

        while (iterator.hasNext()){
            System.out.print("Element:- " +iterator.next() + " ");
        }
        System.out.println();
        System.out.println( queue.reversed()); //reverse view
        System.out.println(queue.pop()); //removes and return the first element fom front of queue
        System.out.println(queue.removeLast());

        System.out.println( queue+" the size is :-  " + queue.spliterator().getExactSizeIfKnown());
        queue.spliterator().trySplit().tryAdvance(System.out::println);
        System.out.println(queue);
        //System.out.println(queue.add(null));

        System.out.println("-------PriorityQueue-----------");
        /// Creates a PriorityQueue with the default initial capacity (11) that orders its elements according to their natural ordering.
        /*
            PriorityQueue()                             // Natural ordering
            PriorityQueue(int initialCapacity)         // With initial size
            PriorityQueue(Comparator<? super E> comp)  // With custom comparator
            PriorityQueue(Collection<? extends E> c)   // From another collection
 */
        Queue<Integer> prior = new PriorityQueue<>(); //default capacity is 11
        prior.add(100);
        prior.add(200);
        prior.add(600);
        prior.addAll(queue);
        prior.add(300);
        System.out.println(prior);

       // prior.retainAll(queue); //-->it keeps only the common elements (intersection) between two collections
        System.out.println(prior);
        System.out.println(queue);
    }
}
