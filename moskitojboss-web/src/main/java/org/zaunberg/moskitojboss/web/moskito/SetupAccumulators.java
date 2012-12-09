package org.zaunberg.moskitojboss.web.moskito;

import net.anotheria.moskito.core.accumulation.Accumulators;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

/**
 * SetupAccumulators listener.
 *
 * @author Saeid Makhmali, <a href="mailto:michaelschuetz83@gmail.com">Michael Schuetz</a>
 */
@WebListener
public class SetupAccumulators implements javax.servlet.ServletContextListener {

    /**
     * Shop gui url name.
     */
    private static final String SHOP_GUI_URL_NAME = "shopGui";

    /**
     * Shop gui url path.
     */
    private static final String SHOP_GUI_URL_PATH = "/";

    /**
     * Shop gui pages url name.
     */
    private static final String SHOP_GUI_PAGES_URL_NAME = "shopGuiPages";

    /**
     * Shop gui pages url path.
     */
    private static final String SHOP_GUI_PAGES_URL_PATH = "/pages/home.seam";

    /**
     * 5m interval.
     */
    private static final String INTERVAL_5M = "5m";

    /**
     * 5m interval.
     */
    private static final String INTERVAL_1H = "1h";

    /**
     * 5m interval.
     */
    private static final String INTERVAL_1D = "1d";

    /**
     * default interval.
     */
    private static final String INTERVAL_DEFAULT = "default";

    /**
     * {@inheritDoc}
     */
    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        setup();
    }

    /**
     * Set up moskito accomulators.
     */
    private void setup() {

        Accumulators.createUrlAVGAccumulator(SHOP_GUI_URL_NAME + " AVG " + INTERVAL_5M, SHOP_GUI_URL_PATH, INTERVAL_5M);
        Accumulators.createUrlREQAccumulator(SHOP_GUI_URL_NAME + " REQ " + INTERVAL_5M, SHOP_GUI_URL_PATH, INTERVAL_5M);
        Accumulators.createUrlAVGAccumulator(SHOP_GUI_URL_NAME + " AVG " + INTERVAL_1H, SHOP_GUI_URL_PATH, INTERVAL_1H);
        Accumulators.createUrlREQAccumulator(SHOP_GUI_URL_NAME + " REQ " + INTERVAL_1H, SHOP_GUI_URL_PATH, INTERVAL_1H);
        Accumulators.createUrlAVGAccumulator(SHOP_GUI_URL_NAME + " AVG " + INTERVAL_1D, SHOP_GUI_URL_PATH, INTERVAL_1D);
        Accumulators.createUrlREQAccumulator(SHOP_GUI_URL_NAME + " REQ " + INTERVAL_1D, SHOP_GUI_URL_PATH, INTERVAL_1D);

        Accumulators.createUrlAVGAccumulator(SHOP_GUI_PAGES_URL_NAME + " AVG " + INTERVAL_5M, SHOP_GUI_PAGES_URL_PATH,
                INTERVAL_5M);
        Accumulators.createUrlREQAccumulator(SHOP_GUI_PAGES_URL_NAME + " REQ " + INTERVAL_5M, SHOP_GUI_PAGES_URL_PATH,
                INTERVAL_5M);
        Accumulators.createUrlAVGAccumulator(SHOP_GUI_PAGES_URL_NAME + " AVG " + INTERVAL_1H, SHOP_GUI_PAGES_URL_PATH,
                INTERVAL_1H);
        Accumulators.createUrlREQAccumulator(SHOP_GUI_PAGES_URL_NAME + " REQ " + INTERVAL_1H, SHOP_GUI_PAGES_URL_PATH,
                INTERVAL_1H);
        Accumulators.createUrlAVGAccumulator(SHOP_GUI_PAGES_URL_NAME + " AVG " + INTERVAL_1D, SHOP_GUI_PAGES_URL_PATH,
                INTERVAL_1D);
        Accumulators.createUrlREQAccumulator(SHOP_GUI_PAGES_URL_NAME + " REQ " + INTERVAL_1D, SHOP_GUI_PAGES_URL_PATH, INTERVAL_1D);

        Accumulators.createAccumulator("TaskCount-All", "TaskCounter", "cumulated", "Counter", INTERVAL_DEFAULT);
        Accumulators.createAccumulator("TaskCount-Business", "TaskCounter", "countBusiness", "Counter", INTERVAL_DEFAULT);
        Accumulators.createAccumulator("TaskCount-Private", "TaskCounter", "countPrivate", "Counter", INTERVAL_DEFAULT);
        Accumulators.createAccumulator("TaskCount-Todo", "TaskCounter", "countTodo", "Counter", INTERVAL_DEFAULT);
        Accumulators.createAccumulator("TaskCount-Important", "TaskCounter", "countImportant", "Counter", INTERVAL_DEFAULT);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void contextDestroyed(final ServletContextEvent sce) {
    }
}