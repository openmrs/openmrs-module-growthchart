
<ul class="navList">
	<li>
		<a id="WTAGEINF" class="growthChartCDCLink" href="#">${ ui.message("growthchart.WTAGEINF") }</a>
	</li>
	<li>
		<a id="LENAGEINF" class="growthChartCDCLink" href="#">${ ui.message("growthchart.LENAGEINF") }</a>
	</li>
	<!-- not implemented yet
	<li>
		<a id="WTLENINF" class="growthChartCDCLink" href="#">${ ui.message("growthchart.WTLENINF") }</a>
	</li>
	-->
	<li>
		<a id="HCAGEINF" class="growthChartCDCLink" href="#">${ ui.message("growthchart.HCAGEINF") }</a>
	</li>
	<%if (patientPropts.age.years >= 2) {%>
		<!-- not implemented yet
		<li>
        	<a id="WTSTAT" class="growthChartCDCLink" href="#">${ ui.message("growthchart.WTSTAT") }</a>
		</li>
		-->
		<li>
			<a id="WTAGE" class="growthChartCDCLink" href="#">${ ui.message("growthchart.WTAGE") }</a>
		</li>
		<li>
			<a id="STATAGE" class="growthChartCDCLink" href="#">${ ui.message("growthchart.STATAGE") }</a>
		</li>
		<li>
			<a id="BMIAGE" class="growthChartCDCLink" href="#">${ ui.message("growthchart.BMIAGE") }</a>
		</li>
	<%}%>
</ul>
<br />
<div id="cdc_growth_charts-container">
	<canvas id="cdc_growth_charts"></canvas>
</div> 
