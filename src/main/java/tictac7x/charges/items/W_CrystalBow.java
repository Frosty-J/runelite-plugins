package tictac7x.charges.items;

import com.google.gson.Gson;
import net.runelite.api.Client;
import net.runelite.api.ItemID;
import net.runelite.client.Notifier;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import tictac7x.charges.ChargesImprovedConfig;
import tictac7x.charges.item.ChargedItem;
import tictac7x.charges.item.triggers.OnChatMessage;
import tictac7x.charges.item.triggers.OnHitsplatApplied;
import tictac7x.charges.item.triggers.TriggerBase;
import tictac7x.charges.item.triggers.TriggerItem;
import tictac7x.charges.store.ItemKey;
import tictac7x.charges.store.Store;

import static tictac7x.charges.store.HitsplatTarget.ENEMY;

public class W_CrystalBow extends ChargedItem {
    public W_CrystalBow(
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
        super(ChargesImprovedConfig.crystal_bow, ItemKey.CRYSTAL_BOW, ItemID.CRYSTAL_BOW, client, client_thread, configs, items, infoboxes, chat_messages, notifier, config, store, gson);

        this.items = new TriggerItem[]{
            new TriggerItem(ItemID.CRYSTAL_BOW),
            new TriggerItem(ItemID.CRYSTAL_BOW_24123),
            new TriggerItem(ItemID.CRYSTAL_BOW_110),
            new TriggerItem(ItemID.CRYSTAL_BOW_110_I),
            new TriggerItem(ItemID.CRYSTAL_BOW_210),
            new TriggerItem(ItemID.CRYSTAL_BOW_210_I),
            new TriggerItem(ItemID.CRYSTAL_BOW_310),
            new TriggerItem(ItemID.CRYSTAL_BOW_310_I),
            new TriggerItem(ItemID.CRYSTAL_BOW_410),
            new TriggerItem(ItemID.CRYSTAL_BOW_410_I),
            new TriggerItem(ItemID.CRYSTAL_BOW_510),
            new TriggerItem(ItemID.CRYSTAL_BOW_510_I),
            new TriggerItem(ItemID.CRYSTAL_BOW_610),
            new TriggerItem(ItemID.CRYSTAL_BOW_610_I),
            new TriggerItem(ItemID.CRYSTAL_BOW_710),
            new TriggerItem(ItemID.CRYSTAL_BOW_710_I),
            new TriggerItem(ItemID.CRYSTAL_BOW_810),
            new TriggerItem(ItemID.CRYSTAL_BOW_810_I),
            new TriggerItem(ItemID.CRYSTAL_BOW_910),
            new TriggerItem(ItemID.CRYSTAL_BOW_910_I),
            new TriggerItem(ItemID.CRYSTAL_BOW_FULL),
            new TriggerItem(ItemID.CRYSTAL_BOW_FULL_I),
            new TriggerItem(ItemID.CRYSTAL_BOW_BASIC),
            new TriggerItem(ItemID.CRYSTAL_BOW_ATTUNED),
            new TriggerItem(ItemID.CRYSTAL_BOW_INACTIVE).fixedCharges(0),
            new TriggerItem(ItemID.CRYSTAL_BOW_PERFECTED),
            new TriggerItem(ItemID.NEW_CRYSTAL_BOW),
            new TriggerItem(ItemID.NEW_CRYSTAL_BOW_4213),
            new TriggerItem(ItemID.NEW_CRYSTAL_BOW_16888),
            new TriggerItem(ItemID.NEW_CRYSTAL_BOW_I),
            new TriggerItem(ItemID.NEW_CRYSTAL_BOW_I_16889),
        };

        this.triggers = new TriggerBase[] {
            // Check.
            new OnChatMessage("Your crystal bow has (?<charges>.+) charges? remaining.").setDynamically(),

            // Attack.
            new OnHitsplatApplied(ENEMY).isEquipped().decreaseCharges(1)
        };
    }
}