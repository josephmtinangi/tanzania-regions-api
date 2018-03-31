<@includes>

 <h1 class="page-header">${district.name}</h1>
 
 <table class="table table-bordered">
 	<thead>
 		<th>SN</th>
 		<th>Name</th>
 		<th>District</th>
 		<th>Date Added</th>
 	</thead>
 	<tbody>
 		<#list district.wards as ward>
 			<tr>
 				<td>${ward.id}.</td>
 				<td>${ward.name}</td>
 				<td>${ward.district.name}</td>
 				<td>${ward.createdAt}</td>
 			</tr>
 		</#list>
 	</tbody>
 </table>

</@includes>