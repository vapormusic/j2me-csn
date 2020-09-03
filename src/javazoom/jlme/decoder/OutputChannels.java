/**
 *-----------------------------------------------------------------------
 *   This program is free software; you can redistribute it and/or modify
 *   it under the terms of the GNU Library General Public License as published
 *   by the Free Software Foundation; either version 2 of the License,or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU Library General Public License for more details.
 *
 *   You should have received a copy of the GNU Library General Public
 *   License along with this program; if not,write to the Free Software
 *   Foundation,Inc.,675 Mass Ave,Cambridge,MA 02139,USA.
 *----------------------------------------------------------------------
 */
package javazoom.jlme.decoder;


public interface OutputChannels {
  /** Flag to indicate output should include both channels. */
  public final static int BOTH_CHANNELS = 0;

  /** Flag to indicate output should include the left channel only. */
  public final static int LEFT_CHANNEL = 1;

  /** Flag to indicate output should include the right channel only. */
  public final static int RIGHT_CHANNEL = 2;

  /** Flag to indicate output is mono. */
  public final static int DOWNMIX_CHANNELS = 3;
}
