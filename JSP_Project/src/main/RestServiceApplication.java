package main;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
 
@ApplicationPath("/rs")
public class RestServiceApplication  extends Application {
	
	private Set<Object> singletons = new HashSet<Object>();
	 
	public RestServiceApplication() {
		singletons.add(new AgentRestService());
	}
 
	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

	@Override
	public Set<Class<?>> getClasses() {
		return null;
	}

}
