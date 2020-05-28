package com.kmw.dongsung.Commons.ServerUrl;

public class Server {

    public static String server1 = "http://192.168.0.41:8080/";
    public static String server = "http://211.251.237.150:9080/";
    public static String choomo = "http://211.251.238.235";

    public static String loginUrl(){
        String str = server + "raspberryLoginProcess.do";
        return str;
    }

    public static String binsoDetailUrl(){
        String str = server + "admin/selectEventDetail.do";
        return str;
    }

    public static String photoDetailUrl(){
        String str = server + "admin/selectPhotoDetail.do";
        return str;
    }

    public static String binsoListUrl(){
        String str = server + "admin/selectEventList.do";
        return str;
    }

    public static String sangaListUrl(){
        String str = server + "admin/selectAllEventList.do";
        return str;
    }

    public static String generalListUrl(){
        String str = server + "admin/selectStatusPlateDetail.do";
        return str;
    }

    public static String waitingOneUrl(){
        String str = server + "admin/selectRaspberryStatusPlateList.do";
        return str;
    }

    public static String waitingTwoUrl(){
        String str = server + "admin/selectStatusPlateFiles.do";
        return str;
    }

    public static String funeralInfoUrl(){
        String str = server + "adminSec/selectFuneralInfo.do";
        return str;
    }

    public static String divisionImgUrl(){
        String str = server + "adminSec/selectFuneralDivideImg.do";
        return str;
    }

    public static String updateRbControl(){
        String str = server + "admin/updateRaspberryControl.do";
        return str;
    }

    //추모의 글 리스
    public static String selectObituaryList(){
        String str = choomo + "/mobile/selectObituaryList.do";
        return str;
    }
}
