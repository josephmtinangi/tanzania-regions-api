<@includes>

 <h1 class="page-header">Districts</h1>
 
 <table class="table table-bordered">
 	<thead>
 		<th>SN</th>
 		<th>Name</th>
 		<th>Region</th>
 		<th>Date Added</th>
 	</thead>
 	<tbody>
 		<#list districts as district>
 			<tr>
 				<td>${district.id}.</td>
 				<td>${district.name}</td>
 				<td>${district.region.name}</td>
 				<td>${district.createdAt}</td>
 			</tr>
 		</#list>
 	</tbody>
 </table>

</@includes>