package com.cringe_studios.cringe_ntp_server;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

// 0x01 <- 1 Second

// 0xFF_FF_FF_FF_FF_FF_FF_FF
//                        ##  <- 1 ByteSecond =  0xFF       + 1 Seconds
//                     ## ##  <- 1 WordSecond =  0xFF       + 1 ByteSeconds
//               ## ## ## ##  <- 1 DWordSecond = 0xFFFF     + 1 WordSeconds
//   ## ## ## ## ## ## ## ##  <- 1 QWordSecond = 0xFFFFFFFF + 1 DWordSeconds


public class CringeTime {
	private static final Instant CRONGE_STUDIOS_BIRTH = ZonedDateTime.of(LocalDateTime.of(2021, Month.FEBRUARY, 25, 12, 5), ZoneId.of("UTC+1")).toInstant(); // 25.02.2021 - 12:05 in UTC+1 (Deutsche Winterzeit)
	
	private Instant origin = null;
	
	public CringeTime(Instant unixInstant) {
		origin = unixInstant;
	}
	
	public CringeTime newCringeTimeFromUnixSeconds(long unixInSeconds, long unixNanoAdjustment) {
		return new CringeTime(Instant.ofEpochSecond(unixInSeconds, unixNanoAdjustment));
	}
	
	public CringeTime newCringeTimeFromUnixMillis(long unixInMillis) {
		return new CringeTime(Instant.ofEpochMilli(unixInMillis));
	}
	
//	public long getCringeSeconds() {
//		
//	}
//	
//	public long getCringeByteSecond() {
//		
//	}
//	
//	public long getCringeWordSecond() {
//		
//	}
//	
//	public long getCringeDWordSecond() {
//		
//	}
//	
//	public long getCringeQWordSecond() {
//		
//	}
//	
//	public long getCringeOWordSecond() {
//		
//	}
	
	@Override
	public String toString() {
		return null;
		
	}
	
	static long unixToCringe(long unixInMillis) { //TODO change return type from "millis" to something better
		long epochBegining = getCringeUnixEpochBeginning();
		double factor = getFactor();
		double jesus = epochBegining - factor * epochBegining;
		return (long) (factor * unixInMillis + jesus);
	}
	
	static double getFactor() { // 1 unix second = PI/E cringe seconds
		return Math.PI / Math.E; // yummy PIE
	}
	
	static long getCringeUnixEpochBeginning() { // in Miliseconds
		long crongeStudiosBirthMillis = CRONGE_STUDIOS_BIRTH.toEpochMilli();
		return crongeStudiosBirthMillis;
	}
	
	static long getMillisUntilNextCringe() { //TODO
		Instant currentTime = Instant.now();
		long now = unixToCringe(currentTime.toEpochMilli());
		
		return 999 - (now % 1000);
		
	}
}
