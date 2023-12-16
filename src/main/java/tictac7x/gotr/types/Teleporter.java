package tictac7x.gotr.types;

import net.runelite.api.ItemID;
import net.runelite.api.ObjectID;

public class Teleporter {
    final public int teleporterGameObjectid;
    final public int runeItemId;
    final public int talismanItemId;
    final private Essence essence;

    final public static int ACTIVE_ANIMATION_ID = 9363;

    public static Teleporter AIR = new Teleporter(ObjectID.GUARDIAN_OF_AIR, ItemID.AIR_RUNE, ItemID.PORTAL_TALISMAN_AIR, Essence.ELEMENTAL);
    public static Teleporter WATER = new Teleporter(ObjectID.GUARDIAN_OF_WATER, ItemID.WATER_RUNE, ItemID.PORTAL_TALISMAN_WATER, Essence.ELEMENTAL);
    public static Teleporter EARTH = new Teleporter(ObjectID.GUARDIAN_OF_EARTH, ItemID.EARTH_RUNE, ItemID.PORTAL_TALISMAN_EARTH, Essence.ELEMENTAL);
    public static Teleporter FIRE = new Teleporter(ObjectID.GUARDIAN_OF_FIRE, ItemID.FIRE_RUNE, ItemID.PORTAL_TALISMAN_FIRE, Essence.ELEMENTAL);

    public static Teleporter MIND = new Teleporter(ObjectID.GUARDIAN_OF_MIND, ItemID.MIND_RUNE, ItemID.PORTAL_TALISMAN_MIND, Essence.CATALYST);
    public static Teleporter BODY = new Teleporter(ObjectID.GUARDIAN_OF_BODY, ItemID.BODY_RUNE, ItemID.PORTAL_TALISMAN_BODY, Essence.CATALYST);
    public static Teleporter COSMIC = new Teleporter(ObjectID.GUARDIAN_OF_COSMIC, ItemID.COSMIC_RUNE, ItemID.PORTAL_TALISMAN_COSMIC, Essence.CATALYST);
    public static Teleporter CHAOS = new Teleporter(ObjectID.GUARDIAN_OF_CHAOS, ItemID.CHAOS_RUNE, ItemID.PORTAL_TALISMAN_CHAOS, Essence.CATALYST);
    public static Teleporter NATURE = new Teleporter(ObjectID.GUARDIAN_OF_NATURE, ItemID.NATURE_RUNE, ItemID.PORTAL_TALISMAN_NATURE, Essence.CATALYST);
    public static Teleporter LAW = new Teleporter(ObjectID.GUARDIAN_OF_LAW, ItemID.LAW_RUNE, ItemID.PORTAL_TALISMAN_LAW, Essence.CATALYST);
    public static Teleporter DEATH = new Teleporter(ObjectID.GUARDIAN_OF_DEATH, ItemID.DEATH_RUNE, ItemID.PORTAL_TALISMAN_DEATH, Essence.CATALYST);
    public static Teleporter BLOOD = new Teleporter(ObjectID.GUARDIAN_OF_BLOOD, ItemID.BLOOD_RUNE, ItemID.PORTAL_TALISMAN_BLOOD, Essence.CATALYST);

    public static Teleporter[] ALL = new Teleporter[]{
        AIR, WATER, EARTH, FIRE, MIND, BODY, COSMIC, CHAOS, NATURE, LAW, DEATH, BLOOD
    };

    public Teleporter(final int teleporterGameObjectid, final int runeItemId, final int talismanItemId, final Essence essence) {
        this.teleporterGameObjectid = teleporterGameObjectid;
        this.runeItemId = runeItemId;
        this.talismanItemId = talismanItemId;
        this.essence = essence;
    }

    public boolean isElemental() {
        return essence == Essence.ELEMENTAL;
    }

    public boolean isCatalyst() {
        return essence == Essence.CATALYST;
    }
}