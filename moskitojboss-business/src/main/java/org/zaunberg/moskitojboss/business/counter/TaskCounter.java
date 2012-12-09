package org.zaunberg.moskitojboss.business.counter;

import net.anotheria.moskito.integration.cdi.Count;

/**
 * Uses MoSKito Counter to track amount of created tasks.
 *
 * @author <a href="mailto:michaelschuetz83@gmail.com">Michael Schuetz</a>
 */

@Count
public class TaskCounter {

    public void countBusiness() {
    }

    public void countPrivate() {
    }

    public void countTodo() {
    }

    public void countImportant() {
    }
}
