/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.growthchart.shared;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.growthchart.AgeUnit;
import org.openmrs.module.growthchart.ChartJSAgeAxis;
import org.openmrs.module.growthchart.GrowthChartApi;
import org.openmrs.module.uicommons.util.InfoErrorMessageUtil;
import org.openmrs.ui.framework.UiUtils;

public class SharedControllerStuff {
	
	public JSONObject patientPropsInit(Patient patient) {
		GrowthChartApi growthChatApi = new GrowthChartApi();
		JSONObject patientOpts = new JSONObject();
		JSONObject patientAge = new JSONObject();
		
		patientAge.put("years", patient.getAge());
		patientAge.put("days", growthChatApi.getPatientAgeInDays(patient));
		patientAge.put("months", growthChatApi.getPatientAgeInMonths(patient));
		patientOpts.put("name", patient.getPersonName().getFullName());
		patientOpts.put("gender", "M".equals(patient.getGender()) ? 2 : ("F".equals(patient.getGender()) ? 1 : null));
		patientOpts.put("age", patientAge);
		return patientOpts;
	}
	
	public JSONObject setupAxisLabelNames() {
		JSONObject chartAxisLabels = new JSONObject();
		
		chartAxisLabels.put("WTAGEINF_x",
		    Context.getMessageSourceService().getMessage("growthchart.chart.label.age.months"));
		chartAxisLabels.put("WTAGEINF_y", Context.getMessageSourceService().getMessage("growthchart.chart.label.weight"));
		chartAxisLabels.put("LENAGEINF_x",
		    Context.getMessageSourceService().getMessage("growthchart.chart.label.age.months"));
		chartAxisLabels.put("LENAGEINF_y", Context.getMessageSourceService().getMessage("growthcharts.chart.label.length"));
		chartAxisLabels.put("WTLENINF_x", Context.getMessageSourceService().getMessage("growthchart.chart.label.length"));
		chartAxisLabels.put("WTLENINF_y", Context.getMessageSourceService().getMessage("growthchart.chart.label.weight"));
		chartAxisLabels.put("HCAGEINF_x",
		    Context.getMessageSourceService().getMessage("growthchart.chart.label.age.months"));
		chartAxisLabels.put("HCAGEINF_y",
		    Context.getMessageSourceService().getMessage("growthchart.chart.label.headCircumference"));
		chartAxisLabels.put("WTSTAT_x", Context.getMessageSourceService().getMessage("growthchart.chart.label.stature"));
		chartAxisLabels.put("WTSTAT_y", Context.getMessageSourceService().getMessage("growthchart.chart.label.weight"));
		chartAxisLabels.put("WTAGE_x", Context.getMessageSourceService().getMessage("growthchart.chart.label.age.years"));
		chartAxisLabels.put("WTAGE_y", Context.getMessageSourceService().getMessage("growthchart.chart.label.weight"));
		chartAxisLabels.put("STATAGE_x", Context.getMessageSourceService().getMessage("growthchart.chart.label.age.years"));
		chartAxisLabels.put("STATAGE_y", Context.getMessageSourceService().getMessage("growthchart.chart.label.stature"));
		chartAxisLabels.put("BMIAGE_x", Context.getMessageSourceService().getMessage("growthchart.chart.label.age.years"));
		chartAxisLabels.put("BMIAGE_y", Context.getMessageSourceService().getMessage("growthchart.chart.label.bmi"));
		chartAxisLabels.put("BFA_x", Context.getMessageSourceService().getMessage("growthchart.chart.label.age.months"));
		chartAxisLabels.put("BFA_y", Context.getMessageSourceService().getMessage("growthchart.chart.label.bmi"));
		chartAxisLabels.put("HCFA_x", Context.getMessageSourceService().getMessage("growthchart.chart.label.age.months"));
		chartAxisLabels.put("HCFA_y",
		    Context.getMessageSourceService().getMessage("growthchart.chart.label.headCircumference"));
		chartAxisLabels.put("LHFA_x", Context.getMessageSourceService().getMessage("growthchart.chart.label.age.months"));
		chartAxisLabels.put("LHFA_y", Context.getMessageSourceService().getMessage("growthchart.chart.label.length"));
		chartAxisLabels.put("WFA_x", Context.getMessageSourceService().getMessage("growthchart.chart.label.age.months"));
		chartAxisLabels.put("WFA_y", Context.getMessageSourceService().getMessage("growthchart.chart.label.weight"));
		chartAxisLabels.put("WFL_x", Context.getMessageSourceService().getMessage("growthchart.chart.label.length"));
		chartAxisLabels.put("WFL_y", Context.getMessageSourceService().getMessage("growthchart.chart.label.weight"));
		
		return chartAxisLabels;
	}
	
	public JSONObject getPatientPlottableData(Patient patient, HttpSession session, UiUtils ui) {
		GrowthChartApi growthChatApi = new GrowthChartApi();
		ChartJSAgeAxis birthTo36Months = new ChartJSAgeAxis(0, 36, 1, AgeUnit.MONTHS);
		ChartJSAgeAxis birthTo24Months = new ChartJSAgeAxis(0, 24, 1, AgeUnit.MONTHS);
		ChartJSAgeAxis twoTo20Years = new ChartJSAgeAxis(2, 20, 1, AgeUnit.YEARS);
		JSONObject patientPlottableData = new JSONObject();
		final String NULL_HEAD_CIRCUMFRENCE = "growthchart.HeadCircumfrenceConceptNotFound";
		
		patientPlottableData.put("wtageinfPatient", growthChatApi.getWeightsAtGivenPatientAges(patient, birthTo36Months));
		patientPlottableData.put("lenageinfPatient", growthChatApi.getHeightsAtGivenPatientAges(patient, birthTo36Months));
		patientPlottableData.put("wtagePatient", growthChatApi.getWeightsAtGivenPatientAges(patient, twoTo20Years));
		patientPlottableData.put("statagePatient", growthChatApi.getHeightsAtGivenPatientAges(patient, twoTo20Years));
		patientPlottableData.put("whoWeightForAgePatient",
		    growthChatApi.getWeightsAtGivenPatientAges(patient, birthTo24Months));
		patientPlottableData.put("whoLengthForAgePatient",
		    growthChatApi.getHeightsAtGivenPatientAges(patient, birthTo24Months));
		try {
			patientPlottableData.put("hcageinfPatient",
			    growthChatApi.getHeadCircumferenceAtGivenPatientAges(patient, birthTo36Months));
			patientPlottableData.put("whoHeadCircumferenceForAgePatient",
			    growthChatApi.getHeadCircumferenceAtGivenPatientAges(patient, twoTo20Years));
		}
		catch (NullPointerException e) {
			InfoErrorMessageUtil.flashInfoMessage(session, ui.message(NULL_HEAD_CIRCUMFRENCE));
		}
		patientPlottableData.put("whoBMIForAgePatient",
		    growthChatApi.getPatientBMIsAcrossAnAgeDifference(patient, birthTo24Months));
		patientPlottableData.put("bmiAgeRevPatient",
		    growthChatApi.getPatientBMIsAcrossAnAgeDifference(patient, twoTo20Years));
		return patientPlottableData;
	}
}
