/*
 * Copyright 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.gwtproject.editor.client.adapters;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.j2cl.junit.apt.J2clTestInput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.gwtproject.editor.client.EditorContext;
import org.gwtproject.editor.client.EditorVisitor;
import org.gwtproject.editor.client.SimpleBeanEditorDriver;
import org.gwtproject.editor.client.annotation.IsDriver;
import org.gwtproject.event.shared.Event;
import org.gwtproject.event.shared.HandlerRegistration;
import org.gwtproject.view.client.CellPreviewEvent;
import org.gwtproject.view.client.HasData;
import org.gwtproject.view.client.Range;
import org.gwtproject.view.client.RangeChangeEvent.Handler;
import org.gwtproject.view.client.RowCountChangeEvent;
import org.gwtproject.view.client.SelectionModel;

/**
 * Tests the {@link HasDataEditor} type, to test it correctly plays with the backing {@link
 * HasData}.
 */
@J2clTestInput(HasDataEditorTest.class)
public class HasDataEditorTest extends GWTTestCase {

  FakeHasData hasData;
  HasDataEditor<Integer> editor;
  HasDataEditorDriver driver;

  public void testAddingValueToTheList() {
    driver.edit(Arrays.asList(0, 1, 3, 4));

    editor.getList().add(2, 2);

    assertEquals(Arrays.asList(0, 1, 2, 3, 4), hasData.getRowData());
  }

  public void testEditAgainWithLongerList() {
    driver.edit(Arrays.asList(0, 1, 2, 3, 4));

    List<Integer> expectedValue = Arrays.asList(0, 1, 2, 3, 4, 5);

    driver.edit(expectedValue);

    assertEquals(expectedValue, editor.getList());
    assertEquals(expectedValue, hasData.getRowData());
  }

  public void testEditAgainWithShorterList() {
    driver.edit(Arrays.asList(0, 1, 2, 3, 4, 5));

    List<Integer> expectedValue = Arrays.asList(4, 3, 2, 1, 0);

    driver.edit(expectedValue);

    assertEquals(expectedValue, editor.getList());
    assertEquals(expectedValue, hasData.getRowData());
  }

  public void testRemovingValueFromTheList() {
    driver.edit(Arrays.asList(0, 1, 2, 3, 4));

    editor.getList().remove(2);

    assertEquals(Arrays.asList(0, 1, 3, 4), hasData.getRowData());
  }

  public void testSimpleEdit() {
    List<Integer> expectedValue = Arrays.asList(0, 1, 2, 3, 4);

    driver.edit(expectedValue);

    assertEquals(expectedValue, editor.getList());
    assertEquals(expectedValue, hasData.getRowData());
  }

  /**
   * See <a href="http://code.google.com/p/google-web-toolkit/issues/detail?id=6959">issue 6959</a>
   */
  public void testTraverseSyntheticCompositeEditor() {
    List<Integer> expectedValue = Arrays.asList(1, 2, 3, 4, 5);

    EditorVisitor visitor = new SyntheticVisitor();

    // check that it won't throw
    driver.accept(visitor);

    driver.edit(expectedValue);

    // Shouldn't affect the editor and HasData
    driver.accept(visitor);

    assertEquals(expectedValue, editor.getList());
    assertEquals(expectedValue, hasData.getRowData());
  }

  @Override
  public String getModuleName() {
    return "org.gwtproject.editor.client.adapters";
  }

  protected void gwtSetUp() {
    hasData = new FakeHasData();
    editor = HasDataEditor.of(hasData);
    driver = new HasDataEditorTest_HasDataEditorDriver_Impl();
    driver.initialize(editor);
  }

  @IsDriver
  interface HasDataEditorDriver
      extends SimpleBeanEditorDriver<List<Integer>, HasDataEditor<Integer>> {}

  private static class FakeHasData implements HasData<Integer> {

    private ArrayList<Integer> rowData = new ArrayList<Integer>();

    @Override
    public HandlerRegistration addCellPreviewHandler(CellPreviewEvent.Handler<Integer> handler) {
      fail("HasDataEditor should never call HasData#addCellPreviewHandler");
      return null;
    }

    @Override
    public HandlerRegistration addRangeChangeHandler(Handler handler) {
      fail("HasDataEditor should never call HasData#addRangeChangeHandler");
      return null;
    }

    @Override
    public HandlerRegistration addRowCountChangeHandler(RowCountChangeEvent.Handler handler) {
      fail("HasDataEditor should never call HasData#addRowCountChangeHandler");
      return null;
    }

    @Override
    public void fireEvent(Event<?> event) {
      fail("HasDataEditor should never call HasData#fireEvent");
    }

    @Override
    public int getRowCount() {
      return rowData.size();
    }

    @Override
    public void setRowCount(int count) {
      setRowCount(count, true);
    }

    public List<Integer> getRowData() {
      return Collections.unmodifiableList(rowData);
    }

    @Override
    public SelectionModel<? super Integer> getSelectionModel() {
      fail("HasDataEditor should never call HasData#getSelectionModel");
      return null;
    }

    @Override
    public void setSelectionModel(SelectionModel<? super Integer> selectionModel) {
      fail("HasDataEditor should never call HasData#setSelectionModel");
    }

    @Override
    public Integer getVisibleItem(int indexOnPage) {
      fail("HasDataEditor should never call HasData#getVisibleItem");
      return null;
    }

    @Override
    public int getVisibleItemCount() {
      fail("HasDataEditor should never call HasData#getVisibleItemCount");
      return 0;
    }

    @Override
    public Iterable<Integer> getVisibleItems() {
      fail("HasDataEditor should never call HasData#getVisibleItems");
      return null;
    }

    @Override
    public Range getVisibleRange() {
      fail("HasDataEditor should never call HasData#getVisibleRange");
      return null;
    }

    @Override
    public void setVisibleRange(Range range) {
      fail("HasDataEditor should never call HasData#setVisibleRange");
    }

    @Override
    public boolean isRowCountExact() {
      return true;
    }

    @Override
    public void setRowCount(int count, boolean isExact) {
      assertTrue(isExact);
      assertTrue("HasDataEditor should only setRowCount to resize down", count < rowData.size());
      while (rowData.size() > count) {
        rowData.remove(rowData.size() - 1);
      }
    }

    @Override
    public void setRowData(int start, List<? extends Integer> values) {
      // sets within the list or adds to it
      assertTrue(0 <= start && start <= rowData.size());
      /*
       * HasDataEditor never calls setRowData with longer lists, but just in
       * case because this fake implementation relies on this behavior.
       */
      assertEquals(1, values.size());
      if (start == rowData.size()) {
        rowData.add(values.get(0));
      } else {
        rowData.set(start, values.get(0));
      }
    }

    @Override
    public void setVisibleRange(int start, int length) {
      fail("HasDataEditor should never call HasData#setVisibleRange");
    }

    @Override
    public void setVisibleRangeAndClearData(Range range, boolean forceRangeChangeEvent) {
      fail("HasDataEditor should never call HasData#setVisibleRangeAndClearData");
    }
  }

  /** A visitor that visits synthetic composite editors. */
  private static class SyntheticVisitor extends EditorVisitor {

    @Override
    public <T> boolean visit(EditorContext<T> ctx) {
      if (ctx.asCompositeEditor() != null) {
        ctx.traverseSyntheticCompositeEditor(this);
      }
      return true;
    }
  }
}
