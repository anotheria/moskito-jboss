package org.zaunberg.moskitojboss.common.testsupport;

import de.akquinet.jbosscc.needle.injection.InjectionProvider;
import de.akquinet.jbosscc.needle.injection.InjectionTargetInformation;
import org.jboss.logging.Logger;

import javax.inject.Inject;

/**
 * @author <a href="mailto:marek.i@gmx.net">Marek Iwaszkiewicz</a>
 */
public class LogInjector implements InjectionProvider<Logger> {

  @Override
  public Logger getInjectedObject(final Class<?> type) {
    return DummyLogger.createInstance();
  }

  @Override
  public boolean verify(final InjectionTargetInformation injectionTargetInformation) {
    return (injectionTargetInformation.isAnnotationPresent(Inject.class) && injectionTargetInformation.getType() == Logger.class);
  }

  @Override
  public Object getKey(final InjectionTargetInformation injectionTargetInformation) {
    return Logger.class;
  }
}
