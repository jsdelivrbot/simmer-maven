package com.joebotics.simmer.client.elcomp;

import com.joebotics.simmer.client.Simmer;
import com.joebotics.simmer.client.gui.EditInfo;
import com.joebotics.simmer.client.model.GpioManager;
import com.joebotics.simmer.client.model.GpioPin;
import com.joebotics.simmer.client.model.GpioPinState;
import com.joebotics.simmer.client.util.StringTokenizer;

import gwt.material.design.client.ui.MaterialListBox;

public class GpioOutputElm extends LogicOutputElm {
    private GpioManager gpioManager = Simmer.getInstance().getGpioManager();
    private GpioPin gpioPin;

    public GpioOutputElm(int xx, int yy) {
        super(xx, yy);
        setGpioPin(gpioManager.getAvailablePins().get(0));
    }

    public GpioOutputElm(int xa, int ya, int xb, int yb, int f, StringTokenizer st) {
        super(xa, ya);
        x2 = xb;
        y2 = yb;
        flags = f;
        try {
            setGpioPin(gpioManager.getPinByName(st.nextToken()));
        } catch (Exception e) {
            setGpioPin(gpioManager.getAvailablePins().get(0));
        }
        allocNodes();
        setupPins();
    }

    public String dump() {
        return super.dump() + " " + gpioPin.getName();
    }

    public int getDumpType() {
        return 400;
    }

    public EditInfo getEditInfo(int n) {
        if (n == 0) {
            EditInfo ei = new EditInfo("GPIO Pin", 0, -1, -1);
            ei.choice = new MaterialListBox();
            // Add current pin value
            ei.choice.addItem(gpioPin.getName());
            // Add other available values
            for (GpioPin pin : gpioManager.getAvailablePins()) {
                ei.choice.addItem(pin.getName());
            }
            // Choose the current value
            ei.choice.setSelectedValue(gpioPin.getName());
            return ei;
        }
        return null;
    }

    public void getInfo(String arr[]) {
        arr[0] = "GPIO output";
        if (this.gpioPin != null) {
            arr[0] += " " + gpioPin.getName();
        }
        arr[1] = (getVolts()[0] < threshold) ? "low" : "high";
        if (isNumeric())
            arr[1] = value;
        arr[2] = "V = " + getVoltageText(getVolts()[0]);
    }

    public void setEditValue(int n, EditInfo ei) {
        if (n == 0) {
            setGpioPin(gpioManager.getPinByName(ei.choice.getSelectedValue()));
        }
        setPoints();
    }

    public void setPoints() {
        super.setPoints();
        if (gpioPin != null) {
            this.getPins()[0].setText(gpioPin.getName());
        }
    }

    private void setGpioPin(GpioPin gpioPin) {
        if (this.gpioPin != null) {
            gpioManager.releasePin(this.gpioPin);
        }
        this.gpioPin = gpioPin;
        gpioManager.holdPin(gpioPin, GpioPinState.OUTPUT);
    }
}
