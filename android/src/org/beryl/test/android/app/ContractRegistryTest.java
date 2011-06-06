package org.beryl.test.android.app;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import org.beryl.app.ContractRegistry;
import org.beryl.app.IContractMediator;
import org.beryl.app.RegisterableContract;

import junit.framework.TestCase;

public class ContractRegistryTest extends TestCase {
	
	public void testInstantiate() {
		MockRoot root = new MockRoot();
		assertNotNull(root.getContractRegistry());
	}

	
	// TODO: This test is broken. Need to create a GC tester class.
	// http://blogs.oracle.com/tor/entry/leak_unit_tests
	public void test_AttachDetach_NoHardReferencesLeftOnCallbackObject() {
		MockRoot root = new MockRoot();
		WeakReference<MockReturnsFiveCallback> contractRef = null;
		
		/* Scope to GC fiveReturn object. */ {
			MockReturnsFiveCallback fiveReturn = new MockReturnsFiveCallback();
			contractRef = new WeakReference<MockReturnsFiveCallback>(fiveReturn);
			root.getContractRegistry().attach(fiveReturn);
			root.getContractRegistry().detach(fiveReturn);
		}
		System.gc();
	}
	
	static interface ITestableContract extends RegisterableContract {
		int returnsFive();
	}
	
	
	static class MockReturnsFiveCallback {
		final ITestableContract fiveReturner = new ITestableContract() {
			public int returnsFive() {
				return 5;
			}
		};
	}
	
	static class MockRoot implements IContractMediator {
		final private ContractRegistry registry = new ContractRegistry(this);
		public ContractRegistry getContractRegistry() {
			return registry;
		}
	}
}
