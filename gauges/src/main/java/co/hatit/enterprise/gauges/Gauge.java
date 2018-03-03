package co.hatit.enterprise.gauges;

import java.io.Serializable;

import javax.enterprise.inject.Default;

@Default
public class Gauge implements Serializable {
	private String name;
	//private String type;
	private String dataSource;
	private String url;
}
