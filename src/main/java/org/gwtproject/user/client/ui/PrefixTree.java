/*
 * Copyright 2007 Google Inc.
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

package org.gwtproject.user.client.ui;

import elemental2.core.JsArray;
import jsinterop.base.Js;
import jsinterop.base.JsForEachCallbackFn;
import jsinterop.base.JsPropertyMap;
import org.gwtproject.core.client.JavaScriptObject;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A prefix tree (aka trie).
 *
 */
class PrefixTree extends AbstractCollection<String> {

  /**
   * Iterates over the structure of a PrefixTree. No concurrency checks are
   * made. This Iterator will output anything new added to the tree if the new
   * entry is after the current position of the Iterator.
   *
   * This Iterator implementation is iterative and uses an internal stack object
   * to avoid call-stack limitations and invocation overhead.
   */
  private static class PrefixTreeIterator implements Iterator<String> {

    // Called from JSNI.
    private JavaScriptObject stack;

    /**
     * Constructor.
     *
     * @param tree The base of the PrefixTree to iterate over
     */
    public PrefixTreeIterator(PrefixTree tree) {
      init();
      addTree(tree, "");
    }

    public boolean hasNext() {
      // Have nextImpl peek at the next value that would be returned.
      return nextImpl(true) != null;
    }

    /**
     * {@inheritDoc} Wraps the native implementation with some sanity checking.
     */
    public String next() {
      final String toReturn = nextImpl(false);

      // A null response indicates that there was no data to be had.
      if (toReturn == null) {
        // Sanity check.
        if (!hasNext()) {
          throw new NoSuchElementException("No more elements in the iterator");
        } else {
          throw new RuntimeException(
              "nextImpl() returned null, but hasNext says otherwise");
        }
      }

      return toReturn;
    }

    public void remove() {
      throw new UnsupportedOperationException("PrefixTree does not support "
          + "removal.  Use clear()");
    }

    /**
     * Add a frame to the work stack.
     *
     * <pre>
     *  frame := {suffixNames, subtrees, prefix, index}
     *  suffixNames := All suffixes in the target PrefixTree
     *  subtrees := All subtrees in the target PrefixTree
     *  prefix := A string that next() will prepend to output from the frame
     *  index := Stores which suffix was last output
     * </pre>
     *
     * @param tree The tree to add
     * @param prefix The prefix to prepend to values in tree
     */
    private void addTree(PrefixTree tree, String prefix)  {
      JsArray<String> arr = tree.suffixes.cast();
      JsArray<String> suffixes = new JsArray<>();

      for (int i = 0; i < arr.length; i++) {
        // Ignore object properties that aren't colon-prepended keys
        if (arr.getAt(i).indexOf(':') == 0) {
          suffixes.push(arr.getAt(i));
        }
      }

      JsPropertyMap frame = JsPropertyMap.of();
      frame.set("suffixNames", suffixes);
      frame.set("subtrees", tree.subtrees);
      frame.set("prefix", prefix);
      frame.set("index", 0);

      ((JsArray)this.stack.cast()).push(frame);
    }

    /**
     * Initialize JSNI objects.
     */
    private void init()  {
      this.stack = Js.cast(new JsArray());
    }

    /**
     * Access JSNI structures.
     *
     * @param peek If this is true, don't advance the iteration, just return the
     *          value that next() would return if it were called
     * @return The next object, or null if there is an error
     */
    private String nextImpl(boolean peek) {
        JsArray<JavaScriptObject> stack = this.stack.cast();
        while (stack.length > 0) {
            JsPropertyMap frame = stack.pop().cast();
            if (Integer.valueOf(frame.get("index").toString()) < ((JsArray) frame.get("suffixNames")).length) {
                String toReturn = frame.get("prefix").toString() + unsafe(((JsArray<String>) frame.get("suffixNames")).getAt(Integer.valueOf(frame.get("index").toString())));
                if (!peek) {
                    int index = Integer.valueOf(frame.get("index").toString());
                    index++;
                    frame.set("index", index);
                }
                // If the current frame has more suffixes, retain it on the stack.
                if (Integer.valueOf(frame.get("index").toString()) < ((JsArray<String>) frame.get("suffixNames")).length) {
                    stack.push(Js.uncheckedCast(frame));
                    // Otherwise, put all of the subtrees on the stack.
                } else {
                    JsArray<String> subtrees = ((JsArray<String>) frame.get("subtrees"));
                    for (int i = 0; i < subtrees.length; i++) {
                        if (subtrees.getAt(i).indexOf(':') != 0) {
                            continue;
                        }
                        String target = frame.get("prefix").toString() + unsafe(subtrees.getAt(i));
                        PrefixTree subtree = (PrefixTree) ((JsPropertyMap) frame.get("subtrees")).get(subtrees.getAt(i));
                        this.addTree(subtree, target);
                    }
                }
                return toReturn;
            } else {
                JsArray<String> subtrees = ((JsArray<String>) frame.get("subtrees"));
                for (int i = 0; i < subtrees.length; i++) {
                    if (subtrees.getAt(i).indexOf(':') != 0) {
                        continue;
                    }
                    String target = frame.get("prefix").toString() + unsafe(subtrees.getAt(i));
                    PrefixTree subtree = (PrefixTree) ((JsPropertyMap) frame.get("subtrees")).get(subtrees.getAt(i));
                    this.addTree(subtree, target);
                }
            }
        }
        return null;
    }
  }

  /**
   * Used by native methods to create an appropriately blessed PrefixTree.
   *
   * @param prefixLength Smaller prefix length equals faster, more direct
   *          searches, at a cost of setup time
   * @return a newly constructed prefix tree
   */
  protected static PrefixTree createPrefixTree(int prefixLength) {
    return new PrefixTree(prefixLength);
  }

  /**
   *  Ensure that a String can be safely used as a key to an associative-array
   *  JavaScript object by prepending a prefix.
   *
   *  @param s The String to make safe
   *  @return A safe version of <code>s</code>
   */
  private static String safe(String s) {
    return ':' + s;
  }

  /**
   *  Undo the operation performed by safe().
   *
   *  @param s A String returned from safe()
   *  @return The original String passed into safe()
   */
  private static String unsafe(String s) {
    return s.substring(1);
  }

  /**
   * Stores the requested prefix length.
   */
  protected final int prefixLength;

  /**
   * Field to store terminal nodes in.
   */
  protected JavaScriptObject suffixes;

  /**
   * Field to store subtrees in.
   */
  protected JavaScriptObject subtrees;

  /**
   * Store the number of elements contained by this PrefixTree and its
   * sub-trees.
   */
  protected int size = 0;

  /**
   * Constructor.
   */
  public PrefixTree() {
    this(2, null);
  }

  /**
   * Constructor.
   *
   * @param source Initialize from another collection
   */
  public PrefixTree(Collection<String> source) {
    this(2, source);
  }

  /**
   * Constructor.
   *
   * @param prefixLength Smaller prefix length equals faster, more direct
   *          searches, at a cost of setup time.
   */
  public PrefixTree(final int prefixLength) {
    this(prefixLength, null);
  }

  /**
   * Constructor.
   *
   * @param prefixLength Smaller prefix length equals faster, more direct
   *          searches, at a cost of setup time.
   * @param source Initialize from another collection
   */
  public PrefixTree(final int prefixLength, final Collection<String> source) {
    this.prefixLength = prefixLength;
    clear();

    if (source != null) {
      addAll(source);
    }
  }

  /**
   * Add a String to the PrefixTree.
   *
   * @param s The data to add
   * @return <code>true</code> if the string was added, <code>false</code>
   *         otherwise
   */
  @Override
  public boolean add(String s) {
     JsArray<String>  suffixes = this.suffixes.cast();
     JsPropertyMap  subtrees = this.subtrees.cast();
     int prefixLength = this.prefixLength;
      // This would indicate a mis-use of the code.
      if ((s == null) || (s.length() == 0)) {
          return false;
      }
      // Use <= so that strings that are exactly prefixLength long don't
      // require some kind of null token.
      if (s.length() <= prefixLength) {
          String safeKey = safe(s);
          if (Js.asPropertyMap(suffixes).has(safeKey)) {
              return false;
          } else {
              // Each tree keeps a count of how large it and its children are.
              this.size++;
              Js.asPropertyMap(suffixes).set(safeKey, true);
              return true;
          }
          // Add the string to the appropriate PrefixTree.
      } else {
          String prefix = safe(s.substring(0, prefixLength));
          PrefixTree theTree;
          if (Js.asPropertyMap(subtrees).has(prefix)) {
              theTree = (PrefixTree)Js.asPropertyMap(subtrees).get(prefix);
          } else {
              theTree = createPrefixTree(prefixLength << 1);
              Js.asPropertyMap(subtrees).set(prefix, theTree);
          }
          String slice = s.substring(prefixLength);
          if (theTree.add(slice)) {
              // The size of the subtree increased, so we need to update the local count.
              this.size++;
              return true;
          } else {
              return false;
          }
      }
  }

  /**
   * Initialize native state.
   */
  @Override
  public void clear() {
      this.size = 0;
      this.subtrees = JavaScriptObject.createObject();
      this.suffixes = JavaScriptObject.createObject();
  }

  @Override
  public boolean contains(Object o) {
    if (o instanceof String) {
      return contains((String) o);
    } else {
      return false;
    }
  }

  public boolean contains(String s) {
    return (getSuggestions(s, 1)).contains(s);
  }

  /**
   * Retrieve suggestions from the PrefixTree. The number of items returned from
   * getSuggestions may slightly exceed <code>limit</code> so that all
   * suffixes and partial stems will be returned. This prevents the search space
   * from changing size if the PrefixTree is used in an interactive manner.
   * <br/> The returned List is guaranteed to be safe; changing its contents
   * will not affect the PrefixTree.
   *
   * @param search The prefix to search for
   * @param limit The desired number of results to retrieve
   * @return A List of suggestions
   */
  public List<String> getSuggestions(String search, int limit) {
    final List<String> toReturn = new ArrayList<String>();
    if ((search != null) && (limit > 0)) {
      suggestImpl(search, "", toReturn, limit);
    }
    return toReturn;
  }

  @Override
  public Iterator<String> iterator() {
    return new PrefixTreeIterator(this);
  }

  /**
   * Get the number of all elements contained within the PrefixTree.
   *
   * @return the size of the prefix tree
   */
  @Override
  public int size() {
    return size;
  }

  protected void suggestImpl(String search, String prefix,
      Collection<String> output, int limit) {
      JsArray<String>  suffixes = this.suffixes.cast();
      JsPropertyMap  subtrees = this.subtrees.cast();
      int prefixLength = this.prefixLength;
      // Search is too big to be found in current tree, just recurse.
      if (search.length() > prefix.length() + prefixLength) {
          String key = safe(search.substring(prefix.length(), prefix.length() + prefixLength));
          // Just pick the correct subtree, if it exists, and call suggestImpl.
          if (Js.asPropertyMap(subtrees).has(key)) {
              PrefixTree subtree = ((PrefixTree) Js.asPropertyMap(subtrees).get(key));
              String target = prefix + unsafe(key);
              subtree.suggestImpl(search, target, output, limit);
          }
          // The answer can only exist in this tree's suffixes or subtree keys.
      } else {
          // Check local suffixes.
          for (int i = 0; i < suffixes.length; i++) {
              if (suffixes.getAt(i).indexOf(':') != 0) {
                  continue;
              }
              String target = prefix + unsafe(suffixes.getAt(i));
              if (target.indexOf(search) == 0) {
                  output.add(target);
              }
              if (output.size() >= limit) {
                  return;
              }
          }
          // Check the subtree keys.  If the key matches, that implies that all
          // elements of the subtree would match.
          subtrees.forEach(key -> {
              if (key.indexOf(':') == 0) {
                  String target = prefix + unsafe(key);
                  PrefixTree subtree = ((PrefixTree) Js.asPropertyMap(subtrees).get(key));
                  // See if the prefix gives us a match.
                  if (target.indexOf(search) == 0) {
                      // Provide as many suggestions as will fit into the remaining limit.
                      // If there is only one suggestion, include it.
                      if ((subtree.size <= limit - output.size()) ||
                      (subtree.size == 1)) {
                          subtree.dump(output, target);
                          // Otherwise, include as many answers as we can by truncating the suffix
                      } else {
                          // Always fully-specify suffixes.
                          JsArray<String>  _suffixes = subtree.suffixes.cast();
                          for (int i = 0; i < _suffixes.length; i++) {
                              if (_suffixes.getAt(i).indexOf(':') == 0) {
                                  output.add(target + unsafe(_suffixes.getAt(i)));
                              }
                          }
                          // Give the keys of the subtree.
                          Js.asPropertyMap(subtree.subtrees).forEach(_key ->{
                              if (_key.indexOf(':') == 0) {
                                  output.add(target + unsafe(_key + "..."));

                              }
                          });
                      }
                  }
              }
          });
      }
  }

  /**
   * Put all contents of the PrefixTree into a Collection.
   *
   * @param output the collection into which the prefixes will be dumped
   * @param prefix the prefix to filter with
   */
  private void dump(Collection<String> output, String prefix) {
    for (String s : this) {
      output.add(prefix + s);
    }
  }
}
