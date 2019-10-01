/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.growthchart.api;

import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.growthchart.AgeUnit;
import org.openmrs.module.growthchart.ChartJSAgeAxis;
import org.openmrs.module.growthchart.GrowthChartApi;
import org.openmrs.test.BaseModuleContextSensitiveTest;

public class GrowthChartApiTest extends BaseModuleContextSensitiveTest {
	
	GrowthChartApi growthChart = new GrowthChartApi();
	
	@Before
	public void execute() throws Exception {
		initializeInMemoryDatabase();
		executeDataSet("growthChartData.xml");
		authenticate();
	}
	
	@Test
	public void shouldGetHeightsAtGivenPatientAges() {
		Patient patient = Context.getPatientService().getPatient(2);
		ChartJSAgeAxis ageAxis = new ChartJSAgeAxis(1, 20, 1, AgeUnit.MONTHS);
		JSONArray json = growthChart.getHeightsAtGivenPatientAges(patient, ageAxis);
		String expectedJsonStringDataAtDiffAges = "[{\"1\":1},{\"6\":3},{\"8\":4},{\"9\":5},{\"11\":6},{\"13\":7}]";
		Assert.assertEquals(expectedJsonStringDataAtDiffAges, json.toString());
	}
	
	@Test
	public void shouldGetWeightsAtGivenPatientAges() {
		Patient patient = Context.getPatientService().getPatient(2);
		ChartJSAgeAxis ageAxis = new ChartJSAgeAxis(1, 20, 1, AgeUnit.MONTHS);
		JSONArray json = growthChart.getWeightsAtGivenPatientAges(patient, ageAxis);
		String expectedJsonStringDataAtDiffAges = "[{\"1\":1},{\"6\":3},{\"8\":4},{\"11\":6},{\"13\":7}]";
		Assert.assertEquals(json.toString(), expectedJsonStringDataAtDiffAges);
	}
	
	@Test
	public void shouldGetgetHeadCircumferenceAtGivenPatientAges() {
		Patient patient = Context.getPatientService().getPatient(2);
		ChartJSAgeAxis ageAxis = new ChartJSAgeAxis(1, 20, 1, AgeUnit.MONTHS);
		Context.getAdministrationService().setGlobalProperty("growthchart.headCircumferenceConceptUuid",
		    "5314AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		JSONArray json = growthChart.getHeadCircumferenceAtGivenPatientAges(patient, ageAxis);
		String expectedJsonStringDataAtDiffAges = "[{\"1\":1},{\"6\":3},{\"8\":4},{\"9\":5},{\"11\":6},{\"13\":7}]";
		Assert.assertEquals(json.toString(), expectedJsonStringDataAtDiffAges);
	}
}
