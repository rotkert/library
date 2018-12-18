package kaminski.library.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class TestRunner {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(LibraryTest.class);
		System.out.println("Failed: " + result.getFailureCount());
	}

}
