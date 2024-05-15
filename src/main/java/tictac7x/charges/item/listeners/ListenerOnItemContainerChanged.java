package tictac7x.charges.item.listeners;

import net.runelite.api.Client;
import net.runelite.api.InventoryID;
import net.runelite.api.Item;
import net.runelite.api.ItemContainer;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.client.Notifier;
import tictac7x.charges.ChargesImprovedConfig;
import tictac7x.charges.item.ChargedItem;
import tictac7x.charges.item.ChargedItemBase;
import tictac7x.charges.item.ChargedItemWithStorage;
import tictac7x.charges.item.storage.StorageItem;
import tictac7x.charges.item.triggers.OnItemContainerChanged;
import tictac7x.charges.item.triggers.TriggerBase;
import tictac7x.charges.item.triggers.TriggerItem;
import tictac7x.charges.store.ItemContainerType;
import tictac7x.charges.store.MenuEntry;

import java.util.Optional;

public class ListenerOnItemContainerChanged extends ListenerBase {
    public ListenerOnItemContainerChanged(final Client client, final ChargedItemBase chargedItem, final Notifier notifier, final ChargesImprovedConfig config) {
        super(client, chargedItem, notifier, config);
    }

    public void trigger(final ItemContainerChanged event) {
        // Get quantity from amount in item container.
        for (final TriggerItem triggerItem : chargedItem.items) {
            if (triggerItem.quantityCharges.isPresent()) {
               for (final Item item : event.getItemContainer().getItems()) {
                    if (item.getId() == triggerItem.itemId) {
                        ((ChargedItem) chargedItem).setCharges(item.getQuantity());
                        break;
                    }
                }
            }
        }

        for (final TriggerBase triggerBase : chargedItem.triggers) {
            if (!isValidTrigger(triggerBase, event)) continue;
            boolean triggerUsed = false;
            final OnItemContainerChanged trigger = (OnItemContainerChanged) triggerBase;

            // Fill storage from inventory all items.
            if (trigger.fillStorageFromInventory.isPresent()) {
                ((ChargedItemWithStorage) chargedItem).storage.fillFromInventory();
                triggerUsed = true;
            }

            // Fill storage from inventory single item.
            if (trigger.fillStorageFromInventorySingle.isPresent()) {
                for (final MenuEntry entry : chargedItem.store.menuOptionsClicked) {
                    final Optional<StorageItem> usedItem = ((ChargedItemWithStorage) chargedItem).getStorageItemFromName(entry.target);
                    if (usedItem.isPresent()) {
                        ((ChargedItemWithStorage) chargedItem).storage.fillFromInventoryIndividually(usedItem.get().getId());
                        triggerUsed = true;
                        break;
                    }
                }
            }

            // Empty storage to inventory.
            if (trigger.emptyStorageToInventory.isPresent()) {
                ((ChargedItemWithStorage) chargedItem).storage.emptyToInventory();
                triggerUsed = true;
            }

            if (super.trigger(trigger)) {
                triggerUsed = true;
            }

            if (triggerUsed) return;
        }
    }

    public boolean isValidTrigger(final TriggerBase triggerBase, final ItemContainerChanged event) {
        if (!(triggerBase instanceof OnItemContainerChanged)) return false;
        final OnItemContainerChanged trigger = (OnItemContainerChanged) triggerBase;

        // Item container type check.
        final ItemContainer itemContainer = event.getItemContainer();
        if (
            itemContainer == null ||
            trigger.itemContainerType == ItemContainerType.INVENTORY && itemContainer.getId() != InventoryID.INVENTORY.getId() ||
            trigger.itemContainerType == ItemContainerType.BANK && itemContainer.getId() != InventoryID.BANK.getId()
        ) {
            return false;
        }

        // Fill storage from inventory check.
        if (trigger.fillStorageFromInventory.isPresent() && !(chargedItem instanceof ChargedItemWithStorage)) {
            return false;
        }

        return super.isValidTrigger(trigger);
    }
}
