package study;

import java.util.Arrays;

//https://tech.kakao.com/2019/10/02/kakao-blind-recruitment-2020-round1/
//���� 4��
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
	
	//words �迭 ��ȿ���˻� 
	public static String[] ValidateWords(String[] words) {
		String[] inputWords = Arrays.copyOf(words, words.length);
		String[] newWords = null;
			
		//���翡 ���� �ܾ ���� �� ���� ��� �ߺ��� �����ϰ� words���� �ϳ��θ� �����˴ϴ�.
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
		//�� ���� �ܾ�� ���� ���ĺ� �ҹ��ڷθ� �����Ǿ� ������, Ư�� ���ڳ� ���ڴ� �������� �ʴ� ������ �����մϴ�.		
		return newWords;
	}
	//���� ��ȿ�� �˻�
	public static boolean CommonValidate(String[] arr) {
		
		//words�� ����(���� �ܾ��� ����)�� 2 �̻� 100,000 �����Դϴ�.
		if( arr.length < 2 && arr.length >= 100000 ) {			
			System.out.println("�迭�� ũ��� 2�̻� 100,000 ���Ͽ����մϴ�.");	return false;
		}
		//�� ���� �ܾ��� ���̴� 1 �̻� 10,000 ���Ϸ� �� ���ڿ��� ���� �����ϴ�.
		//��ü ���� �ܾ� ������ ���� 2 �̻� 1,000,000 �����Դϴ�.
		int nWordCnt = 0;
		for(int i = 0 ; i < arr.length ; i++ )
		{
			if( arr[i].length() < 1 || arr[i].length() > 10000 || arr[i].isEmpty() ){
				System.out.println("�� �˻� Ű������ ���̴� 1 �̻� 10,000 ���Ϸ� �� ���ڿ��� ���� �����ϴ�.");	return false;
			}
			nWordCnt += arr[i].length();
			if ( nWordCnt <= 2 || nWordCnt >= 1000000 ){
				System.out.println("��ü �ܾ� ������ ���� 2 �̻� 1,000,000 �����Դϴ�.");	return false;
			}
		}
		
		return true;
	}
	//���� �迭 ��ȿ��
	public static String[] ValidateQueries(String[] queries) {	
		//�˻� Ű����� �ߺ��� ���� �ֽ��ϴ�.
		//�� �˻� Ű����� ���� ���ĺ� �ҹ��ڿ� ���ϵ�ī�� ������ '?' �θ� �����Ǿ� ������, Ư�� ���ڳ� ���ڴ� �������� �ʴ� ������ �����մϴ�.
		//�˻� Ű����� ���ϵ�ī�� ������ '?'�� �ϳ� �̻� ���Ե� ������, '?'�� �� �˻� Ű������ ���λ� �ƴϸ� ���̻� �� �ϳ��θ� �־����ϴ�.
		//���� ��� "??odo", "fro??", "?????"�� ������ Ű�����Դϴ�.
		//�ݸ鿡 "frodo"('?'�� ����), "fr?do"('?'�� �߰��� ����), "?ro??"('?'�� ���ʿ� ����)�� �Ұ����� Ű�����Դϴ�.
		int nNewQueriesCnt = 0;
		for(int i = 0 ; i < queries.length; i++ )
		{
			if ( queries[i].indexOf("?") == -1 ) {
				queries[i] = "";
			}
			//if(���λ��̸鼭 ���̻��̸鼭 ��ü�� ? �ƴҰ��)
			else if ( queries[i].charAt(0) == '?' && queries[i].charAt(queries[i].length()-1) == '?' )
			{				
				for ( int j = 0 ; j < queries[i].length(); j++ ){
					if( queries[i].charAt(j) != '?' ) {
						queries[i] = "";
					}
				}
			}
			//else if(���λ絵�ƴϰ� ���̻絵 �ƴҰ��)
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
