package study;

import java.util.HashMap;

public class SichuanCastle {

	public static void main(String[] args) {
		String[] arr  = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
		String[] arr1 = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
		
		System.out.println(eraseBlock(toCharArray(arr)));
		System.out.println(eraseBlock(toCharArray(arr1)));
	}
	
	public static char[][] toCharArray(String[] arr) {
		if(arr == null) return null;
		int m = arr.length;
		int n = arr[0].length();
		
		char charry[][] = new char[m][n];
		
		for(int i = 0 ; i < arr.length ; i++ ) {
			for(int j = 0 ; j < arr[i].length(); j++) {
				charry[i][j] = arr[i].charAt(j);
				System.out.print(charry[i][j] + " ");
			}
			System.out.println();
		}			
		System.out.println();
		return charry;
	}

	public static int eraseBlock(char[][] arr) {
		if(arr == null) return -1;
		
		HashMap<String, String> block = new HashMap<>();
		String tmp ="";
		
		for(int i = 0 ; i < arr.length -1  ; i++ ) {
			for(int j = 0 ; j < arr[i].length - 1 ; j++) {
				if( arr[i][j] != ' ' && arr[i][j] == arr[i][j+1] && arr[i][j] == arr[i+1][j] && arr[i][j] == arr[i+1][j+1] ) {					
					block.put( tmp.format("%d%d", i,j)  , "");
					block.put( tmp.format("%d%d", i,j+1)  , "");	
					block.put( tmp.format("%d%d", i+1,j)  , "");	
					block.put( tmp.format("%d%d", i+1,j+1)  , "");	
				}
			}
		}	
		
		if(block.size() == 0) {
			return 0;
		}else {
			int a,b;
			 for( String key : block.keySet()){	            		  
	            a = Integer.parseInt(key.substring(0, 1));
	            b = Integer.parseInt(key.substring(1, 2));
	           
	            arr[a][b] = ' ';
	        }
			 
			for(int i = 0 ; i < arr.length ; i++ ) {
					for(int j = 0 ; j < arr[i].length; j++) {						
						if(arr[i][j] == ' ') {
							for(int k = i ;  k > 0 ; k--) {
								arr[k][j] = arr[k-1][j];
								arr[k-1][j] = ' ';
							}
						}
				}
			}	
	      	        
		}
	/*	
	   for(int i = 0 ; i < arr.length ; i++ ) {
			for(int j = 0 ; j < arr[i].length; j++) {
				
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}			
		System.out.println();
        */
		return block.size() + eraseBlock(arr);			
				
	}
}
