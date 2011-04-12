package org.beryl.test.android.app;

import org.beryl.app.IntentChooser;

import android.test.ActivityInstrumentationTestCase2;

public class IntentChooserTest extends ActivityInstrumentationTestCase2<IntentChooser> {

	private IntentChooser intentChooserActivity;
	
	public IntentChooserTest() {
		super(IntentChooser.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		intentChooserActivity = this.getActivity();
	}
	
	public void testPreconditions() {
		assertNotNull(intentChooserActivity);
	}
}
