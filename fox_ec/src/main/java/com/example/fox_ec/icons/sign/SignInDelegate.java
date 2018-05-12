package com.example.fox_ec.icons.sign;

import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.example.fox_core.fragment.LatteDelegate;
import com.example.fox_ec.R;
import com.example.fox_ec.icons.main.EcBottomDelegate;
import com.example.fox_ec.icons.sign.callback.IViewSignCallBack;
import com.example.fox_ec.icons.sign.presenter.SignInPresenter;

/**
 * @Author Alan
 * Date 2018/5/9 0009
 * Function
 * Issue
 */

public class SignInDelegate extends LatteDelegate implements IViewSignCallBack{

    TextInputEditText mUser;
    TextInputEditText mPassWord;
    AppCompatButton mBtnSignIn;

    private String mStrUser;
    private String mStrPassWord;
    private SignInPresenter mSignInPresenter;

    @Override
    public Object getLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(View rootView) throws Exception {
        mUser = rootView.findViewById(R.id.edit_sign_in_email);
        mPassWord = rootView.findViewById(R.id.edit_sign_in_password);
        mBtnSignIn = rootView.findViewById(R.id.btn_sign_in);

        mSignInPresenter = new SignInPresenter(this);

        initEvent();
    }

    private void initEvent() {
        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStrUser = mUser.getText().toString();
                mStrPassWord = mPassWord.getText().toString();
                mSignInPresenter.init(mStrUser,mStrPassWord);
            }
        });
    }


    @Override
    public void onUserNull() {
        setErrorNull();
        mUser.setError("用户名为空");
    }

    @Override
    public void onPassWordNull() {
        setErrorNull();
        mPassWord.setError("密码为空");
    }

    @Override
    public void onUserFormatError() {
        setErrorNull();
        mUser.setError("错误的邮箱格式");
    }

    @Override
    public void onPasswordFormatError() {
        setErrorNull();
        mPassWord.setError("用户名");
    }

    private void setErrorNull(){
        mUser.setError(null);
        mPassWord.setError(null);
    }

    @Override
    public void onError() {
        setErrorNull();

    }

    @Override
    public void onSuccess() {
        setErrorNull();
        getSupportDelegate().startWithPop(new EcBottomDelegate());
    }
}
