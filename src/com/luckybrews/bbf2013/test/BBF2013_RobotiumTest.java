package com.luckybrews.bbf2013.test;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.TextView;

import com.jayway.android.robotium.solo.Solo;
import com.luckybrews.bbf2013.BeerListActivity;
import com.luckybrews.bbf2013.DetailsActivity;
import com.luckybrews.bbf2013.MainActivity;
import com.luckybrews.bbf2013.MyDrinksActivity;
import com.luckybrews.bbf2013.R;
import com.luckybrews.bbf2013.WishlistActivity;

public class BBF2013_RobotiumTest extends ActivityInstrumentationTestCase2<MainActivity> {
	
	private Solo solo;

	public BBF2013_RobotiumTest() {
		super(MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}

	@Override
	  public void tearDown() throws Exception {
	    try {
	      solo.finalize();
	    } catch (Throwable e) {

	      e.printStackTrace();
	    }
	    getActivity().finish();
	    super.tearDown();
	  }
	
	public void testOpenLists() {
		
		// Check that we have the right activity
	    solo.assertCurrentActivity("wrong activiy", MainActivity.class);

		
		// open the beer list
		solo.clickOnText("Beer List");
		
		 // Validate that the Activity is the correct one
	    solo.assertCurrentActivity("wrong activiy", BeerListActivity.class);
	    
	    solo.goBack();
	    solo.assertCurrentActivity("wrong activiy", MainActivity.class);
	    
	    solo.clickOnText("My Wishlist");
	    solo.assertCurrentActivity("wrong activiy", WishlistActivity.class);
	    
	    solo.goBack();
	    solo.assertCurrentActivity("wrong activiy", MainActivity.class);
	    
	    solo.clickOnText("My Drinks");
	    solo.assertCurrentActivity("wrong activiy", MyDrinksActivity.class);
	    
	    solo.goBack();
	}
	
	public void testSaveBeer() {
		
		// open the beer list
		solo.clickOnText("Beer List");
		
		// mark one beer as drunk
		solo.clickInList(0);
		solo.assertCurrentActivity("wrong activiy", DetailsActivity.class);
		
		solo.clickOnText("Yes");
		
		// name of saved beer
		TextView tw = (TextView) solo.getView(R.id.beername);
		String name = "" + tw.getText();
		
		solo.goBack();
		solo.goBack();
		solo.assertCurrentActivity("wrong activiy", MainActivity.class);
		
		solo.clickOnText("My Drinks");
	    solo.assertCurrentActivity("wrong activiy", MyDrinksActivity.class);
	    
	    solo.clickInList(0);
		solo.assertCurrentActivity("wrong activiy", DetailsActivity.class);
		
		TextView tw2 = (TextView) solo.getView(R.id.beername);
		String saved_name = "" + tw2.getText();
		assertEquals(name, saved_name);	
		
		solo.goBack();
		solo.goBack();
	}
	
	public void testWhislistBeer() {
		
		// open the beer list
		solo.clickOnText("Beer List");
		
		// mark one beer as drunk
		solo.clickInList(0);
		solo.assertCurrentActivity("wrong activiy", DetailsActivity.class);
		
		solo.clickOnText("Wish List");
		
		// name of saved beer
		TextView tw = (TextView) solo.getView(R.id.beername);
		String name = "" + tw.getText();
		
		solo.goBack();
		solo.goBack();
		solo.assertCurrentActivity("wrong activiy", MainActivity.class);
		
		solo.clickOnText("My Wishlist");
	    solo.assertCurrentActivity("wrong activiy", WishlistActivity.class);
	    
	    solo.clickInList(0);
		solo.assertCurrentActivity("wrong activiy", DetailsActivity.class);
		
		TextView tw2 = (TextView) solo.getView(R.id.beername);
		String saved_name = "" + tw2.getText();
		assertEquals(name, saved_name);	
		
		solo.goBack();
		solo.goBack();
	}
	
	public void testSortByName() {
		
		// open the beer list
		solo.clickOnText("Beer List");
		
		solo.clickOnMenuItem("Name", true);
		
		solo.clickInList(0);
		
		TextView tw = (TextView) solo.getView(R.id.beername);
		String name = "" + tw.getText();
		
		assertEquals("8 Ball", name);
		
		solo.goBack();
		solo.goBack();
	}
		
	
	public void testSortByAbv() {
		
		// open the beer list
		solo.clickOnText("Beer List");
		
		solo.clickOnMenuItem("ABV", true);
		
		solo.clickInList(0);
		
		TextView tw = (TextView) solo.getView(R.id.beername);
		String name = "" + tw.getText();
		
		assertEquals("Neck Oil", name);
		
		solo.goBack();
		solo.goBack();
	}
	
	public void testSortByColor() {
		
		// open the beer list
		solo.clickOnText("Beer List");
		
		solo.clickOnMenuItem("Color", true);
		
		solo.clickInList(0);
		
		TextView tw = (TextView) solo.getView(R.id.beername);
		String name = "" + tw.getText();
		
		assertEquals("Imperial Smog", name);
		
		solo.goBack();
		solo.goBack();
	}
	
	public void testSortByStyle() {
		
		// open the beer list
		solo.clickOnText("Beer List");
		
		solo.clickOnMenuItem("Style", true);
		
		solo.clickInList(0);
		
		TextView tw = (TextView) solo.getView(R.id.beername);
		String name = "" + tw.getText();
		
		assertEquals("Neck Oil", name);
		
		solo.goBack();
		solo.goBack();
	}
	
	public void testRemoveWishlist() {
		
		// add 1 beer to wishlist
		solo.clickOnText("Beer List");
		solo.clickInList(2);
		TextView tw0 = (TextView) solo.getView(R.id.beername);
		String name0 = "" + tw0.getText();
		Log.d("TEST", "name0: " + name0);
		solo.clickOnText("Wish List");
		solo.goBack();
		solo.goBack();
		
		// add 1 beer to wishlist
		solo.clickOnText("Beer List");
		solo.clickInList(1);
		TextView tw1 = (TextView) solo.getView(R.id.beername);
		String name1 = "" + tw1.getText();
		Log.d("TEST", "name1: " + name1);
		solo.clickOnText("Wish List");
		solo.goBack();
		solo.goBack();

		// check in wishlist
		solo.clickOnText("My Wishlist");
		solo.clickInList(2);
		TextView tw2 = (TextView) solo.getView(R.id.beername);
		String name2 = "" + tw2.getText();
		Log.d("TEST", "name2: " + name2);
		assertEquals(name1, name2);
		solo.clickOnText("Yes");
		solo.goBack();
		solo.goBack();
		
		// check not in wishlist
		solo.clickOnText("My Wishlist");
		solo.clickInList(0);
		TextView tw3 = (TextView) solo.getView(R.id.beername);
		String name3 = "" + tw3.getText();
		Log.d("TEST", "name3: " + name3);	
		assertEquals(name0, name3);
		solo.goBack();
		solo.goBack();
	}

}
