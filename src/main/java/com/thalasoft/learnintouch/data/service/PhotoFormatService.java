package com.thalasoft.learnintouch.data.service;

import com.thalasoft.learnintouch.data.dao.domain.PhotoFormat;

public interface PhotoFormatService {

	public PhotoFormat save(PhotoFormat photoFormat);

	public boolean isNotUsedByAnyPhoto(PhotoFormat photoFormat);

}
