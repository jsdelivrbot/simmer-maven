package com.joebotics.simmer.client.gui.widget;

import gwt.material.design.client.ui.MaterialTextArea;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsType;

@JsType
public class TextArea extends MaterialTextArea {

    @JsMethod
    @Override
    public void setText(String text) {
        super.setText(text);
    }

    @JsMethod
    public void appendText(String text) {
        super.setText(getText() + text);
    }

    @JsMethod
    @Override
    public String getText() {
        return super.getText();
    }

    @JsMethod
    @Override
    public void clear() {
        super.clear();
    }
}