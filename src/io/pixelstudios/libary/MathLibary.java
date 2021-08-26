package io.pixelstudios.libary;

import java.util.Random;

public class MathLibary {

	public static double roundUp(double number) {
		number = number * 10;
		number = Math.round(number);
		number = number / 10;
		return number;
	}
	
	public static double getPercent(double number, double maxNumber) {
		return number*100/maxNumber;
	}
	public static String generateShortID() {
		char[] charakterlist = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
								'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
								'1','2','3','4','5','6','7','8','9','_','-'};
		String generatedid = "";
		Random random = new Random();
		for(int length = 0;length < 10;length++) {
			generatedid = generatedid + charakterlist[random.nextInt(charakterlist.length)];
		}
		return generatedid;
	}
	public static String getTimeRange(Long from, Long to) {			
		Long rawData = from - to;
		long sekunden = rawData/1000;
		long minuten = sekunden/60;
		long stunden = minuten/60;
		long tage = stunden/24;
		sekunden %= 60;
		minuten %= 60;
		stunden %= 24;
					
		if(tage >= 1) {		
			if(stunden == 0) {
				return tage+" days";
			}else {
				return tage+" days, "+stunden+" hours";
			}
			}else if(stunden >= 1){ 
				if(minuten == 0) {
					return stunden+" hours";
				}else {
					return stunden+" hours, "+stunden+" minutes";			
				}	
			}else if(minuten >= 1){
				if(sekunden == 0) {
					return minuten+" minutes";
				}else {
					return minuten+" minutes, "+sekunden+" seconds";			
				}	
			}else if(sekunden >= 1){
				return sekunden+" seconds";							
			}else if(rawData < 1000) {
				return 1+" second";
			}
			return "Hmm something went wrong ._.";	
		}
}
