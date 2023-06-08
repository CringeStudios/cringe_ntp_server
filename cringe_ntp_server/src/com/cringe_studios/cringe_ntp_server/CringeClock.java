package com.cringe_studios.cringe_ntp_server;

import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CringeClock {
	void startClock() {
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		Instant currentTime = Instant.now();
		double factor = CringeTime.getFactor();
		long currentCringeTime = CringeTime.unixToCringe(currentTime.toEpochMilli());
		
		service.scheduleAtFixedRate(() -> CringeNTP.getWebSockets(), 2 , (long) (factor * 1_000), TimeUnit.MILLISECONDS);
	}
}