package tictac7x.charges.item;

import net.runelite.api.Client;
import net.runelite.client.Notifier;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import tictac7x.charges.ChargesImprovedConfig;
import tictac7x.charges.store.ItemKey;
import tictac7x.charges.store.ItemActivity;
import tictac7x.charges.store.Store;

public class ChargedStatusItem extends ChargedItem {
    public ChargedStatusItem(
        final ItemKey infobox_id,
        final int item_id, Client client,
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
        super(
            infobox_id,
            item_id,
            client,
            client_thread,
            configs,
            items,
            infoboxes,
            chat_messages,
            notifier,
            config,
            store,
            plugin
        );
    }

    protected void deactivate() {
        setActivity(ItemActivity.DEACTIVATED);
    }

    protected void activate() {
        setActivity(ItemActivity.ACTIVATED);
    }

    private void setActivity(final ItemActivity status) {
        configs.setConfiguration(ChargesImprovedConfig.group, getConfigStatusKey(), status);
    }
}
