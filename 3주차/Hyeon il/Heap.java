import java.util.Arrays;
import java.util.PriorityQueue;

public class Heap {
	public int Solution(int[] arr,int k) {
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		int comp=0;
		for(int i =0;i<arr.length;i++) {
			heap.add(arr[i]);
		}
		int i=0;
		
		while(heap.size()>=2) {
			comp=heap.poll();
			if(comp>=k) {
				return i;
			}
			comp = comp + (heap.poll()*2);
			heap.add(comp);
			i++;
		}
		return -1;
	}
	public static void main(String[] args) {
		int[] arr = {1,2,3,9,10,12};
		int k = 100000000;
 		Heap sol = new Heap();
 		System.out.println(sol.Solution(arr, k));
	}

}
