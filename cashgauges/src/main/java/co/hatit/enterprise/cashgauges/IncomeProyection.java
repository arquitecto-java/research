package co.hatit.enterprise.cashgauges;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import co.hatit.enterprise.gauges.Gauge;
import co.hatit.enterprise.gauges.GaugeRegistry;

@ApplicationScoped
@Named
public class IncomeProyection {
	
	@Inject
	private GaugeRegistry registry;
	
	public void init(@Observes @Initialized(ApplicationScoped.class) Object init){
		System.out.println("@ApplicationScoped initialized for cashgauges");
		registry.addGauge(new Gauge(){
			@Override
			public String toString() {
				return "Custom gauge";
			}
		});
	}
	
	@Produces
	public Gauge getGauge(){
		return new Gauge(){
			@Override
			public String toString() {
				return "Produced gauge";
			}
		};
	}
	
	public void listenGauge(@Observes Gauge event) {
		  System.out.println("Received gauge event: " + event);
	}
}
