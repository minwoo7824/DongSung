package com.kmw.dongsung.Commons;

import android.text.TextUtils;
import android.util.Log;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;

public class HangulInput {
    public InputConnection currentInputConnection;
    public boolean deleteBtnFlag = false;
    boolean isViewClicked = false;
    boolean reverseFlag = false;
    public static int twoText25,twoText27 = -1;
    public static int kigyek,dikc,bieb,sieb,ziek = -1;


    private String getExtractText()
    {
        ExtractedTextRequest req = new ExtractedTextRequest();
        req.token = 0;
        req.flags = InputConnection.GET_TEXT_WITH_STYLES;
        req.hintMaxLines = 10;
        req.hintMaxChars = 10000;

        ExtractedText et = null;
        try {
            et = currentInputConnection.getExtractedText(req,
                    InputConnection.GET_EXTRACTED_TEXT_MONITOR);
            return et.text.toString();
        } catch (NullPointerException e) {
            e.printStackTrace();
            return "";
        }
        // return et.text.toString();
    }



    public HangulInput(InputConnection currentInputConnection)
    {
        this.currentInputConnection = currentInputConnection;
    }





    static int cho=99, jung=99, jong=99; // 초기에 H_NONE 으로 셋팅
    int gcho, gjung, gjong; // ㄱ
    int ncho, njung, njong; // ㄴ
    int lcho, ljung, ljong; // ㄹ
    boolean gMode = false;
    boolean nMode = false;
    boolean lMode = false;

    // 한글 조립 상태에 대한 상수
    final int H_NONE = 0;			// 아무것도 없는 상태				예:
    final int H_CHO = 1;			// 초성 하나 입력된 상태. 			예:ㄱ, ㄳ
    final int H_JUNGONLY = 2;		// 초성없이 중성만 입력된 상태. 	예:ㅏ
    final int H_JUNG = 3;			// 초성 + 중성. 					예:가, 과
    final int H_JONG = 4;			// 초성 + 중성 + 종성				예:각, 닭
    final int H_DOT = 5;            // 천지인 점 입력

    // 각 음소별 속성
// 첨자 0 : 0이면 자음, 1이면 모음
// 첨자 1 : 초성의 순서값. 99는 낱글자 구성만 가능하고 음절은 불가하다는 뜻
//        : 중성은 음소의 등장 순서가 코드 순서와 일치하므로 -30하면 됨
// 첨자 2 : 종성의 순서값. 종성이 없을 경우는 0임. 99는 종성으로 쓸 수 없는 문자
    int[][] hanattr = {
            {0, 0, 1},		// 0.ㄱ
            {0, 1, 2},		// 1.ㄲ
            {0,99, 3},		// 2.ㄳ
            {0, 2, 4},		// 3.ㄴ
            {0,99, 5},		// 4.ㄵ
            {0,99, 6},		// 5.ㄶ
            {0, 3, 7},		// 6.ㄷ
            {0, 4,99},		// 7.ㄸ
            {0, 5, 8},		// 8.ㄹ
            {0,99, 9},		// 9.ㄺ
            {0,99,10},		// 10.ㄻ
            {0,99,11},		// 11.ㄼ
            {0,99,12},		// 12.ㄽ
            {0,99,13},		// 13.ㄾ
            {0,99,14},		// 14.ㄿ
            {0,99,15},		// 15.ㅀ
            {0, 6,16},		// 16.ㅁ
            {0, 7,17},		// 17.ㅂ
            {0, 8,99},		// 18.ㅃ
            {0,99,18},		// 19.ㅄ
            {0, 9,19},		// 20.ㅅ
            {0,10,20},		// 21.ㅆ
            {0,11,21},		// 22.ㅇ
            {0,12,22},		// 23.ㅈ
            {0,13,99},		// 24.ㅉ
            {0,14,23},		// 25.ㅊ
            {0,15,24},		// 26.ㅋ
            {0,16,25},		// 27.ㅌ
            {0,17,26},		// 28.ㅍ
            {0,18,27},		// 29.ㅎ
            {1, 0, 0},		// 30.ㅏ
            {1, 0, 0},		// 31.ㅐ
            {1, 0, 0},		// 32.ㅑ
            {1, 0, 0},		// 33.ㅒ
            {1, 0, 0},		// 34.ㅓ
            {1, 0, 0},		// 35.ㅔ
            {1, 0, 0},		// 36.ㅕ
            {1, 0, 0},		// 37.ㅖ
            {1, 0, 0},		// 38.ㅗ
            {1, 0, 0},		// 39.ㅘ
            {1, 0, 0},		// 40.ㅙ
            {1, 0, 0},		// 41.ㅚ
            {1, 0, 0},		// 42.ㅛ
            {1, 0, 0},		// 43.ㅜ
            {1, 0, 0},		// 44.ㅝ
            {1, 0, 0},		// 45.ㅞ
            {1, 0, 0},		// 46.ㅟ
            {1, 0, 0},		// 47.ㅠ
            {1, 0, 0},		// 48.ㅡ
            {1, 0, 0},		// 49.ㅢ
            {1, 0, 0},		// 50.ㅣ
            {1, 0, 0},      // 51. ·
            {1, 0, 0},      // 52. ··
    };

    final static String TAG="HangulIME";
    boolean engChar = false;
    boolean hanMode = false;
    boolean dotMode = false;
    // 입력 문자의 cyclic rotation. ㄱ->ㅋ->ㄱ
    final static int MOD = 2;
    int hanKeyDownCount = 0;
    int initBtnId = 0;


    // 조립 문자열
    public StringBuilder mComp = new StringBuilder();

// 한글 입력을 처리한다.

    public void ProcessHangul(int code)
    {
        boolean bConso;
        int idx = 0;


        int crPos = 0;
        try {
            crPos = currentInputConnection.getExtractedText(new ExtractedTextRequest(), InputConnection.GET_EXTRACTED_TEXT_MONITOR).selectionStart;
        }catch (NullPointerException err) {
            err.printStackTrace();
        }

        // XML 문서의 키 코드를 ㄱ을 베이스로 한 첨자로 변환한다.
        // 이 첨자는 유니코드상 낱글자의 순서값이며 음소의 코드로 사용한다.

        Log.d("HWI","code 확인 : "+code);
        if (code >= 0x3131 && code <= 0x3163)
        {
            idx = code - 0x3131;
        } else if(code == 0x2022)
        {
            idx = 51;
//			if(preidx == 0) {
//				idx = 51; preidx = idx;
//			}else if(preidx == 51) {
//				idx = 51; preidx = 0;
//			}
        }else {
            // 한글이 아닌 다른 문자이면 조립을 끝낸다.

            FinishCompo();

            ResetCompo();

            // 문자 입력
            currentInputConnection.commitText(String.valueOf((char) code), 1);
            Log.d("HWI", "키 입력 테스트 지점 02 : " + String.valueOf((char) code));
            return;
        }

//		Log.d(TAG, "IN: State = " + GetHanState() + ",idx = " + idx);

        // 입력된 글자가 자음인지 조사한다.
        if (hanattr[idx][0] == 0) {
            bConso = true;
        } else {
            bConso = false;
        }

        int hanState = GetHanState();

        if(deleteBtnFlag == true && !(crPos == 0) || isViewClicked && !(crPos==0) )
        {

            int hangulUnicode;
            String word="";
            try {
                word = getExtractText().substring(crPos-1, crPos);
            }catch (IndexOutOfBoundsException err) {
                err.printStackTrace();
            }catch (NullPointerException err) {
                err.printStackTrace();
            }



            if(!TextUtils.isEmpty(word))
            {
                if((int)word.charAt(0) >= 0xAC00 && (int)word.charAt(0) <= 0xD79F) {
                    //한글
                    Log.d(TAG,"word = " +word.charAt(0));
                    mComp.setLength(0);
                    mComp.append(word);
                    Log.d(TAG,"mComp = " +mComp.toString());
                    hangulUnicode = (int)word.charAt(0) - 0xAC00;
                    Log.d(TAG,"hangulUnicode = " +hangulUnicode+"for "+(int)word.charAt(0));
                    jong = hangulUnicode % 28;
                    jung = ((hangulUnicode - jong)/28) % 21; jung = jung+30;
                    cho = ((hangulUnicode - jong)/28) / 21;

                    if(jong==0) {
                        jong=99;
                    }else {
                        for(int i=0; i< hanattr.length; i++ ) {
                            if(hanattr[i][2] == jong) { // 종성 idx 값 찾기.
                                jong = i;
                                break;
                            }
                        }
                    }

                    for(int i=0; i< hanattr.length; i++ ) {
                        if(hanattr[i][1] == cho) { // 초성 idx 값 찾기
                            cho = i;
                            break;
                        }
                    }

                    Log.e(TAG,"word = " +word+": cho = "+cho+", jung = "+jung+", jong = "+jong);

                    hanState = GetHanState();
                    try {
                        currentInputConnection.setComposingRegion(crPos-1, crPos);
                    }catch (IndexOutOfBoundsException err) {
                        err.printStackTrace();
                    }catch (NullPointerException err) {
                        err.printStackTrace();
                    }
                    Log.d(TAG, "hanState = " +hanState);
                }else
                if((int)word.charAt(0) >= 0x3131 && (int)word.charAt(0) <= 0x3163	) {
                    //한글
                    Log.d(TAG,"word = " +word.charAt(0));
                    mComp.setLength(0);
                    mComp.append(word);
                    Log.d(TAG,"mComp = " +mComp.toString());
                    int tempIdx = (int)word.charAt(0) - 0x3131;
                    if(hanattr[tempIdx][0]==0) { // 자음
                        cho = tempIdx; jung = 99; jong =99;
                    }else { //모음
                        cho = 99; jung = tempIdx; jong =99;

                    }
                    Log.d(TAG,"code = " +(int)word.charAt(0));
                    Log.d(TAG,"word = " +word+": cho = "+cho+", jung = "+jung+", jong = "+jong);
//
//                     		for(int i=0; i< hanattr.length; i++ ) {
//                    				if(hanattr[i][1] == cho) {
//                 					cho = i;
//                 					break;
//                 				}
//                     		}

                    Log.d(TAG, "H_NONE crPos = " +crPos);
                    hanState = GetHanState();
                    try {
                        currentInputConnection.setComposingRegion(crPos-1, crPos);
                    }catch (IndexOutOfBoundsException err) {
                        err.printStackTrace();
                    }catch (NullPointerException err) {
                        err.printStackTrace();
                    }
                    Log.d(TAG, "hanState = " +hanState);
                }
            }

            deleteBtnFlag = false;
            isViewClicked = false;
            Log.d(TAG, "deleteBtnFlag = " +deleteBtnFlag);
        }

        // 조립 상태에 따라 분기한다. 이후 입력된 글자의 자음, 모음 여부와 기존 글자의
        // 복자음, 복모음 여부에 따라 다양하게 분기된다.
        switch(hanState) {
            case H_NONE:
                // 자음이면 자음 단독으로 입력한다. 낱글자 코드를 쓰면 된다.
                if (bConso) {
                    cho = idx;
                    if(gMode == true) {
                        Log.d("H_NONE", "종성으로 ㄱ 이 입력된 상태");
                        if(idx == 20) { // ㅅ 이 초성으로 입력되면, ㄱㅅ 복자음으로 종성을 구성함.
                            Log.d("H_NONE", "ㅅ 이 초성으로 입력되면, ㄱㅅ 복자음으로 종성을 구성함");
                            gjong = 2;
//						if(inputType == 161 || inputType == 16385) {
//							HangulBs();
//							HangulBs();
//						}else {
//							HangulBs();
//							HangulBs();
//							HangulBs();
//						}

                            Log.d("H_NONE", "GetHanCode = " + GetHanCode(gcho, gjung, gjong));
                            AppendCompo(GetHanCode(gcho, gjung, gjong));
                            cho = gcho; jung = gjung; jong = gjong;
                        }else {
                            Log.d("H_NONE", "AppendCompo(idx + 0x3131) calling");
                            AppendCompo(idx + 0x3131);
                        }
                        gMode = false;
                    }else
                    if(nMode == true) { // 종성으로 ㄴ 이 입력된 상태
                        Log.d("H_NONE", "종성으로 ㄴ 이 입력된 상태");
                        if(idx == 23) { // ㅈ 이 초성으로 입력되면, ㄴㅈ 복자음으로 종성을 구성함.
                            Log.d("H_NONE", "ㅈ 이 초성으로 입력되면, ㄴㅈ 복자음으로 종성을 구성함");
                            njong = 4;
//						if(inputType == 161 || inputType == 16385) {
//							HangulBs();
//							HangulBs();
//						}else {
//							HangulBs();
//							HangulBs();
//							HangulBs();
//						}

                            Log.d("H_NONE", "GetHanCode = " + GetHanCode(ncho, njung, njong));
                            AppendCompo(GetHanCode(ncho, njung, njong));
                            cho = ncho; jung = njung; jong = njong;
                        }else
                        if(idx == 29) { // ㅎ 이 초성으로 입력되면, ㄴㅎ 복자음으로 종성을 구성함.
                            Log.d("H_NONE", "ㅎ 이 초성으로 입력되면, ㄴㅎ 복자음으로 종성을 구성함");
                            njong = 5;
//						if(inputType == 161 || inputType == 16385) {
//							HangulBs();
//							HangulBs();
//						}else {
//							HangulBs();
//							HangulBs();
//							HangulBs();
//						}
                            Log.d("H_NONE", "GetHanCode = " + GetHanCode(ncho, njung, njong));
                            AppendCompo(GetHanCode(ncho, njung, njong));
                            cho = ncho; jung = njung; jong = njong;

//						currentInputConnection.setComposingText(mComp, 1);
//						currentInputConnection.setComposingText(mComp, 1);
                        }else {
                            Log.d("H_NONE", "AppendCompo(idx + 0x3131) calling");
                            AppendCompo(idx + 0x3131);
                        }
                        nMode = false;
                    }else
                    if(lMode == true) {
                        Log.d("H_NONE", "종성으로 ㄹ 이 입력된 상태");
                        if(idx == 0) { // ㄱ 이 초성으로 입력되면, ㄹ+ㄱ 복자음으로 종성을 구성함.
                            Log.d("H_NONE", "ㄱ 이 초성으로 입력되면, ㄹㄱ 복자음으로 종성을 구성함");
                            ljong = 9; // ㄹ+ㄱ 복자음 종성
//						if(inputType == 161 || inputType == 16385) {
//							HangulBs();
//							HangulBs();
//						}else {
//							HangulBs();
//							HangulBs();
//							HangulBs();
//						}

                            Log.d("H_NONE", "GetHanCode = " + GetHanCode(lcho, ljung, ljong));
                            AppendCompo(GetHanCode(lcho, ljung, ljong));
                            cho = lcho; jung = ljung; jong = ljong;
                        }else
                        if(idx == 16) { // ㅁ 이 초성으로 입력되면, ㄹ+ㅁ 복자음으로 종성을 구성함.
                            Log.d("H_NONE", "ㅁ 이 초성으로 입력되면, ㄹㅁ 복자음으로 종성을 구성함");
                            ljong = 10; // ㄹ+ㅁ 복자음 종성
//						if(inputType == 161 || inputType == 16385) {
//							HangulBs();
//							HangulBs();
//						}else {
//							HangulBs();
//							HangulBs();
//							HangulBs();
//						}

                            Log.d("H_NONE", "GetHanCode = " + GetHanCode(lcho, ljung, ljong));
                            AppendCompo(GetHanCode(lcho, ljung, ljong));
                            cho = lcho; jung = ljung; jong = ljong;
                        }else
                        if(idx == 17) { // ㅂ 이 초성으로 입력되면, ㄹ+ㅂ 복자음으로 종성을 구성함.
                            Log.d("H_NONE", "ㅁ 이 초성으로 입력되면, ㄹㅁ 복자음으로 종성을 구성함");
                            ljong = 11; // ㄹ+ㅂ 복자음 종성
//						if(inputType == 161 || inputType == 16385) {
//							HangulBs();
//							HangulBs();
//						}else {
//							HangulBs();
//							HangulBs();
//							HangulBs();
//						}

                            Log.d("H_NONE", "GetHanCode = " + GetHanCode(lcho, ljung, ljong));
                            AppendCompo(GetHanCode(lcho, ljung, ljong));
                            cho = lcho; jung = ljung; jong = ljong;
                        }else
                        if(idx == 20) { // ㅅ 이 초성으로 입력되면, ㄹ+ㅅ 복자음으로 종성을 구성함.
                            Log.d("H_NONE", "ㅅ 이 초성으로 입력되면, ㄹㅅ 복자음으로 종성을 구성함");
                            ljong = 12; // ㄹ+ㅅ 복자음 종성
//						if(inputType == 161 || inputType == 16385) {
//							HangulBs();
//							HangulBs();
//						}else {
//							HangulBs();
//							HangulBs();
//							HangulBs();
//						}

                            Log.d("H_NONE", "GetHanCode = " + GetHanCode(lcho, ljung, ljong));
                            AppendCompo(GetHanCode(lcho, ljung, ljong));
                            cho = lcho; jung = ljung; jong = ljong;
                        }else
                        if(idx == 27) { // ㅌ 이 초성으로 입력되면, ㄹ+ㅌ 복자음으로 종성을 구성함.
                            Log.d("H_NONE", "ㅌ 이 초성으로 입력되면, ㄹㅌ 복자음으로 종성을 구성함");
                            ljong = 13; // ㄹ+ㅌ 복자음 종성
//						if(inputType == 161 || inputType == 16385) {
//							HangulBs();
//							HangulBs();
//						}else {
//							HangulBs();
//							HangulBs();
//							HangulBs();
//						}

                            Log.d("H_NONE", "GetHanCode = " + GetHanCode(lcho, ljung, ljong));
                            AppendCompo(GetHanCode(lcho, ljung, ljong));
                            cho = lcho; jung = ljung; jong = ljong;

                        }else
                        if(idx == 28 ) { // ㅍ 이 초성으로 입력되면, ㄹ+ㅍ 복자음으로 종성을 구성함.
                            Log.d("H_NONE", "ㅍ 이 초성으로 입력되면, ㄹㅍ 복자음으로 종성을 구성함");
                            ljong = 10;  // ㄹ+ㅍ 복자음 종성
//						if(inputType == 161 || inputType == 16385) {
//							HangulBs();
//							HangulBs();
//						}else {
//							HangulBs();
//							HangulBs();
//							HangulBs();
//						}

                            Log.d("H_NONE", "GetHanCode = " + GetHanCode(lcho, ljung, ljong));
                            AppendCompo(GetHanCode(lcho, ljung, ljong));
                            cho = lcho; jung = ljung; jong = ljong;

                        }else
                        if(idx == 29) { // ㅎ 이 초성으로 입력되면, ㄹ+ㅎ 복자음으로 종성을 구성함.
                            Log.d("H_NONE", "ㅎ 이 초성으로 입력되면, ㄹㅎ 복자음으로 종성을 구성함");
                            ljong = 15;  // ㄹ+ㅎ 복자음 종성
//						if(inputType == 161 || inputType == 16385) {
//							HangulBs();
//							HangulBs();
//						}else {
//							HangulBs();
//							HangulBs();
//							HangulBs();
//						}

                            Log.d("H_NONE", "GetHanCode = " + GetHanCode(lcho, ljung, ljong));
                            AppendCompo(GetHanCode(lcho, ljung, ljong));
                            cho = lcho; jung = ljung; jong = ljong;
                        }else {
                            Log.d("H_NONE", "AppendCompo(idx + 0x3131) calling");
                            AppendCompo(idx + 0x3131);
                        }
                        lMode = false;
                    }
                    else
                    {
                        Log.d("H_NONE", "AppendCompo(idx + 0x3131) calling");
                        AppendCompo(idx + 0x3131);
                    }
                    // 모음이면 모음만으로 글자 구성.
                } else {
                    if(idx == 51) {
//					prejung = idx;
                        jung = idx;
                        Log.d("H_NONE_DOT", "AppendDot(0xB7, hanState) calling, jung = " + jung);
                        AppendDot(0xB7, hanState);
                    }else {
                        jung = idx;
//					prejung = jung;
                        Log.d("H_NONE_MO", "AppendCompo(idx + 0x3131) calling, jung = " +jung);
                        AppendCompo(idx + 0x3131);
                    }
                }
                break;
            case H_CHO:
                if (IsBokJa(cho) == false) {
                    if (bConso) {
                        int bokja = findBokJa(cho, idx);
                        // 복자음 구성 가능하면 복자음만으로 초성 만듬. 예:ㄱ상태에서 ㅅ입력시 ㄳ
                        if (bokja != 99) {
                            cho = bokja;
                            ReplaceCompo(cho + 0x3131);
                            // 복자음 구성되지 않을 경우 - 음절 분리. 예:ㄱ 상태에서 ㄴ입력시
                        } else {
                            // 조립중인 글자 완성하고 새로 입력된 초성으로 단독 글자 만듬
                            Log.d("H_CHO", "FinishCompo() calling");
                            FinishCompo();
                            Log.d("H_CHO", "ResetCompo() calling");
                            ResetCompo();
                            cho = idx;
                            Log.d("H_CHO", "AppendCompo(cho + 0x3131) calling");
                            AppendCompo(cho + 0x3131);
                        }
                    } else {
                        // 모음이면 앞에 입력된 자음과 결합하여 새 글자로 대체. 예:ㄱ 상태에서 ㅏ입력시 가
                        if(idx == 51) {
                            jung = idx;
                            if(gMode == true || nMode == true || lMode == true) {
                                Log.d("H_CHO_mo", "commitText(mComp.substring(0, 1), 1)");
                                currentInputConnection.commitText(mComp.substring(0, 1), 1);

                                Log.d("HWI", "키 입력 테스트 지점 03 : " + mComp.substring(0, 1));

                                Log.d("H_CHO_mo", "mComp.replace(0, 2, mComp.substring(1, 2)) = " +mComp);
                                mComp.replace(0, 2, mComp.substring(1, 2));
                                Log.d("H_CHO_DOT", "AppendDot(0xB7,hanState) calling");
                                AppendDot(0xB7, hanState);
                                nMode = false; gMode = false; lMode = false;
                            }else {
                                Log.d("H_CHO_DOT", "AppendDot(0xB7,hanState) calling");
                                AppendDot(0xB7, hanState);
                                Log.i(TAG,"second");
                            }
                        }else {
                            jung = idx;
                            if(gMode == true || nMode == true || lMode == true) {

                                mComp.replace(0, 2, mComp.substring(0, 1));
                                Log.d("H_CHO_mo", "mComp.replace(0, 2, mComp.substring(0, 1)) = " + mComp);
                                Log.d("H_CHO_mo", "commitText(mComp.substring(0, 1), 1)");

                                currentInputConnection.commitText(mComp.substring(0, 1), 1);
                                Log.d("HWI", "키 입력 테스트 지점 05 : " + mComp.substring(0, 1));

                                mComp.setLength(0);
//							currentInputConnection.commitText("", 1);
//							FinishCompo();
//							ResetCompo();
                                Log.d("H_CHO_mo", "AppendCompo(GetHanCode("+cho+", "+jung+", 99))");
                                AppendCompo(GetHanCode(cho, jung, 99));
                                nMode = false; gMode = false; lMode = false;
                            }else {
                                Log.d("H_CHO_mo", "ReplaceCompo(GetHanCode("+cho+", "+jung+", 99))");
                                ReplaceCompo(GetHanCode(cho, jung, 99));
                            }
                        }
                    }
                } else {
                    // 복자음 초성만 입력된 상태에서 또 자음이 들어올 경우. 예:ㄳ 상태에서 ㄴ입력시 ㄳㄴ
                    if (bConso) {
                        // 새로 입력된 초성으로 단독 글자 만듬
                        FinishCompo();
                        ResetCompo();
                        cho = idx;
                        AppendCompo(cho + 0x3131);
                    } else {
                        // 복자음 앞쪽 초성만으로 한 글자 완성. 예:ㄳ상태에서 ㅏ 입력시 ㄱ사
                        ReplaceCompo(GetLeftBokJa(cho) + 0x3131);

                        // 복자음 뒤쪽 글자와 새로 입력된 중성과 조합하여 새 음절 분리
                        FinishCompo();
                        int newcho = GetRightBokJa(cho);
                        ResetCompo();
                        cho = newcho;
                        jung = idx;
                        AppendCompo(GetHanCode(cho, jung, 99));
                    }
                }
                break;
            case H_JUNGONLY:
                // 모음만 입력된 상태에서 자음이 들어오면 음절 분리. 예:ㅏ상태에서 ㄱ입력시 ㅏㄱ
                if (bConso) {
                    // 새로 입력된 초성으로 단독 글자 만듬
                    FinishCompo();
                    ResetCompo();
                    cho = idx;
                    AppendCompo(cho + 0x3131);
                } else {
                    if(idx != 51) {
                        int bokmo = findBokMo(jung, idx);
                        // 복모음 구성 가능하면 복모음으로 대체. 단 초성은 없음. 예:ㅗ상태에서 ㅏ입력시 ㅘ
                        if (bokmo != 99) {
                            jung = bokmo;
                            ReplaceCompo(jung + 0x3131);
                            // 복모음 구성되지 않을 경우 - 음절 분리
                        } else {
                            // 새로 입력된 중성으로 단독 글자 만듬
                            FinishCompo();
                            ResetCompo();
                            jung = idx;
                            AppendCompo(jung + 0x3131);
                        }
                        // H_NONE 에서 ㅣ, ·, ㅡ 중에 어떤 것이 먼저 입력되었는가에 따라 처리해 줌.
                    }else  {
//					if(idx == 51){
//					if(prejung == 51) {
//						jung = 52; // ·· 입력 상태.
//						Log.d("H_JUNGONLY", "AppendDot(0xB7, hanState) calling, jung = " + jung);
//						AppendDot(0xB7, hanState);
//					}else if(prejung == 50) {
//						jung = 30;  // ㅏ
//						prejung = 51;
//						ReplaceCompo(jung + 0x3131);
//					}else if(prejung == 48) {
//						jung = 43;  // ㅜ
//						prejung = 51;
//						ReplaceCompo(jung + 0x3131);
//					}
                        Log.d("H_JUNG_bokmo", "findBokMo(jung, idx) calling");
                        int bokmo = findBokMo(jung, idx);
                        // 복모음 구성 가능하면 복모음으로 대체
                        if (bokmo != 99) {
                            if(bokmo == 52 ) {
                                jung = bokmo;
                                Log.d("H_JUNG_bokmo", "AppendDot(0xB7) calling");
                                AppendDot(0xB7, hanState);

                            }else if(bokmo == 51) { // 단, ·· 이면 dot을 입력.
                                jung = bokmo;
                                Log.d("H_JUNG_bokmo", "AppendDot(0xB7) calling");
                                AppendDot(0xB7, hanState);
                            }
                            else {
                                jung = bokmo;
                                ReplaceCompo(jung + 0x3131);
                            }
                            // 복모음 구성되지 않을 경우 - 음절 분리. 예:아 상태에서 ㅓ입력시 아ㅓ
                        } else {
                            // 조립중인 글자 완성
                            FinishCompo();

                            // 중성만으로 글자 구성
                            if(idx == 51) { //단, · 입력 시 조립중인 글자 완성 후 dot 입력처리
                                ResetCompo();
                                jung = idx;
                                AppendDot(0xB7, hanState);
                            }else {
                                ResetCompo();
                                jung = idx;
                                AppendCompo(jung + 0x3131);
                            }
                        }

                    }
                }
                break;
            case H_JUNG:
                // 자음이면 받침으로
                if (bConso) {
                    // 받침이 될 수 없는 글자인 경우 음절 분리. 예:아 상태에서 ㄸ입력시 아ㄸ
                    if (hanattr[idx][2] == 99) {
                        // 조립중인 글자 완성
                        FinishCompo();

                        // 새로 입력된 초성으로 단독 글자 만듬
                        ResetCompo();
                        cho = idx;
                        AppendCompo(cho + 0x3131);
                    } else {
                        jong = idx;
                        ReplaceCompo(GetHanCode(cho, jung, jong));
                    }

//				prejung = 0; // ? 초기화 필요할 듯.

                    // 또 모음이면 복모음 구성
                } else {
                    Log.d("H_JUNG_bokmo", "findBokMo("+jung+", "+idx+")");
                    int bokmo = findBokMo(jung, idx);
                    // 복모음 구성 가능하면 복모음으로 대체
                    if (bokmo != 99) {
                        if(bokmo == 52 ) {
                            jung = bokmo;
                            Log.d("H_JUNG_bokmo", "AppendCompo(0xB7) calling");
                            AppendDot(0xB7, hanState);

                        }else if(bokmo == 51) { // 단, ·· 이면 dot을 입력.
                            jung = bokmo;
                            Log.d("H_JUNG_bokmo", "AppendCompo(0xB7) calling");
                            AppendDot(0xB7, hanState);
                        }
                        else {
                            jung = bokmo;
                            Log.d("H_JUNG_bokmo", "ReplaceCompo(GetHanCode(cho, jung, jong)) calling");
                            ReplaceCompo(GetHanCode(cho, jung, jong));
                        }
                        // 복모음 구성되지 않을 경우 - 음절 분리. 예:아 상태에서 ㅓ입력시 아ㅓ
                    } else {
                        // 조립중인 글자 완성
                        FinishCompo();

                        // 중성만으로 글자 구성
                        if(idx == 51) { //단, · 입력 시 조립중인 글자 완성 후 dot 입력처리
                            ResetCompo();
                            jung = idx;
                            AppendDot(0xB7, hanState);
                        }else {
                            ResetCompo();
                            jung = idx;
                            AppendCompo(jung + 0x3131);
                        }
                    }
                }
                break;
            case H_JONG:
                Log.d("H_JONG_ja", "jong = "+jong);
                Log.d("H_JONG_ja", "IsBokJa("+jong+") = "+IsBokJa(jong) );
                if (IsBokJa(jong) == false) {
                    // 자음이면 복자음 받침 구성. 또는 새 음절로 분리
                    if (bConso) {
                        Log.d("H_JONG_ja", "자음이면 복자음 받침 구성. 또는 새 음절로 분리");
                        Log.d("H_JONG_ja", "findBokja(jong, idx) = ("+jong+", "+idx+")");
                        int bokja = findBokJa(jong, idx);
                        // 복자음 구성 가능하면 복자음 받침으로 대체. 예:달 상태에서 ㄱ입력시 닭
                        if (bokja != 99) {
                            Log.d("H_JONG_ja", "복자음 구성 가능하면 복자음 받침으로 대체. 예:달 상태에서 ㄱ입력시 닭");
                            Log.d("H_JONG_ja", "jong <= bokja = " +bokja);
                            jong = bokja;
                            Log.d("H_JONG_ja", "ReplaceCompo(GetHanCode(cho, jung, jong)) calling");
                            ReplaceCompo(GetHanCode(cho, jung, jong));
                            // 복자음 구성되지 않을 경우 - 음절 분리
                        } else {
                            Log.d("H_JONG_ja", "복자음 구성되지 않을 경우 - 음절 분리");
                            Log.d("H_JONG_ja", "조립중인 글자 완성");

                            // ㄱ, ㄴ, ㄹ 이 종성으로 입력된 상태, 완료 처리를 하지 않는다.
                            if( jong == 0 &&  idx == 29|| // ㅎ:29
                                    (jong == 3 && (idx == 20 || idx == 25)) || // ㅅ:20, ㅊ:25
                                    (jong == 8 && (idx == 22 || idx == 26 || idx == 6))) { // ㄷ:6, ㅇ: 22, ㅋ:26
                                FinishCompo();
                                // 조립중인 글자 완성
                            }else {
                                FinishCompo();
                            }

                            Log.d("H_JONG_ja", "jong = " +jong);
                            if(jong == 0) { // 종성이 ㄱ 이면, ㅅ과 복모음 구성이 가능하므로 일단 H_NONE 에서 최종 결정.
                                Log.d("H_JONG_ja", "종성이 ㄱ 이면, ㅅ과 복모음 구성이 가능하므로 일단 H_NONE 에서 최종 결정");
                                gMode = true; nMode = false; lMode = false;
                                gcho = cho; gjung = jung; gjong = jong;
                                Log.d("H_JONG_ja", "gcho, gjung, gjong = " +gcho+","+gjung+","+gjong);

                            }else
                            if(jong == 3) { // 종성이 ㄴ 이면, ㅎ과 복모음 구성이 가능하므로 일단 H_NONE 에서 최종 결정.
                                Log.d("H_JONG_ja", "종성이 ㄴ 이면, ㅎ과 복모음 구성이 가능하므로 일단 H_NONE 에서 최종 결정");
                                gMode = false; nMode = true; lMode = false;
                                ncho = cho; njung = jung; njong = jong;
                                Log.d("H_JONG_ja", "ncho, njung, njong = " +ncho+","+njung+","+njong);

                            }else
                            if(jong == 8) { // 종성이 ㄹ 이면, ㅁ, ㅌ, ㅎ과 복모음 구성이 가능하므로 일단 H_CHO 에서 최종 결정.
                                Log.d("H_JONG_ja", "종성이 ㄹ 이면, ㅁ, ㅌ, ㅎ과 복모음 구성이 가능하므로 일단 H_NONE 에서 최종 결정");
                                gMode = false; nMode = false; lMode = true;
                                lcho = cho; ljung = jung; ljong = jong;
                                Log.d("H_JONG_ja", "lcho, ljung, ljong = " +lcho+","+ljung+","+ljong);
                            }

                            // ㄱ, ㄴ, ㄹ 이 종성으로 입력된 상태, 리셋처리를 하지 않는다.
                            if( jong == 0 &&  idx == 29 || // ㅎ:29
                                    (jong == 3 && (idx == 20 || idx == 25)) ||              // ㅅ:20, ㅊ:25
                                    (jong == 8 && (idx == 22 || idx == 26 || idx == 6)) ) { // ㄷ:6, ㅇ: 22, ㅋ:26
                                cho = idx; jung = 99; jong = 99;
                                AppendCompo(cho + 0x3131);
                                //ResetCompo();
                                // 새로 입력된 초성으로 단독 글자 만듬
                            }
                            else {
                                Log.d("H_JONG_ja", "새로 입력된 초성으로 단독 글자 만듬");
                                ResetCompo();
                                cho = idx;
                                AppendCompo(cho + 0x3131);

                                // H_CHO_mo 에서 mComp.replace 가 일어나지 않도록 처리
                                gMode = false; nMode = false; lMode = false;
                            }
                        }
                        // 모음이면 음절 분리
                    } else {
                        Log.d("H_JONG_mo", "모음이면 음절 분리");
                        Log.d("H_JONG_mo", "기존의 초성, 중성으로 한 글자 완성");
                        // 기존의 초성, 중성으로 한 글자 완성
                        ReplaceCompo(GetHanCode(cho, jung, 99));
                        FinishCompo();

                        Log.d("H_JONG_mo", "이전 글자의 종성을 초성으로 하고 새로 입력된 중성과 조합하여 새 음절 분리");
                        // 이전 글자의 종성을 초성으로 하고 새로 입력된 중성과 조합하여 새 음절 분리
                        int newcho = jong;
                        ResetCompo();
                        cho = newcho;

                        // 단, dot 입력되면
                        if(idx == 51) {
                            Log.d("H_JONG_mo", "단, dot 입력되면");
                            Log.d("H_JONG_mo", "idx = " +idx);
                            AppendCompo(cho + 0x3131);
                            Log.d("H_JONG_mo", "AppendCompo(cho + 0x3131)");
                            AppendDot(0xB7, hanState);
                            Log.d("H_JONG_mo", "AppendDot(0xB7, hanState)");
                            jung = idx;
                        }else {
                            Log.d("H_JONG_mo", "단, dot 입력 안되면");
                            Log.d("H_JONG_mo", "idx = " +idx);
                            jung = idx;
                            AppendCompo(GetHanCode(cho, jung, 99));
                        }
                    }
                } else {
                    // 자음이면 무조건 음절 분리
                    if (bConso) {
                        Log.d("H_JONG_bokja_ja", "자음이면 무조건 음절 분리");
                        Log.d("H_JONG_bokja_ja", "새로 입력된 초성으로 단독 글자 만듬");
                        // 새로 입력된 초성으로 단독 글자 만듬
                        FinishCompo();
                        ResetCompo();
                        cho = idx;
                        AppendCompo(cho + 0x3131);
                        // 모음이면 복자음 하나 떼 와서 음절 분리
                    } else {
                        Log.d("H_JONG_bokja_mo", "모음이면 복자음 하나 떼 와서 음절 분리");
                        Log.d("H_JONG_bokja_mo", "기존의 초성, 중성, 복자음 앞자로 한 글자 완성");
                        // 기존의 초성, 중성, 복자음 앞자로 한 글자 완성
                        ReplaceCompo(GetHanCode(cho, jung, GetLeftBokJa(jong)));

                        Log.d("H_JONG_bokja_mo", "이전 글자의 복자음 뒷자를 초성으로 하고 새로 입력된 중성과 조합하여 새 음절 분리");
                        // 이전 글자의 복자음 뒷자를 초성으로 하고 새로 입력된 중성과 조합하여 새 음절 분리
                        FinishCompo();
                        int newcho = GetRightBokJa(jong);
                        ResetCompo();

                        if(idx == 51) {
                            jung = idx;
                            cho = newcho;
                            Log.d("H_CHO_DOT", "AppendDot(0xB7,hanState) calling");
                            AppendCompo(cho + 0x3131);
                            AppendDot(0xB7, hanState);
                        }else {
                            cho = newcho;
                            jung = idx;
                            AppendCompo(GetHanCode(cho, jung, 99));
                        }

                    }
                }
                break;
        }


        Log.d(TAG, "OUT: cho = "+cho+ ",jung = "+ jung+", jong =" +jong);
    }

    // 조립중인 한글 삭제 처리 - 역 오토마타
    public void HangulBs()
    {
        reverseFlag = true;

        int crPos = 0;
        try {
            crPos = currentInputConnection.getExtractedText(new ExtractedTextRequest(), InputConnection.GET_EXTRACTED_TEXT_MONITOR).selectionStart;
        }
        catch (NullPointerException err)
        {
            err.printStackTrace();
        }

        int hanState = GetHanState();

        Log.e("HWI","오토마타에서 딜리트 시 --> mComp : " +mComp.toString()+"  State : " + hanState+"  deleteBtnFlag : "+deleteBtnFlag+"  crPos : "+crPos);

        if(deleteBtnFlag && !(crPos==0))
        {
            int hangulUnicode;
            String word = "";
            try {
                word = getExtractText().substring(crPos-1, crPos);
            }catch(IndexOutOfBoundsException err) {
                err.printStackTrace();
            }catch(NullPointerException err) {
                err.printStackTrace();
            }


            Log.d("HWI","오토마타에서 앞글자를 가져오는 부분 확인 : word : "+word);

            /// 앞에 텍스트가 존재하는 경우
            if(!TextUtils.isEmpty(word))
            {
                ///조건 확인을 위해 로그 찍어봄
                Log.d("HWI","오토마타 01 word : "+word);
                if((int)word.charAt(0) >= 0xAC00 && (int)word.charAt(0) <= 0xD79F)
                {
                    Log.d("HWI","오토마타 케이스 01 word : "+word);
                    //한글
                    Log.d("HangulBs_","word = " +word.charAt(0));
//            		mComp.setLength(0);
                    mComp.append(word);
                    Log.d("HangulBs_","mComp = " +mComp.toString());
                    hangulUnicode = (int)word.charAt(0) - 0xAC00;
                    Log.d("HangulBs_","hangulUnicode = " +hangulUnicode+"for "+(int)word.charAt(0));
                    jong = hangulUnicode % 28;
                    jung = ((hangulUnicode - jong)/28) % 21; jung = jung+30;
                    cho = ((hangulUnicode - jong)/28) / 21;

                    if(jong==0) {
                        jong=99;
                    }else {
                        for(int i=0; i< hanattr.length; i++ ) {
                            if(hanattr[i][2] == jong) { // 종성 idx 값 찾기.
                                jong = i;
                                break;
                            }
                        }
                    }

                    for(int i=0; i< hanattr.length; i++ ) {
                        if(hanattr[i][1] == cho) { // 초성 idx 값 찾기
                            cho = i;
                            break;
                        }
                    }

                    Log.e("HangulBs_","word = " +word+": cho = "+cho+", jung = "+jung+", jong = "+jong);

                    Log.d("HangulBs_", "H_NONE crPos = " +crPos);
                    hanState = GetHanState();
                    try {
                        currentInputConnection.setComposingRegion(crPos-1, crPos);
                    }catch (IndexOutOfBoundsException err) {
                        err.printStackTrace();
                    }catch (NullPointerException err) {
                        err.printStackTrace();
                    }

                    Log.d("HangulBs_", "hanState = " +hanState);
                }
                else if((int)word.charAt(0) >= 0x3131 && (int)word.charAt(0) <= 0x3163	)
                {
                    Log.d("HWI","오토마타 케이스 02 word : "+word);
                    //한글
                    Log.d("HangulBs","word = " +word.charAt(0));
//                 		mComp.setLength(0);
                    mComp.append(word);
                    Log.d("HangulBs","mComp = " +mComp.toString());
                    int tempIdx = (int)word.charAt(0) - 0x3131;
                    if(hanattr[tempIdx][0]==0) { // 자음
                        cho = tempIdx; jung = 99; jong =99;
                    }else { //모음
                        cho = 99; jung = tempIdx; jong =99;

                    }
                    Log.d("HangulBs","code = " +(int)word.charAt(0));
                    Log.d("HangulBs","word = " +word+": cho = "+cho+", jung = "+jung+", jong = "+jong);
//
//                 		for(int i=0; i< hanattr.length; i++ ) {
//                				if(hanattr[i][1] == cho) {
//             					cho = i;
//             					break;
//             				}
//                 		}

                    Log.d("HangulBs", "H_NONE crPos = " +crPos);
                    hanState = GetHanState();
                    try {
                        currentInputConnection.setComposingRegion(crPos-1, crPos);
                    }catch (IndexOutOfBoundsException err) {
                        err.printStackTrace();
                    }catch (NullPointerException err) {
                        err.printStackTrace();
                    }
                    Log.d("HangulBs", "hanState = " +hanState);
                }
            }
        }
        Log.d("HWI","오토마타 케이스 0001 hanState : "+hanState);
        switch(hanState)
        {
            case H_NONE:
                currentInputConnection.finishComposingText();

                ResetCompo();
                currentInputConnection.deleteSurroundingText(1,0);

                break;
            case H_CHO:
                if (IsBokJa(cho) == false) {
                    Log.d("HangulBs_CHO", "복자 아니면, ResetCompo, commitText(), hanMode = false");
                    ResetCompo();
                    currentInputConnection.commitText("",0);

                    hanMode = false; // space key 에서 바로 간격이 입력되도록 하기 위함
                } else {
                    // 복자음 초성인 경우 앞쪽 초성만 남김. ㄳ -> ㄱ
                    Log.d("HangulBs_CHO", "복자음 초성인 경우 앞쪽 초성만 남김. ㄳ -> ㄱ");
                    cho = GetLeftBokJa(cho);
                    ReplaceCompo(cho + 0x3131);
                }
                break;
            case H_JUNGONLY:
                // 단모임인 경우는 삭제
                Log.d("HWI", "HangulBs_JUNGONLY : 단모임인 경우는 삭제");
                Log.d("HWI", "HangulBs_JUNGONLY : IsBokMo = " +IsBokMo(jung)+", jung = "+jung);

                if (IsBokMo(jung) == false)
                {
                    Log.d("HWI","오토마타 케이스 테스트 000001");
                    ResetCompo();
                    currentInputConnection.finishComposingText();
                    currentInputConnection.deleteSurroundingText(1, 0);
                    // 복모음이면 앞쪽 모음만 남김. ㅘ -> ㅗ
                }
                else
                {
                    Log.d("HWI","오토마타 케이스 테스트 000002");
                    Log.d("HangulBs_JUNGONLY", "복모음이면 앞쪽 모음만 남김. ㅘ -> ㅗ");
                    jung = GetLeftBokMo(jung);

                    if(jung == 51 || jung == 52)
                    {
                        Log.d("HWI","오토마타 케이스 테스트 000003");
                        ResetCompo();
                        currentInputConnection.finishComposingText();
                        currentInputConnection.deleteSurroundingText(1,0);
                    }
                    else
                    {
                        Log.d("HWI","오토마타 케이스 테스트 000004");
                        ReplaceCompo(jung + 0x3131);
                    }
                }

                break;
            case H_JUNG:
                // 복모음이 아니면 모음 삭제하고 자음만 남긴다. 예:가 -> ㄱ
                if (IsBokMo(jung) == false) {
                    Log.d("HangulBs_JUNG", "복모음이 아니면 모음 삭제하고 자음만 남긴다. 예:가 -> ㄱ");
                    ReplaceCompo(cho + 0x3131);
                    jung = 99;
                    // 복모음이면 뒤쪽 모음을 삭제한다. 예:와 -> 오
                } else {
                    Log.d("HangulBs_JUNG", "복모음이면 뒤쪽 모음을 삭제한다. 예:와 -> 오");
                    jung = GetLeftBokMo(jung);
                    // 단, dot 복모음이면 모음 삭제하고 자음만 남긴다.
                    if(jung == 51 || jung == 52) {
                        ReplaceCompo(cho + 0x3131);
                        jung = 99;
                    }else {
                        ReplaceCompo(GetHanCode(cho,jung,99));
                    }
                }
                break;
            case H_JONG:
                // 복자음이 아니면 받침 삭제한다. 예:간 -> 가
                if (IsBokJa(jong) == false) {
                    Log.d("HangulBs_JONG", "복자음이 아니면 받침 삭제한다. 예:간 -> 가");
                    ReplaceCompo(GetHanCode(cho, jung, 99));
                    jong = 99;
                    // 복자음 받침이면 뒤쪽 받침만 삭제한다. 예:닭 -> 달
                } else {
                    Log.d("HangulBs_JONG", "복자음 받침이면 뒤쪽 받침만 삭제한다. 예:닭 -> 달");
                    jong = GetLeftBokJa(jong);
                    ReplaceCompo(GetHanCode(cho,jung,jong));
                }
                break;
            case H_DOT:
                ResetCompo();
                currentInputConnection.commitText("",0);
                break;
        }
    }

    // cho, jung, jong 값으로 한글 유니코드 음절 코드를 구한다.
    int GetHanCode(int acho, int ajung, int ajong)
    {
        Log.e("GetHanCode", "cho="+acho+", jung="+ajung+", jong="+ajong);

        int choorder, jungorder, jongorder;

        choorder = hanattr[acho][1];

        if (ajung == 99) {
            jungorder = 99;
        } else {
            jungorder = ajung - 30;
        }

        if (ajong == 99 /*|| ajong == 0*/) {
            jongorder = 99;
        } else {
            jongorder = hanattr[ajong][2];
        }

        // 유니코드 "가"자를 베이스로 초성만큼 거리 띄움
        int resultcode = 0xac00 + (choorder *21 * 28);

        // 중성이 있으면 중성만큼 띄움
        if (jungorder != 99) {
            resultcode += (jungorder * 28);
        }

        // 종성이 있으면 종성만큼 띄움
        if (jongorder != 99) {
            resultcode += jongorder;
        }

        return resultcode;
    }

    // 현재 한글 조립 상태를 조사한다.
    public int GetHanState()
    {
        Log.e("GetHanState", "IN: cho="+cho+",jung="+jung+",jong="+jong);
        // 초성이 없는 경우 - 아무것도 없거나 중성만 입력된 경우
        if (cho == 99)
        {
            if (jung == 99)
            {
                return H_NONE;
            }else {
                return H_JUNGONLY;
            }
        }

        // 초성은 있는데 중성이 없는 경우
        if (jung == 99) return H_CHO;

        // 중성이 있는데 종성이 없는 경우
        if (jong == 99 /*|| jong == 0*/) return H_JUNG;

        // 천지인 점 입력
        if (jung == 100) return H_DOT;

        // 종성까지 다 입력된 경우
        return H_JONG;
    }

    // 복자음 테이블
//첨자 0 : 원래 음소
//첨자 2 : 새로 입력된 음소
//첨자 3 : 대체될 음소
    int arBokJa[][] = {
            {0,20,2},		// 앇
            {3,23,4},		// 앉
            {3,29,5},		// 않
            {8,0,9},		// 앍
            {8,16,10},		// 앎
            {8,17,11},		// 앏
            {8,20,12},		// 앐
            {8,27,13},		// 앑
            {8,28,14},		// 앒
            {8,29,15},		// 앓
            {17,20,19},		// 앖
    };

    //복자음인지 조사하여 순서값 리턴. 아니면 99 리턴
    int findBokJa(int oldidx, int newidx) {
        int bokja = 99;
        for (int i=0;i<arBokJa.length;i++) {
            if (oldidx == arBokJa[i][0] && newidx == arBokJa[i][1]) {
                bokja = arBokJa[i][2];
                break;
            }
        }
        return bokja;
    }

    // 복자음인지 조사한다.
    boolean IsBokJa(int code) {
        for (int i=0;i<arBokJa.length;i++) {
            if (code == arBokJa[i][2]) {
                return true;
            }
//			if (code == arBokJa[i][0]) {
//				return true;
//			}
        }
        return false;
    }

    // 복자음의 왼쪽을 구한다.
    int GetLeftBokJa(int code) {
        for (int i=0;i<arBokJa.length;i++) {
            if (code == arBokJa[i][2]) {
                return arBokJa[i][0];
            }
        }
        return 99;
    }

    // 복자음의 오른쪽을 구한다.
    int GetRightBokJa(int code) {
        for (int i=0;i<arBokJa.length;i++) {
            if (code == arBokJa[i][2]) {
                return arBokJa[i][1];
            }
        }
        return 99;
    }

    // 복모음 테이블
// 첨자 0 : 원래 음소
// 첨자 2 : 새로 입력된 음소
// 첨자 3 : 대체될 음소
    int[][] arBokMo = {
            {50,51,30},     // ㅣ+·=ㅏ
            {30,51,32},		// ㅏ+·=ㅑ
            {32,51,30},		// ㅑ+·=ㅏ
            {51,50,34},     // ·+ㅣ=ㅓ
            {51,51,52},		// ·+·=··
            {52,51,51},		// ··+·=·
            {52,50,36},     // ··+ㅣ=ㅕ
            {51,48,38},     // ·+ㅡ=ㅗ
            {52,48,42},		// ··+ㅡ=ㅛ
            {48,51,43},		// ㅡ+·=ㅜ
            {43,51,47},		// ㅜ+·=ㅠ
            {47,51,43},		// ㅠ+·=ㅜ
            {30,50,31},		// ㅏ+ㅣ=ㅐ
            {34,50,35},		// ㅓ+ㅣ=ㅔ
            {36,50,37},		// ㅕ+ㅣ=ㅖ
            {32,50,33},		// ㅑ+ㅣ=ㅒ
            {38,50,41},		// ㅗ+ㅣ=ㅚ
            {38,30,39},		// ㅗ+ㅏ=ㅘ
            {41,51,39},		// ㅚ+·=ㅘ
            {47,50,44},		// ㅠ+ㅣ=ㅝ
            {43,50,46},		// ㅜ+ㅣ=ㅟ
            {43,34,44},		// ㅜ+ㅓ=ㅝ
            {38,31,40},		// ㅗ+ㅐ=ㅙ
            {39,50,40},		// ㅘ+ㅣ=ㅙ
            {43,35,45},		// ㅜ+ㅔ=ㅞ
            {44,50,45},		// ㅝ+ㅣ=ㅞ
            {48,50,49},		// ㅡ+ㅣ=ㅢ
    };

    //복모음인지 조사하여 순서값 리턴. 아니면 99 리턴
    int findBokMo(int oldidx, int newidx) {
        int bokmo = 99;
        for (int i=0;i<arBokMo.length;i++) {
            if (oldidx == arBokMo[i][0] && newidx == arBokMo[i][1]) {
                bokmo = arBokMo[i][2];
                break;
            }
        }
        return bokmo;
    }

    // 복모음인지 조사한다.
    boolean IsBokMo(int code) {
        for (int i=0;i<arBokMo.length;i++) {
            if (code == arBokMo[i][2]) {
                if(code == 30 || code == 31 || code == 32 || code == 33 ||
                        code == 35 || code == 37 || code ==43  || code == 47) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    // 복모음의 왼쪽을 구한다.
    int GetLeftBokMo(int code) {
        for (int i=0;i<arBokMo.length;i++) {
            if (code == arBokMo[i][2]) {
                return arBokMo[i][0];
            }
        }

        return 99;
    }

    // 복모음의 오른쪽을 구한다.
    int GetRightBokMo(int code) {
        for (int i=0;i<arBokMo.length;i++) {
            if (code == arBokMo[i][2]) {
                return arBokMo[i][0];
            }
        }

        return 99;
    }

    //한글 입력기의 상태 변수값을 초기화한다.
    public void ResetCompo()
    {
        cho = jung = jong = 99;
        Log.d("ResetCompo", "mComp.setLength(0)");
        mComp.setLength(0);
        Log.d("ResetCompo", "mComp =  " + mComp);
    }

    // 조립문자열 뒤에 추가
    void AppendCompo(int code) {
        Log.d("AppendCompo", "mCompo = " + mComp);
        InputConnection ime = currentInputConnection;

        mComp.append((char)code);
        Log.e("AppendCompo", "mCompo.append = " + mComp);

        ime.setComposingText(mComp, 1);
        Log.d("AppendCompo", "End of AppendCompo ");
    }

    void AppendDot(int code, int hanState) {
        Log.d("AppendDot", "mCompo = " +mComp+", length = " +mComp.length());
        InputConnection ime = currentInputConnection;

        Log.d("AppendDot", "State = " + hanState);

        switch(hanState) {
            case H_NONE:
                Log.d("AppendDot", "H_NONE");
                mComp.append((char)code);
                break;
            case H_CHO:
                Log.d("AppendDot", "H_CHO");
                Log.i(TAG,"length : " + mComp.length());
                mComp.append((char)code);
                break;
            case H_JUNGONLY:
                Log.d("AppendDot", "H_JUNGONLY");
                if(mComp.length() == 1) {
                    mComp.append((char)code);
                }else if(mComp.length() == 2){
                    mComp.replace(0, 2, "·");
                }
                break;
            case H_JUNG:
                Log.d("AppendDot", "H_JUNG");
                if(mComp.length() == 3) {
                    mComp.replace(1, 3, "·");
                }else {
                    mComp.append((char)code);
                }
                break;
            case H_JONG:
                Log.d("AppendDot", "H_JONG");
                mComp.append((char)code);
                break;
        }

        Log.d("AppendDot", "mCompo.append = " + mComp);
        ime.setComposingText(mComp, 1);
    }

    // 조립 문자열 대체
    void ReplaceCompo(int code) {
        Log.d("ReplaceCompo", "code = " + code);
        InputConnection ime = currentInputConnection;

        Log.d("ReplaceCompo", "IN: gMode = " + gMode +", "+"nMode = " + nMode +", "+ "lMode = " + lMode);
        if(mComp.length() == 2) {
            mComp.replace(0, 2, String.valueOf((char)code));
            Log.e("ReplaceCompo", "mComp.replace(0, 2, String.valueOf((char)code)) = " +String.valueOf((char)code));
        }else if(mComp.length() == 3) {
            Log.d("ReplaceCompo", "if(mComp.length() == 3");
            mComp.replace(0, 3, String.valueOf((char)code));
            Log.e("ReplaceCompo", "mComp.replace(0, 3, String.valueOf((char)code)) = " +String.valueOf((char)code));
        }
        if(mComp.length() == 2 && (nMode == false|| lMode == false)) {
            Log.d("ReplaceCompo", "if(mComp.length() == 2 && nMode == false)");
            mComp.replace(0, 2, String.valueOf((char)code));
            Log.e("ReplaceCompo", "mComp.replace(0, 2, String.valueOf((char)code)) = " +String.valueOf((char)code));
        }else if(mComp.length() == 3 && (nMode == false || lMode == false)) {
            Log.d("ReplaceCompo", "if(mComp.length() == 3 && nMode == false)");
            mComp.replace(0, 3, String.valueOf((char)code));
            Log.e("ReplaceCompo", "mComp.replace(0, 3, String.valueOf((char)code)) = " +String.valueOf((char)code));
        }
        Log.d("ReplaceCompo", "mComp = " + mComp);
        Log.d("ReplaceCompo", "Before mComp.setCharAt(0, (char)code) ");

        Log.d("HWI", "가끔 여기서 앱이 죽는 경우가 있어 확인 필요 (char)code : " + (char) code + " code : " + code);

        if(mComp != null && mComp.length() > 0)
        {
//            mComp.setCharAt(1,(char)code);
        }




        Log.e("ReplaceCompo", "mComp.setCharAt(0, (char)code) = " + mComp);
        //준을+space+ㅈ 입력되면 "준을=>줁" 으로 바뀜., "ㅇ" 입력상태에서 "ㅁ"이 입력되면 H_NONE 상태로 가면서 nMode=false로
        //셋팅되는 기회를 갖게됨. 하지만, "ㅡ" 모음이 입력되면서 H_CHO 상태로 천이됨. nMode=true 상태가 유지되고,
        //"ㄹ"이 입력되면서 다시 nMode=false 될 기회를 갖지만, 여기서 space 키가 입력되면 초기화 되고 다시 H_NONE 상태로 갈 준비가 됨.
        //그리고, "ㄹ" 종성 입력으로 인한 nMode=false가 될 기회를 잃음. 이때 "ㄴ"과 종성 복자음을 형성할 수 있는 "ㅈ"이 들어오면 오류 발생.
        //그래서, 이 단계에서 종성 복자음 모드를 해제해야 함.
        gMode = false; nMode = false; lMode = false;
        Log.d("ReplaceCompo", "OUT: gMode = " + gMode +", "+"nMode = " + nMode +", "+ "lMode = " + lMode);

        Log.d("HWI","입력 확인 테스트 001 : "+mComp+"  ime : "+ime);
        char cr = (char)code;
        String change = String.valueOf(cr);
        change = Character.toString(cr);
        change = new Character(cr).toString();

        ime.setComposingText(change,1);

        Log.d("HWI","입력 확인 테스트 002 : "+mComp+"  ime : "+ime);
    }

    // 조립 중인 문자열 확정
    public void FinishCompo() {
        Log.d("FinishCompo", "FinishCompo() called");
        InputConnection ime = currentInputConnection;
        Log.d("FinishCompo", "currentInputConnection called");
        ime.finishComposingText();
//        twoText = -1;
        Log.d("FinishCompo", "finishComposingText() called");
    }

    public void replaceDoubleTouch25(String inputText){

        if (twoText25 == -1){

        }else{
            HangulBs();
        }

        twoText25 = convertStringToUnicode(inputText,0);

        for (int i=0; i<=inputText.length(); i++) {
            try {
                ProcessHangul(convertStringToUnicode(inputText,i));
            }catch (Exception e){}
        }
    }

    public void replaceDoubleTouch27(String inputText){

        if (twoText27 == -1){

        }else{
            HangulBs();
        }

        twoText27 = convertStringToUnicode(inputText,0);

        for (int i=0; i<=inputText.length(); i++) {
            try {
                ProcessHangul(convertStringToUnicode(inputText,i));
            }catch (Exception e){}
        }
    }

    public int convertStringToUnicode(String text, int x)
    {
        char firstChar = text.charAt(x);
        int uniCode = firstChar;
        Log.d("HWI", "유니코드 : " + uniCode);
        return uniCode;
    }
}
