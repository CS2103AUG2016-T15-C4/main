package jym.manager.ui;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

import java.util.logging.Logger;

import jym.manager.commons.core.LogsCenter;
import jym.manager.commons.util.FxViewUtil;
import jym.manager.model.task.ReadOnlyTask;

/**
 * The Browser Panel of the App.
 */
public class BrowserPanel extends UiPart{

    private static Logger logger = LogsCenter.getLogger(BrowserPanel.class);
    private WebView browser;

    /**
     * Constructor is kept private as {@link #load(AnchorPane)} is the only way to create a BrowserPanel.
     */
    private BrowserPanel() {

    }

    @Override
    public void setNode(Node node) {
        //not applicable
    }

    @Override
    public String getFxmlPath() {
        return null; //not applicable
    }

    /**
     * Factory method for creating a Browser Panel.
     * This method should be called after the FX runtime is initialized and in FX application thread.
     * @param placeholder The AnchorPane where the BrowserPanel must be inserted
     */
    public static BrowserPanel load(AnchorPane placeholder){
        logger.info("Initializing browser");
        BrowserPanel browserPanel = new BrowserPanel();
        browserPanel.browser = new WebView();
        placeholder.setOnKeyPressed(Event::consume); // To prevent triggering events for typing inside the loaded Web page.
        FxViewUtil.applyAnchorBoundaryParameters(browserPanel.browser, 0.0, 0.0, 0.0, 0.0);
        placeholder.getChildren().add(browserPanel.browser);
        return browserPanel;
    }

    public void loadTaskPage(ReadOnlyTask task) {
        loadPage("https://www.google.com.sg/#safe=off&q=" + task.getDescription().toString().replaceAll(" ", "+"));
    }

    public void loadPage(String url){
        browser.getEngine().load(url);
    }

    /**
     * Frees resources allocated to the browser.
     */
    public void freeResources() {
        browser = null;
    }

}
