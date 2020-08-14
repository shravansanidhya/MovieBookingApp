package com.mindtree.user.service;

import com.mindtree.user.entity.Preference;
import com.mindtree.user.exception.NotFoundException;

/**
 * @author M1049008
 *
 */
public interface PreferenceService {

	public Preference findByUserId(long userId) throws NotFoundException;

	public Preference update(Preference preference) throws NotFoundException;

	public void deleteByUserId(long userId);

}
