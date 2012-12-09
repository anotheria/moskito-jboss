package org.zaunberg.moskitojboss.web.jsf;

import java.util.ResourceBundle;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

public class ResourceBundleProducer {

	@Inject
	public FacesContext facesContext;

	@Produces
	public ResourceBundle getResourceBundle() {
		return ResourceBundle.getBundle("/messages", facesContext.getViewRoot()
				.getLocale());
	}
}
