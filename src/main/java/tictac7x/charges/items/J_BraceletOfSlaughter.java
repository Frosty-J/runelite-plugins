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
import tictac7x.charges.item.triggers.TriggerBase;
import tictac7x.charges.item.triggers.TriggerItem;
import tictac7x.charges.store.ItemKey;
import tictac7x.charges.store.Store;

public class J_BraceletOfSlaughter extends ChargedItem {
    public J_BraceletOfSlaughter(
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
        super(ChargesImprovedConfig.bracelet_of_slaughter, ItemKey.BRACELET_OF_SLAUGHTER, ItemID.BRACELET_OF_SLAUGHTER, client, client_thread, configs, items, infoboxes, chat_messages, notifier, config, store, gson);

        this.items = new TriggerItem[]{
            new TriggerItem(ItemID.BRACELET_OF_SLAUGHTER).needsToBeEquipped(),
        };

        this.triggers = new TriggerBase[] {
            new OnChatMessage("Your bracelet of slaughter has (?<charges>.+) charges? left.").setDynamically(),
            new OnChatMessage("Your bracelet of slaughter prevents your slayer count from decreasing. It has (?<charges>.+) charges? left.").setDynamically(),
            new OnChatMessage("Your bracelet of slaughter prevents your slayer count from decreasing. It then crumbles to dust.").fixedCharges(30).notification("Your slaughter bracelet crumbles to dust.")
        };
    }
}