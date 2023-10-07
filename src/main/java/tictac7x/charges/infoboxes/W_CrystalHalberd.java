package tictac7x.charges.infoboxes;

import net.runelite.api.Client;
import net.runelite.api.ItemID;
import net.runelite.client.Notifier;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import tictac7x.charges.ChargedItemInfoBox;
import tictac7x.charges.ChargesImprovedConfig;
import tictac7x.charges.store.ChargesItem;
import tictac7x.charges.store.Store;
import tictac7x.charges.triggers.TriggerChatMessage;
import tictac7x.charges.triggers.TriggerHitsplat;
import tictac7x.charges.triggers.TriggerItem;

public class W_CrystalHalberd extends ChargedItemInfoBox {
    public W_CrystalHalberd(
        final Client client,
        final ClientThread client_thread,
        final ConfigManager configs,
        final ItemManager items,
        final InfoBoxManager infoboxes,
        final ChatMessageManager chat_messages,
        final Notifier notifier,
        final ChargesImprovedConfig config,
        final Store store,
        final Plugin plugin
    ) {
        super(ChargesItem.CRYSTAL_HALBERD, ItemID.CRYSTAL_HALBERD, client, client_thread, configs, items, infoboxes, chat_messages, notifier, config, store, plugin);
        this.config_key = ChargesImprovedConfig.crystal_halberd;
        this.triggers_items = new TriggerItem[]{
            new TriggerItem(ItemID.CRYSTAL_HALBERD),
            new TriggerItem(ItemID.CRYSTAL_HALBERD_24125),
            new TriggerItem(ItemID.CRYSTAL_HALBERD_110),
            new TriggerItem(ItemID.CRYSTAL_HALBERD_110_I),
            new TriggerItem(ItemID.CRYSTAL_HALBERD_210),
            new TriggerItem(ItemID.CRYSTAL_HALBERD_210_I),
            new TriggerItem(ItemID.CRYSTAL_HALBERD_310),
            new TriggerItem(ItemID.CRYSTAL_HALBERD_310_I),
            new TriggerItem(ItemID.CRYSTAL_HALBERD_410),
            new TriggerItem(ItemID.CRYSTAL_HALBERD_410_I),
            new TriggerItem(ItemID.CRYSTAL_HALBERD_510),
            new TriggerItem(ItemID.CRYSTAL_HALBERD_510_I),
            new TriggerItem(ItemID.CRYSTAL_HALBERD_610),
            new TriggerItem(ItemID.CRYSTAL_HALBERD_610_I),
            new TriggerItem(ItemID.CRYSTAL_HALBERD_710),
            new TriggerItem(ItemID.CRYSTAL_HALBERD_710_I),
            new TriggerItem(ItemID.CRYSTAL_HALBERD_810),
            new TriggerItem(ItemID.CRYSTAL_HALBERD_810_I),
            new TriggerItem(ItemID.CRYSTAL_HALBERD_910),
            new TriggerItem(ItemID.CRYSTAL_HALBERD_910_I),
            new TriggerItem(ItemID.CRYSTAL_HALBERD_FULL),
            new TriggerItem(ItemID.CRYSTAL_HALBERD_FULL_I),
            new TriggerItem(ItemID.CRYSTAL_HALBERD_BASIC),
            new TriggerItem(ItemID.CRYSTAL_HALBERD_ATTUNED),
            new TriggerItem(ItemID.CRYSTAL_HALBERD_INACTIVE).fixedCharges(0),
            new TriggerItem(ItemID.CRYSTAL_HALBERD_PERFECTED),
            new TriggerItem(ItemID.NEW_CRYSTAL_HALBERD_FULL),
            new TriggerItem(ItemID.NEW_CRYSTAL_HALBERD_FULL_I),
            new TriggerItem(ItemID.NEW_CRYSTAL_HALBERD_FULL_16893),
            new TriggerItem(ItemID.NEW_CRYSTAL_HALBERD_FULL_I_16892),
        };
        this.triggers_chat_messages = new TriggerChatMessage[]{
            new TriggerChatMessage("Your crystal halberd has (?<charges>.+) charges? remaining.")
        };
        this.triggers_hitsplats = new TriggerHitsplat[]{
            new TriggerHitsplat(1).onEnemy().equipped()
        };
    }
}
