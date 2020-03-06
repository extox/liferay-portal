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

package com.liferay.account.service.impl;

import com.liferay.account.exception.DuplicateAccountEntryOrganizationRelException;
import com.liferay.account.model.AccountEntryOrganizationRel;
import com.liferay.account.service.AccountEntryLocalService;
import com.liferay.account.service.base.AccountEntryOrganizationRelLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.SetUtil;

import java.util.List;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.liferay.account.model.AccountEntryOrganizationRel",
	service = AopService.class
)
public class AccountEntryOrganizationRelLocalServiceImpl
	extends AccountEntryOrganizationRelLocalServiceBaseImpl {

	@Override
	public AccountEntryOrganizationRel addAccountEntryOrganizationRel(
			long accountEntryId, long organizationId)
		throws PortalException {

		if (hasAccountEntryOrganizationRel(accountEntryId, organizationId)) {
			throw new DuplicateAccountEntryOrganizationRelException();
		}

		accountEntryLocalService.getAccountEntry(accountEntryId);
		organizationLocalService.getOrganization(organizationId);

		AccountEntryOrganizationRel accountEntryOrganizationRel =
			createAccountEntryOrganizationRel(counterLocalService.increment());

		accountEntryOrganizationRel.setAccountEntryId(accountEntryId);
		accountEntryOrganizationRel.setOrganizationId(organizationId);

		accountEntryOrganizationRel = updateAccountEntryOrganizationRel(
			accountEntryOrganizationRel);

		_reindexOrganization(organizationId);

		return accountEntryOrganizationRel;
	}

	@Override
	public void addAccountEntryOrganizationRels(
			long accountEntryId, long[] organizationIds)
		throws PortalException {

		for (long organizationId : organizationIds) {
			addAccountEntryOrganizationRel(accountEntryId, organizationId);
		}
	}

	@Override
	public void deleteAccountEntryOrganizationRel(
			long accountEntryId, long organizationId)
		throws PortalException {

		accountEntryOrganizationRelPersistence.removeByA_O(
			accountEntryId, organizationId);

		_reindexOrganization(organizationId);
	}

	@Override
	public void deleteAccountEntryOrganizationRels(
			long accountEntryId, long[] organizationIds)
		throws PortalException {

		for (long organizationId : organizationIds) {
			deleteAccountEntryOrganizationRel(accountEntryId, organizationId);
		}
	}

	@Override
	public List<AccountEntryOrganizationRel> getAccountEntryOrganizationRels(
		long accountEntryId) {

		return accountEntryOrganizationRelPersistence.findByAccountEntryId(
			accountEntryId);
	}

	@Override
	public List<AccountEntryOrganizationRel>
		getAccountEntryOrganizationRelsByOrganizationId(long organizationId) {

		return accountEntryOrganizationRelPersistence.findByOrganizationId(
			organizationId);
	}

	@Override
	public int getAccountEntryOrganizationRelsCount(long accountEntryId) {
		return accountEntryOrganizationRelPersistence.countByAccountEntryId(
			accountEntryId);
	}

	@Override
	public boolean hasAccountEntryOrganizationRel(
		long accountEntryId, long organizationId) {

		AccountEntryOrganizationRel accountEntryOrganizationRel =
			accountEntryOrganizationRelPersistence.fetchByA_O(
				accountEntryId, organizationId);

		if (accountEntryOrganizationRel != null) {
			return true;
		}

		return false;
	}

	/**
	 * Creates an AccountEntryOrganizationRel for each given organizationId,
	 * unless it already exists, and removes existing
	 * AccountEntryOrganizationRels if their organizationId is not present in
	 * the given organizationIds.
	 *
	 * @param accountEntryId
	 * @param organizationIds
	 * @throws PortalException
	 * @review
	 */
	@Override
	public void setAccountEntryOrganizationRels(
			long accountEntryId, long[] organizationIds)
		throws PortalException {

		if (organizationIds == null) {
			return;
		}

		List<Long> currentOrganizationIds = ListUtil.toList(
			getAccountEntryOrganizationRels(accountEntryId),
			AccountEntryOrganizationRel::getOrganizationId);

		Set<Long> deleteOrganizationIdsSet = SetUtil.fromCollection(
			currentOrganizationIds);

		deleteOrganizationIdsSet.removeAll(ListUtil.fromArray(organizationIds));

		deleteAccountEntryOrganizationRels(
			accountEntryId, ArrayUtil.toLongArray(deleteOrganizationIdsSet));

		Set<Long> addOrganizationIdsSet = SetUtil.fromArray(organizationIds);

		addOrganizationIdsSet.removeAll(currentOrganizationIds);

		addAccountEntryOrganizationRels(
			accountEntryId, ArrayUtil.toLongArray(addOrganizationIdsSet));
	}

	@Reference
	protected AccountEntryLocalService accountEntryLocalService;

	private void _reindexOrganization(long organizationId)
		throws PortalException {

		Indexer<Organization> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			Organization.class);

		indexer.reindex(Organization.class.getName(), organizationId);
	}

}