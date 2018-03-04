package co.hatit.enterprise.gauges;

import java.util.List;

public interface GaugeRegistry {
	void addGauge(Gauge g);
	
	List<Gauge> getRegistered();
	
	List<Gauge> getInjected();
}
