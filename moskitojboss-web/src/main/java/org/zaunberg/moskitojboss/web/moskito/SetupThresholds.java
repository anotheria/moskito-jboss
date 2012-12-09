package org.zaunberg.moskitojboss.web.moskito;

import net.anotheria.moskito.core.threshold.Thresholds;
import net.anotheria.moskito.core.threshold.guard.LongBarrierPassGuard;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

/**
 * SetupThresholds listener.
 *
 * @author Saeid Makhmali, <a href="mailto:michaelschuetz83@gmail.com">Michael Schuetz</a>
 */

@WebListener
public class SetupThresholds implements javax.servlet.ServletContextListener {

    /**
     * thread count guards.
     */
    private static final LongBarrierPassGuard[] THREAD_COUNT_GUARDS = Guards.createLongGuards(200, 200, 300, 500, 700);

    /**
     * session count guards.
     */
    private static final LongBarrierPassGuard[] SESSION_COUNT_GUARDS = Guards.createLongGuards(1000, 1000, 1500, 2000, 2500);

    /**
     * maximum open files guards.
     */
    private static final LongBarrierPassGuard[] MAX_OPEN_FILES_GUARDS = Guards.createLongGuards(800, 800, 1000, 2000, 4000);

    /**
     * task count guards, cumuluated.
     */
    private static final LongBarrierPassGuard[] TASK_COUNT_GUARDS_CUMULATED = Guards.createLongGuards(50, 50, 100, 200, 300);

    /**
     * task count guards.
     */
    private static final LongBarrierPassGuard[] TASK_COUNT_GUARDS = Guards.createLongGuards(10, 10, 20, 50, 100);


    /**
     * {@inheritDoc}
     */
    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        addThreshold("SessionCount", "SessionCount", "Sessions", "Cur", SESSION_COUNT_GUARDS);
        addThreshold("MaxOpenFiles", "OS", "OS", "MaxOpenFiles", MAX_OPEN_FILES_GUARDS);

        addThreshold("TaskCount-All", "TaskCounter", "cumulated", "Counter", TASK_COUNT_GUARDS_CUMULATED);
        addThreshold("TaskCount-Business", "TaskCounter", "countBusiness", "Counter", TASK_COUNT_GUARDS);
        addThreshold("TaskCount-Private", "TaskCounter", "countPrivate", "Counter", TASK_COUNT_GUARDS);
        addThreshold("TaskCount-Todo", "TaskCounter", "countTodo", "Counter", TASK_COUNT_GUARDS);
        addThreshold("TaskCount-Important", "TaskCounter", "countImportant", "Counter", TASK_COUNT_GUARDS);
    }

    /**
     * @param displayName  displayName
     * @param producerName producerName
     * @param statName     statName
     * @param valueName    valueName
     * @param guards       specific guards
     */
    private void addThreshold(final String displayName, final String producerName, final String statName, final String valueName, final LongBarrierPassGuard[] guards) {
        Thresholds.addThreshold(displayName, producerName, statName, valueName,
                "default", guards);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void contextDestroyed(final ServletContextEvent sce) {
    }
}