/*    
    Copyright (C) Paul Falstad and Iain Sharp
    
    This file is part of CircuitJS1.

    CircuitJS1 is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 2 of the License, or
    (at your option) any later version.

    CircuitJS1 is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CircuitJS1.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.joebotics.simmer.client.elcomp.chips;

import com.joebotics.simmer.client.elcomp.ChipElm;
import com.joebotics.simmer.client.elcomp.Pin;
import com.joebotics.simmer.client.elcomp.Side;
import com.joebotics.simmer.client.util.StringTokenizer;

//import java.awt.*;
//import java.util.StringTokenizer;

// contributed by Edward Calver

public class PisoShiftElm extends ChipElm {
	boolean clockstate = false;

	short data = 0;// Lack of unsigned types sucks

	boolean modestate = false;

	public PisoShiftElm(int xx, int yy) {
		super(xx, yy);
	}
	public PisoShiftElm(int xa, int ya, int xb, int yb, int f,
			StringTokenizer st) {
		super(xa, ya, xb, yb, f, st);
	}
	public void execute() {
		if (getPins()[0].getValue() && !modestate) {
			modestate = true;
			data = 0;
			if (getPins()[2].getValue())
				data += 128;
			if (getPins()[3].getValue())
				data += 64;
			if (getPins()[4].getValue())
				data += 32;
			if (getPins()[5].getValue())
				data += 16;
			if (getPins()[6].getValue())
				data += 8;
			if (getPins()[7].getValue())
				data += 4;
			if (getPins()[8].getValue())
				data += 2;
			if (getPins()[9].getValue())
				data += 1;
		} else if (getPins()[1].getValue() && !clockstate) {
			clockstate = true;
			if ((data & 1) == 0)
				getPins()[10].setValue(false);
			else
				getPins()[10].setValue(true);
			data = (byte) (data >>> 1);
		}
		if (!getPins()[0].getValue())
			modestate = false;
		if (!getPins()[1].getValue())
			clockstate = false;
	}

	public String getChipName() {
		return "PISO shift register";
	}

	public int getDumpType() {
		return 186;
	}

	public int getPostCount() {
		return 11;
	}

	public int getVoltageSourceCount() {
		return 1;
	}

	boolean hasReset() {
		return false;
	}

	public void setupPins() {
		setSizeX(10);
		setSizeY(3);
		setPins(new Pin[getPostCount()]);

		getPins()[0] = new Pin(1, Side.WEST, "L");
		getPins()[1] = new Pin(2, Side.WEST, "");
		getPins()[1].setClock(true);

		getPins()[2] = new Pin(1, Side.NORTH, "I7");
		getPins()[3] = new Pin(2, Side.NORTH, "I6");
		getPins()[4] = new Pin(3, Side.NORTH, "I5");
		getPins()[5] = new Pin(4, Side.NORTH, "I4");
		getPins()[6] = new Pin(5, Side.NORTH, "I3");
		getPins()[7] = new Pin(6, Side.NORTH, "I2");
		getPins()[8] = new Pin(7, Side.NORTH, "I1");
		getPins()[9] = new Pin(8, Side.NORTH, "I0");

		getPins()[10] = new Pin(1, Side.EAST, "Q");
		getPins()[10].setOutput(true);

	}

}
