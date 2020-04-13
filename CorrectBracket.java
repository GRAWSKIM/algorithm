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
		//1. �Է��� �� ���ڿ��� ���, �� ���ڿ��� ��ȯ�մϴ�.
		if(w == null || w.isEmpty()) return "";			
		
		String u = "", v = "";
		Map<String, String> r = splitBracket(w, u, v);
		String flag = r.get("RESULT");
		if( flag.equals("OK") ) {
			return w;
		}
		u = r.get("u");
		v = r.get("v");
		//3. ���ڿ� u�� "�ùٸ� ��ȣ ���ڿ�" �̶�� ���ڿ� v�� ���� 1�ܰ���� �ٽ� �����մϴ�.
		if(isCorrect(u)) {
			 //3-1. ������ ��� ���ڿ��� u�� �̾� ���� �� ��ȯ�մϴ�.			
			return u + makeCorrectBracket(v); 
		}
		//4. ���ڿ� u�� "�ùٸ� ��ȣ ���ڿ�"�� �ƴ϶�� �Ʒ� ������ �����մϴ�.
		//4-1. �� ���ڿ��� ù ��° ���ڷ� '('�� ���Դϴ�.
		String s = "(";
		//4-2. ���ڿ� v�� ���� 1�ܰ���� ��������� ������ ��� ���ڿ��� �̾� ���Դϴ�.
		s += makeCorrectBracket(v);
		//4-3. ')'�� �ٽ� ���Դϴ�.
		s += ")";
		//4-4. u�� ù ��°�� ������ ���ڸ� �����ϰ�, ������ ���ڿ��� ��ȣ ������ ����� �ڿ� ���Դϴ�.
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
		
		//4-5. ������ ���ڿ��� ��ȯ�մϴ�.		
		return s;
	}
	
	public static Map<String, String> splitBracket(String w, String u, String v) {
		//2. ���ڿ� w�� �� "�������� ��ȣ ���ڿ�" u, v�� �и��մϴ�. ��, u�� "�������� ��ȣ ���ڿ�"�� �� �̻� �и��� �� ����� �ϸ�, v�� �� ���ڿ��� �� �� �ֽ��ϴ�.
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
