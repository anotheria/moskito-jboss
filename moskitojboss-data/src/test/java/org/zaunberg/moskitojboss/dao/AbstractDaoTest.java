package org.zaunberg.moskitojboss.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.zaunberg.moskitojboss.common.testsupport.LogInjector;

import de.akquinet.jbosscc.needle.db.transaction.TransactionHelper;
import de.akquinet.jbosscc.needle.injection.InjectionProvider;
import de.akquinet.jbosscc.needle.junit.DatabaseRule;
import de.akquinet.jbosscc.needle.junit.NeedleRule;

/**
 * @author <a href="mailto:marek.i@gmx.net">Marek Iwaszkiewicz</a>
 */
public abstract class AbstractDaoTest {

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @Rule
    public NeedleRule needleRule = createNeedleRule();

    protected TransactionHelper transactionHelper;

    @Before
    public final void setupHelper() throws Exception {
        transactionHelper = new TransactionHelper(databaseRule.getEntityManager());
    }

    protected NeedleRule createNeedleRule() {
        final List<InjectionProvider<?>> injectionProviders = new ArrayList<InjectionProvider<?>>();
        injectionProviders.add(databaseRule);
        injectionProviders.add(new LogInjector());

        injectionProviders.addAll(getAdditionalInjectionProviders());

        return new NeedleRule(injectionProviders.toArray(new InjectionProvider[injectionProviders.size()]));
    }

    protected List<InjectionProvider<?>> getAdditionalInjectionProviders() {
        return new ArrayList<InjectionProvider<?>>();
    }

}
