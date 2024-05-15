package tictac7x.charges.item.storage;

import tictac7x.charges.store.Charges;

import java.util.Optional;

public class StorageItem {
    public final int itemId;
    public Optional<String> checkName = Optional.empty();
    public Optional<String> displayName = Optional.empty();
    public int quantity = Charges.UNKNOWN;

    public StorageItem(final int itemId) {
        this.itemId = itemId;
    }

    public StorageItem checkName(final String checkName) {
        this.checkName = Optional.of(checkName);
        return this;
    }

    public StorageItem displayName(final String displayName) {
        this.displayName = Optional.of(displayName);
        return this;
    }

    public StorageItem quantity(final int quantity) {
        this.quantity = quantity;
        return this;
    }

    public int getId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    public void setCheckName(final String displayName) {
        this.checkName = Optional.of(displayName);
    }

    public void setDisplayName(final String displayName) {
        this.displayName = Optional.of(displayName);
    }
}
