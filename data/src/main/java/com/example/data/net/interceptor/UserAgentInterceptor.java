package com.example.data.net.interceptor;

import java.io.IOException;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Replaces default user agent by a custom one provided with dagger
 */
public class UserAgentInterceptor implements okhttp3.Interceptor {
  private static final String USER_AGENT_HEADER_NAME = "User-Agent";
  private final String userAgentHeaderValue;

  public UserAgentInterceptor(String userAgentHeaderValue) {
    this.userAgentHeaderValue = userAgentHeaderValue;
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    final Request originalRequest = chain.request();
    final Request requestWithUserAgent = originalRequest.newBuilder().removeHeader(USER_AGENT_HEADER_NAME).addHeader(
        USER_AGENT_HEADER_NAME, userAgentHeaderValue
    ).build();
    return chain.proceed(requestWithUserAgent);
  }
}
