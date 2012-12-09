package org.zaunberg.moskitojboss.web.controller;

import net.anotheria.moskito.integration.cdi.Monitor;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Stereotype;
import javax.inject.Named;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static net.anotheria.moskito.integration.cdi.MonitoringCategorySelector.WEB;

/**
 * Stereotype for Web Controller classes.
 *
 * @author <a href="mailto:michaelschuetz83@gmail.com">Michael Schuetz</a>
 */
@Named
@SessionScoped
@Monitor(WEB)
@Stereotype
@Target(TYPE)
@Retention(RUNTIME)
public @interface WebController {
}
