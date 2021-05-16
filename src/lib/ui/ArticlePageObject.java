package lib.ui;

import io.appium.java_client.AppiumDriver;

import lib.Platform;
import org.openqa.selenium.WebElement;


abstract public class ArticlePageObject extends MainPageObject {
    protected static String
            TITLE,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            EXISTING_FOLDER,
            ELEMENT_TYPE_NAVIGATION_BAR,
            CANCEL_SEARCH_BUTTON;


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(
                TITLE,
                "Cannot find article title",
                15
        );
    }

    public WebElement waitForTitleElement(int seconds) {
        return this.waitForElementPresent(
                TITLE,
                "Cannot find article title",
                seconds
        );
    }

    public WebElement waitForNavigationTypeElement() {
        return this.waitForElementPresent(
                ELEMENT_TYPE_NAVIGATION_BAR,
                "Cannot find " + ELEMENT_TYPE_NAVIGATION_BAR,
                15
        );
    }

    public WebElement waitForNavigationTypeElement(int seconds) {
        return this.waitForElementPresent(
                ELEMENT_TYPE_NAVIGATION_BAR,
                "Cannot find " + ELEMENT_TYPE_NAVIGATION_BAR,
                seconds
        );
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }
    }

    public String getElementTypeNavigationBar() {
        WebElement nav_element = waitForNavigationTypeElement();
        return nav_element.getAttribute("name");
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpFindToElements(
                    FOOTER_ELEMENT,
                    "Cannot find footer element",
                    40
            );
        } else {
            this.swipeUpTillElementAppear(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40
            );
        }

    }

    public void addArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                15
        );
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find the option to add article to reading list",
                15
        );
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it' tip overlay",
                15
        );
        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of articles folder",
                15
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder input",
                15
        );
        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK Button",
                15
        );
    }

    public void addArticlesToMySaved() {
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 5);
    }

    public void addArticleToExistingList() {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                15
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find the option to add article to reading list",
                15
        );

        this.waitForElementAndClick(
                EXISTING_FOLDER,
                "Cannot find 'Lists of lists' element on the page",
                15
        );
    }

    public void closeArticle() {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article, cannot find X link",
                15
        );
    }

    public void tapCancelButtonInSearch() {
        this.waitForElementAndClick(
                CANCEL_SEARCH_BUTTON,
                "Cannot find 'Cancel' search button",
                10);
    }

}
