package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@value='Search Wikipedia']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
        SEARCH_CANCEL_BUTTON = "id:Cancel";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeCell";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_RESULT_BY_NAME_AND_DESCRIPTION_TPL_IOS = "xpath://XCUIElementTypeCell/XCUIElementTypeStaticText[contains(@name,'{TITLE}')]/following-sibling::*[contains(@name,'{DESCRIPTION}')]";
    }

    public IOSSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
