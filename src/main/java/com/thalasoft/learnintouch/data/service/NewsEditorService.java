package com.thalasoft.learnintouch.data.service;

import com.thalasoft.learnintouch.data.dao.domain.NewsEditor;

public interface NewsEditorService {

	public NewsEditor save(NewsEditor newsEditor);

	public boolean isNotUsedByAnyNewsStory(NewsEditor newsEditor);

}
