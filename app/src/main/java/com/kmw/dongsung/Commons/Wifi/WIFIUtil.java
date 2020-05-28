package com.kmw.dongsung.Commons.Wifi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class WIFIUtil extends InfWIFIConsts {

    private static WIFIUtil __sharedWifiUtil = null;

    private String mSSID = null;
    private String mWPA = null;

    public static WIFIUtil getInstance() {
        if ( __sharedWifiUtil == null )
            __sharedWifiUtil = new WIFIUtil();
        return __sharedWifiUtil;
    }

    private WIFIUtil()
    {
        super();
    }

    /**
     * Set SSID, wpa
     * @param ssid
     * @param wpa
     */
    public void setSSIDandWPA(String ssid, String wpa) {
        mSSID = ssid;
        mWPA = wpa;
    }

    /**
     * 현재 연결된 Wifi SSID 확인
     * @param context
     * @return
     */
    public String getSSID(Context context) {

        WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo;
        String ssid = null;
        wifiInfo = wifiManager.getConnectionInfo();

        if (wifiInfo.getSupplicantState()== SupplicantState.COMPLETED) {
            ssid = wifiInfo.getSSID().replaceAll("\"", "");
        }

        Log.d("SSID", ""+ssid);

        if ( ssid == null )
            ssid = "";
        return ssid;
    }

    /**
     * Wifi 사용가능 확인
     * @param context
     * @return
     */
    public boolean isEndableWIFI(Context context) {
        WifiManager wifi = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        if (wifi.isWifiEnabled()){
            return true;
        }
        return false;
    }

    /**
     * Wifi 연결 확인
     * @param context
     * @return
     */
    public boolean isConnectWIFI(Context context) {

        boolean bIsWiFiConnect = false;
        ConnectivityManager oManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo oInfo = oManager.getActiveNetworkInfo();

        if(oInfo != null)
        {
            NetworkInfo.State oState = oInfo.getState();
            if(oState == NetworkInfo.State.CONNECTED)
            {
                switch(oInfo.getType())
                {
                    case ConnectivityManager.TYPE_WIFI:
                        bIsWiFiConnect = true;
                        break;
                    case ConnectivityManager.TYPE_MOBILE:
                        break;
                    default:
                        break;
                }
            }
        }
        return bIsWiFiConnect;
    }

    /**
     * Mac address 받아오기
     * @param context
     * @return
     */
    public String getMacAddress(Context context) {
        WifiManager wimanager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        String macAddress = wimanager.getConnectionInfo().getMacAddress();
        Log.d("MAC", "" + macAddress);
        if ( macAddress == null )
            macAddress = "";
        return macAddress;
    }


    /**
     * WIFI 삭제
     * @param context
     */
    public void removeWifiSpecialSSID(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if ( isConnectWIFI(context) ) {
            if (mSSID != null && getSSID(context).equalsIgnoreCase(mSSID)) {
                int networkId = wifiManager.getConnectionInfo().getNetworkId();
                wifiManager.removeNetwork(networkId);
                wifiManager.saveConfiguration();
                wifiManager.disconnect();
                wifiManager.disableNetwork(networkId);
            }
        }
    }


    /**
     * 와이파이 연결
     * @param context
     * @param wifiConfig
     */
    public void connectWifi(Context context, int wifiConfig) {

/////////////////// 공통 //////////////////////////
        WifiConfiguration wfc = new WifiConfiguration();
        wfc.SSID = "\"".concat(mSSID).concat("\"");
        wfc.status = WifiConfiguration.Status.DISABLED;
        wfc.priority = 40;
/////////////////// 공통 //////////////////////////


////////////////// 보안 방식에 따른 설정 //////////////////////
//        int type = 2;
        if (wifiConfig == WIFI_CONFIG_OPEN) {
//        open일때
            wfc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            wfc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
            wfc.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
            wfc.allowedAuthAlgorithms.clear();
            wfc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
        } else if (wifiConfig == WIFI_CONFIG_WPA) {
//    wep일때
            wfc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            wfc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
            wfc.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
            wfc.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
            wfc.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
            wfc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);

            wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
            wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
            wfc.wepKeys[0] = mWPA;
            wfc.wepTxKeyIndex = 0;
        } else if (wifiConfig == WIFI_CONFIG_WPA2) {
//    wpa나 wpa2일때
            wfc.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
            wfc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
            wfc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
            wfc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
            wfc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
            wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            wfc.preSharedKey = "\"".concat(mWPA).concat("\"");
        }

////////////////// 보안 방식에 따른 설정 //////////////////////
        WifiManager wimanager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        int networkId = wimanager.addNetwork(wfc);

        if (networkId != -1) {
            wimanager.enableNetwork(networkId, true);
        }
    }
}
