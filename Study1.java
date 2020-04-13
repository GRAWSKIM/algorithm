package study;

public class Study1 {

	public static void main(String[] args) {
		String[] arr1 = {"01001","10100","11100","10010","01011"};
		String[] arr2 = {"11110","00001","10101","10001","11100"};
		
		char[] temp = null;
		char[] temp2 = null;
		
		boolean v1,v2;
		for(int i = 0 ; i < arr1.length ; i++) {
			temp = arr1[i].toCharArray();
			temp2 = arr2[i].toCharArray();
			
			for(int j = 0 ; j<temp.length ; j++) {
				if( temp[j] == '0' ) v1 = false; 
				else v1 = true;
				
				if( temp2[j] == '0' ) v2 = false; 
				else v2 = true;
				
				if( v1 | v2) System.out.print("#");
				else System.out.print(" ");
			}
			
			System.out.println();
		}

	}
}
