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

package com.liferay.change.tracking.internal.upgrade;

import com.liferay.change.tracking.internal.upgrade.v2_2_0.UpgradeCTPreferences;
import com.liferay.change.tracking.internal.upgrade.v2_3_0.UpgradeCompanyId;
import com.liferay.change.tracking.internal.upgrade.v2_4_0.UpgradeCTSchemaVersion;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Daniel Kocsis
 */
@Component(
	immediate = true,
	service = {ChangeTrackingServiceUpgrade.class, UpgradeStepRegistrator.class}
)
public class ChangeTrackingServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"1.0.0", "1.0.1",
			new com.liferay.change.tracking.internal.upgrade.v1_0_1.
				UpgradeCTCollection());

		registry.register(
			"1.0.1", "2.0.0",
			new com.liferay.change.tracking.internal.upgrade.v2_0_0.
				UpgradeSchema());

		registry.register(
			"2.0.0", "2.1.0",
			new com.liferay.change.tracking.internal.upgrade.v2_1_0.
				UpgradeSchema());

		registry.register("2.1.0", "2.2.0", new UpgradeCTPreferences());

		registry.register("2.2.0", "2.3.0", new UpgradeCompanyId());

		registry.register("2.3.0", "2.4.0", new UpgradeCTSchemaVersion());
	}

}