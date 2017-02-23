package com.example.commons;

import android.content.Context;
import android.net.wifi.WifiManager;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import timber.log.Timber;

public final class DeviceWifiDataConnectionUtils {

  private DeviceWifiDataConnectionUtils() {
  }

  public static String getMacAddress(Context context) {
    WifiManager wimanager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    String macAddress = wimanager.getConnectionInfo().getMacAddress();
    if (macAddress == null) {
      macAddress = "Device don't have mac address or wi-fi is disabled";
    }
    return macAddress;
  }

  public static String getSSIDAddress(Context context) {
    WifiManager wimanager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    String ssidAddress = wimanager.getConnectionInfo().getSSID();

    if (ssidAddress == null) {
      ssidAddress = "Device don't have SSID wi-fi or wi-fi is disabled";
    }
    return ssidAddress;
  }

  public static String getIPAddress(Context context) {
    WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    int ipAddress = wifiManager.getConnectionInfo().getIpAddress();

    // Convert little-endian to big-endianif needed
    if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
      ipAddress = Integer.reverseBytes(ipAddress);
    }

    byte[] ipByteArray = BigInteger.valueOf(ipAddress).toByteArray();

    String ipAddressString;
    try {
      ipAddressString = InetAddress.getByAddress(ipByteArray).getHostAddress();
    } catch (UnknownHostException ex) {
      Timber.e(ex, "Unable to get host address.");
      ipAddressString = null;
    }

    return ipAddressString;
  }
}
