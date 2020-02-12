package com.epam.preprod.veremiev.task11.core.filters;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Locale;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Test;

import com.epam.preprod.veremiev.task11.constants.ContextAttributes;

import freemarker.core._ArrayEnumeration;

public class LocaleFilterTest {

	@Test
	public void shouldSetLocaleLocaleByCookiesInFirstPriority() throws Exception {
		String contextLocales = "ru en ua";
		String contextDefaultLocale = "en";

		Locale[] browserLocales = new Locale[] { new Locale("en") };

		Cookie[] cookies = new Cookie[] { new Cookie("locale", "ru") };

		Locale expectedLocale = new Locale("ru");
		test(contextLocales, contextDefaultLocale, browserLocales, expectedLocale, cookies);
	}

	@Test
	public void shouldSetLocaleLocaleByCookiesEvenIfNotSupported() throws Exception {
		String contextLocales = "en ua";
		String contextDefaultLocale = "en";

		Locale[] browserLocales = new Locale[] { new Locale("en") };

		Cookie[] cookies = new Cookie[] { new Cookie("locale", "ru") };

		Locale expectedLocale = new Locale("ru");
		test(contextLocales, contextDefaultLocale, browserLocales, expectedLocale, cookies);
	}

	@Test
	public void shouldSetLocaleByBrowserLocaleIfSupported() throws Exception {
		String contextLocales = "ru en ua";
		String contextDefaultLocale = "en";

		Locale[] browserLocales = new Locale[] { new Locale("ru"), new Locale("en") };

		Cookie[] cookies = new Cookie[0];

		Locale expectedLocale = new Locale("ru");
		test(contextLocales, contextDefaultLocale, browserLocales, expectedLocale, cookies);
	}

	@Test
	public void shouldSetLocaleByBrowserPrioritiestLocaleIfSupported() throws Exception {
		String contextLocales = "ru en ua";
		String contextDefaultLocale = "en";

		Locale[] browserLocales = new Locale[] { new Locale("en"), new Locale("ru") };

		Cookie[] cookies = new Cookie[0];

		Locale expectedLocale = new Locale("en");
		test(contextLocales, contextDefaultLocale, browserLocales, expectedLocale, cookies);
	}

	@Test
	public void shouldSetDefaultLocaleIfBrowserLocalesNotSupported() throws Exception {
		String contextLocales = "ru en ua";
		String contextDefaultLocale = "en";

		Locale[] browserLocales = new Locale[] { new Locale("ar") };

		Cookie[] cookies = new Cookie[0];

		Locale expectedLocale = new Locale("en");
		test(contextLocales, contextDefaultLocale, browserLocales, expectedLocale, cookies);
	}

	private void test(String contextLocales, String contextDefaultLocale, Locale[] browserLocales,
			Locale expectedLocale, Cookie[] cookies) throws Exception {
		LocaleFilter localeFilter = new LocaleFilter();
		FilterConfig fConfig = mock(FilterConfig.class);
		when(fConfig.getInitParameter(ContextAttributes.AVAILABLE_LOCALES)).thenReturn(contextLocales);
		when(fConfig.getInitParameter(ContextAttributes.DEFAULT_LOCALE)).thenReturn(contextDefaultLocale);
		when(fConfig.getInitParameter(ContextAttributes.LOCALES_COOKIE_LIFETIME))
				.thenReturn(String.valueOf(Integer.MAX_VALUE));
		when(fConfig.getInitParameter(ContextAttributes.LOCALES_CONTAINER)).thenReturn("cookies");
		ServletContext servletContext = mock(ServletContext.class);
		when(fConfig.getServletContext()).thenReturn(servletContext);

		localeFilter.init(fConfig);

		FilterChain chain = mock(FilterChain.class);
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);

		when(request.getLocale()).thenReturn(browserLocales[0]);
		when(request.getLocales()).thenReturn(new _ArrayEnumeration(browserLocales, browserLocales.length));
		when(request.getCookies()).thenReturn(cookies);

		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		doAnswer(i -> {
			HttpServletRequest internalRequest = (HttpServletRequest) i.getArguments()[0];
			Assert.assertEquals(expectedLocale, internalRequest.getLocale());
			return null;
		}).when(chain).doFilter(any(HttpServletRequest.class), any(HttpServletResponse.class));

		localeFilter.doFilter(request, response, chain);
	}
}
