/* This file is part of Sawa, the Scriptic-Java compiler
 *
 * Sawa is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package scriptic.tools.lowlevel;

import java.io.*;

class UnsignedByteOperand extends InstructionOperand
{
  int val;

  UnsignedByteOperand(int n) { val = n; }
  int size(ClassEnvironment e, CodeAttribute code)  { return 1; }
  void write(ClassEnvironment e, CodeAttribute ce, DataOutputStream out)
    throws IOException, ByteCodingException
  {
    if (val >= 256)
      throw new ByteCodingException("Operand is too large (" +val+ ") for this instruction");
    out.writeByte((byte)(0xff & val));
  }
  static UnsignedByteOperand readFrom (DataInputStream in, LongHolder pos)
    throws IOException {
    pos.value++;
    return new UnsignedByteOperand (in.readUnsignedByte());
  }
  public String getPresentation (Instruction owner) {return "#"+val;}
}

