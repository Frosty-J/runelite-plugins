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
import tictac7x.charges.item.ChargedItem;
import tictac7x.charges.ChargesImprovedConfig;
import tictac7x.charges.store.ItemKey;
import tictac7x.charges.store.Store;
import tictac7x.charges.item.triggers.TriggerChatMessage;
import tictac7x.charges.item.triggers.TriggerHitsplat;
import tictac7x.charges.item.triggers.TriggerItem;

public class A_CrystalBody extends ChargedItem {
    public A_CrystalBody(
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
        super(ItemKey.CRYSTAL_BODY, ItemID.CRYSTAL_BODY, client, client_thread, configs, items, infoboxes, chat_messages, notifier, config, store);
        this.config_key = ChargesImprovedConfig.crystal_body;
        this.triggersItems = new TriggerItem[]{
            new TriggerItem(ItemID.CRYSTAL_BODY),
            new TriggerItem(ItemID.CRYSTAL_BODY_27697),
            new TriggerItem(ItemID.CRYSTAL_BODY_27709),
            new TriggerItem(ItemID.CRYSTAL_BODY_27721),
            new TriggerItem(ItemID.CRYSTAL_BODY_27733),
            new TriggerItem(ItemID.CRYSTAL_BODY_27745),
            new TriggerItem(ItemID.CRYSTAL_BODY_27757),
            new TriggerItem(ItemID.CRYSTAL_BODY_27769),
            new TriggerItem(ItemID.CRYSTAL_BODY_INACTIVE).fixedCharges(0),
            new TriggerItem(ItemID.CRYSTAL_BODY_INACTIVE_27699).fixedCharges(0),
            new TriggerItem(ItemID.CRYSTAL_BODY_INACTIVE_27711).fixedCharges(0),
            new TriggerItem(ItemID.CRYSTAL_BODY_INACTIVE_27723).fixedCharges(0),
            new TriggerItem(ItemID.CRYSTAL_BODY_INACTIVE_27735).fixedCharges(0),
            new TriggerItem(ItemID.CRYSTAL_BODY_INACTIVE_27747).fixedCharges(0),
            new TriggerItem(ItemID.CRYSTAL_BODY_INACTIVE_27759).fixedCharges(0),
            new TriggerItem(ItemID.CRYSTAL_BODY_INACTIVE_27771).fixedCharges(0)
        };
        this.triggersChatMessages = new TriggerChatMessage[]{
            new TriggerChatMessage("Your crystal body has (?<charges>.+) charges? remaining").onItemClick()
        };
        this.triggersHitsplats = new TriggerHitsplat[]{
            new TriggerHitsplat(1).equipped().onSelf()
        };
    }
}