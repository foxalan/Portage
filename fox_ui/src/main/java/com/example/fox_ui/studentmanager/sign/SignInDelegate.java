package com.example.fox_ui.studentmanager.sign;

import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.fox_core.fragment.LatteDelegate;
import com.example.fox_core.util.L;
import com.example.fox_ui.R;
import com.example.fox_ui.studentmanager.main.MainDelegate;
import com.example.fox_ui.studentmanager.sign.out.SignOutDelegate;

/**
 * @Author Alan
 * Date 2018/5/23 0023
 * Function :登入界面
 * Issue :
 */

public class SignInDelegate extends LatteDelegate implements View.OnClickListener,ISignInViewListener{

    private TextInputEditText mTvStuId;
    private TextInputEditText mTvPwd;
    private AppCompatButton mBtSignIn;
    private AppCompatTextView mTvSignOut;
    private SignInHandler mSignInHandler;

    @Override
    public Object getLayout() {
        return R.layout.delegate_student_sign_in;
    }

    @Override
    public void onBindView(View rootView) throws Exception {
        mTvStuId = rootView.findViewById(R.id.edit_sign_in_stu_id);
        mTvPwd = rootView.findViewById(R.id.edit_sign_in_password);
        mBtSignIn = rootView.findViewById(R.id.btn_sign_in);
        mTvSignOut = rootView.findViewById(R.id.tv_link_sign_up);

        mSignInHandler = SignInHandler.create(this);

        mBtSignIn.setOnClickListener(this);
        mTvSignOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_sign_in) {
            String strStuId = mTvStuId.getText().toString();
            String strStuPwd = mTvPwd.getText().toString();
            mSignInHandler.signIn(strStuId,strStuPwd);
        } else if (i==R.id.tv_link_sign_up){
            getSupportDelegate().start(new SignOutDelegate(),SINGLETASK);
        }
    }

    @Override
    public void onSignInSuccess() {
        restartTextLayout();
        getSupportDelegate().startWithPop(new MainDelegate());
    }

    @Override
    public void onSignInFail() {
        restartTextLayout();
        L.e("用户名或密码错误");
    }

    @Override
    public void onUserNameNull() {
        restartTextLayout();
        mTvStuId.setError("用户名不能为空");
    }

    @Override
    public void onPasswordNull() {
        restartTextLayout();
        mTvPwd.setError("密码不能为空");
    }

    @Override
    public void onPasswordNorm() {
        restartTextLayout();
        mTvPwd.setError("密码长度不能低于6位");
    }

    public void restartTextLayout(){
        mTvPwd.setError(null);
        mTvStuId.setError(null);
    }


}
