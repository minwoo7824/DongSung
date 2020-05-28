package com.kmw.dongsung.Commons;

public class HangulVersionArray {

    public static String ReturnText(int keycode,boolean shiftStatus){
        String text = "";

        if (keycode == 45){
            if (shiftStatus){
                text = "ㅃ";
            }else{
                text = "ㅂ";
            }
        }else if (keycode == 51){
            if (shiftStatus){
                text = "ㅉ";
            }else{
                text = "ㅈ";
            }
        }else if (keycode == 33){
            if (shiftStatus){
                text = "ㄸ";
            }else{
                text = "ㄷ";
            }
        }else if (keycode == 46){
            if (shiftStatus){
                text = "ㄲ";
            }else{
                text = "ㄱ";
            }
        }else if (keycode == 48){
            if (shiftStatus){
                text = "ㅆ";
            }else{
                text = "ㅅ";
            }
        }else if (keycode == 53){
            text = "ㅛ";
        }else if (keycode == 49){
            text = "ㅕ";
        }else if (keycode == 37){
            text = "ㅑ";
        }else if (keycode == 43){
            if (shiftStatus){
                text = "ㅒ";
            }else{
                text = "ㅐ";
            }
        }else if (keycode == 44){
            if (shiftStatus){
                text = "ㅖ";
            }else{
                text = "ㅔ";
            }
        }else if (keycode == 71){
            if (shiftStatus){
                text = "{";
            }else{
                text = "[";
            }
        }else if (keycode == 72){
            if (shiftStatus){
                text = "}";
            }else{
                text = "]";
            }
        }
        //2
        else if (keycode == 29){
            text = "ㅁ";
        }else if (keycode == 47){
            text = "ㄴ";
        }else if (keycode == 32){
            text = "ㅇ";
        }else if (keycode == 34){
            text = "ㄹ";
        }else if (keycode == 35){
            text = "ㅎ";
        }else if (keycode == 36){
            text = "ㅗ";
        }else if (keycode == 38){
            text = "ㅓ";
        }else if (keycode == 39){
            text = "ㅏ";
        }else if (keycode == 40){
            text = "ㅣ";
        }else if (keycode == 74){
            if (shiftStatus){
                text = ":";
            }else{
                text = ";";
            }
        }else if (keycode == 75){
            if (shiftStatus){
                text = "\"";
            }else{
                text = "'";
            }
        }
        //3
        else if (keycode == 54){
            text = "ㅋ";
        }else if (keycode == 52){
            text = "ㅌ";
        }else if (keycode == 31){
            text = "ㅊ";
        }else if (keycode == 50){
            text = "ㅍ";
        }else if (keycode == 30){
            text = "ㅠ";
        }else if (keycode == 42){
            text = "ㅜ";
        }else if (keycode == 41){
            text = "ㅡ";
        }else if (keycode == 55){
            if (shiftStatus){
                text = "<";
            }else{
                text = ",";
            }
        }else if (keycode == 56){
            if (shiftStatus){
                text = ">";
            }else{
                text = ".";
            }
        }else if (keycode == 76){
            if (shiftStatus){
                text = "?";
            }else{
                text = "/";
            }
        }

        //space
        else if (keycode == 62){
            text = " ";
        }else if (keycode == 66){
            text = "\n";
        }

        //number
        else if (keycode == 8 || keycode == 145){
            text = "1";
        }else if (keycode == 9 || keycode == 146){
            text = "2";
        }else if (keycode == 10 || keycode == 147){
            text = "3";
        }else if (keycode == 11 || keycode == 148){
            text = "4";
        }else if (keycode == 12 || keycode == 149){
            text = "5";
        }else if (keycode == 13 || keycode == 150){
            text = "6";
        }else if (keycode == 14 || keycode == 151){
            text = "7";
        }else if (keycode == 15 || keycode == 152){
            text = "8";
        }else if (keycode == 16 || keycode == 153){
            text = "9";
        }else if (keycode == 7 || keycode == 144){
            text = "10";
        }
        return text;
    }
}
