<%
    ui.decorateWith("appui", "standardEmrPage", [ title: ui.message("growthchart.growthCharts") ])
%>

<% ui.includeJavascript("growthchart", "Chart.min.js") %>
<% ui.includeJavascript("growthchart", "growthCharts.js") %>

 <script type="text/javascript">
	var breadcrumbs = [
        { icon: "icon-home", link: '/' + OPENMRS_CONTEXT_PATH + '/index.htm' },
        { label: "${patientPropts.name}", link: "/" + OPENMRS_CONTEXT_PATH + "/coreapps/clinicianfacing/patient.page?patientId=${patient.uuid}"},
        { label: "${ ui.message('growthchart.growthCharts') }"}
    ];
</script>

 ${ ui.includeFragment("coreapps", "patientHeader", [ patient: patient ]) }

 <div class="standards-provider-tabs">
	<ul>
	    <li>
	        <a href="#cdc" >
	             ${ ui.message("growthchart.cdc") }
	        </a>
	    </li>
	    <li>
	    	<a href="#who">
	        	${ ui.message("growthchart.who") }
	        </a>
	    </li>
    </ul>

     <div id="cdc">
    	${ ui.includeFragment("growthchart", "cdcGrowthCharts") }
    </div>
    <div id="who">
    	${ ui.includeFragment("growthchart", "whoGrowthCharts") }
    </div>
</div>
<br/>

 <script type="text/javascript">
    jQuery(function() {
    	jQuery(".standards-provider-tabs").tabs();
    	
    	var patientPropts = ${patientPropts};
    	var chartAxisLabels = ${chartAxisLabels};
    	var patientPlottableData = ${patientPlottableData};
    	var wtageinfPatient = patientPlottableData.wtageinfPatient;
		var wtagePatient = patientPlottableData.wtagePatient;
		var statagePatient = patientPlottableData.statagePatient;
		var lenageinfPatient = patientPlottableData.lenageinfPatient;
		var whoWeightForAgePatient = patientPlottableData.whoWeightForAgePatient;
		var whoLengthForAgePatient = patientPlottableData.whoLengthForAgePatient;
		var hcageinfPatient = patientPlottableData.hcageinfPatient;
		var whoHeadCircumferenceForAgePatient = patientPlottableData.whoHeadCircumferenceForAgePatient;
		var bmiAgeRevPatient = patientPlottableData.bmiAgeRevPatient;
		var whoBMIForAgePatient = patientPlottableData.whoBMIForAgePatient;
		var wtleninfPatient = patientPlottableData.wtleninfPatient;
		var wtstatPatient = patientPlottableData.wtstatPatient;
		var whoWeightForLengthPatient = patientPlottableData.whoWeightForLengthPatient;
    
    	//TODO fix this trigger
    	jQuery('#WTAGEINF').trigger('click');
    	
    	//Handling selection on whoGrowthChartsDropDown
    	
         jQuery("#whoGrowthChartsDropDown").change(function(event){
               var id= jQuery("#whoGrowthChartsDropDown :selected").attr("id");
                     if (id==='WFA'){
                   	     resetChartJSCanvas("who_growth_charts");
    		             renderWHOPatient_ChartJS_LineGraph_WeightForAge(whoWeightForAgePatient,"who_growth_charts", patientPropts, {x: chartAxisLabels.WFA_x, y: chartAxisLabels.WFA_y});
    		             indentifySelectedCdcLink("WFA", "who");
    		             
    		             event.preventDefault();
    		             
                               }
                     if (id === 'LHFA'){
                          resetChartJSCanvas("who_growth_charts");
    		              renderWHOPatient_ChartJS_LineGraph_LengthForAge(whoLengthForAgePatient,"who_growth_charts", patientPropts, {x: chartAxisLabels.LHFA_x, y: chartAxisLabels.LHFA_y});
    		              indentifySelectedCdcLink("LHFA", "who");
    		
    		              event.preventDefault();
                              }
                      if (id === 'WFL'){
                    	  resetChartJSCanvas("who_growth_charts");
    		              renderWHOPatient_ChartJS_LineGraph_WeightForLength(whoWeightForLengthPatient,"who_growth_charts", patientPropts, {x: chartAxisLabels.WFL_x, y: chartAxisLabels.WFL_y});
    		              indentifySelectedCdcLink("WFL", "who");
    		
    		              event.preventDefault();
                    }
                      if (id === 'HCFA'){
                  	     resetChartJSCanvas("who_growth_charts");
    		             renderWHOPatient_ChartJS_LineGraph_HeadCircumferenceForAge(whoHeadCircumferenceForAgePatient,"who_growth_charts", patientPropts, {x: chartAxisLabels.HCFA_x, y: chartAxisLabels.HCFA_y});
    		             indentifySelectedCdcLink("HCFA", "who");
    		
    		             event.preventDefault();
                    }
                      if (id === 'BFA'){
                        resetChartJSCanvas("who_growth_charts");
    		            renderWHOPatient_ChartJS_LineGraph_BMIForAge(whoBMIForAgePatient,"who_growth_charts", patientPropts, {x: chartAxisLabels.BFA_x, y: chartAxisLabels.BFA_y});
    		            indentifySelectedCdcLink("BFA", "who");
    		
    		            event.preventDefault();
                    }
                    
              });
              //Handling selection on cdcGrowthChartsDropDown
              
             jQuery("#cdcGrowthChartsDropDown").change(function(event){
                    var id= jQuery("#cdcGrowthChartsDropDown :selected").attr("id");
                    
                    if (id === 'WTAGEINF'){
                       resetChartJSCanvas("cdc_growth_charts");
    		           renderPatient_ChartJS_LineGraph_WeightAgeInf(wtageinfPatient,"cdc_growth_charts", patientPropts, {x: chartAxisLabels.WTAGEINF_x, y: chartAxisLabels.WTAGEINF_y});
    		           indentifySelectedCdcLink("WTAGEINF");
    		
    		           event.preventDefault();
                            }
                      
                     if (id === 'LENAGEINF'){
                       resetChartJSCanvas("cdc_growth_charts");
    		           renderPatient_ChartJS_LineGraph_LengthAgeInf(lenageinfPatient,"cdc_growth_charts", patientPropts, {x: chartAxisLabels.LENAGEINF_x, y: chartAxisLabels.LENAGEINF_y});
    		           indentifySelectedCdcLink("LENAGEINF");
    		
    		           event.preventDefault();
                           }
                    
                     if (id === 'WTLENINF'){
                       	 resetChartJSCanvas("cdc_growth_charts");
    		             renderPatient_ChartJS_LineGraph_WeightLengthInf(wtleninfPatient,"cdc_growth_charts", patientPropts, {x: chartAxisLabels.WTLENINF_x, y: chartAxisLabels.WTLENINF_y});
    		             indentifySelectedCdcLink("WTLENINF");
    		
    		             event.preventDefault();
                            }
                      if (id === 'HCAGEINF'){
                       	 resetChartJSCanvas("cdc_growth_charts");
    		             renderPatient_ChartJS_LineGraph_HeightAgeInf(hcageinfPatient,"cdc_growth_charts", patientPropts, {x: chartAxisLabels.HCAGEINF_x, y: chartAxisLabels.HCAGEINF_y});
    		             indentifySelectedCdcLink("HCAGEINF");
    		
    		             event.preventDefault();
                            }
                      if (id === 'WTSTAT'){
                       	 resetChartJSCanvas("cdc_growth_charts");
    		             renderPatient_ChartJS_LineGraph_WeightStature(wtstatPatient,"cdc_growth_charts", patientPropts, {x: chartAxisLabels.WTSTAT_x, y: chartAxisLabels.WTSTAT_y});
    		             indentifySelectedCdcLink("WTSTAT");
    		
    		             event.preventDefault();
                            }
                      if (id === 'WTAGE'){
                       	 resetChartJSCanvas("cdc_growth_charts");
    		             render_ChartJS_weightForAgeTwoToTwentyYears(wtagePatient,"cdc_growth_charts", patientPropts, {x: chartAxisLabels.WTAGE_x, y: chartAxisLabels.WTAGE_y});
    		             indentifySelectedCdcLink("WTAGE");
    		
    		             event.preventDefault();
                            
                           }
                       if (id === 'STATAGE'){
                       	 resetChartJSCanvas("cdc_growth_charts");
    		             render_ChartJS_statureForAgeTwoToTwentyYears(statagePatient,"cdc_growth_charts", patientPropts, {x: chartAxisLabels.STATAGE_x, y: chartAxisLabels.STATAGE_y});
    		             indentifySelectedCdcLink("STATAGE");
    		
    		             event.preventDefault();
                            }
                            
                        if (id === 'BMIAGE'){
                      	resetChartJSCanvas("cdc_growth_charts");
    		            renderPatient_ChartJS_LineGraph_bmiAge(bmiAgeRevPatient,"cdc_growth_charts", patientPropts, {x: chartAxisLabels.BMIAGE_x, y: chartAxisLabels.BMIAGE_y});
    		            indentifySelectedCdcLink("BMIAGE");
    		
    		            event.preventDefault();
                            }
                            
                       });
 
    	
	});
</script> 