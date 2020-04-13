package study;

public class DBCacheRunningTimeCheck {

	public static void main(String[] args) {
		
		//int cacheSize = 3;	String[] cities = 	{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
		//int cacheSize = 3;	String[] cities = {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"};
		//int cacheSize = 2;	String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
		//int cacheSize = 5;	String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
		int cacheSize = 2;	String[] cities = {"Jeju", "Pangyo", "NewYork", "newyork"};
		//int cacheSize = 0;	String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
		
		System.out.println( TimeCheck(cities,cacheSize) );
	}
	
	public static String TimeCheck(String[] cities, int cacheSize) {
		
		String[] cachedData = new String[cacheSize];
		
		int result = 0;
		boolean isCachedData = false ;
		
		
		
		for(int i = 0; i < cities.length ; i++ ) {
			
			isCachedData = false;
			
			for(int j = 0; j < cachedData.length ; j++ ) {
				
				if(isCachedData == true ) continue;
				
				//cache hit
				if( //대문자로 비교한다
					(cities[i].toUpperCase()).equals( cachedData[j] == null  ? "" :  cachedData[j].toUpperCase() ) 
					) {
					isCachedData = true;
				}
				//cache miss
				else {
					isCachedData = false;								
				}
				
			}
			
			if(isCachedData) {
				result += 1;
			}else {
				result += 5;
				ChangeCache(cachedData,cities[i]);
			}
		}
		
		return ""+result;
	}
	
	//캐쉬 교체
	public static void ChangeCache(String[] cachedData, String newData) {
		
		
		for(int i = cachedData.length-1 ; i > 0 ; i--) {
			
			cachedData[i] = cachedData[i-1];
			
		}
		
		if(cachedData.length >= 1) cachedData[0] = newData;
		
	}

}
