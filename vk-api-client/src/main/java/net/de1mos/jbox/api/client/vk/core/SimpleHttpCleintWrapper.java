package net.de1mos.jbox.api.client.vk.core;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;

public class SimpleHttpCleintWrapper implements HttpClientWrapper {

	@Override
	public HttpClient getDefaultHttpClient() {
		// TODO Auto-generated method stub
		return wrapClient(new DefaultHttpClient());
	}

	@Override
	public HttpClient wrapClient(HttpClient client) {
		// http://javaskeleton.blogspot.com/2010/07/avoiding-peer-not-authenticated-with.html
		// wrapping the client to successfully perform https queries without any
		// certificates

		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			ctx.init(null, new TrustManager[] { dontCareTrustManager }, null);
			SSLSocketFactory ssf = new SSLSocketFactory(ctx);

			ClientConnectionManager baseCcm = client.getConnectionManager();
			SchemeRegistry sr = baseCcm.getSchemeRegistry();
			sr.register(new Scheme("https", 443, ssf));

			// http://stackoverflow.com/questions/4612573/exception-using-httprequest-execute-invalid-use-of-singleclientconnmanager-c
			// http://foo.jasonhudgins.com/2010/03/http-connections-revisited.html
			// avoiding
			// "invalid use of SingleClientConnManager: connection still allocated."
			// exception

			ClientConnectionManager safeCcm = new ThreadSafeClientConnManager(
					sr);
			return new DefaultHttpClient(safeCcm, client.getParams());
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

	}


	/**
	 * Accepts all given certificates
	 */
	private static final X509TrustManager dontCareTrustManager = new X509TrustManager() {
		private final X509Certificate[] empty = new X509Certificate[0];

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return empty;
		}

		@Override
		public void checkServerTrusted(X509Certificate[] ar, String st)
				throws CertificateException {
		}

		@Override
		public void checkClientTrusted(X509Certificate[] ar, String st)
				throws CertificateException {
		}
	};

}
