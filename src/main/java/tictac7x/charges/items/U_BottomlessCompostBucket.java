package tictac7x.charges.items;

import com.google.gson.Gson;
import net.runelite.api.Client;
import net.runelite.api.ItemID;
import net.runelite.api.Skill;
import net.runelite.client.Notifier;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import tictac7x.charges.ChargesImprovedConfig;
import tictac7x.charges.item.ChargedItemWithStorage;
import tictac7x.charges.item.storage.StorageItem;
import tictac7x.charges.item.triggers.OnChatMessage;
import tictac7x.charges.item.triggers.OnXpDrop;
import tictac7x.charges.item.triggers.TriggerBase;
import tictac7x.charges.item.triggers.TriggerItem;
import tictac7x.charges.store.Store;

import java.util.Optional;

public class U_BottomlessCompostBucket extends ChargedItemWithStorage {
    public U_BottomlessCompostBucket(
        final Client client,
        final ClientThread client_thread,
        final ConfigManager configs,
        final ItemManager items,
        final InfoBoxManager infoboxes,
        final ChatMessageManager chat_messages,
        final Notifier notifier,
        final ChargesImprovedConfig config,
        final Store store,
        final Gson gson
    ) {
        super(ChargesImprovedConfig.bottomless_compost_bucket, ItemID.BOTTOMLESS_COMPOST_BUCKET_22997, client, client_thread, configs, items, infoboxes, chat_messages, notifier, config, store, gson);
        storage = storage.maximumTotalQuantity(10000).storeableItems(
            new StorageItem(ItemID.ULTRACOMPOST).checkName("Ultracompost"),
            new StorageItem(ItemID.SUPERCOMPOST).checkName("Supercompost"),
            new StorageItem(ItemID.COMPOST).checkName("Compost")
        );

        this.items = new TriggerItem[]{
            new TriggerItem(ItemID.BOTTOMLESS_COMPOST_BUCKET).fixedCharges(0),
            new TriggerItem(ItemID.BOTTOMLESS_COMPOST_BUCKET_22997),
        };

        this.triggers = new TriggerBase[] {
            // Compost something.
            new OnChatMessage("You treat the .* with (?<compost>.+).").matcherConsumer(m -> {
                storage.remove(getStorageItemFromName(m.group("compost")), 1);
            }).onItemClick(),

            // Check.
            new OnChatMessage("Your bottomless compost bucket has a single use of (?<type>.+) ?compost remaining.").setFixedCharges(1),

            // Automatic chat message after use.
            new OnChatMessage("Your bottomless compost bucket has (?<quantity>.+) uses of (?<compost>.+) ?compost remaining.").matcherConsumer(m -> {
                final int quantity = getCleanQuantity(m.group("quantity"));
                storage.put(getStorageItemFromName(m.group("compost")), quantity);
            }),

            // Check.
            new OnChatMessage("Your bottomless compost bucket is currently holding one use of (?<compost>.+).").matcherConsumer(m -> {
                storage.put(getStorageItemFromName(m.group("compost")), 1);
            }),
            new OnChatMessage("Your bottomless compost bucket is currently holding (?<quantity>.+) uses of (?<compost>.+).").matcherConsumer(m -> {
                final int quantity = getCleanQuantity(m.group("quantity"));
                storage.put(getStorageItemFromName(m.group("compost")), quantity);
            }),

            // Discard.
            new OnChatMessage("You discard the contents of your bottomless compost bucket.").emptyStorage(),

            // Fill.
            new OnChatMessage("You fill your bottomless compost bucket with .* buckets? of (?<compost>.+). Your bottomless compost bucket now contains a total of (?<quantity>.+) uses.").matcherConsumer(m -> {
                final int quantity = getCleanQuantity(m.group("quantity"));
                storage.put(getStorageItemFromName(m.group("compost")), quantity);
            }),

            // Full.
            new OnChatMessage("Your bottomless compost bucket is just about full. You won't be able to squeeze any more compost in there.").consumer(() -> {
                storage.put(getCompostType(), 10000);
            }),

            // Fill compost from bin.
            new OnXpDrop(Skill.FARMING).onMenuOption("Take").onMenuTarget("Compost Bin", "Big Compost Bin").consumer(() -> {
                storage.add(getCompostType(), 2);
            }),
        };
    }

    private int getCleanQuantity(final String charges) {
        return Integer.parseInt(charges.replaceAll(",", "").replaceAll("\\.", ""));
    }

    private Optional<StorageItem> getCompostType() {
        for (final StorageItem storageItem : getStorage().values()) {
            if (storageItem.getQuantity() > 0) {
                return Optional.of(storageItem);
            }
        }

        return Optional.empty();
    }
}
