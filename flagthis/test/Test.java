package flagthis.test;

import java.io.IOException;

import flagthis.pubsubhubbub.Publisher;

public class Test {

	public static void main(String[] args) {
		Publisher publisher = new Publisher();
		try {
			int status = publisher.publish("http://pubsubhubbub.appspot.com/publish", "http://www.royans.net/rant/2009/07/05/cell-phone-speeds-reliability-in-us/");
			System.out.println("Return status : "+status);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
