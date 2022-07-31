package com.thalasoft.learnintouch.data.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.PreferenceDao;
import com.thalasoft.learnintouch.data.dao.domain.Preference;
import com.thalasoft.learnintouch.data.service.PreferenceService;

@Transactional
public class PreferenceServiceImpl implements PreferenceService {

	@Autowired
	private PreferenceDao preferenceDao;

	protected void setPreferenceDao(PreferenceDao preferenceDao) {
		this.preferenceDao = preferenceDao;
	}

//	private List preferences;
	
	@Override
	public Preference save(Preference admin) {
		return preferenceDao.makePersistent(admin);
	}

	@Override
	public Preference load(Integer id) {
		return preferenceDao.findWithId(id, false);
	}

//	@Override
//	public void initialize(List ) {
//	    if (is_array($this->preferences) && count($this->preferences) > 0) {
//	      foreach ($this->preferences as $name => $preferenceData) {
//	        $defaultValue = $this->getDefaultValue($name);
//	        $defaultValue = LibString::escapeQuotes($defaultValue);
//
//	        // Create the preferences that do not yet exist
//	        if (!$this->isMlText($name)) {
//	          if (!$preference = $this->selectByName($name)) {
//	            $preference = new Preference();
//	            $preference->setName($name);
//	            $preference->setValue($defaultValue);
//	            $this->insert($preference);
//	            }
//	          } else {
//	          if (!$preference = $this->selectByName($name)) {
//	            $preference = new Preference();
//	            $preference->setName($name);
//	            $preference->setIsMlText(1);
//	            $this->insert($preference);
//	            } else if (!$preference->getIsMlText()) {
//	            $preference->setIsMlText(1);
//	            $this->update($preference);
//	            }
//	          }
//	        }
//	      }
//	    }

}
