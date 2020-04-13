package study;

import java.util.regex.Pattern;

public class CompStrSol {

	public static void main(String[] args) {

		Solution("aabbaccc");
		Solution("ababcdcdababcdcd");
		Solution("abcabcdede");
		Solution("abcabcabcabcdededededede");
		Solution("xababcdcdababcdcd");
		
	}

	public static int Solution(String str) {		
		//문자열 최대길이 에서부터 1 까지 압축실행 그중 제일 낮은 값 return		
		int min = str.length(),tmp = -1;
		for(int i = 1 ; i <= ( Math.ceil(str.length()/2 )) ; i++) {
			tmp = Comp(str,i);
			if( min > tmp ) {
				min = tmp;
			}		
		}
		System.out.println(str + " : " + min);
		return min;
	}
	
	public static int Comp(String str, int cnt) {
		
		String comp1 = "",comp2 = "";
		String tmp = "";
		int wordcnt = 1;
		for(int i = 0 ; i < str.length() ; i = i+cnt ) {
			if(str.length() <  i + cnt ) {
				break;
			}
			comp1 = str.substring( i , i + cnt );
			if(str.length() <  i + cnt + cnt ) {
				if(wordcnt == 1) {
					tmp += comp1;
				}else {
					tmp += wordcnt + comp1;
				}
				if(str.length() > i + cnt) {
					tmp += str.substring( i + cnt , str.length() );
				}
				break;
			}
			comp2 = str.substring( i + cnt , i + cnt  + cnt );
			
			if( comp1.equals(comp2) ) {
				wordcnt++;
			}else {
				if(wordcnt == 1) {
					tmp += comp1;
				}else {
					tmp += wordcnt + comp1;
				}
				wordcnt = 1;
			}
		}
		
		//System.out.println(cnt +" : "+ tmp  +" : "+ tmp.length()) ;
		
		if(tmp.equals(str)) return str.length();
		
		String str1 = "", str2 = "", descstr = "";
		int idx = 0;
		while(true) {
			
			if( idx + 1 > tmp.length() || idx + cnt > tmp.length()) {
				if ( cnt > (tmp.length() - (idx + cnt)) ) {
					descstr += tmp.substring(idx, tmp.length());
				}
				break;
			}				
			
			str1 = tmp.substring(idx, idx+1);
			
			if( Pattern.matches("^[0-9]*$", str1) ) {
				idx++;
				for(int j = 0 ; j < Integer.parseInt(str1) ; j++) {
					str2 += tmp.substring(idx, idx + cnt);										
				}
				descstr += str2;
				str2= "";
			}else {
				descstr += tmp.substring(idx, idx + cnt);
				
			}
			
			
			idx += cnt;
		}

		//System.out.println( tmp + " : " + descstr + " : " + str );
		return descstr.equals(str) ? tmp.length() : 999999;
		/*
		String tmp = "";
		int idx = 0, wordcnt = 1;
		
		while (true) {

			if( str.substring( idx, idx+cnt ).equals(str.substring( idx+cnt, idx + cnt + cnt ) ) ){
				wordcnt++;
			}else {	
				if( wordcnt == 1 ) {
					tmp += str.substring( idx, idx+cnt );
				}else {
					tmp += wordcnt + str.substring( idx, idx+cnt );
				}
				wordcnt = 1;								
			}
			idx += cnt;
						
			
			if(idx + cnt + cnt >= (str.length()) ) {
				
				if( str.substring( idx, idx+cnt ).equals(str.substring( idx+cnt, idx + cnt + cnt ) ) ){
					wordcnt++;	
				}
				if( wordcnt == 1 ) {
					tmp += str.substring( idx, idx+cnt );
				}else {
					tmp += wordcnt + str.substring( idx, idx+cnt );
				}
				break;
			}
		}
		
		String str1 = "", str2 = "", descstr = "";
		for(int i = 0 ; i < tmp.length() ; i++ ) {
			str1 = tmp.substring(i, i+1);
			if( Pattern.matches("^[0-9]*$", str1) ) {
				for(int j = 0 ; j < Integer.parseInt(str1) ; j++) {
					str2 += tmp.substring(i+1, i+2);
				}				
				descstr += str2;
				i++;
			}else {
				descstr += str1;
			}
			str2 = "";			
		}
		System.out.println( tmp + " : " + descstr + " : " + str );
		return descstr.equals(str) ? tmp.length() : 999999;
		*/
	}
}