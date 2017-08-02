package com.varun.android.listview.helpers;

import com.varun.android.listview.model.Reminder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ReminderContentHelper {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Reminder> ITEMS = new ArrayList<Reminder>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Reminder> ITEM_MAP = new HashMap<String, Reminder>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createReminderItem(i));
        }
    }

    private static void addItem(Reminder item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static Reminder createReminderItem(int position) {
        return new Reminder(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }
}
