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
package org.gwtproject.media.client;

import com.google.j2cl.junit.apt.J2clTestInput;
import org.gwtproject.dom.client.MediaElement;
import org.gwtproject.user.client.ui.RootPanel;
import org.junit.After;
import org.junit.Before;

/**
 * Tests {@link Audio}.
 *
 * <p>Because HtmlUnit does not support HTML5, you will need to run these tests manually in order to
 * have them run. To do that, go to "run configurations" or "debug configurations", select the test
 * you would like to run, and put this line in the VM args under the arguments tab:
 * -Dgwt.args="-runStyle Manual:1"
 */
@J2clTestInput(AudioTest.class)
public class AudioTest extends MediaTest {
  protected Audio audio;

  static final String audioUrlMp3 = "smallmp3.mp3";
  static final String audioFormatMp3 = "audio/mpeg";
  static final String audioUrlOgg = "smallogg.ogg";
  static final String audioFormatOgg = "audio/ogg";

  @Override
  public MediaBase getMedia() {
    return audio;
  }

  @Override
  public String getModuleName() {
    return "org.gwtproject.media.MediaTest";
  }

  @Before
  protected void gwtSetUp() throws Exception {
    audio = Audio.createIfSupported();

    if (audio == null) {
      return; // don't continue if not supported
    }

    String canPlayMp3 = audio.canPlayType(audioFormatMp3);
    String canPlayOgg = audio.canPlayType(audioFormatOgg);
    if (canPlayMp3.equals(MediaElement.CAN_PLAY_PROBABLY)) {
      audio.setSrc(audioUrlMp3);
    } else if (canPlayOgg.equals(MediaElement.CAN_PLAY_PROBABLY)) {
      audio.setSrc(audioUrlOgg);
    } else if (canPlayMp3.equals(MediaElement.CAN_PLAY_MAYBE)) {
      audio.setSrc(audioUrlMp3);
    } else if (canPlayOgg.equals(MediaElement.CAN_PLAY_MAYBE)) {
      audio.setSrc(audioUrlOgg);
    } else {
      throw new Exception("Could not find suitable audio format");
    }

    // allow tests to autoplay audio without user interaction in browsers with
    // strict autoplay policies, e.g. Chrome
    audio.setMuted(true);

    RootPanel.get().add(audio);
  }

  @After
  protected void gwtTearDown() throws Exception {
    if (audio == null) {
      return; // don't continue if not supported
    }

    // clean up
    audio.pause();
    audio.setSrc("");
    audio.load();
    RootPanel.get().remove(audio);
  }
}
