package com.siit.okhttpdemo;

import java.io.Reader;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class OkHttpContributors {
	private static final String ENDPOINT = "https://api.github.com/repos/square/okhttp/contributors";
	private static final Gson GSON = new Gson();
	private static final TypeToken<List<Contributor>> CONTRIBUTORS = new TypeToken<List<Contributor>>() {
	};

	public OkHttpContributors() {
	}

	public String excuteHttpContribtors() throws Exception {
		OkHttpClient client = new OkHttpClient();

		// Create request for remote resource.
		Request request = new Request.Builder().url(ENDPOINT).build();

		// Execute the request and retrieve the response.
		Response response = client.newCall(request).execute();

		// Deserialize HTTP response to concrete type.
		Reader body = response.body().charStream();
		List<Contributor> contributors = GSON.fromJson(body,
				CONTRIBUTORS.getType());

		// Sort list by the most contributions.
		Collections.sort(contributors, new Comparator<Contributor>() {
			@Override
			public int compare(Contributor c1, Contributor c2) {
				return c2.contributions - c1.contributions;
			}
		});

		String str = "";
		// Output list of contributors.
		for (Contributor contributor : contributors) {
			System.out.println(contributor.login + ": "
					+ contributor.contributions);
			str = str + contributor.login + ": " + contributor.contributions
					+ "\n";
		}
		return str;
	}

	class Contributor {
		String login;
		String id;
		String avatar_url;
		String gravatar_id;
		String url;
		String html_url;
		String followers_url;
		String following_url;
		String gists_url;
		String starred_url;
		String subscriptions_url;
		String organizations_url;
		String repos_url;
		String events_url;
		String received_events_url;
		String type;
		String site_admin;
		int contributions;
	}
}
