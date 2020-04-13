package study;

import java.util.ArrayList;

public class HashLock {

	public static void main(String[] args) {
		int key[][] = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};	
		int lock[][] = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
		
		if( Solution(key, lock) == true ) {
			System.out.println("true");
		}else {
			System.out.println("false");
		}
	}
	
	public static Boolean Solution (int key[][], int lock[][]) {
				
		int idx = 0;
		
		boolean bFind = false;
		while( idx < 3 ) {			
			
			if( matchKey(key, lock) == false ) {				
				key = turn(key);				
			}else {
				break;
			}			
			idx++;
		}

		
		return true;
	}
	
	//key와 lock을 비교한다			
	public static boolean matchKey( int key[][], int lock[][] ) {
		
		ArrayList<String> arr1 = new ArrayList<>();
		
		for(int x = 0 ; x < lock.length ; x++  ) {			
			for(int y = 0 ; y < lock[x].length; y++) {			
				arr1.add( x + "," + y );
			}
		}
		
		ArrayList<String> arr2 = new ArrayList<>();
		
		for(int x = 0 ; x < key.length ; x++  ) {			
			for(int y = 0 ; y < key[x].length; y++) {
				if( key[x][y] == 0 ) {
					arr2.add( x + "," + y );
				}
			}
		}
		
		if( arr2.size() < arr1.size() ) return false;
		
		int a = -1, b = -1;
		ArrayList<String> arr3 = new ArrayList<>();
		for(int i = 0 ; i < arr1.size() ; i++) {
			String[] ab = arr1.get(i).split(",");			
			if( i == 0 ) {				
				a = Integer.parseInt(ab[0]);
				b = Integer.parseInt(ab[1]);
			}else {				
				arr3.add( "a" + (a - Integer.parseInt(ab[0])) + ",b" + (b - Integer.parseInt(ab[1])) );
			}
		}
		
		ArrayList<String> arr4 = new ArrayList<>();
		for(int i = 0 ; i < arr2.size() ; i++) {
			String[] ab = arr1.get(i).split(",");			
			if( i == 0 ) {				
				a = Integer.parseInt(ab[0]);
				b = Integer.parseInt(ab[1]);
			}else {				
				arr4.add( "a" + (a - Integer.parseInt(ab[0])) + ",b" + (b - Integer.parseInt(ab[1])) );
			}
		}
		
		boolean match = false;
		
		for(int i = 0 ; i < arr3.size() ; i++ ) {
			if(i< arr4.size() ) {
				arr3.get(i);
			}else {
				match = false;
			}			
		}
		
		return match;
	}
	
	//90도 회전 
	public static int[][] turn(int key[][]) {
		
		int[][] key2 = new int[key.length][key.length];
		
		for(int x = 0 ; x < key.length ; x++  ) {			
			for(int y = 0 ; y < key[x].length; y++) {
				key2[x][y] = key[(key[x].length-1) - y][x];
			}
		}
		
		return key2;		
	}
	
	public static void print(int key[][]) {
		
		for(int x = 0 ; x < key.length ; x++  ) {			
			for(int y = 0 ; y < key[x].length; y++) {
				System.out.print(key[x][y] + " ");				
			}
			System.out.println();
		}
	}
	
	
}