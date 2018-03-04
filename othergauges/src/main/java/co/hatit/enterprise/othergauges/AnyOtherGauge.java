package co.hatit.enterprise.othergauges;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import co.hatit.enterprise.gauges.Gauge;
import co.hatit.enterprise.gauges.GaugeRegistry;

@ApplicationScoped
@Named
public class AnyOtherGauge {
	
	@Inject
	private GaugeRegistry registry;
	
	static int counter;
	
	public AnyOtherGauge() {
		System.out.println("Constructing AnyOtherGauge " + this);
	}
	
	public void init(@Observes @Initialized(ApplicationScoped.class) Object init){
		counter++;
		System.out.println("@ApplicationScoped initialized " + this);
		registry.addGauge(new Gauge(){
			@Override
			public String toString() {
				return "Custom gauge";
			}
		});
	}
	
	public void destroyed(@Observes @Destroyed(ApplicationScoped.class) Object destroyed) {
		counter--;
		System.out.println("@ApplicationScoped destroyed " + this);
	}
	
	@Produces
	public Gauge getGauge(){
		return new Gauge(){
			@Override
			public String toString() {
				return "Produced gauge from AnyOtherGauge";
			}
		};
	}
	
	public void listenGauge(@Observes Gauge event) {
		  System.out.println("Received gauge event: " + event + " by " + this);
	}
	
	@Override
	public String toString() {
		return "AnyOtherGauge-" + hashCode() + "-loadedFrom-" + getClass().getClassLoader() + "-withCounter-" + counter;
	}
}
