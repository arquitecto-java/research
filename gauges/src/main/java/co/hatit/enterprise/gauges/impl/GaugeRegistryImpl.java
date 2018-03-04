package co.hatit.enterprise.gauges.impl;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
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
public class GaugeRegistryImpl implements GaugeRegistry, Serializable {
	
	@Inject Instance<Gauge> gauge;
	
	private List<WeakReference<Gauge>> registered = new ArrayList<>();
	
	static private List<WeakReference<GaugeRegistry>> instances = new ArrayList<>();
	
	@Inject
	Event<Gauge> event;
	
	static int counter;
	
	public GaugeRegistryImpl() {
		instances.add(new WeakReference<GaugeRegistry>(this));
		System.out.println("Constructing GaugeRegistryImpl " + this);
	}
	
	@PostConstruct
	public void postConstruct(){
		counter++;
		System.out.println("postConstruct " + this);
	}
	
	public void init(@Observes @Initialized(ApplicationScoped.class) Object init){
		System.out.println("@ApplicationScoped initialized " + this);
//		gauge.forEach(g -> System.out.println("Injected gauge: " + g + " on " + this));
//		gauge.forEach(g -> event.fire(g));
	}
	
	public void destroyed(@Observes @Destroyed(ApplicationScoped.class) Object destroyed) {
		System.out.println("@ApplicationScoped destroyed " + this);
	}
	
	@PreDestroy
	public void preDestroy() {
		counter--;
		System.out.println("preDestroy " + this);
	}
	
	@Override
	public String toString() {
		return "GaugeRegistryImpl-" + hashCode() + "-withCounter-" + counter;
	}

	public void addGauge(Gauge g) {
		registered.add(new WeakReference<Gauge>(g));
		System.out.println("Registering " + g + " in " + this);
//		event.fire(g);
	}
	
	@Override
	public List<Gauge> getInjected() {
		ArrayList<Gauge> result = new ArrayList<>();
		gauge.forEach(g -> result.add(g));
		return result;
	}

	@Override
	public List<Gauge> getRegistered() {
		return registered.stream().filter(r -> r.get() != null).map(r -> r.get()).collect(Collectors.toList());
	}
}
