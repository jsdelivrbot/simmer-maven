package com.joebotics.simmer.client.elcomp.sensors;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.ui.Image;
import com.joebotics.simmer.client.elcomp.ChipElm;
import com.joebotics.simmer.client.elcomp.Pin;
import com.joebotics.simmer.client.elcomp.Side;
import com.joebotics.simmer.client.gui.EditInfo;
import com.joebotics.simmer.client.gui.util.Graphics;
import com.joebotics.simmer.client.gui.util.Point;
import com.joebotics.simmer.client.gui.util.Rectangle;
import com.joebotics.simmer.client.util.StringTokenizer;
import gwt.material.design.client.ui.MaterialSwitch;

/**
 * Created by gologuzov on 17.01.18.
 * LSM303 Triple-axis Accelerometer+Magnetometer(Compass) Board
 */
public class LSM303Elm extends ChipElm {
    private final ImageElement accel = ImageElement.as(new Image("imgs/components/accel.png").getElement());

    public LSM303Elm(int xx, int yy) {
        super(xx, yy);
        footprintName = "SIP8";
    }

    public LSM303Elm(int xa, int ya, int xb, int yb, int f, StringTokenizer st) {
        super(xa, ya, xb, yb, f, st);
        footprintName = "SIP8";
    }

    public void execute() {
        getPins()[0].setValue(false);
    }

    @Override
    public String getChipName() {
        return "LSM303";
    }

    @Override
    public int getVoltageSourceCount() {
        return 1;
    }

    public int getDumpType() {
        return 603;
    }

    public int getPostCount() {
        return 8;
    }

    public void setupPins() {
        setSizeX(2);
        setSizeY(8);
        setPins(new Pin[getPostCount()]);

        getPins()[0] = new Pin(0, Side.EAST, "SCL");
        getPins()[1] = new Pin(1, Side.EAST, "SDA");
        getPins()[2] = new Pin(2, Side.EAST, "I2");
        getPins()[3] = new Pin(3, Side.EAST, "I1");
        getPins()[4] = new Pin(4, Side.EAST, "DRDY");
        getPins()[5] = new Pin(5, Side.EAST, "GND");
        getPins()[6] = new Pin(6, Side.EAST, "Vin");
        getPins()[7] = new Pin(7, Side.EAST, "3V3");
    }

    @Override
    public void setEditValue(int n, EditInfo ei) {
        super.setEditValue(n, ei);
    }

    @Override
    public EditInfo getEditInfo(int n) {
        return super.getEditInfo(n);
    }

    public void draw(Graphics g) {
        Point center = getCenterPoint();
        g.getContext().drawImage(accel, center.getX() - accel.getWidth() / 2, rectPointsY[0] + accel.getHeight() / 2);
        super.draw(g);
    }
}
