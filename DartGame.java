package study;

import java.util.regex.Pattern;

public class DartGame {
	
	final private static int MAXGAMECOUNT = 3;
	
	public static void main(String[] args) {
		
		int idx = 0;
		
		String[] str_input_arr = { "1S2D*3T", "1D2S#10S", "1D2S0T","1S*2T*3S", "1D#2S*3S","1T2D3D#","1D2S3T*"};
		//String[] str_input_arr = {"1D#2S*3S"};
		
		while (idx < str_input_arr.length ) {
			
			String input_data = str_input_arr[idx];
			if(input_data.length() < 0 ) return;
			
			int[] score = new int[MAXGAMECOUNT]; 
			String[] formula = new String[MAXGAMECOUNT];	
			
			String strFom = "",strFom2 = "",strFormula = "";
			int result = 0,var = 0,gamecnt = -1;
			for(int i = 0 ; i < input_data.length() ; i++) {
				
				strFom = String.valueOf(input_data.charAt(i));
				//System.out.println(strFom);
				//숫자인지
				if( Pattern.matches("^[0-9]*$", strFom) ) {
					//System.out.println(gamecnt);
					if( (i+1) < input_data.length() 
						&& Pattern.matches("^[0-9]*$", String.valueOf(input_data.charAt(i+1)) )
					  )
					{
						strFom += String.valueOf(input_data.charAt(i+1)) ;
						i++;
					}
					
					score[++gamecnt] = Integer.parseInt(strFom);
					
					formula[gamecnt] = strFom;
				}
				//알파벳인지
				else if (Pattern.matches("^[a-zA-Z]*$", strFom)) {
					
					if(strFom.equals("S")) {
						score[gamecnt] = (int) Math.pow(score[gamecnt], 1);
						formula[gamecnt] += "^1";
					}else if(strFom.equals("D")) {
						score[gamecnt] = (int) Math.pow(score[gamecnt], 2);
						formula[gamecnt] += "^2";
					}else if(strFom.equals("T")) {
						score[gamecnt] = (int) Math.pow(score[gamecnt], 3);
						formula[gamecnt] += "^3";
					}
					
				}
				//특수문자인지
				else if(Pattern.matches("[!@#$%^&*(),.?\\\":{}|<>]", strFom)) {
					
					if(strFom.equals("*")) {
						score[gamecnt] = score[gamecnt]*2;
						formula[gamecnt] += "*2";
						if(gamecnt > 0 ) {
							score[gamecnt-1] = score[gamecnt-1]*2;
							formula[gamecnt-1] += "*2";
						}
						
					}else if(strFom.equals("#")) {
						score[gamecnt] = score[gamecnt]*-1;
						formula[gamecnt] += "*(-1)";
					}
				}
					
				
			}
			
			for(int j = 0 ; j < score.length ; j++) {
				if(strFormula.length() > 0) strFormula += "+";  
				strFormula += "("+formula[j]+")";
				result += score[j];
				
				//System.out.println(score[j]);
			}
			System.out.println(input_data + " : " + strFormula +" = "+result);
			
			idx++;
		}
		
		
	}
	
	

}
