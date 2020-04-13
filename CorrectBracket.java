package study;

import java.util.HashMap;
import java.util.Map;

public class CorrectBracket {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println( makeCorrectBracket("(()())()") );
		System.out.println( makeCorrectBracket(")(") );
		System.out.println( makeCorrectBracket("()))((()") );
	}
	
	public static String makeCorrectBracket(String w) {
		//1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
		if(w == null || w.isEmpty()) return "";			
		
		String u = "", v = "";
		Map<String, String> r = splitBracket(w, u, v);
		String flag = r.get("RESULT");
		if( flag.equals("OK") ) {
			return w;
		}
		u = r.get("u");
		v = r.get("v");
		//3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
		if(isCorrect(u)) {
			 //3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.			
			return u + makeCorrectBracket(v); 
		}
		//4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.
		//4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
		String s = "(";
		//4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
		s += makeCorrectBracket(v);
		//4-3. ')'를 다시 붙입니다.
		s += ")";
		//4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
		char[] a = u.toCharArray();
		for(int i = 0 ; i < a.length ; i++ ) {
			if(i == 0 || i == a.length-1 ) {
				a[i] = ' ';
			}
		}
		String s2 = String.valueOf(a);
		s2 = s2.trim();
		for(int i = 0 ; i < s2.length() ; i++ ) {
			if ( s2.substring(i, i+1).equals(")") ) {
				s += "(";
			}else {
				s += ")";
			}
		}
		
		//4-5. 생성된 문자열을 반환합니다.		
		return s;
	}
	
	public static Map<String, String> splitBracket(String w, String u, String v) {
		//2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다.
		Map<String, String> r = new HashMap<String, String>();
		if(isCorrect(w)) {
			r.put("RESULT", "OK");
			return r;
		}
		
		r.put("RESULT", "X");
		
		if(isBalance(w)) {
			r.put("u", w);
			r.put("v", "");
		}else {
			for(int i = w.length() ; i > 0 ; i-- ) {
				if( isCorrect(w.substring(0, i)) || isBalance(w.substring(0, i) ) )  {
					r.put("u", w.substring(0, i));
					r.put("v", w.substring(i,w.length()));
					break;
				}
			}
		}
		
		return r;
	}
	
	public static Boolean isBalance(String w) {
		int openCnt = 0, closeCnt = 0;
		
		for(int i = 0 ; i < w.length() ; i++ ) {
			if ( w.substring(i, i+1).equals(")") ) {
				closeCnt++;
			}else {
				openCnt++;
			}
		}
		if(openCnt == closeCnt) {
			return true;
		}else {
			return false;
		}			
	}
	
	public static Boolean isCorrect(String w) {
		
		if( w.length() == 0 || !isBalance(w)) return false;
		
		char[] a = w.toCharArray();
		
		boolean removeflag = false;
		if( a[0] == ')' || a[a.length - 1] == '(' )  return false;
		
		for(int i = 0 ; i < a.length ; i++ ) {
			if(a[i] == '(') {
				for(int j = i ; j < a.length ; j++ ) {
					if( removeflag == false && a[j] == ')') {
						a[i] = ' ';
						a[j] = ' ';
						removeflag = true;
					}
				}
				removeflag = false;
			}
		}
		
		for(int i = 0; i < a.length ; i++ ) {
			if( a[i] != ' ' ) {
				return false;
			}
		}
		
		return true;
	}
}
