package org.gwtproject.user.history;

import org.gwtproject.user.history.client.HistoryTest;
import org.gwtproject.user.history.client.HistoryTestNoopTokenEncoder;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({HistoryTest.class, HistoryTestNoopTokenEncoder.class})
public class HistorySuite {}
