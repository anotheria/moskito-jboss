package org.zaunberg.moskitojboss.web;

import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author <a href="mailto:michaelschuetz83@gmail.com">Michael Schuetz</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface LoggedInUser{
}


