package com.example.commons;

import android.bluetooth.BluetoothAdapter;
import android.util.Log;

public final class BluetoothUtils {

  private BluetoothUtils() {
  }

  /**
   * get bluetooth local device name
   *
   * @return device name String
   */
  public static String getLocalBluetoothName() {
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    // if device does not support Bluetooth
    if (mBluetoothAdapter == null) {
      Log.d(BluetoothUtils.class.getCanonicalName(), "device does not support bluetooth");
      return null;
    }

    return mBluetoothAdapter.getName();
  }

  /**
   * get bluetooth adapter MAC address
   *
   * @return MAC address String
   */
  public static String getBluetoothMacAddress() {
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    // if device does not support Bluetooth
    if (mBluetoothAdapter == null) {
      Log.d(BluetoothUtils.class.getCanonicalName(), "device does not support bluetooth");
      return null;
    }

    return mBluetoothAdapter.getAddress();
  }
}
