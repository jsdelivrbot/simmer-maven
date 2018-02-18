package com.joebotics.simmer.client.gui.dialog;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.joebotics.simmer.client.Simmer;
import com.joebotics.simmer.client.gui.BreadBoard;
import com.joebotics.simmer.client.util.OptionKey;
import com.joebotics.simmer.client.util.Options;

import gwt.material.design.addins.client.window.MaterialWindow;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialRange;
import gwt.material.design.client.ui.MaterialSwitch;

public class OptionsDialog extends Composite {

    private static OptionsDialogUiBinder uiBinder = GWT.create(OptionsDialogUiBinder.class);

    interface OptionsDialogUiBinder extends UiBinder<Widget, OptionsDialog> {
    }

    @UiField
    MaterialWindow modal;

    @UiField
    MaterialCheckBox showCurrentCheckItem, showVoltageCheckItem, showPowerCheckItem, showValuesCheckItem,
            smallGridCheckItem, euroResistorCheckItem, backgroundCheckItem, conventionCheckItem;

    @UiField
    MaterialRange speedBar, currentBar;

    @UiField
    MaterialSwitch showBreadboardBanks;

    @UiField
    MaterialRange breadboardWidth, breadboardHeight, breadboardRowCount, breadboardTopMargin, breadboardLeftMargin,
            breadboardRowThickness;

    @UiField
    MaterialButton btnSaveConfig, btnResetConfig, btnDownloadConfig, btnUploadConfig;

    private Options model;

    public OptionsDialog() {
        initWidget(uiBinder.createAndBindUi(this));
        model = Simmer.getInstance().getOptions();
        showCurrentCheckItem.setValue(model.getBoolean(OptionKey.SHOW_CURRENT));
        showVoltageCheckItem.setValue(model.getBoolean(OptionKey.SHOW_VOLTAGE));
        showPowerCheckItem.setValue(model.getBoolean(OptionKey.SHOW_POWER));
        showValuesCheckItem.setValue(model.getBoolean(OptionKey.SHOW_VALUES));
        smallGridCheckItem.setValue(model.getBoolean(OptionKey.SMALL_GRID));
        euroResistorCheckItem.setValue(model.getBoolean(OptionKey.EURO_RESISTORS));
        backgroundCheckItem.setValue(model.getBoolean(OptionKey.WHITE_BACKGROUND));
        conventionCheckItem.setValue(model.getBoolean(OptionKey.CONVENTIONAL_CURRENT_MOTION));
        speedBar.setValue(model.getInteger(OptionKey.SIMULATION_SPEED));
        speedBar.setValue(model.getInteger(OptionKey.CURRENT_SPEED));
    }

    @UiHandler("modal")
    public void modalOpeningHandler(OpenEvent<Boolean> event) {
        fillBreadboardFields();
    }

    @UiHandler("modal")
    public void modalClosingHandler(CloseEvent<Boolean> event) {
        showBreadboardBanks.setValue(false, true);
    }

    @UiHandler({ "showCurrentCheckItem", "showVoltageCheckItem", "showPowerCheckItem", "showValuesCheckItem",
            "euroResistorCheckItem", "conventionCheckItem" })
    public void checkItemHandler(ValueChangeEvent<Boolean> event) {
        MaterialCheckBox comp = (MaterialCheckBox) event.getSource();
        model.setValue(OptionKey.valueOf(comp.getName()), comp.getValue());
    }

    @UiHandler("speedBar")
    public void speedBarHandler(ChangeEvent event) {
        model.setValue(OptionKey.SIMULATION_SPEED, speedBar.getValue());
        speedBar.getThumb().getElement().getStyle().setVisibility(Style.Visibility.HIDDEN);
    }
    @UiHandler("speedBar")
    public void speedBarHandler(TouchStartEvent event) {
        model.setValue(OptionKey.SIMULATION_SPEED, speedBar.getValue());
        speedBar.getThumb().getElement().getStyle().setVisibility(Style.Visibility.VISIBLE);
    }

    @UiHandler("currentBar")
    public void currentBarHandler(ChangeEvent event) {
        model.setValue(OptionKey.CURRENT_SPEED, currentBar.getValue());
        currentBar.getThumb().getElement().getStyle().setVisibility(Style.Visibility.HIDDEN);

    }
    @UiHandler("currentBar")
    public void currentBarHandler(TouchStartEvent event) {
        model.setValue(OptionKey.CURRENT_SPEED, currentBar.getValue());
        currentBar.getThumb().getElement().getStyle().setVisibility(Style.Visibility.VISIBLE);

    }
    @UiHandler({ "smallGridCheckItem", "backgroundCheckItem" })
    public void checkGridHandler(ValueChangeEvent<Boolean> event) {
        checkItemHandler(event);
        Simmer.getInstance().setGrid();
    }

    @UiHandler("showBreadboardBanks")
    public void showBreadboardBanksHandler(ValueChangeEvent<Boolean> event) {
        BreadBoard.showAllBanks(event.getValue());
    }

    @UiHandler({"breadboardWidth"})
    public void breadboardWidthHandler(ValueChangeEvent<Integer> event) {
        BreadBoard.config.width = event.getValue();
        BreadBoard.applyConfig();
        breadboardWidth.getThumb().getElement().getStyle().setVisibility(Style.Visibility.HIDDEN);
    }
    @UiHandler({"breadboardWidth"})
    public void breadboardWidthHandler(TouchStartEvent event) {

        breadboardWidth.getThumb().getElement().getStyle().setVisibility(Style.Visibility.VISIBLE);

    }

    @UiHandler({"breadboardHeight"})
    public void breadboardHeightHandler(ValueChangeEvent<Integer> event) {
        BreadBoard.config.height = event.getValue();
        BreadBoard.applyConfig();
        breadboardHeight.getThumb().getElement().getStyle().setVisibility(Style.Visibility.HIDDEN);

    }
    @UiHandler({"breadboardHeight"})
    public void breadboardHeightHandler(TouchStartEvent event) {

        breadboardHeight.getThumb().getElement().getStyle().setVisibility(Style.Visibility.VISIBLE);

    }
    @UiHandler({"breadboardRowCount"})
    public void breadboardRowCountHandler(ValueChangeEvent<Integer> event) {
        BreadBoard.config.rowCount = event.getValue();
        BreadBoard.applyConfig();
        breadboardRowCount.getThumb().getElement().getStyle().setVisibility(Style.Visibility.HIDDEN);


    }
    @UiHandler({"breadboardRowCount"})
    public void breadboardRowCountHandler(TouchStartEvent event) {
        breadboardRowCount.getThumb().getElement().getStyle().setVisibility(Style.Visibility.VISIBLE);

    }

    @UiHandler({"breadboardTopMargin"})
    public void breadboardTopMarginHandler(ValueChangeEvent<Integer> event) {
        BreadBoard.config.topMargin = event.getValue();
        BreadBoard.applyConfig();
        breadboardTopMargin.getThumb().getElement().getStyle().setVisibility(Style.Visibility.HIDDEN);

    }

    @UiHandler({"breadboardTopMargin"})
    public void breadboardTopMarginHandler(TouchStartEvent event) {

        breadboardTopMargin.getThumb().getElement().getStyle().setVisibility(Style.Visibility.VISIBLE);

    }

    @UiHandler({"breadboardLeftMargin"})
    public void breadboardLeftMarginHandler(ValueChangeEvent<Integer> event) {
        BreadBoard.config.leftMargin = event.getValue();
        BreadBoard.applyConfig();
        breadboardLeftMargin.getThumb().getElement().getStyle().setVisibility(Style.Visibility.HIDDEN);

    }
    @UiHandler({"breadboardLeftMargin"})
    public void breadboardLeftMarginHandler(TouchStartEvent event) {

        breadboardLeftMargin.getThumb().getElement().getStyle().setVisibility(Style.Visibility.VISIBLE);

    }

    @UiHandler({"breadboardRowThickness"})
    public void breadboardRowThicknessHandler(ValueChangeEvent<Integer> event) {
        BreadBoard.config.thickness = event.getValue();
        BreadBoard.applyConfig();
        breadboardRowThickness.getThumb().getElement().getStyle().setVisibility(Style.Visibility.HIDDEN);

    }
    @UiHandler({"breadboardRowThickness"})
    public void breadboardRowThicknessHandler(TouchStartEvent event) {
        breadboardRowThickness.getThumb().getElement().getStyle().setVisibility(Style.Visibility.VISIBLE);

    }

    @UiHandler("btnSaveConfig")
    public void btnSaveConfigHandler(ClickEvent event) {
        BreadBoard.saveConfig();
    }

    @UiHandler("btnResetConfig")
    public void btnResetConfigHandler(ClickEvent event) {
        BreadBoard.resetConfig();
        fillBreadboardFields();
        BreadBoard.applyConfig();
    }

    @UiHandler("btnDownloadConfig")
    public void btnDownloadConfigHandler(ClickEvent event) {
        BreadBoard.downloadConfig();
    }

    @UiHandler("btnUploadConfig")
    public void btnUploadConfigHandler(ClickEvent event) {
        BreadBoard.uploadConfig();
        BreadBoard.applyConfig();
    }

    private void fillBreadboardFields() {
        breadboardWidth.setValue(BreadBoard.config.width);
        breadboardHeight.setValue(BreadBoard.config.height);
        breadboardRowCount.setValue(BreadBoard.config.rowCount);
        breadboardTopMargin.setValue(BreadBoard.config.topMargin);
        breadboardLeftMargin.setValue(BreadBoard.config.leftMargin);
        breadboardRowThickness.setValue(BreadBoard.config.thickness);
    }

    public void open() {
        modal.open();
    }
}
