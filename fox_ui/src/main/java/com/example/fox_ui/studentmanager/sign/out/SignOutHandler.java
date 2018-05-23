package com.example.fox_ui.studentmanager.sign.out;


/**
 * @Author Alan
 * Date 2018/5/23 0023
 * Function
 * Issue
 */

public class SignOutHandler {


    private ISignOutViewListener listener;

    public SignOutHandler(ISignOutViewListener listener){
        this.listener = listener;
    }

    public static SignOutHandler create(ISignOutViewListener listener){

        return new SignOutHandler(listener);
    }


    public void signOut(String stuId,String name,String sex,String age,String psd ,String psdAgain){

        if ("".equals(stuId)||"".equals(name)||"".equals(sex)){
            listener.onCompleteError();
            return;
        }

        if ("".equals(age)||"".equals(psd)||"".equals(psdAgain)){
            listener.onCompleteError();
            return;
        }

        if (!psd.equals(psdAgain)){

            listener.onPasswordDis();
        }



    }


}
