package study;

import java.util.Arrays;

//https://tech.kakao.com/2019/10/02/kakao-blind-recruitment-2020-round1/
//문제 4번
public class FindKeywordInLyric {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
		String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
		
		if (!(CommonValidate(words) && CommonValidate(queries))) return;
		
		words = ValidateWords(words);
		queries = ValidateQueries(queries);			
		
		FindKeyword(words, queries);
		//result : [3, 2, 4, 1, 0]
	}
	
	public static void FindKeyword(String[] words, String[] queries) {
		int offset1,offset2;
		int nDupCnt = 0; 
		for(int i = 0 ; i <queries.length; i ++) {
			
			if( queries[i].indexOf("?") == 0 ) {
				offset1 = queries[i].lastIndexOf("?") + 1;
				offset2 = queries[i].length(); 
			}else {
				offset1 = 0;
				offset2 = queries[i].indexOf("?");
			}
						
			for(int j = 0 ; j <words.length; j++) {
				if ( queries[i].length() == words[j].length() ) {
					if ( queries[i].substring(offset1, offset2).equals(words[j].substring(offset1, offset2)) ) {
						//System.out.print(queries[i].substring(offset1, offset2) + " : ");
						//System.out.println(words[j].substring(offset1, offset2));
						nDupCnt++;
					}
				}
			}
			System.out.println(nDupCnt);
			nDupCnt = 0;
		}
		
		
	}
	
	//words 배열 유효성검사 
	public static String[] ValidateWords(String[] words) {
		String[] inputWords = Arrays.copyOf(words, words.length);
		String[] newWords = null;
			
		//가사에 동일 단어가 여러 번 나올 경우 중복을 제거하고 words에는 하나로만 제공됩니다.
		int nNewWordsCnt = 0;
		for( int i = 0; i < inputWords.length; i++ ) {
			for(int j = 0 ; j < inputWords.length; j++ ) {
				if ( i == j ) continue;
				if ( inputWords[i].equals(inputWords[j]) ){
					inputWords[j] = "";
				}
			}
			if ( !inputWords[i].equals("") )
			{
				nNewWordsCnt++;
			}
		}
		newWords = new String[nNewWordsCnt];
		int idx = 0;
		for(int i = 0 ; i < inputWords.length; i++ ) {
			if ( !inputWords[i].equals("") ) {
				newWords[idx++]  = inputWords[i];
			}
		}		
		//각 가사 단어는 오직 알파벳 소문자로만 구성되어 있으며, 특수 문자나 숫자는 포함하지 않는 것으로 가정합니다.		
		return newWords;
	}
	//공통 유효성 검사
	public static boolean CommonValidate(String[] arr) {
		
		//words의 길이(가사 단어의 개수)는 2 이상 100,000 이하입니다.
		if( arr.length < 2 && arr.length >= 100000 ) {			
			System.out.println("배열의 크기는 2이상 100,000 이하여야합니다.");	return false;
		}
		//각 가사 단어의 길이는 1 이상 10,000 이하로 빈 문자열인 경우는 없습니다.
		//전체 가사 단어 길이의 합은 2 이상 1,000,000 이하입니다.
		int nWordCnt = 0;
		for(int i = 0 ; i < arr.length ; i++ )
		{
			if( arr[i].length() < 1 || arr[i].length() > 10000 || arr[i].isEmpty() ){
				System.out.println("각 검색 키워드의 길이는 1 이상 10,000 이하로 빈 문자열인 경우는 없습니다.");	return false;
			}
			nWordCnt += arr[i].length();
			if ( nWordCnt <= 2 || nWordCnt >= 1000000 ){
				System.out.println("전체 단어 길이의 합은 2 이상 1,000,000 이하입니다.");	return false;
			}
		}
		
		return true;
	}
	//쿼리 배열 유효성
	public static String[] ValidateQueries(String[] queries) {	
		//검색 키워드는 중복될 수도 있습니다.
		//각 검색 키워드는 오직 알파벳 소문자와 와일드카드 문자인 '?' 로만 구성되어 있으며, 특수 문자나 숫자는 포함하지 않는 것으로 가정합니다.
		//검색 키워드는 와일드카드 문자인 '?'가 하나 이상 포함돼 있으며, '?'는 각 검색 키워드의 접두사 아니면 접미사 중 하나로만 주어집니다.
		//예를 들어 "??odo", "fro??", "?????"는 가능한 키워드입니다.
		//반면에 "frodo"('?'가 없음), "fr?do"('?'가 중간에 있음), "?ro??"('?'가 양쪽에 있음)는 불가능한 키워드입니다.
		int nNewQueriesCnt = 0;
		for(int i = 0 ; i < queries.length; i++ )
		{
			if ( queries[i].indexOf("?") == -1 ) {
				queries[i] = "";
			}
			//if(접두사이면서 접미사이면서 전체가 ? 아닐경우)
			else if ( queries[i].charAt(0) == '?' && queries[i].charAt(queries[i].length()-1) == '?' )
			{				
				for ( int j = 0 ; j < queries[i].length(); j++ ){
					if( queries[i].charAt(j) != '?' ) {
						queries[i] = "";
					}
				}
			}
			//else if(접두사도아니고 접미사도 아닐경우)
			else if ( queries[i].charAt(0) != '?' && queries[i].charAt(queries[i].length()-1) != '?' )
			{
				queries[i] = "";
			}
			if ( !queries[i].equals("") )
			{
				nNewQueriesCnt++;
			}
		}
		String[] newQueries = new String[nNewQueriesCnt];
		int idx = 0;
		for(int i = 0; i < queries.length; i++ ){
			if( !queries[i].equals("") ) {
				newQueries[idx++] = queries[i];
			}				
		}
		
		return newQueries;
	}

}
