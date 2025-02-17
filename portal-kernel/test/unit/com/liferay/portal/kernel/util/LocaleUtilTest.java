/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.test.log.LogCapture;
import com.liferay.portal.test.log.LogEntry;
import com.liferay.portal.test.log.LoggerTestUtil;

import java.util.List;
import java.util.Locale;
import java.util.logging.Level;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Wesley Gong
 */
@PrepareForTest(LanguageUtil.class)
@RunWith(PowerMockRunner.class)
public class LocaleUtilTest extends PowerMockito {

	@Test
	public void testFromLanguageId() {
		mockStatic(LanguageUtil.class);

		when(
			LanguageUtil.isAvailableLocale(Locale.US)
		).thenReturn(
			true
		);

		try (LogCapture logCapture = LoggerTestUtil.configureJDKLogger(
				LocaleUtil.class.getName(), Level.WARNING)) {

			List<LogEntry> logEntries = logCapture.getLogEntries();

			Assert.assertEquals(Locale.US, LocaleUtil.fromLanguageId("en_US"));
			Assert.assertEquals(logEntries.toString(), 0, logEntries.size());

			logEntries.clear();

			LocaleUtil.fromLanguageId("en");

			Assert.assertEquals(logEntries.toString(), 1, logEntries.size());

			LogEntry logEntry = logEntries.get(0);

			Assert.assertEquals(
				"en is not a valid language id", logEntry.getMessage());
		}
	}

	@Test
	public void testFromLanguageIdBCP47() {
		mockStatic(LanguageUtil.class);

		when(
			LanguageUtil.isAvailableLocale(Locale.US)
		).thenReturn(
			true
		);

		Assert.assertEquals(Locale.US, LocaleUtil.fromLanguageId("en-US"));

		when(
			LanguageUtil.isAvailableLocale(Locale.SIMPLIFIED_CHINESE)
		).thenReturn(
			true
		);

		Assert.assertEquals(
			Locale.SIMPLIFIED_CHINESE, LocaleUtil.fromLanguageId("zh-Hans-CN"));

		when(
			LanguageUtil.isAvailableLocale(Locale.TRADITIONAL_CHINESE)
		).thenReturn(
			true
		);

		Assert.assertEquals(
			Locale.TRADITIONAL_CHINESE,
			LocaleUtil.fromLanguageId("zh-Hant-TW"));
	}

}