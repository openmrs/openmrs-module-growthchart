/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.growthchart.page.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.growthchart.AgeUnit;
import org.openmrs.module.growthchart.ChartJSAgeAxis;
import org.openmrs.module.growthchart.GrowthChartApi;
import org.openmrs.module.growthchart.shared.SharedControllerStuff;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

public class GrowthChartsPageController {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	public void controller(PageModel model, @RequestParam("patientId") Patient patient, HttpSession session, UiUtils ui) {
		SharedControllerStuff shared = new SharedControllerStuff();
		model.addAttribute("patientPropts", shared.patientPropsInit(patient));
		model.addAttribute("chartAxisLabels", shared.setupAxisLabelNames());
		model.addAttribute("patientPlottableData", shared.getPatientPlottableData(patient, session, ui));
	}
}
