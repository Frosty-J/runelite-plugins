package tictac7x.charges.item;

import net.runelite.api.Client;
import net.runelite.api.events.AnimationChanged;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.GraphicChanged;
import net.runelite.api.events.HitsplatApplied;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.api.events.StatChanged;
import net.runelite.api.events.VarbitChanged;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.client.Notifier;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.ui.overlay.infobox.InfoBox;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import tictac7x.charges.ChargesImprovedConfig;
import tictac7x.charges.ChargesImprovedPlugin;
import tictac7x.charges.item.listeners.OnAnimationChanged;
import tictac7x.charges.item.listeners.OnChatMessage;
import tictac7x.charges.item.listeners.OnGraphicChanged;
import tictac7x.charges.item.listeners.OnHitsplatApplied;
import tictac7x.charges.item.listeners.OnItemContainerChanged;
import tictac7x.charges.item.listeners.OnStatChanged;
import tictac7x.charges.item.listeners.OnVarbitChanged;
import tictac7x.charges.item.listeners.OnWidgetLoaded;
import tictac7x.charges.item.triggers.TriggerAnimation;
import tictac7x.charges.item.triggers.TriggerChatMessage;
import tictac7x.charges.item.triggers.TriggerGraphic;
import tictac7x.charges.item.triggers.TriggerHitsplat;
import tictac7x.charges.item.triggers.TriggerItem;
import tictac7x.charges.item.triggers.TriggerItemContainer;
import tictac7x.charges.item.triggers.TriggerReset;
import tictac7x.charges.item.triggers.TriggerStat;
import tictac7x.charges.item.triggers.TriggerWidget;
import tictac7x.charges.store.Charges;
import tictac7x.charges.store.ItemActivity;
import tictac7x.charges.store.ItemKey;
import tictac7x.charges.store.Store;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.Color;
import java.util.Optional;

public class ChargedItemInfobox extends InfoBox {
    private final ChargedItem chargedItem;
    private final ItemManager items;
    private final InfoBoxManager infoboxes;
    private final ChargesImprovedConfig config;
    private int itemId;

    public ChargedItemInfobox(
        final ChargedItem chargedItem,
        final ItemManager items,
        final InfoBoxManager infoboxes,
        final ClientThread clientThread,
        final ChargesImprovedConfig config,
        final ChargesImprovedPlugin plugin
    ) {
        super(items.getImage(chargedItem.item_id), plugin);
        this.chargedItem = chargedItem;
        this.items = items;
        this.infoboxes = infoboxes;
        this.config = config;
        this.itemId = chargedItem.item_id;
    }

    @Override
    public String getName() {
        return super.getName() + "_" + chargedItem.item_id;
    }

    @Override
    public String getText() {
//        System.out.println("get text");

        return ChargesImprovedPlugin.getChargesMinified(chargedItem.getCharges());
    }

    @Override
    public String getTooltip() {
//        System.out.println("get tooltip");

        return "";
    }

    @Override
    public Color getTextColor() {
        final int charges = chargedItem.getCharges();

        if (charges == Charges.UNKNOWN) {
            return config.getColorUnknown();
        }

        if (
            charges == 0 && !chargedItem.zero_charges_is_positive ||
            chargedItem.negative_full_charges != null && charges == chargedItem.negative_full_charges ||
            chargedItem.needs_to_be_equipped_for_infobox && !chargedItem.in_equipment ||
            chargedItem.is_negative ||
            chargedItem.isDeactivated()
        ) {
            return config.getColorEmpty();
        }

        return config.getColorDefault();
    }

    @Override
    public boolean render() {
        if (itemId != chargedItem.item_id) {
            this.itemId = chargedItem.item_id;
            setImage(items.getImage(itemId));
            infoboxes.updateInfoBoxImage(this);
        }

        return (
            config.showInfoboxes() &&
            !config.getHiddenInfoboxes().contains(chargedItem.infobox_id) &&
            chargedItem.getCharges() != Charges.UNLIMITED &&
            (chargedItem.in_inventory || chargedItem.in_equipment)
        );
    }
}


