package flagthis.pubsubhubbub;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.SSLSocketFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

final class GetThread extends Thread {

	private final HttpClient httpClient;
	private final HttpContext context;
	private final HttpPost httppost;
	public HttpResponse httpresponse = null;

	public GetThread(HttpClient httpClient, HttpPost httppost) {
		this.httpClient = httpClient;
		this.context = new BasicHttpContext();
		this.httppost = httppost;
	}

	@Override
	public void run() {
		try {
			httpresponse = this.httpClient.execute(this.httppost, this.context);
			HttpEntity entity = httpresponse.getEntity();
			if (entity != null) {
				entity.consumeContent();
			}
		} catch (Exception ex) {
			this.httppost.abort();
		}
	}

}