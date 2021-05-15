package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iOSMyListsPageObject extends MyListsPageObject {
    static {

        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[contains(@name, '{TITLE}')]/../..";
        CLOSE_OVERLAY_SYNC_YOUR_SAVED_ARTICLES="id:Close";
        SUBTITLE_ELEMENT = "id:";
    }

    public iOSMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}
