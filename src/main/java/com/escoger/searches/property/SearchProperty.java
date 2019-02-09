package com.escoger.searches.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("p2c-searches")
public class SearchProperty {

	private int threadpoolcount;
	private int randomresponsesize;
	
	public int getRandomresponsesize() {
		return randomresponsesize;
	}

	public void setRandomresponsesize(int randomresponsesize) {
		this.randomresponsesize = randomresponsesize;
	}

	public int getThreadpoolcount() {
		return threadpoolcount;
	}

	public void setThreadpoolcount(int threadpoolcount) {
		this.threadpoolcount = threadpoolcount;
	}

	
}
