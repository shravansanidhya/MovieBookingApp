//package com.mindtree.user.service.serviceImpl;
//
//import java.util.NoSuchElementException;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.mindtree.user.entity.Preference;
//import com.mindtree.user.exception.NotFoundException;
//import com.mindtree.user.repository.PreferenceRepository;
//import com.mindtree.user.service.PreferenceService;
//
///**
// * @author M1049008
// *
// */
//@Service
//public class PreferenceServiceImpl implements PreferenceService {
//
//	@Autowired
//	private PreferenceRepository preferenceRepo;
//	private static final Logger LOGGER = LoggerFactory.getLogger(PreferenceServiceImpl.class);
//
//	@Override
//	public Preference findByUserId(long userId) throws NotFoundException {
//		Preference preferenecByUserId = preferenceRepo.findByUserId(userId);
//		LOGGER.info("Preference for user id retireved!");
//		if (preferenecByUserId == null) {
//			LOGGER.error("Preference for user id " + userId + " does not exists!");
//			throw new NotFoundException("Preference with user id " + userId + " does not exists!");
//		}
//		return preferenecByUserId;
//	}
//
//	@Override
//	public Preference update(Preference preference) throws NotFoundException {
//		try {
//			Preference persistedPreference = preferenceRepo.findById(preference.getId()).get();
//			BeanUtils.copyProperties(preference, persistedPreference);
//			return preferenceRepo.saveAndFlush(persistedPreference);
//		} catch (NoSuchElementException e) {
//			LOGGER.error("Preference with id " + preference.getId() + " does not exists!");
//			throw new NotFoundException("Preference with id " + preference.getId() + " does not exists!");
//		}
//	}
//
//	@Override
//	public void deleteByUserId(long userId) {
//		preferenceRepo.deleteByUserId(userId);
//	}
//
//}
