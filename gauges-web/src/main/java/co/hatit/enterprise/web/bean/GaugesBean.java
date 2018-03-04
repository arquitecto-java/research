package co.hatit.enterprise.web.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import co.hatit.enterprise.gauges.Gauge;
import co.hatit.enterprise.gauges.GaugeRegistry;
@ViewScoped
@ManagedBean
public class GaugesBean implements Serializable {
	
	@Inject
	private GaugeRegistry gaugeRegistry;
	
	static int counter;
	
	public GaugesBean() {
		System.out.println("Constructing GaugesBean " + this);
	}
	
	@PostConstruct
	public void postConstruct(){
		counter++;
		System.out.println("postConstruct " + this);
	}
	
	@PreDestroy
	public void preDestroy() {
		counter--;
		System.out.println("preDestroy " + this);
	}
	
	public List<Gauge> getInjected(){
		return gaugeRegistry.getInjected();
	}
	
	public List<Gauge> getRegistered(){
		return gaugeRegistry.getRegistered();
	}
}
