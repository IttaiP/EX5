package exercise.android.reemh.todo_items;

import junit.framework.TestCase;

import org.junit.Test;

public class TodoItemsHolderImplTest extends TestCase {
  @Test
  public void test_when_addingTodoItem_then_callingListShouldHaveThisItem() {
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");

    // verify
    assertEquals(1, holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void test_when_adding2TodoItems_then_callingListShouldHave2Items() {
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.addNewInProgressItem("do homework");


    // verify
    assertEquals(2, holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void test_when_notAddingTodoItem_then_callingListShouldNotHaveItem() {
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test

    // verify
    assertEquals(0, holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void test_when_addingTodoItemAndDeleting_then_callingListShouldNotHaveThisItem() {
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.deleteItem(holderUnderTest.getCurrentItems().get(0));

    // verify
    assertEquals(0, holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void test_when_addingTodoItemAndMarkingDone_then_callingListShouldHaveThisItemDone() {
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(0));

    // verify
    assertTrue(holderUnderTest.getCurrentItems().get(0).done);
  }

  @Test
  public void test_when_addingTodoItemAndMarkingDoneThenNot_then_callingListShouldHaveThisItemDone() {
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(0));
    holderUnderTest.markItemInProgress(holderUnderTest.getCurrentItems().get(0));


    // verify
    assertFalse(holderUnderTest.getCurrentItems().get(0).done);
  }

  @Test
  public void test_when_addingTodoItem_then_callingListShouldHaveThisItemWithDescription() {
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");

    // verify
    assertEquals("do shopping", holderUnderTest.getCurrentItems().get(0).todoText);
  }

  @Test
  public void test_when_adding3TodoItem_then_callingListShouldHave3Items() {
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.addNewInProgressItem("do shopping2");
    holderUnderTest.addNewInProgressItem("do shopping3");


    // verify
    assertEquals(3, holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void test_when_deletingDoneTodoItem_then_callingListShouldNotHaveThisItem() {
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(0));
    holderUnderTest.deleteItem(holderUnderTest.getCurrentItems().get(0));

    // verify
    assertEquals(0, holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void test_when_addingIdenticleTodoItesm_then_callingListShouldHaveThisItemTwice() {
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.addNewInProgressItem("do shopping");


    // verify
    assertEquals(2, holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void test_when_addingIdenticleTodoItemsAndDeletingOne_then_callingListShouldHaveThisItemOnce() {
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.deleteItem(holderUnderTest.getCurrentItems().get(0));


    // verify
    assertEquals(1, holderUnderTest.getCurrentItems().size());
  }
}

