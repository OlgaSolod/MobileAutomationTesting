package lib.Tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    private static final String name_of_folder = "Learning programming";

    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(name_of_folder);
        } else {
            articlePageObject.addArticlesToMySaved();
        }
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.closeArticle();
        } else {
            articlePageObject.closeArticle();
            articlePageObject.tapCancelButtonInSearch();
        }

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyLists();
        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {

            myListsPageObject.openFolderByName(name_of_folder);
        } else {
            myListsPageObject.tapCloseButtonInOverlayScreenInSaved();
        }
        myListsPageObject.swipeArticleToDelete(article_title);
    }

    @Test
    public void testSavedTwoArticlesToMyListsAndDeleteOne() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String article_title_first = articlePageObject.getArticleTitle();
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(name_of_folder);
        } else {
            articlePageObject.addArticlesToMySaved();
        }
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.closeArticle();
        } else {
            articlePageObject.closeArticle();
            articlePageObject.tapCancelButtonInSearch();
        }

//        articlePageObject.addArticleToMyList(name_of_folder);
//        articlePageObject.closeArticle();

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("JavaScript");

        articlePageObject.waitForTitleElement();
        String article_title_second = articlePageObject.getArticleTitle();
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToExistingList();
        } else {
            articlePageObject.addArticlesToMySaved();
        }
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.closeArticle();
        } else {
            articlePageObject.closeArticle();
            articlePageObject.tapCancelButtonInSearch();
        }

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(name_of_folder);
        } else {
            myListsPageObject.tapCloseButtonInOverlayScreenInSaved();
        }

        myListsPageObject.swipeArticleToDelete(article_title_first);

        myListsPageObject.waitForArticleToAppearByTitle(article_title_second);
//        myListsPageObject.openArticleByTitle(article_title_second);


        String title_in_article_attribute = articlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Elements isn't concur with!",
                article_title_second,
                title_in_article_attribute);
    }
}
