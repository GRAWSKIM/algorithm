package study;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class NewsClustering {

	public static void main(String[] args) {
		
		GetSimil("FRANCE","french");
		GetSimil("handshake","shake hands");
		GetSimil("aa1+aa2","AAAA12");
		GetSimil("E=M*C^2","e=m*c^2");
		
	}
	
	public static double GetSimil(String str1, String str2) {
		
		Double result = 0.0;
		
		result =  (double)GetSet(SplitStr(str1),SplitStr(str2),"n") / (double)GetSet(SplitStr(str1),SplitStr(str2),"u")  ;
		System.out.println( " 답 : " + (int) ( result * 65536));
		return (int) ( result * 65536);
	}
	
	public static ArrayList<String> SplitStr(String str) {
		
		ArrayList<String> arr = new ArrayList<>();
		
		String tmp = "",tmp2 = "";
		
		Boolean isExcept = false;
		/*
		 * char tmp3;
		System.out.println();
		String str2 = str;
		for(int i = 0 ; i < str.length() ; i++ ) {
			tmp2 = String.valueOf(str.charAt(i));
			
			if( tmp2.contains(" ") || Pattern.matches("^[0-9]*$", tmp2) || Pattern.matches("[=+!@#$%^&*(),.?\\\\\\\":{}|<>]", tmp2)   ) {
				System.out.println("tmp2 : " + tmp2);
				str2 = str2.replace(tmp2, "");
			}
		}
		System.out.println("확인 : " + str2);
		str = str2;
		*/
		for(int i = 0 ; i < str.length() -1 ; i++ ) {
			
			tmp = str.substring(i, i+2);
			for(int j = 0 ; j < tmp.length() ; j++) {
				
				tmp2 = String.valueOf(tmp.charAt(j));
				
				if(tmp2.contains(" ") || Pattern.matches("^[0-9]*$", tmp2) || Pattern.matches("[+=!@#$%^&*(),.?\\\\\\\":{}|<>]", tmp2) ) isExcept = true;
				
			}
			
			if(!isExcept) { 
				arr.add(tmp);
				System.out.print( " " + tmp);
			}
			tmp.isEmpty();
			isExcept = false;
			
		}
		
		System.out.println();
		return arr;
	}

	public static int GetSet (ArrayList<String> arr1 , ArrayList<String> arr2, String flag) {
		int cnt = 0;
		
		int wordcnt = 0;
		
		Map<String, Integer> mCnt = new HashMap<String, Integer>();
		ArrayList<String> aCnt = new ArrayList<>();
		
		if(flag.equals("n")) {
			for(int i = 0 ; i < arr1.size() ; i++) {
				
				if( mCnt.get(arr1.get(i).toUpperCase()) == null ) {
					mCnt.put(arr1.get(i).toUpperCase(), 1);
				}else {
					wordcnt = mCnt.get(arr1.get(i).toUpperCase());
					mCnt.put(arr1.get(i).toUpperCase(), wordcnt+1);
				}
				wordcnt = 0;
			}
			
			for(int i = 0 ; i < arr2.size() ; i++) {
				
				if( mCnt.get(arr2.get(i).toUpperCase()) == null ) {
					mCnt.put(arr2.get(i).toUpperCase(), 1);
				}else {
					wordcnt = mCnt.get(arr2.get(i).toUpperCase());
					mCnt.put(arr2.get(i).toUpperCase(), wordcnt+1);
				}
				wordcnt = 0;
				
			}
			//System.out.println( "mcnt.size : " + mCnt.size());
			
			for(Map.Entry<String, Integer> entry :  mCnt.entrySet()) {
				//System.out.println( " mCnt.get(entry.getKey()) : " +  mCnt.get(entry.getKey()) );
				if ( mCnt.get(entry.getKey()) > 1 ) cnt++; 
				 
			}
			

			
		}else if(flag.equals("u")) {
			//합집합
			
			
			for(int i = 0 ; i < arr1.size() ; i++) {
				mCnt.put(arr1.get(i).toUpperCase(), 1);
			}
			
			for(int i = 0 ; i < arr2.size() ; i++) {
				mCnt.put(arr2.get(i).toUpperCase(), 1);
			}
			
			cnt = mCnt.size();
			
		
		}
		System.out.println("-------------------");
		for(Map.Entry<String, Integer> entry :  mCnt.entrySet()) {
			
			 System.out.println(entry.getKey());   
			 
		}
		System.out.println("-------------------");
		

	//	System.out.println("flag : " + flag + ",  cnt : " +cnt );
		return cnt == 0 ? 1 : cnt;
	}
	
}
