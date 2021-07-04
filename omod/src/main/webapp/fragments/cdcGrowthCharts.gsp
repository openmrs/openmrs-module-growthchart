
<select id="cdcGrowthChartsDropDown">
<option value="" disabled selected hidden>Select chart</option>
	<option id="WTAGEINF">
		${ ui.message("growthchart.WTAGEINF") }
	</option>
	<option id="LENAGEINF">
		${ ui.message("growthchart.LENAGEINF") }
	</option>
	<option id="WTLENINF">
	${ ui.message("growthchart.WTLENINF") }
	</option>
	
	<option id="HCAGEINF">
		${ ui.message("growthchart.HCAGEINF") }
	</option>
	<%if (patientPropts.age.years >= 2) {%>
		<option id="WTSTAT">
        	${ ui.message("growthchart.WTSTAT") }
		</option>
		
		<option id="WTAGE">
			${ ui.message("growthchart.WTAGE") }
		</option>
		<option id="STATAGE">
		${ ui.message("growthchart.STATAGE") }
		</option>
		<option id="BMIAGE">
		${ ui.message("growthchart.BMIAGE") }
		</option>
	<%}%>
</select>
<br />
<div id="cdc_growth_charts-container">
	<canvas id="cdc_growth_charts"></canvas>
</div> 
