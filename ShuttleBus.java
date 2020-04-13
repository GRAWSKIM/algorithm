package study;

import java.time.LocalTime;
import java.util.ArrayList;

public class ShuttleBus {

	public static void main(String[] args) {
		//��Ʋ ���� Ƚ�� n, 
		//��Ʋ ���� ���� t, 
		//�� ��Ʋ�� Ż �� �ִ� �ִ� ũ�� �� m, 
		//ũ�簡 ��⿭�� �����ϴ� �ð��� ���� �迭 timetable�� �Է����� �־�����.
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
		//9�÷� SETTING
		LocalTime tShuttleTime = LocalTime.of(9,0,0,0);
		LocalTime tShuttleTime_Prev;
		LocalTime tResult = null;
		int nRealBoardedCnt = 0; 
		ArrayList<String> aBoardTime = new ArrayList<>();
		
		//������������ Ƚ���� �ð�ǥ
		for(int i = 0 ; i < n ; i++) {
						
			//��Ʋ�ð��� ž�°����� �ο� ���ϱ�
			//������ 1���� �� ���
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
			//������ 1���̻� �� ���
			else if (n > 1) {
				
				tShuttleTime_Prev = tShuttleTime.minusMinutes(t);
				
				for( int k = 0 ; k < timetable.length ; k++ ) {						
					
					if( 	//�����ð����� �۰ų� ���� ���
							(Integer.parseInt(timetable[k].substring(0, 2)) <  tShuttleTime.getHour() 
							||(Integer.parseInt(timetable[k].substring(0, 2)) ==  tShuttleTime.getHour()
							&& Integer.parseInt(timetable[k].substring(3, timetable[k].length())) <=  tShuttleTime.getMinute() ))
							//�׸���
							&&
							//�����ð����� Ŭ���
							(Integer.parseInt(timetable[k].substring(0, 2)) >  tShuttleTime_Prev.getHour() 
							||(Integer.parseInt(timetable[k].substring(0, 2)) ==  tShuttleTime_Prev.getHour()
							&& Integer.parseInt(timetable[k].substring(3, timetable[k].length())) >  tShuttleTime_Prev.getMinute())
							)
					) {
						aBoardTime.add(timetable[k]);
					}
											
				}
			}
			//�׳� ����ó��
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
			//ž���Ϸ��� ũ�翡 ���ڰ� ž�°����ο����� ���� ��� 
			else 
			{	//��Ʋ �����ð� 								
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
