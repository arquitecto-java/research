package co.hatit.enterprise.gauges.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Typed;
import javax.inject.Inject;
import javax.inject.Named;

import co.hatit.enterprise.gauges.Gauge;
import co.hatit.enterprise.gauges.GaugeRegistry;

@Named
@ApplicationScoped
@Typed(GaugeRegistry.class)
public class GaugeRegistryImpl implements GaugeRegistry {
	
	@Inject Instance<Gauge> gauge;
	
	@Inject
	Event<Gauge> event;
	
	public void init(@Observes @Initialized(ApplicationScoped.class) Object init){
		System.out.println("@ApplicationScoped initialized for gauge");
		gauge.forEach(g -> System.out.println("Injected gauge: " + g));
		gauge.forEach(g -> event.fire(g));
	}

	public void addGauge(Gauge g) {
		System.out.println("Registering " + g);
		event.fire(g);
	}

}
