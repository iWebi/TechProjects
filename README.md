TechProjects
============

It will contain various projects created for personal use and technical skills build.

<b>ExpenseStatus:</b>
This project is created using <a href="http://www.gebish.org/">geb</a> library. 
<ul>
<li>Uses page object pattern to create tests to Login to various bank/credit card provider companies (USAA, Bank Of Ametica) and other expenses related stores (CostCo, Macys, Target).</li>
<li> This project is created similar to mint.com but has potential to generate various reports and warn on the expense status.</li>
<li> It is run as a Windows service to trigger 4 times a day to capture the metrics and shoot the status mail on the expenses</li>
<li> Technology involved is Geb (Selenium+groovy flavor) based tests. If you want to browse, start with <a href="https://github.com/iWebi/TechProjects/blob/master/ExpensesStatus/src/test/groovy/com/webi/ExpenseReportSpec.groovy">ExpenseReportSpec.groovy test</a></li>
</ul>
