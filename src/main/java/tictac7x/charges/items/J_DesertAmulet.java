package tictac7x.charges.items;

import net.runelite.api.Client;
import net.runelite.api.ItemID;
import net.runelite.client.Notifier;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import tictac7x.charges.ChargesImprovedConfig;
import tictac7x.charges.item.ChargedItem;
import tictac7x.charges.item.triggers.TriggerChatMessage;
import tictac7x.charges.item.triggers.TriggerDailyReset;
import tictac7x.charges.item.triggers.TriggerItem;
import tictac7x.charges.store.ItemKey;
import tictac7x.charges.store.Store;

public class J_DesertAmulet extends ChargedItem {
    public J_DesertAmulet(
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
        super(ItemKey.DESERT_AMULET, ItemID.DESERT_AMULET, client, client_thread, configs, items, infoboxes, chat_messages, notifier, config, store);
        this.config_key = ChargesImprovedConfig.desert_amulet;
        this.triggersItems = new TriggerItem[]{
            new TriggerItem(ItemID.DESERT_AMULET_3).maxCharges(1),
        };
        this.triggersChatMessages = new TriggerChatMessage[]{
            new TriggerChatMessage("You have already used your available teleports for today.").fixedCharges(0),
        };
        this.triggersResetsDaily = new TriggerDailyReset[]{
            new TriggerDailyReset(1).specificItem(ItemID.DESERT_AMULET_3),
        };
    }
}