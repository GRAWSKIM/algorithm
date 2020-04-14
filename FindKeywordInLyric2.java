package study;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import DataStructure.Trie;
import DataStructure.TrieNode;

//Trie 구조를 이용
public class FindKeywordInLyric2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
		String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
//		String[] queries = {"fro", "o", "fr", "fro", "pro"};

		Map<Integer, Trie> arr_normal = new HashMap<Integer, Trie>();
		Map<Integer, Trie> arr_revers = new HashMap<Integer, Trie>();
	
		//문자 길이별로 Trie 노드를 만든다
		Trie tree;
		for (int i = 0; i < words.length ; i++ ) {
			if ( arr_normal.get(words[i].length()) == null ) {
				tree = new Trie();
				tree.insert(words[i]);
				arr_normal.put(words[i].length(), tree);				
			}
			else {
				tree = arr_normal.get(words[i].length());
				tree.insert(words[i]);				
			}
			
			if ( arr_revers.get(words[i].length()) == null ) {		
				tree = new Trie();
				tree.insert(new StringBuffer(words[i]).reverse().toString());
				arr_revers.put(words[i].length(),tree);
			}
			else {
				tree = arr_revers.get(words[i].length());
				tree.insert(new StringBuffer(words[i]).reverse().toString());
			}
		}		
		
		for(int i = 0; i < queries.length; i++ ) {
			if( queries[i].indexOf("?") == 0 ) {
				queries[i] = new StringBuffer(queries[i]).reverse().toString();				
				tree = arr_revers.get(queries[i].length());
				if(tree != null)
					System.out.println(
							GetCountTrieNode(tree.searchNode(queries[i].replace("?", "")))
							);
				else 
					System.out.println(0);
			}
			else {
				tree = arr_normal.get(queries[i].length());
				if(tree != null)
					System.out.println(
							GetCountTrieNode(tree.searchNode(queries[i].replace("?", "")))
							);
				else 
					System.out.println(0);			
			}
				
			
		}	
		
	}
	
	public static int GetCountTrieNode(TrieNode node) {
		if(node.isLeaf) return 1;
		
		HashMap<Character, TrieNode> chidren = node.children;
		int tot = 0;
		
		for(char key : chidren.keySet()) {
			tot += GetCountTrieNode(chidren.get(key));
		}
	
		return tot;
	}

}
