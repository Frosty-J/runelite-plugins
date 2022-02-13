package tictac7x.balloon;

import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Graphics2D;
import tictac7x.balloon.BalloonConfig.Logs;

import net.runelite.api.ItemID;
import net.runelite.client.game.ItemManager;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.ImageComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;
import net.runelite.client.ui.overlay.components.ComponentOrientation;

public class BalloonOverlayLogs extends OverlayPanel {
    private final BalloonConfig config;
    private final ItemManager items;
    private final BalloonStorage balloon_storage;

    private final PanelComponent panel = new PanelComponent();

    public BalloonOverlayLogs(final BalloonConfig config, final ConfigManager configs, final ItemManager items, final BalloonStorage balloon_storage) {
        this.config = config;
        this.items = items;
        this.balloon_storage = balloon_storage;

        setLayer(OverlayLayer.UNDER_WIDGETS);
        setPosition(OverlayPosition.ABOVE_CHATBOX_RIGHT);

        panel.setWrap(true);
        panel.setOrientation(ComponentOrientation.HORIZONTAL);
        panel.setBackgroundColor(null);

        panelComponent.setOrientation(ComponentOrientation.VERTICAL);
        panelComponent.setBorder(new Rectangle(0,0,0,0));
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        final BalloonConfig.style style = config.getStyle();

        if (style == BalloonConfig.style.WIDGET) {
            panel.getChildren().clear();

            // Logs - Entrana / Taverly.
            if (balloon_storage.shouldLogsBeDisplayed(Logs.LOGS_REGULAR)) {
                panel.getChildren().add(new ImageComponent(items.getImage(ItemID.LOGS, balloon_storage.getLogs(Logs.LOGS_REGULAR), true)));
            }

            // Oak logs - Crafting guild.
            if (balloon_storage.shouldLogsBeDisplayed(Logs.LOGS_OAK)) {
                panel.getChildren().add(new ImageComponent(items.getImage(ItemID.OAK_LOGS, balloon_storage.getLogs(Logs.LOGS_OAK), true)));
            }

            // Willow logs - Varrock.
            if (balloon_storage.shouldLogsBeDisplayed(Logs.LOGS_WILLOW)) {
                panel.getChildren().add(new ImageComponent(items.getImage(ItemID.WILLOW_LOGS, balloon_storage.getLogs(Logs.LOGS_WILLOW), true)));
            }

            // Yew logs - Castle wars.
            if (balloon_storage.shouldLogsBeDisplayed(Logs.LOGS_YEW)) {
                panel.getChildren().add(new ImageComponent(items.getImage(ItemID.YEW_LOGS, balloon_storage.getLogs(Logs.LOGS_YEW), true)));
            }

            // Magic logs - Grand tree.
            if (balloon_storage.shouldLogsBeDisplayed(Logs.LOGS_MAGIC)) {
                panel.getChildren().add(new ImageComponent(items.getImage(ItemID.MAGIC_LOGS, balloon_storage.getLogs(Logs.LOGS_MAGIC), true)));
            }

            if (panel.getChildren().size() > 0) {
                panelComponent.getChildren().clear();
                panelComponent.getChildren().add(panel);
                return super.render(graphics);
            }
        }

        return null;
    }
}
