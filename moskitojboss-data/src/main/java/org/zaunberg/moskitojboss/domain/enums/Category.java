package org.zaunberg.moskitojboss.domain.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author <a href="mailto:marek.i@gmx.net">Marek Iwaszkiewicz</a>
 * @author <a href="mailto:michaelschuetz83@gmail.com">Michael Schuetz</a>
 */
public enum Category implements I18nEnum {

    PRIVATE("org.zaunberg.moskitojboss.category.private"), // (PINK)
    BUSINESS("org.zaunberg.moskitojboss.category.business"), // (YELLOW)
    TODO("org.zaunberg.moskitojboss.category.todo"), // (RED),
    IMPORTANT("org.zaunberg.moskitojboss.category.important"); // (BLUE)

    private final String i18nDescriptionId;

    private Category(final String i18nDescriptionId) {
        this.i18nDescriptionId = i18nDescriptionId;
    }

    @Override
    public String getI18nDescriptionId() {
        return i18nDescriptionId;
    }

    private static final List<Category> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Category randomCategory() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }


}
