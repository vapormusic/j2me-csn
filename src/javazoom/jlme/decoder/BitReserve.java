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

final class BitReserve {
  private final static int BUFSIZE = 4096 * 8;
  private final static int BUFSIZE_MASK = BUFSIZE - 1;
  static int offset,buf_byte_idx;
  private static final int[] buf = new int[BUFSIZE];
  static int buf_bit_idx;
  public static int totbit;
  //public int hsstell() {
  //  return totbit;
  //}

  public final int hgetbits(int N) {
   totbit += N;
    val=0;
    if (buf_byte_idx + N < BUFSIZE) {
      while (N-- > 0) {
        val <<= 1;
        val |= ((buf[buf_byte_idx++] == 0) ? 0 : 1);
      }
    }
    else {
      while (N-- > 0) {
        val <<= 1;
        val |= ((buf[buf_byte_idx] == 0) ? 0 : 1);
        buf_byte_idx = (buf_byte_idx + 1) & BUFSIZE_MASK;
      }
    }

    return val;
 
   }


  static int val;

  public final int hget1bit() {
    totbit++;
    val = buf[buf_byte_idx];
    buf_byte_idx = (buf_byte_idx + 1) & BUFSIZE_MASK;
    return val;
  }

  public final void hputbuf(int val) {
    buf[offset++] = val & 0x80;
    buf[offset++] = val & 0x40;
    buf[offset++] = val & 0x20;
    buf[offset++] = val & 0x10;
    buf[offset++] = val & 0x08;
    buf[offset++] = val & 0x04;
    buf[offset++] = val & 0x02;
    buf[offset++] = val & 0x01;
    if (offset == BUFSIZE)
      offset = 0;
  }


  public final void rewindNbits(int N) {
    totbit -= N;
    buf_byte_idx -= N;
    if (buf_byte_idx < 0) {
      buf_byte_idx += BUFSIZE;
    }
  }

  static int bits;
  public final void rewindNbytes(int N) {
    bits = (N << 3);
    totbit -= bits;
    buf_byte_idx -= bits;
    if (buf_byte_idx < 0) {
      buf_byte_idx += BUFSIZE;
    }
  }

}
