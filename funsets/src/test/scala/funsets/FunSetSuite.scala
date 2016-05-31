package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(4)
    val s5 = singletonSet(5)
    val u1 = union(s1,s2)
    val u2 = union(u1, s3)
    val u3 = union(s4, s5)
    val u4 = union(u2, u3)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersect contains elements that are in both sets") {
    new TestSets {
      val i = intersect(u1, s2)
      assert(!contains(i, 1), "1 Not in both")
      assert(contains(i, 2), "2 in both")
      assert(!contains(i, 3), "3 in neither")
    }
  }

  test("diff contains elements that are in this set and not that set") {
    new TestSets {
      val i = diff(u1, s2)
      assert(contains(i, 1), "1 not in s2")
      assert(!contains(i, 2), "2 is in s2")
      assert(!contains(i, 3), "3 not in either")
    }
  }

  test("filter elements of set which satisfy a predicate") {
    new TestSets {
      val f = filter(u2, _ % 2 == 0)
      val f2 = filter(u4, _ < 5)
      assert(!contains(f, 1), "1 is not divisible by 2")
      assert(contains(f, 2), "2 is divisible by 2")
      assert(!contains(f, 3), "3 is not divisible by 2")
      assert(!contains(f, 5), "5 is not less than 5")
    }
  }

  test("forall checks all elements of set satisfy a predicate") {
    new TestSets {
      val f = forall(u2, _ % 2 == 0)
      val f2 = forall(u2, _ < 4)
      val f3 = forall(u2, _ != 0)
      assert(!f, "Not all even")
      assert(f2, "All less than 4")
      assert(f3, "None equal to zero")
    }
  }

  test("exists checks if an element is present that satisfies a predicate") {
    new TestSets {
      val e = exists(u2, _ % 2 == 0)
      val e2 = exists(u2, _ < 4)
      val e3 = exists(u2, _ == 0)
      assert(e, "One is even")
      assert(e2, "All less than 4")
      assert(!e3, "None equal to zero")
    }
  }

  test("map applies a function to each element in set") {
    new TestSets {
      val m = map(s1, _ + 1)
      val e2 = exists(u2, _ < 4)
      val e3 = exists(u2, _ == 0)
      assert(contains(m, 2), "one is mapped to 2")
    }
  }


}
