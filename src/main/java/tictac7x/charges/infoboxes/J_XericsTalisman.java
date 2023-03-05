package tictac7x.charges.infoboxes;

import net.runelite.api.Client;
import net.runelite.api.ItemID;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import tictac7x.charges.ChargedItemInfoBox;
import tictac7x.charges.ChargesImprovedConfig;
import tictac7x.charges.ChargesItem;
import tictac7x.charges.triggers.TriggerAnimation;
import tictac7x.charges.triggers.TriggerChatMessage;
import tictac7x.charges.triggers.TriggerItem;
import tictac7x.charges.triggers.TriggerWidget;

public class J_XericsTalisman extends ChargedItemInfoBox {
    public J_XericsTalisman(final Client client, final ClientThread client_thread, final ConfigManager configs, final ItemManager items, final InfoBoxManager infoboxes, final ChatMessageManager chat_messages, final ChargesImprovedConfig config, final Plugin plugin) {
        super(ChargesItem.XERICS_TALISMAN, ItemID.XERICS_TALISMAN, client, client_thread, configs, items, infoboxes, chat_messages, config, plugin);
        this.config_key = ChargesImprovedConfig.xerics_talisman;
        this.triggers_items = new TriggerItem[]{
            new TriggerItem(ItemID.XERICS_TALISMAN_INERT).fixedCharges(0),
            new TriggerItem(ItemID.XERICS_TALISMAN),
        };
        this.triggers_chat_messages = new TriggerChatMessage[]{
            new TriggerChatMessage("The talisman has one charge.").onItemClick(),
            new TriggerChatMessage("The talisman has (?<charges>.+) charges.").onItemClick(),
            new TriggerChatMessage("Your talisman has run out of charges.").onItemClick(),
        };
        this.triggers_animations = new TriggerAnimation[]{
            new TriggerAnimation(3865).decreaseCharges(1)
        };
        this.triggers_widgets = new TriggerWidget[]{
            new TriggerWidget("Your talisman now has one charge."),
            new TriggerWidget("Your talisman now has (?<charges>.+) charges?."),
            new TriggerWidget("The talisman has (?<charges>.+) charges.").customWidget(187, 0, 1)
        };
    }
}
