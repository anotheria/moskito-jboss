package org.zaunberg.moskitojboss.web.jsf;

import java.util.Locale;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.jboss.seam.faces.qualifier.Faces;

public class FacesLocaleResolver {
	
	@Inject
	private FacesContext facesContext;

	public boolean isActive() {
		return (facesContext != null)
				&& (facesContext.getCurrentPhaseId() != null);
	}

	@Produces
	@Faces
	public Locale getLocale() {
		if (facesContext.getViewRoot() != null)
			return facesContext.getViewRoot().getLocale();
		else
			return facesContext.getApplication().getViewHandler()
					.calculateLocale(facesContext);
	}
}
