package flagthis.pubsubhubbub;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Publisher {

	/*
	 * @param The Hub address you want to publish it to
	 * @param The topic_url you want to publish
	 */
	public  int publish(String hub, String topic_url) throws IOException {
		if (hub != null) {
			hub = "http://pubsubhubbub.appspot.com/publish";
			@SuppressWarnings("unused")
			URL verifying_topic_url = new URL(topic_url);
			URL hub_url = new URL(topic_url);

			String data = URLEncoder.encode("hub.mode", "UTF-8") + "="
					+ URLEncoder.encode("publish", "UTF-8");
			data += "&" + URLEncoder.encode("hub.url", "UTF-8") + "="
					+ URLEncoder.encode(topic_url, "UTF-8");

			HttpURLConnection connection = (HttpURLConnection)hub_url.openConnection();
			connection.setRequestProperty("User-agent", "flagthis.pubsubhubbub");
			connection.setRequestProperty("CONTENT-TYPE",
					"application/x-www-form-urlencoded");

			connection.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(connection
					.getOutputStream());
			wr.write(data);
			wr.flush();

			return connection.getResponseCode();
		}
		return 400;
	}

}
