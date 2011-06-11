package org.beryl.test.android.app;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import org.beryl.app.ContractRegistry;
import org.beryl.app.IContractMediator;
import org.beryl.app.RegisterableContract;

import junit.framework.TestCase;

public class ContractRegistryTest extends TestCase {
	
	// TODO: AttachMembers and DetachMembers are not tested.
	
	public void test_Constructor_ThrowsNoException() {
		MockNoContractRoot root = new MockNoContractRoot();
		assertNotNull(root.getContractRegistry());
	}

	public void test_Constructor_EmptyContractHasNoAttachments() {
		MockNoContractRoot root = new MockNoContractRoot();
		final ContractRegistry cr = root.getContractRegistry();
		
		assertEquals(0, cr.getAll(ISelfReturnerContract.class).size());
		assertEquals(0, cr.getAll(IFiveReturnerContract.class).size());
	}
	
	public void test_Constructor_AttachSelfMembers() {
		MockSelfReturnerRoot root = new MockSelfReturnerRoot();
		final ContractRegistry cr = root.getContractRegistry();
		
		assertEquals(1, cr.getAll(ISelfReturnerContract.class).size());
	}
	
	// TODO: This test is broken. Need to create a GC tester class.
	// http://blogs.oracle.com/tor/entry/leak_unit_tests
	public void test_AttachDetach_NoHardReferencesLeftOnCallbackObject() {
		MockNoContractRoot root = new MockNoContractRoot();
		WeakReference<FiveAndSelfReturner> contractRef = null;
		
		/* Scope to GC fiveReturn object. */ {
			FiveAndSelfReturner fiveReturn = new FiveAndSelfReturner();
			contractRef = new WeakReference<FiveAndSelfReturner>(fiveReturn);
			root.getContractRegistry().add(fiveReturn);
			root.getContractRegistry().remove(fiveReturn);
		}
		System.gc();
	}

	public void test_Attach_DisallowDoubleAttachment() {
		MockNoContractRoot root = new MockNoContractRoot();
		FiveAndSelfReturner fiveReturn = new FiveAndSelfReturner();
		final ContractRegistry cr = root.getContractRegistry();
		cr.add(fiveReturn);
		cr.add(fiveReturn);
		assertEquals(1, cr.getAll(IFiveReturnerContract.class).size());
	}

	public void test_Attach_AttachingGarbageDoesNothing() {
		final MockNoContractRoot root = new MockNoContractRoot();
		final Object garbage = new Object();
		final ContractRegistry cr = root.getContractRegistry();
		cr.add(garbage);
		assertEquals(0, cr.getAll(IFiveReturnerContract.class).size());
		assertEquals(0, cr.getAll(ISelfReturnerContract.class).size());
	}
	
	public void test_AttachDetach_ContractIsRemoved() {
		MockNoContractRoot root = new MockNoContractRoot();
		FiveAndSelfReturner fiveReturn = new FiveAndSelfReturner();
		final ContractRegistry cr = root.getContractRegistry();
		cr.add(fiveReturn);
		cr.remove(fiveReturn);
		assertEquals(0, cr.getAll(IFiveReturnerContract.class).size());
	}
	
	public void test_getAll_ReturnsAllSelfReturners() {
		ContractRegistry registry = setupSelfAndFiveReturnFilledContract();
		ArrayList<ISelfReturnerContract> selfs = registry.getAll(ISelfReturnerContract.class);
		assertEquals(2, selfs.size());
	}
	
	public void test_getContractRegistry_ThrowsClassCastExceptionForGarbage() {
		boolean exceptionCaught = false;
		try {
		ContractRegistry.getContractRegistry(new Object());
		} catch(ClassCastException e) {
			exceptionCaught = true;
		} finally {
			assertTrue("ClassCastException was not thrown.", exceptionCaught);
		}
	}
	
	public void test_getContractRegistry_ReturnsGoodRegistryForValidObject() {
		ContractRegistry registry = ContractRegistry.getContractRegistry(new MockSelfReturnerRoot());
		assertNotNull(registry);
	}
	
	// Testing Helpers

	private static ContractRegistry setupSelfAndFiveReturnFilledContract() {
		MockSelfReturnerRoot root = new MockSelfReturnerRoot();
		final ContractRegistry registry = root.getContractRegistry();
		FiveAndSelfReturner fiveReturn = new FiveAndSelfReturner();
		registry.add(fiveReturn);
		return registry;
	}
	
	static interface IFiveReturnerContract extends RegisterableContract {
		int returnsFive();
	}
	
	static interface ISelfReturnerContract extends RegisterableContract {
		Object returnsSelf();
	}

	static class FiveAndSelfReturner {
		final IFiveReturnerContract fiveReturner = new IFiveReturnerContract() {
			public int returnsFive() {
				return 5;
			}
		};
		
		final ISelfReturnerContract selfReturner = new ISelfReturnerContract() {
			public Object returnsSelf() {
				return FiveAndSelfReturner.this;
			}
		};
	}
	
	static class MockNoContractRoot implements IContractMediator {
		final private ContractRegistry registry = new ContractRegistry();
		
		public MockNoContractRoot() {
			registry.add(this);
		}
		
		public ContractRegistry getContractRegistry() {
			return registry;
		}
	}
	
	static class MockSelfReturnerRoot implements IContractMediator {
		final private ContractRegistry registry = new ContractRegistry();
		
		public MockSelfReturnerRoot() {
			registry.add(this);
		}

		public ContractRegistry getContractRegistry() {
			return registry;
		}
		
		final ISelfReturnerContract selfReturner = new ISelfReturnerContract() {
			public Object returnsSelf() {
				return MockSelfReturnerRoot.this;
			}
		};
	}
}
