package org.zaunberg.moskitojboss.web.moskito;

import net.anotheria.moskito.core.threshold.guard.LongBarrierPassGuard;

import static net.anotheria.moskito.core.threshold.ThresholdStatus.*;
import static net.anotheria.moskito.core.threshold.guard.GuardedDirection.DOWN;
import static net.anotheria.moskito.core.threshold.guard.GuardedDirection.UP;

/**
 * Utility Class for working with default guards.
 * Defined status changes:  None --> Green --> Yellow --> Orange --> Red --> Purple
 *
 * @author <a href="mailto:michaelschuetz83@gmail.com">Michael Schuetz</a>
 */
public final class Guards {

    /**
     * prevent initialization.
     */
    private Guards() {
    }

    /**
     * Create Long based Guards.
     *
     * @param greenValue  value of green status
     * @param yellowValue value of yellow status
     * @param orangeValue value of orange status
     * @param redValue    value of red status
     * @param purpleValue value of purple status
     * @return array of specific guards
     */
    public static LongBarrierPassGuard[] createLongGuards(final int greenValue, final int yellowValue, final int orangeValue, final int redValue, final int purpleValue) {
        return new LongBarrierPassGuard[]{
                new LongBarrierPassGuard(GREEN, greenValue, DOWN),
                new LongBarrierPassGuard(YELLOW, yellowValue, UP),
                new LongBarrierPassGuard(ORANGE, orangeValue, UP),
                new LongBarrierPassGuard(RED, redValue, UP),
                new LongBarrierPassGuard(PURPLE, purpleValue, UP)};
    }
}
