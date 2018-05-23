package com.example.administrator.protage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.fox_core.fragment.LatteDelegate;
import com.example.fox_core.net.RestClient;
import com.example.fox_core.net.callback.IError;
import com.example.fox_core.net.callback.IFailure;
import com.example.fox_core.net.callback.ISuccess;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by 傅令杰 on 2017/4/2
 */

public class ExampleDelegate extends LatteDelegate {


    private Button btn1;
    private String result;

  //  private String str_json = "{cs:[{id:\"5\",name:\"Tony\",gallery:\"7000\",job:\"Test\",amount:\"36000\",date:\"04 28 2018 11:58AM\",height:\"163.33\",fox_number:\"W0103200\",}{id:\"4\",name:\"Tony\",gallery:\"7000\",job:\"Test\",amount:\"36000\",date:\"05 4 2018 3:28PM\",height:\"163.33\",fox_number:\"W0103200\",}{id:\"1\",name:\"alan\",gallery:\"14000\",job:\"android\",amount:\"35000\",date:\"03 28 2018 4:04PM\",height:\"172.22\",fox_number:\"w0103550\",}{id:\"2\",name:\"rh\",gallery:\"6200\",job:\"android\",amount:\"55000\",date:\"03 28 2018 4:04PM\",height:\"170.00\",fox_number:\"w0103551\",}{id:\"3\",name:\"james\",gallery:\"26200\",job:\"taiwang\",amount:\"2550000\",date:\"03 28 2018 4:03PM\",height:\"160.00\",fox_number:\"w0103552\",}]}";

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Toast.makeText(getContext(), "连接服务器失败", Toast.LENGTH_LONG).show();
                    break;
                case 1:
                    Toast.makeText(getContext(), result, Toast.LENGTH_LONG).show();

                    break;
                default:
                    break;
            }
        }
    };


//    @Override
//    public Object setLayout() {
//        return com.diabin.fastec.example.R.layout.delegate_example;
//    }

   // @OnClick(com.diabin.fastec.example.R.id.btn_test)
    void onClickTest() {
        // testWX();
//        Log.e("alan", "btn click");
//        try {
//            JSONObject object = new JSONObject(str_json);
//            Log.e("tang","object"+object.toString());
//            JSONArray array = object.getJSONArray("cs");
//            for (int i = 0; i < array.length(); i++) {
//                String id = array.getJSONObject(0).getString("id");
//                Log.e("tang","id="+id);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//            Log.e("tang",e.toString());
//        }
     //   btnClick();
        clickButton();

    }

    private TextView tv_test;


    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
//        testRestClient();
       // tv_test = (TextView) rootView.findViewById(R.id.tv_test);

        RestClient.builder()
             //   .url("http://api.feige.ee/SmsService/Send ")
                //  .url("http://192.168.1.78/HelloWrold/login")
                .params("Account","18202710074")
                .params("Pwd","7aa634b8a64225398e271a90c")
                .params("Content","您的验证码是1234")
                .params("Mobile","18202710074")
                .params("SignId","42657")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.e("alan","response:"+response);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Log.e("alan","onFailure");
                    }
                })
                .build()
                .post();
//        RestClient.builder()
//                .url("WebService.asmx")
//                .success(new ISuccess() {
//                    @Override
//                    public void onSuccess(String response) {
//                        Log.e("alan",response);
//                    }
//                })
//                .failure(new IFailure() {
//                    @Override
//                    public void onFailure() {
//                        Log.e("alan","fail");
//                    }
//                })
//                .build()
//                .get();
    }

    private void testWX() {
    }


    public void clickButton(){
        Log.e("alan","click");
        RestClient.builder()
            //    .url("login")
                .params("username","alan")
                        .params("passwrod","tang")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                    //    Log.e("alan",response+"success");
                        Log.e("alan","success");
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Log.e("alan","fail");
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Log.e("alan","error"+msg+"======"+code);
                    }
                })
                .build()
                .post();


    }

    private void btnClick() {
        Log.e("alan", "clicked");

        final String SERVICE_NS = "http://tempuri.org/";
        //命名空间
        final String SOAP_ACTION = "http://tempuri.org/HelloWorld";
        //用来定义消息请求的地址，也就是消息发送到哪个操作
        //这里的IP改为10.0.2.2因为localhost 取的是模拟器的地址
        //    final String SERVICE_URL = "http://10.0.2.2/web/WebService.asmx";
        // final String SERVICE_URL = "http://10.0.2.2/test1/WebService.asmx";
        final String SERVICE_URL = "http://114.55.238.8/WebService1.asmx";
        //final String SERVICE_URL = "http://10.0.0.0:2593/WebService.asmx";
        //URL地址，这里写发布的网站的本地地址

        String methodName = "HelloWorld";
        //创建HttpTransportSE传输对象，该对象用于调用Web Service操作
        final HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);
        ht.debug = true;
        //使用SOAP1.1协议创建Envelop对象。从名称上来看,SoapSerializationEnvelope代表一个SOAP消息封包；但ksoap2-android项目对
        //SoapSerializationEnvelope的处理比较特殊，它是HttpTransportSE调用Web Service时信息的载体--客户端需要传入的参数，需要通过
        //SoapSerializationEnvelope对象的bodyOut属性传给服务器；服务器响应生成的SOAP消息也通过该对象的bodyIn属性来获取。
        final SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        //实例化SoapObject对象，创建该对象时需要传入所要调用的Web Service的命名空间、Web Service方法名
        SoapObject soapObject = new SoapObject(SERVICE_NS, methodName);
        //对dotnet webservice协议的支持,如果dotnet的webservice
        envelope.dotNet = true;
        //调用SoapSerializationEnvelope的setOutputSoapObject()方法，或者直接对bodyOut属性赋值，将前两步创建的SoapObject对象设为
        //SoapSerializationEnvelope的付出SOAP消息体
        envelope.bodyOut = soapObject;

        new Thread() {
            @Override
            public void run() {
                Log.e("alan", "start run");
                try {
                    //调用WebService，调用对象的call()方法，并以SoapSerializationEnvelope作为参数调用远程Web Service
                    ht.call(SOAP_ACTION, envelope);
                    if (envelope.getResponse() != null) {
                        Log.e("alan", "run success");
                        //获取服务器响应返回的SOAP消息，调用完成后，访问SoapSerializationEnvelope对象的bodyIn属性，该属性返回一个
                        //SoapObject对象，该对象就代表了Web Service的返回消息。解析该SoapObject对象，即可获取调用Web Service的返回值
                        SoapObject so = (SoapObject) envelope.bodyIn;
                        //接下来就是从SoapObject对象中解析响应数据的过程了
                        result = so.getPropertyAsString(0);

                        Log.e("alan", "success result：" + result);
                        Message msg = new Message();
                        msg.what = 1;
                        handler.sendMessage(msg);
                    } else {
                        Message msg = new Message();
                        msg.what = 0;
                        handler.sendMessage(msg);
                        Log.e("alan", "run failed");
                    }
                } catch (XmlPullParserException e) {
                    Log.e("alan", "XmlPullParserException");
                    e.printStackTrace();
                } catch (HttpResponseException e) {
                    Log.e("alan", "httpResponse");
                    e.printStackTrace();
                } catch (SoapFault soapFault) {
                    Log.e("alan", "soapFault");
                    soapFault.printStackTrace();
                } catch (IOException e) {
                    Log.e("alan", "IOException" + e.toString());
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public Object getLayout() {
        return null;
    }

    @Override
    public void onBindView(View rootView) throws Exception {

    }
}
