package com.future.utilslib.net;

import java.io.IOException;
import java.nio.charset.Charset;

import com.orhanobut.logger.Logger;

import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

public class LoggerInterceptor implements Interceptor {
    private static final String TAG = "HjHttp";
    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = request = chain.request().newBuilder()
                .addHeader("app_key", "myAppKey")
                .addHeader("sign", "12345678")
                .addHeader("times_tamp", "2019-06-01 13:47:45")
                .build();
        ;
        StringBuilder sb = new StringBuilder();
        String content = null;
        try {
            long t1 = System.nanoTime();

            logForRequest(request, chain.connection(), sb);


            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            content = response.body().string();
            sb
                    .append("\n")
                    .append("Response     : Code : ")
                    .append(response.code())
                    .append("; 耗时: ")
                    .append((int) ((t2 - t1) / 1e6))
                    .append("ms; \n")
                    .append("ResponseBody : ")
                    .append(content)
            ;
            Logger.e(TAG, sb.toString());

            MediaType mediaType = response.body().contentType();

            return response.newBuilder()
                    .body(ResponseBody.create(mediaType, content))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return chain.proceed(request);
        }

    }

    private void logForRequest(Request request, Connection connection, StringBuilder sb) throws IOException {

        RequestBody requestBody = request.body();
        boolean hasRequestBody = requestBody != null;
        Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;

        Headers headers = request.headers();
        for (int i = 0, count = headers.size(); i < count; i++) {
            sb.append("\n\t" + headers.name(i) + ": " + headers.value(i));
        }
        sb.append("\n").append("Request      : ").append(request.toString()).append(" ").append(protocol);
        //请求地址
        if (hasRequestBody) {
            if (isPlaintext(requestBody.contentType())) {
                bodyToString(request, sb);
            } else {
                sb.append("body: maybe [file part] , too large too print , ignored!");
            }
        }

    }

    private static boolean isPlaintext(MediaType mediaType) {
        if (mediaType == null) return false;
        if (mediaType.type() != null && mediaType.type().equals("text")) {
            return true;
        }
        String subtype = mediaType.subtype();
        if (subtype != null) {
            subtype = subtype.toLowerCase();
            if (subtype.contains("x-www-form-urlencoded") ||
                    subtype.contains("json") ||
                    subtype.contains("xml") ||
                    subtype.contains("html"))
                return true;
        }
        return false;
    }

    private void bodyToString(Request request, StringBuilder sb) throws IOException {

        final Request copy = request.newBuilder().build();
        final Buffer buffer = new Buffer();
        copy.body().writeTo(buffer);
        Charset charset = UTF8;
        MediaType contentType = copy.body().contentType();
        if (contentType != null) {
            charset = contentType.charset(UTF8);
        }
        //请求参数
        sb.append("\n").append("RequestBody  : ").append(buffer.readString(charset));

    }

}
