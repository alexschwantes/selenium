/*
Copyright 2014 Software Freedom Conservancy

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package org.openqa.selenium.mobile;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.testing.JUnit4TestBase;

public class NetworkConnectionTest extends JUnit4TestBase {

  private NetworkConnection networkConnectionDriver;

  @Before
  public void setUp() throws Exception {
    WebDriver augmented = new Augmenter().augment(driver);
    Assume.assumeTrue(augmented instanceof NetworkConnection);
    networkConnectionDriver = (NetworkConnection) augmented;
  }

  @Test
  public void testToggleAirplaneMode() {
    NetworkConnection.ConnectionType current = networkConnectionDriver.getNetworkConnection();
    NetworkConnection.ConnectionType modified = null;
    if (current.isAirplaneMode()) {
      modified = networkConnectionDriver.setNetworkConnection(NetworkConnection.ConnectionType.ALL);
    } else {
      modified =
          networkConnectionDriver
              .setNetworkConnection(NetworkConnection.ConnectionType.AIRPLANE_MODE);
    }
    Assert.assertEquals("airplane mode should have been toggled", !current.isAirplaneMode(),
                        modified.isAirplaneMode());
  }

}
