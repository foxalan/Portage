package com.example.fox_ui.studentmanager.sign.out;

import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.example.fox_core.fragment.LatteDelegate;
import com.example.fox_ui.R;

/**
 * @Author Alan
 * Date 2018/5/23 0023
 * Function
 * Issue
 */

public class SignOutDelegate extends LatteDelegate implements View.OnClickListener,ISignOutViewListener {

    private TextInputEditText mTvStuId;
    private TextInputEditText mTvStuName;
    private TextInputEditText mTvStuAge;
    private TextInputEditText mTvStuSex;
    private TextInputEditText mTvStuPwd;
    private TextInputEditText mTvStuPwdAgain;
    private AppCompatButton mBtnSignOut;
    private SignOutHandler mSignOutHandler;

    @Override
    public Object getLayout() {
        return R.layout.delegate_student_sign_out;
    }

    @Override
    public void onBindView(View rootView) throws Exception {
        mTvStuId = rootView.findViewById(R.id.edit_sign_out_stu_id);
        mTvStuName = rootView.findViewById(R.id.edit_sign_out_stu_name);
        mTvStuAge = rootView.findViewById(R.id.edit_sign_out_stu_age);
        mTvStuSex = rootView.findViewById(R.id.edit_sign_out_stu_sex);
        mTvStuPwd = rootView.findViewById(R.id.edit_sign_in_password);
        mTvStuPwdAgain = rootView.findViewById(R.id.edit_sign_out_password_again);
        mBtnSignOut = rootView.findViewById(R.id.btn_sign_out);

        mBtnSignOut.setOnClickListener(this);
        mSignOutHandler = SignOutHandler.create(this);
    }

    @Override
    public void onClick(View v) {
        String id = mTvStuId.getText().toString();
        String name = mTvStuName.getText().toString();
        String age = mTvStuAge.getText().toString();
        String sex = mTvStuSex.getText().toString();
        String pwd = mTvStuPwd.getText().toString();
        String pwdAgain = mTvStuPwdAgain.getText().toString();

        mSignOutHandler.signOut(id,name,sex,age,pwd,pwdAgain);
    }

    @Override
    public void onCompleteError() {
        restartTextLayout();
        setTextErrorNull();
    }

    @Override
    public void onPasswordDis() {
        restartTextLayout();
        mTvStuPwdAgain.setError("两次密码不一样");
    }

    @Override
    public void onSignOutSuccess() {
       restartTextLayout();
    }

    @Override
    public void onFailStuIdRepeat() {
        restartTextLayout();
    }

    private void restartTextLayout(){
        mTvStuId.setError(null);
        mTvStuName.setError(null);
        mTvStuAge.setError(null);
        mTvStuSex.setError(null);
        mTvStuPwd.setError(null);
        mTvStuPwdAgain.setError(null);
    }

    private void setTextErrorNull(){
        if ("".equals(mTvStuId.getText())){
            mTvStuId.setError("不可以为空");
            return;
        }

        if ("".equals(mTvStuName.getText())){
            mTvStuName.setError("不可以为空");
            return;
        }

        if ("".equals(mTvStuAge.getText())){
            mTvStuAge.setError("不可以为空");
            return;
        }

        if ("".equals(mTvStuSex.getText())){
            mTvStuSex.setError("不可以为空");
            return;
        }

        if ("".equals(mTvStuPwd.getText())){
            mTvStuPwd.setError("不可以为空");
            return;
        }

        if ("".equals(mTvStuPwdAgain.getText())){
            mTvStuPwd.setError("不可以为空");
            return;
        }
    }

}
