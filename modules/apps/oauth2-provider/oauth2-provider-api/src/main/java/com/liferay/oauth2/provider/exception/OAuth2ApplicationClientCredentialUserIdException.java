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

package com.liferay.oauth2.provider.exception;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class OAuth2ApplicationClientCredentialUserIdException
	extends PortalException {

	/**
	 * @deprecated As of Mueller (7.2.x), since 7.2, unused
	 */
	@Deprecated
	public OAuth2ApplicationClientCredentialUserIdException() {
	}

	public OAuth2ApplicationClientCredentialUserIdException(
		long userId, String userScreenName, long clientCredentialUserId,
		String clientCredentialUserScreenName) {

		super(
			StringBundler.concat(
				"User ", userId, " is not allowed to impersonate user ",
				clientCredentialUserId, " via client credentials grant"));

		this.userId = userId;
		this.clientCredentialUserId = clientCredentialUserId;
		this.userScreenName = userScreenName;
		this.clientCredentialUserScreenName = clientCredentialUserScreenName;
	}

	/**
	 * @deprecated As of Mueller (7.2.x), since 7.2, unused
	 */
	@Deprecated
	public OAuth2ApplicationClientCredentialUserIdException(String msg) {
		super(msg);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), since 7.2, unused
	 */
	@Deprecated
	public OAuth2ApplicationClientCredentialUserIdException(
		String msg, Throwable cause) {

		super(msg, cause);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), since 7.2, unused
	 */
	@Deprecated
	public OAuth2ApplicationClientCredentialUserIdException(Throwable cause) {
		super(cause);
	}

	public long clientCredentialUserId;
	public String clientCredentialUserScreenName;
	public long userId;
	public String userScreenName;

}