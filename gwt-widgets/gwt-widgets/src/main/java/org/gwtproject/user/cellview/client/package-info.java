/*
 * Copyright 2010 Google Inc.
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

/**
 * The "cellview" widget set. The current widgets include:
 *
 * <ul>
 *   <li>{@link org.gwtproject.user.cellview.client.CellList CellList} - a simple vertical list of
 *       items, rendered by a common {@link org.gwtproject.cell.client.Cell Cell}
 *   <li>{@link org.gwtproject.user.cellview.client.CellTable CellTable} - a table in which each
 *       {@link org.gwtproject.user.cellview.client.Column Column} is rendered by a common Cell. The
 *       table may have headers and footers defined by the {@link
 *       org.gwtproject.user.cellview.client.Header Header} class. The {@link
 *       org.gwtproject.user.cellview.client.IdentityColumn IdentityColumn}, {@link
 *       org.gwtproject.user.cellview.client.TextColumn TextColumn}, and {@link
 *       org.gwtproject.user.cellview.client.TextHeader TextHeader} classes are provided for
 *       convenience.
 *   <li>{@link org.gwtproject.user.cellview.client.CellTree CellTree} - a tree in which all the
 *       children of a node are rendered by a common cell.
 *   <li>{@link org.gwtproject.user.cellview.client.CellBrowser CellBrowser} - a tree in which tree
 *       levels are displayed horizontally, with only a single node per level open at a time.
 * </ul>
 *
 * <p>CellTable supports the notion of paging, which may be controlled by an {@link
 * org.gwtproject.user.cellview.client.AbstractPager} such as {@link
 * org.gwtproject.user.cellview.client.SimplePager SimplePager}, {@link
 * org.gwtproject.user.cellview.client.PageSizePager PageSizePager}, or a user-defined subclass.
 *
 * @since GWT 2.1
 */
package org.gwtproject.user.cellview.client;
