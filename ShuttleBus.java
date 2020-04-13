package study;

import java.time.LocalTime;
import java.util.ArrayList;

public class ShuttleBus {

	public static void main(String[] args) {
		//셔틀 운행 횟수 n, 
		//셔틀 운행 간격 t, 
		//한 셔틀에 탈 수 있는 최대 크루 수 m, 
		//크루가 대기열에 도착하는 시각을 모은 배열 timetable이 입력으로 주어진다.
		int n,t,m;
		
		String[][] arr = { 
				{"08:00", "08:01", "08:02", "08:03"}, //09:00
				{"09:10", "09:09", "08:00"},  		//09:09
				{"09:00", "09:00", "09:00", "09:00"},//08:59
				{"00:01", "00:01", "00:01", "00:01", "00:01"},// 00:00
				{"23:59"},	//09:00
				{"23:59","23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"} //18:00
				};
		int idx = 0; 
		
		n = 1;	t= 1;	m = 5;	
		System.out.println(GetArrivalTime(n,t,m,arr[idx++]));
		n = 2;	t= 10;	m = 2;
		System.out.println(GetArrivalTime(n,t,m,arr[idx++]));
		n = 2;	t= 1;	m = 2;	
		System.out.println(GetArrivalTime(n,t,m,arr[idx++]));
		n = 1;	t= 1;	m = 5;	
		System.out.println(GetArrivalTime(n,t,m,arr[idx++]));
		n = 1;	t= 1;	m = 1;	
		System.out.println(GetArrivalTime(n,t,m,arr[idx++]));
		n = 10;	t= 60;	m = 45;	
		System.out.println(GetArrivalTime(n,t,m,arr[idx++]));
		
	}

	public static String  GetArrivalTime (int n , int t, int m, String[] timetable_i) {
		
		String[]  timetable = TableSort(timetable_i);
		//9시로 SETTING
		LocalTime tShuttleTime = LocalTime.of(9,0,0,0);
		LocalTime tShuttleTime_Prev;
		LocalTime tResult = null;
		int nRealBoardedCnt = 0; 
		ArrayList<String> aBoardTime = new ArrayList<>();
		
		//배차간격으로 횟수로 시간표
		for(int i = 0 ; i < n ; i++) {
						
			//셔틀시간에 탑승가능한 인원 구하기
			//버스가 1번만 올 경우
			if(n == 1) {
										
				for( int k = 0 ; k < timetable.length ; k++ ) {

					if( Integer.parseInt(timetable[k].substring(0, 2)) <  tShuttleTime.getHour() ||
							(Integer.parseInt(timetable[k].substring(0, 2)) ==  tShuttleTime.getHour()
							&& Integer.parseInt(timetable[k].substring(3, timetable[k].length())) <=  tShuttleTime.getMinute() )
					) {
						aBoardTime.add(timetable[k]);
					}
					
				}
			}
			//버스가 1번이상 올 경우
			else if (n > 1) {
				
				tShuttleTime_Prev = tShuttleTime.minusMinutes(t);
				
				for( int k = 0 ; k < timetable.length ; k++ ) {						
					
					if( 	//배차시간보다 작거나 같은 경우
							(Integer.parseInt(timetable[k].substring(0, 2)) <  tShuttleTime.getHour() 
							||(Integer.parseInt(timetable[k].substring(0, 2)) ==  tShuttleTime.getHour()
							&& Integer.parseInt(timetable[k].substring(3, timetable[k].length())) <=  tShuttleTime.getMinute() ))
							//그리고
							&&
							//전차시간보다 클경우
							(Integer.parseInt(timetable[k].substring(0, 2)) >  tShuttleTime_Prev.getHour() 
							||(Integer.parseInt(timetable[k].substring(0, 2)) ==  tShuttleTime_Prev.getHour()
							&& Integer.parseInt(timetable[k].substring(3, timetable[k].length())) >  tShuttleTime_Prev.getMinute())
							)
					) {
						aBoardTime.add(timetable[k]);
					}
											
				}
			}
			//그냥 예외처리
			else {
				return "X";
			}				
			
			if(  aBoardTime.size()  >= m ) 
			{	

				String sBoardTime  = "";
				for (int a = 0 ; a < aBoardTime.size() ; a++ ) {
					if( a == (m-1)) {
						sBoardTime = aBoardTime.get(a);
					}
				}
													
				LocalTime lBoardTime = LocalTime.of(
						Integer.parseInt(sBoardTime.substring(0, 2)),
						Integer.parseInt(sBoardTime.substring(3, sBoardTime.length())),
						0,
						0);
				
				lBoardTime = lBoardTime.minusMinutes(1);
				//return lBoardTime.getHour() + ":" + lBoardTime.getMinute();
				//return lBoardTime.toString();
				tResult = lBoardTime;
				
			}
			//탑승하려는 크루에 숫자가 탑승가능인원보다 적을 경우 
			else 
			{	//셔틀 도착시간 								
				if( (n-1) == i &&tResult == null ) tResult =  tShuttleTime;
			}
			
			
			tShuttleTime = tShuttleTime.plusMinutes(t);
			
			aBoardTime.clear();
		}
		
		return tResult.toString();
	}
	
	public static String[] TableSort (String[] Input) {
		
		String a = "", b = "", tmp = "";
		
		int tablelength = Input.length;
		while (tablelength > 0) {
			for(int i = 0 ; i < Input.length-1 ; i++) {
				a = Input[i];
				b = Input[i+1];
				
				if( 
						Integer.parseInt(a.substring(0, 2)) > Integer.parseInt(b.substring(0, 2)) 
						||
						(Integer.parseInt(a.substring(0, 2)) == Integer.parseInt(b.substring(0, 2)) 
						&&Integer.parseInt(a.substring(3, a.length())) > Integer.parseInt(b.substring(3, b.length())))
						) {
					tmp = Input[i];
					Input[i] = Input[i+1];
					Input[i+1] = tmp;
					tmp = "";
				}
			}
			tablelength--;
		}
		
		
		
		return Input;		
	}
}
