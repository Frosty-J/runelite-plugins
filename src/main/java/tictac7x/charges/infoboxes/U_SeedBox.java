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
import tictac7x.charges.triggers.TriggerItem;

public class U_SeedBox extends ChargedItemInfoBox {
    public U_SeedBox(
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
        super(ChargesItem.SEED_BOX, ItemID.SEED_BOX, client, client_thread, configs, items, infoboxes, chat_messages, notifier, config, store, plugin);
        this.config_key = ChargesImprovedConfig.seed_box;
        this.triggers_items = new TriggerItem[]{
            new TriggerItem(ItemID.SEED_BOX),
            new TriggerItem(ItemID.OPEN_SEED_BOX),
        };
        this.zero_charges_is_positive = true;
        this.triggers_chat_messages = new TriggerChatMessage[]{
            new TriggerChatMessage("(The|Your) seed box is( now| already)? empty.").fixedCharges(0),
            new TriggerChatMessage("Stored (?<charges>.+) x .* seed in your seed box.").increaseDynamically(),
            new TriggerChatMessage("You put (?<charges>.+) x .* seed straight into your open seed box.").increaseDynamically(),
            new TriggerChatMessage("Emptied (?<charges>.+) x .* seed to your inventory.").decreaseDynamically(),
            new TriggerChatMessage("The seed box contains:").fixedCharges(0),
            new TriggerChatMessage("(?<charges>.+) x .* seed.").increaseDynamically(),
        };
    }
}