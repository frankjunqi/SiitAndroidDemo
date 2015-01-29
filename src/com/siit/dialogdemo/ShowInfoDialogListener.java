package com.siit.dialogdemo;

/**
 * 回调消息
 * @author Tc003167
 *
 */
public interface ShowInfoDialogListener {
	public static String BTN_LEFT = "BTN_LEFT";
	public static String BTN_RIGHT = "BTN_RIGHT";
	public static String BTN_CANCEL = "BTN_CANCEL";
    public void refreshUI(String sType);
}
