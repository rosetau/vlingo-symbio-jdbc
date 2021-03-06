// Copyright © 2012-2020 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.

package io.vlingo.symbio.store.state.jdbc.hsqldb;

import java.util.UUID;

import io.vlingo.symbio.store.DataFormat;
import io.vlingo.symbio.store.common.jdbc.Configuration.TestConfiguration;
import io.vlingo.symbio.store.common.jdbc.hsqldb.HSQLDBConfigurationProvider;
import io.vlingo.symbio.store.state.StateStore.StorageDelegate;
import io.vlingo.symbio.store.state.jdbc.JDBCStateStoreActorTest;

public class HSQLDBJDBCStateStoreActorTest extends JDBCStateStoreActorTest {

  @Override
  protected StorageDelegate delegate() throws Exception {
    System.out.println("Starting: HSQLDBJDBCStateStoreActorTest: delegate()");
    return new HSQLDBStorageDelegate(configuration, world.defaultLogger());
  }

  @Override
  protected TestConfiguration testConfiguration(final DataFormat format) throws Exception {
    System.out.println("Starting: HSQLDBJDBCStateStoreActorTest: testConfiguration()");
    return HSQLDBConfigurationProvider.testConfiguration(format, UUID.randomUUID().toString());
  }

  @Override
  protected String someOfTypeStreams(final Class<?> type) {
    return "select * from " + tableName(type) + " where cast(s_id as integer) >= 21 and cast(s_id as integer) <= 25";
  }

  @Override
  protected String someOfTypeStreamsWithParameters(final Class<?> type) {
    return "select * from " + tableName(type) + " where cast(s_id as integer) >= ? and cast(s_id as integer) <= ?";
  }
}
