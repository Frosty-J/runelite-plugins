package tictac7x.daily.dailies;

import net.runelite.api.Client;
import net.runelite.api.ItemID;
import net.runelite.api.Varbits;
import net.runelite.client.game.ItemManager;
import tictac7x.daily.TicTac7xDailyTasksConfig;
import tictac7x.daily.common.DailyInfobox;
import tictac7x.daily.TicTac7xDailyTasksPlugin;

public class BucketsOfSlime extends DailyInfobox {
    private final String tooltip = "Exchange %d bones to buckets of slime and bonemeal from Robin at Porty Phasmatys";

    public BucketsOfSlime(final Client client, final TicTac7xDailyTasksConfig config, final ItemManager itemManager, final TicTac7xDailyTasksPlugin plugin) {
        super(TicTac7xDailyTasksConfig.buckets_of_slime, itemManager.getImage(ItemID.BUCKET_OF_SLIME), client, config, plugin);
    }

    @Override
    public boolean isShowing() {
        return (
            config.showBucketsOfSlime() &&
            isDiaryCompleted(Varbits.DIARY_MORYTANIA_EASY) &&
            isDiaryCompleted(Varbits.DIARY_MORYTANIA_MEDIUM) &&
            getRemainingBucketsOfSlimeAmount() > 0
        );
    }

    @Override
    public String getText() {
        return String.valueOf(getRemainingBucketsOfSlimeAmount());
    }

    @Override
    public String getTooltip() {
        return String.format(tooltip, getRemainingBucketsOfSlimeAmount());
    }

    private int getRemainingBucketsOfSlimeAmount() {
        int buckets_of_slime = 0;

        final boolean easy   = isDiaryCompleted(Varbits.DIARY_MORYTANIA_EASY);
        final boolean medium = isDiaryCompleted(Varbits.DIARY_MORYTANIA_MEDIUM);
        final boolean hard   = isDiaryCompleted(Varbits.DIARY_MORYTANIA_HARD);
        final boolean elite  = isDiaryCompleted(Varbits.DIARY_MORYTANIA_ELITE);

        if (easy && medium && hard && elite) { buckets_of_slime = 39; } else
        if (easy && medium && hard) { buckets_of_slime = 26; } else
        if (easy && medium) { buckets_of_slime = 13; } else
        if (easy) { buckets_of_slime = 0; }

        return buckets_of_slime - client.getVarbitValue(Varbits.DAILY_BONEMEAL_STATE);
    }
}
